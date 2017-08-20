package cn.rock.mybatis.po;

import java.util.List;

public class UserOrderSimple {

    private String id;
    private String userName;

    private List<String> orderTitleList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getOrderTitleList() {
        return orderTitleList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderTitleList(List<String> orderTitleList) {
        this.orderTitleList = orderTitleList;
    }
}
