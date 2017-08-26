package cn.rock.mybatis.dao.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import cn.rock.mybatis.po.RichUser;
import cn.rock.mybatis.po.UserContiansObj;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.rock.mybatis.dao.UserDao;
import cn.rock.mybatis.po.User;

public class UserDaoImplTest {

	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 加载配置文件
		String resource = "mybatisConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 根据mytais的配置创建SqlSessionFactory

		sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

	}

	@Test
	public void testFindUserById() throws Exception {

		//构建dao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User userById = userDao.findUserById(1);
		System.out.println(userById);
		//调用 dao方法
		//利用自定义类型转化器的查询
//		RichUser richUser = userDao.getRichUserById(1);
//
//		System.out.println(richUser);
	}
	@Test
	public void getUserContiansObjById() throws Exception {

		//构建dao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		UserContiansObj userById = userDao.getUserContiansObjById(1);
		System.out.println(userById);
	}
	@Test
	public void getUserContiansObjById1() throws Exception {
		//构建dao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		UserContiansObj userById = userDao.getUserContiansObjById1(1);
		System.out.println(userById);
	}
	@Test
	public void insterUser() throws Exception {
		//构建dao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = new User();
		user.setUsername("小wang");
		user.setScore(100f);
		user.setAddress("苏州");
		user.setBirthday(new Date());
		userDao.insertUser(user);
		System.out.println(user.getId());
		System.out.println(user);
	}

	/**
	 * SQL 注入演示
	 * @throws Exception
	 */
	@Test
	public void testFindUserWithInjection() throws Exception {

		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		//List<User> userWithInjection = userDao.findUserWithInjection("1");
		List<User> userWithInjection = userDao.findUserWithInjection("'a' or 1=1");
		System.out.println(userWithInjection);

	}

}
