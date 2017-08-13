package cn.itcast.mybatis.po;


/**
 * 订单自定义po，包括订单信息包括用户信息
 * @author Thinkpad
 *
 */
public class OrdersCustom extends User {
	
	private String user_id;//用户id
	private String order_number;//订单号
	


	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	
	
	
}
