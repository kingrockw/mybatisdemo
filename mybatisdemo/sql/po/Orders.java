package cn.itcast.mybatis.po;

import java.util.List;

/**
 * 订单信息
 * @author Thinkpad
 *
 */
public class Orders {
	private int id;//订单id
	private int user_id;//用户id
	private String order_number;//订单号
	
	private User user;//用户信息
	
	private List<Orderdetail> orderdetails;//订单明细信息
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	
	
	
}
