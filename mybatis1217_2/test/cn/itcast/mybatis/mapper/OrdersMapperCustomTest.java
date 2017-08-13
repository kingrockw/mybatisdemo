package cn.itcast.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

public class OrdersMapperCustomTest {

	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 加载配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 根据mytais的配置创建SqlSessionFactory

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	@Test
	public void testFindOrdersUserList() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<OrdersCustom> list = ordersMapperCustom.findOrdersUserList();
		System.out.println(list.size());
	}

	// 测试 一对一
	@Test
	public void testFindOrdersUserListResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersUserListResultMap();
		System.out.println(list.size());
	}

	// 测试 一对多
	@Test
	public void testfindOrdersUserDetailList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersUserDetailList();
		System.out.println(list.size());
	}

	// 测试 多对多
	@Test
	public void testfindOrdersUserDetailItemList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersUserDetailItemList();
		System.out.println(list.size());
	}

	// 测试延迟加载
	@Test
	public void testfindOrdersList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersList();
		for (Orders orders : list) {
			int order_id = orders.getId();// 取订单id
			User user = orders.getUser();// 查询用户信息
		}

	}

	// 测试一级缓存
	@Test
	public void testCache1() throws Exception {
		// 在同一个sqlsession中查询用户
		SqlSession sqlSession = sqlSessionFactory.openSession();

		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 执行一次查询
		User user = userMapper.findUserById(10);
		System.out.println(user);

		// 执行用户更新，此时mybatis清除一级缓存
		// 构造更新对象
		/*
		 * User user_update = new User();
		 * 
		 * user_update.setId(10); user_update.setUsername("张小三");
		 * userMapper.updateUser(user_update); //提交事务 sqlSession.commit();
		 */

		// 执行第二次查询
		User user1 = userMapper.findUserById(10);
		System.out.println(user1);

		sqlSession.close();

	}

	// 测试 二级缓存
	@Test
	public void testCache2() throws Exception {
		// 在同一个sqlsession中查询用户
		SqlSession sqlSession1 = sqlSessionFactory.openSession();

		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

		// 在同一个sqlsession中查询用户
		SqlSession sqlSession2 = sqlSessionFactory.openSession();

		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

		// 创建一个SqlSession用于更新用户
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);

		// 从sqlSession1中查询用户
		User user = userMapper1.findUserById(10);
		System.out.println(user);
		sqlSession1.close();

		// 更新用户操作
		/*User user_update = new User();
		user_update.setId(10);
		user_update.setUsername("张小三");
		userMapper3.updateUser(user_update);
		// 提交事务，清除二级缓存
		sqlSession3.commit();
		sqlSession3.close();*/

		// 从sqlSession2中查询用户
		User user2 = userMapper2.findUserById(10);
		System.out.println(user2);
		sqlSession2.close();

	}

}
