package edgar.mybatis.sgg2022;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edgar.mybatis.guigu2022.mapper.UserMapper;
import edgar.mybatis.guigu2022.pojo.User;
import edgar.mybatis.guigu2022.query.UserQuery;
import edgar.util.MybatisUtil;

public class TestUser extends TestBase {
	
	private static User testNewUser;
	
	
	@BeforeClass
	public static void setUp() {
		TestBase.setUp();
		
		createTestUser();
	}
	
	private static void createTestUser() {
		testNewUser = new User(0,
				faker.name().fullName(),
				faker.internet().password(8, 16, true, true, true),
				faker.number().numberBetween(10, 50),
				1,
				faker.internet().emailAddress());
		userDao.insertUser(testNewUser);
	}
	
	private static void deleteTestUser() {
		userDao.deleteUserByUsername(testNewUser.getUsername());
	}
	
	@AfterClass
	public static void tearDown() {
		deleteTestUser();
		
		TestBase.tearDown();
	}
	

	@Test
	public void testInsertUser() {
		User user = new User(0,
				faker.name().fullName(),
				faker.internet().password(8, 16, true, true, true),
				faker.number().numberBetween(10, 50),
				1,
				faker.internet().emailAddress());
		int result = userDao.insertUser(user);
		assertEquals("UserMapper.insertUser() fail", 1, result);
	}
	
	@Test
	public void testBatchInsertUsers() {
		List<User> users = List.of(
				new User(0, faker.name().fullName(), faker.internet().password(8, 16, true, true, true), faker.number().numberBetween(10, 50), 2, faker.internet().emailAddress()),
				new User(0, faker.name().fullName(), faker.internet().password(8, 16, true, true, true), faker.number().numberBetween(10, 50), 2, faker.internet().emailAddress())
				);
		
		int result = userDao.batchInsertUsers(users);
		assertEquals("UserMapper.insertUsersByList() fail", 2, result);
	}
	
	@Test
	public void testUpdateUser() {
		// 性别修改成女
		testNewUser.setSex(2);
		
		int result = userDao.updateUser(testNewUser);
		assertEquals("UserMapper.updateUser() fail", 1, result);
		User updatedUser = userDao.getUserById(testNewUser.getId());
		assertEquals("UserMapper.updateUser() fail", 2, updatedUser.getSex().intValue());
	}
	
	@Test
	public void testDeleteUserByIds() {
		int result = userDao.deleteUserByIds("1,2,3");
		assertTrue("UserMapper.testDeleteUserByIds() fail", result >= 0);
	}
	
	@Test
	public void deleteUserByIdArray() {
		int result = userDao.deleteUserByIdArray(new Integer[]{4, 5, 6});
		assertTrue("UserMapper.deleteUserByIdArray() fail", result >= 0);
	}
	
	@Test
	public void testGetUserById() {
		User user = userDao.getUserById(testNewUser.getId());
		assertNotNull("UserMapper.getUserById() fail", user);
		assertEquals("UserMapper.getUserById() fail", testNewUser.getId(), user.getId());
	}
	
	@Test
	public void testGetUserByUsername() {
		List<User> users = userDao.getUsersByUsername(testNewUser.getUsername());
		assertNotNull("UserMapper.getAllUsers() fail", users);
		assertEquals("UserMapper.getAllUsers() fail", 1, users.size());
	}
	
	@Test
	public void testGetUsersByQuery() {
		// 有查询条件
		UserQuery query = new UserQuery();
		query.setAgeMin(20);
		query.setAgeMax(30);
		query.setNameStartwith("廖");
		List<User> users = userDao.getUsersByQuery(query);
		assertNotNull("UserMapper.getUsersByQuery() fail", users);
		assertTrue("UserMapper.getUsersByQuery() fail", users.size() > 0);
		
		// 无查询条件
		query = new UserQuery();
		users = userDao.getUsersByQuery(query);
		assertNotNull("UserMapper.getUsersByQuery() fail", users);
		assertTrue("UserMapper.getUsersByQuery() fail", users.size() > 0);
	}
	
	@Test
	public void testGetAlllUsers() {
		List<User> users = userDao.getAllUsers();
		assertNotNull("UserMapper.getAllUsers() fail", users);
		assertTrue("UserMapper.getAllUsers() fail", users.size() > 0);
	}
	
	@Test
	public void testGetAllUsersToMap() {
		Map<Integer, User> users = userDao.getAllUsersToMap();
		assertNotNull("UserMapper.getAllUsersToMap() fail", users);
		assertTrue("UserMapper.getAllUsersToMap() fail", users.size() > 0);
	}
	
	@Test
	public void testGetUsersByEmailLike() {
		List<User> users = userDao.getUsersByEmailLike("yahoo");
		assertNotNull("UserMapper.testGetUsersByEmailLike() fail", users);
		assertTrue("UserMapper.testGetUsersByEmailLike() fail", users.size() > 0);
	}
	
	@Test
	public void testGetUserCount() {
		Integer count = userDao.getUserCount();
		assertTrue("UserMapper.getUserCount() fail", count > 1);
	}

	@Test
	public void testCheckLogin() {
		User user = userDao.checkLogin(testNewUser.getUsername(), testNewUser.getPassword());
		assertNotNull("UserMapper.checkLogin() fail", user);
		assertEquals("UserMapper.checkLogin() fail", testNewUser.getUsername(), user.getUsername());
		assertEquals("UserMapper.checkLogin() fail", testNewUser.getPassword(), user.getPassword());
	}
	
	@Test
	public void testL2Cache() {
		
		SqlSession newSession;
		UserMapper newUserDao;
		User user;
		
		// 第一次查询
		newSession = MybatisUtil.getAutoCommitSession();
		newUserDao = session.getMapper(UserMapper.class);
		user = newUserDao.getUserById(testNewUser.getId());
		System.out.println(user);
		// 提交或关闭才生效
//		newSession.commit();
		newSession.close();
		
		// 第二次查询
		user = userDao.getUserById(testNewUser.getId());
		System.out.println(user);
		
		// 第三次查询
//		newSession = MybatisUtil.getAutoCommitSession();
		newUserDao = session.getMapper(UserMapper.class);
		user = newUserDao.getUserById(testNewUser.getId());
		System.out.println(user);
		
	}
}
