package cn.rock.mybatis.interceptor.returnmap;

/**
 * Created by wjl48511 on 2017/8/26
 */
public class MapParamPlus {

    public  String  keyField;

    public  String valueField;

    public MapParamPlus(String keyField, String valueField) {
        this.keyField = keyField;
        this.valueField = valueField;
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }
}
