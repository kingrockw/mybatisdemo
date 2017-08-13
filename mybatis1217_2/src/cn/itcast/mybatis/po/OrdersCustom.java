package cn.itcast.mybatis.po;

/**
 * 
 * <p>Title: OrdersCustom</p>
 * <p>Description:订单信息扩展类 </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2014-12-18上午9:28:48
 * @version 1.0
 */
public class OrdersCustom extends  Orders {
	
	//用户名称 
	private String username;
	//用户地址
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
