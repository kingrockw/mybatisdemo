package cn.rock.mybatis.dao.impl;

import cn.itcast.mybatis.po.User;
import cn.rock.mybatis.Mapper.UserOrdersDao;
import cn.rock.mybatis.interceptor.returnmap.MapParam;
import cn.rock.mybatis.interceptor.returnmap.MapParamPlus;
import cn.rock.mybatis.po.UserOrder;
import cn.rock.mybatis.po.UserOrderSimple;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserOrderDaoTest {
    // 会话工厂
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        // 加载配置文件
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 根据mytais的配置创建SqlSessionFactory

        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

    }

    @Test
    public void testlistUserOrdersByUserId() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession创建代理对象(接口的实现类对象)
        UserOrdersDao userOrdersDao = sqlSession.getMapper(UserOrdersDao.class);
        UserOrder userOrders = userOrdersDao.getUserOrdersByUserId(1);
        System.out.println(userOrders.getOrdersList().size());
        System.out.println(userOrders);
    }

    @Test
    public void testlistUserOrderSimple() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession创建代理对象(接口的实现类对象)
        UserOrdersDao userOrdersDao = sqlSession.getMapper(UserOrdersDao.class);
        List<UserOrderSimple> userOrderSimples = userOrdersDao.listUserOrderSimple();
        System.out.println(userOrderSimples);
    }

    @Test
    public void testmapUserIdName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession创建代理对象(接口的实现类对象)
        UserOrdersDao userOrdersDao = sqlSession.getMapper(UserOrdersDao.class);

        MapParam mapParam = new MapParam("id","username");
        Map<String, String> objectObjectMap = userOrdersDao.mapUserIdName(mapParam);

        System.out.println(objectObjectMap);
    }

    @Test
    public void testmapUserIdNameplus() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession创建代理对象(接口的实现类对象)
        UserOrdersDao userOrdersDao = sqlSession.getMapper(UserOrdersDao.class);

        MapParamPlus mapParam = new MapParamPlus("id","username");
        List<Integer> ids = new ArrayList<>();
        ids.add(16);
        Map<String, String> objectObjectMap = userOrdersDao.mapUserIdNamePlus(mapParam,ids);

        System.out.println(objectObjectMap);
    }

}
