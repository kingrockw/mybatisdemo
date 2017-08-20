package cn.rock.mybatis.dao.impl;

import cn.rock.mybatis.dao.UserDao;
import cn.rock.mybatis.po.RichUser;
import cn.rock.mybatis.po.User;
import cn.rock.mybatis.po.UserContiansObj;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 
 * <p>
 * Title: UserDaoImpl
 * </p>
 * <p>
 * Description: 用户管理dao实现类
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2014-12-17下午2:49:04
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {

	// 注入SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {

		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 通过sqlSession查询用户信息(发起数据库操作)
		// 第一个参数statement：指定mapper映射文件中statement的id，指定 时需要前边加上statement所属的命名空间
		// 第二个参数parameter，指定 输入参数
		// selectOne返回的是单条记录，如果select返回多条记录(list集合)，使用selectOne会报错
		// 根据映射文件中的resultType指定输出类型
		User user = sqlSession.selectOne("test.findUserById", id);

		// 遍历查询结果
		// System.out.println(user);

		return user;
	}

	@Override
	public void insertUser(User user) throws Exception {
		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.insert("test.insertUser", user);

		sqlSession.commit();

		sqlSession.close();

	}

	@Override
	public List<User> findUserList() throws Exception {
		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();


		List<User> list = sqlSession.selectList("test.findUserList", "张");

		System.out.println(list.size());
		return list;
	}

	@Override
	public RichUser getRichUserById(int id) throws Exception {
        // 根据SqlSessionFactory创建SqlSession

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过sqlSession查询用户信息(发起数据库操作)
        // 第一个参数statement：指定mapper映射文件中statement的id，指定 时需要前边加上statement所属的命名空间
        // 第二个参数parameter，指定 输入参数
        // selectOne返回的是单条记录，如果select返回多条记录(list集合)，使用selectOne会报错
        // 根据映射文件中的resultType指定输出类型
        RichUser richUser = sqlSession.selectOne("test.getRichUserById", id);

        // 遍历查询结果
       System.out.println(richUser);

        return richUser;

	}

	@Override
	public UserContiansObj getUserContiansObjById(int id) throws Exception {
		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();


		UserContiansObj userContiansObj = sqlSession.selectOne("test.getUserContiansObjById", id);

		// 遍历查询结果
		System.out.println(userContiansObj);

		return userContiansObj;

	}
	@Override
	public UserContiansObj getUserContiansObjById1(int id) throws Exception {
		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();

		UserContiansObj userContiansObj = sqlSession.selectOne("test.getUserContiansObjById1", id);

		// 遍历查询结果
		System.out.println(userContiansObj);

		return userContiansObj;

	}

	@Override
	public List<User> findUserWithInjection(String id) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> user = sqlSession.selectList("test.getUserWithInjection", id);

		// 遍历查询结果
		System.out.println(user);

		return user;
	}


}
