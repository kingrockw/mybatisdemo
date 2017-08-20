package cn.rock.mybatis.po;

/**
 * 订单信息
 * @author Thinkpad
 */
public class Orders {
	private int id;//订单id
	private int userId; //用户id
	private String orderContent;//订单内容
	private String orderTitle;//订单名称
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
}
