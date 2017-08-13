package cn.rock.mybatis.dao.impl;

import java.io.InputStream;

import cn.rock.mybatis.po.RichUser;
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
//		RichUser richUser = userDao.getRichUserById(1);
//
//		System.out.println(richUser);
	}

}
