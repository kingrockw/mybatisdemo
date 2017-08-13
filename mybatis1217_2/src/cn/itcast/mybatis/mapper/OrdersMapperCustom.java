package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;

/**
 * 
 * <p>
 * Title: OrdersMapperCustom
 * </p>
 * <p>
 * Description: 订单管理
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2014-12-18上午9:26:04
 * @version 1.0
 */
public interface OrdersMapperCustom {

	// 查询订单及用户信息
	public List<OrdersCustom> findOrdersUserList() throws Exception;

	// 查询订单及用户信息使用resultMap
	public List<Orders> findOrdersUserListResultMap() throws Exception;

	// 查询订单及订单明细信息
	public List<Orders> findOrdersUserDetailList() throws Exception;
	
	//查询订单及明细和商品信息
	public List<Orders> findOrdersUserDetailItemList()throws Exception;
	
	//查询订单信息，实现延迟加载用户信息
	public List<Orders>  findOrdersList()throws Exception;
}
