package cn.rock.mybatis.typehandler;

import cn.rock.mybatis.po.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressTypeHandler extends BaseTypeHandler<Address> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Address address, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, address.toString());
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, String s) throws SQLException {
      return new Address(resultSet.getString(s));
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return  new Address(resultSet.getString(i));
    }

    @Override
    public Address getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new Address(callableStatement.getString(i));
    }
}
