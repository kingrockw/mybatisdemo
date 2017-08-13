package cn.rock.mybatis.jdbc;

import java.sql.*;
import java.util.Date;

public class JdbcDemo {
	
	//定义sql语句，根据名称查询用户表的记录
	private static String sql = "SELECT * FROM USER WHERE username =  ?";
	
	public static void main(String[] args) throws SQLException {
		
		//数据库连接
		Connection connection = null;
		//预编译statement
		//好处：防止 sql注入，提高数据的性能
		PreparedStatement preparedStatement = null;
		//结果集
		ResultSet resultSet = null;
		
		try {
			
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//连接数据库
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatisdemo", "root", "rock");
			
			//构造preparedStatement
			preparedStatement = connection.prepareStatement(sql);
			Statement statement = connection.createStatement();

			//向preparedStatement中占位符的位置设置参数
			preparedStatement.setString(1, "张三");
			
			//发起数据库查询请求，得到结果集
			resultSet = preparedStatement.executeQuery();
			//遍历查询结果
			
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				Date birthday = resultSet.getDate("birthday");
				System.out.println(id+"   "+username+"	"+birthday);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//释放资源
			if(resultSet!=null){
				resultSet.close();
			}
			if(preparedStatement!=null){
				preparedStatement.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
		
		
	}

}
