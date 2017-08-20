package cn.rock.mybatis.po;

import java.util.Date;

public class BaseInfo {
    private String sex;// 性别
    private Date birthday;// 出生日期

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
