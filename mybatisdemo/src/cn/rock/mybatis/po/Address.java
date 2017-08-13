package cn.rock.mybatis.po;

public class Address {
    private String province;

    private String city;
    public Address(String address) {
        if (address != null) {
            String[] segments = address.split(",");
            if (segments.length > 1) {
                this.province = segments[0];
                this.city = segments[1];
            }
            else if (segments.length > 0) {
                this.city = segments[0];
            }
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.province + "," + this.city;
    }
}
