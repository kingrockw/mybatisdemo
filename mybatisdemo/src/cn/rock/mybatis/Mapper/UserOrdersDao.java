package cn.rock.mybatis.Mapper;

import cn.rock.mybatis.po.UserOrder;
import cn.rock.mybatis.po.UserOrderSimple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrdersDao {
   //@Param("id")
   UserOrder getUserOrdersByUserId(int id);

   List<UserOrderSimple> listUserOrderSimple();
}
