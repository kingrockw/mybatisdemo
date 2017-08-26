package cn.rock.mybatis.interceptor.returnmap;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * Created by wjl48511 on 2017/8/26
 */

@Intercepts(@Signature(method="handleResultSets", type=ResultSetHandler.class, args={Statement.class}))
public class MapInterceptorPlus implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();


    public Object intercept(Invocation invocation) throws Throwable {
        //通过invocation获取代理的目标对象
        Object target = invocation.getTarget();

        //ResultSetHandler proxyResultSetHandler = (ResultSetHandler)target;

        MetaObject meteHandler = MetaObject.forObject(target, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (meteHandler.hasGetter("h")) {
            Object object = meteHandler.getValue("h");
            meteHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    DEFAULT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (meteHandler.hasGetter("target")) {
            Object object = meteHandler.getValue("target");
            meteHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                    DEFAULT_REFLECTOR_FACTORY);
        }

        Object parameterObject = meteHandler.getValue("parameterHandler.parameterObject");

        if (parameterObject instanceof  Map){
            Map parameterObj = (Map) parameterObject;

            Object p1 = parameterObj.get("param1");

            if (p1 instanceof MapParamPlus) {//拦截到了
                MapParamPlus mapParam = (MapParamPlus) p1;
                //获取到当前的Statement
                Statement stmt = (Statement) invocation.getArgs()[0];
                //通过Statement获取到当前的结果集，对其进行处理，并返回对应的处理结果
                return handleResultSet(stmt.getResultSet(), mapParam);
            }
        }


        //如果没有进行拦截处理，则执行默认逻辑
        return invocation.proceed();
    }

    /**
     * 处理结果集
     * @param resultSet
     * @param mapParam
     * @return
     */
    private Object handleResultSet(ResultSet resultSet, MapParamPlus mapParam) {
        if (resultSet != null) {
            //拿到Key对应的字段
            String keyField =  mapParam.getKeyField();
            //拿到Value对应的字段
            String valueField =  mapParam.getValueField();
            //定义用于存放Key-Value的Map
            Map<Object, Object> map = new HashMap<Object, Object>();
            //handleResultSets的结果一定是一个List，当我们的对应的Mapper接口定义的是返回一个单一的元素，并且handleResultSets返回的列表
            //的size为1时，Mybatis会取返回的第一个元素作为对应Mapper接口方法的返回值。
            List<Object> resultList = new ArrayList<Object>();
            try {
                //把每一行对应的Key和Value存放到Map中
                while (resultSet.next()) {
                    Object key = resultSet.getObject(keyField);
                    Object value = resultSet.getObject(valueField);
                    map.put(key, value);
                }
            } catch (SQLException e) {

                e.printStackTrace();
            } finally {
                closeResultSet(resultSet);
            }
            //把封装好的Map存放到List中并进行返回
            resultList.add(map);
            return resultList;
        }
        return null;
    }

    /**
     * 关闭ResultSet
     * @param resultSet 需要关闭的ResultSet
     */
    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {

        }
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    public Object plugin(Object obj) {
        return Plugin.wrap(obj, this);
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    public void setProperties(Properties props) {

    }

}