package cn.rock.mybatis.Mapper;

import cn.rock.mybatis.interceptor.returnmap.MapParam;
import cn.rock.mybatis.interceptor.returnmap.MapParamPlus;
import cn.rock.mybatis.po.UserOrder;
import cn.rock.mybatis.po.UserOrderSimple;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserOrdersDao {
   //@Param("id")
   UserOrder getUserOrdersByUserId(int id);

   List<UserOrderSimple> listUserOrderSimple();

   Map<String,String> mapUserIdName(MapParam map);

   Map<String,String> mapUserIdNamePlus(MapParamPlus map,@Param("ids") List<Integer> ids);
}
