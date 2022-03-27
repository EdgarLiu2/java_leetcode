package edgar.mybatis.sgg2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edgar.mybatis.guigu2022.generated.pojo.User2;
import edgar.mybatis.guigu2022.generated.pojo.User2Example;


public class TestMBGUser extends TestBase {
	
	@Test
	public void testUpdateUserById() {
		List<User2> users = userMBGDao.selectByExample(null);
		assertNotNull("UserMBGMapper.selectByExample() fail", users);
		
		// 修改第一条记录
		User2 user = users.get(0);
		user.setPassword(null);
		user.setSex((byte)2);
		int result = userMBGDao.updateByPrimaryKeySelective(user);
		assertEquals("UserMBGMapper.updateByPrimaryKeySelective() fail", 1, result);
	}
	
	@Test
	public void testGetUsersByEmailLike() {
		User2Example query = new User2Example();
		query.createCriteria().andEmailLike("%@yahoo.com");
		
		List<User2> users = userMBGDao.selectByExample(query);
		assertNotNull("UserMBGMapper.selectByExample() fail", users);
		assertTrue("UserMBGMapper.selectByExample() fail", users.size() > 0);
	}
	
	@Test
	public void testGetUsersByAgeBetween() {
		User2Example query = new User2Example();
		query.createCriteria().andAgeBetween((byte)20, (byte)30);
		
		List<User2> users = userMBGDao.selectByExample(query);
		assertNotNull("UserMBGMapper.selectByExample() fail", users);
		assertTrue("UserMBGMapper.selectByExample() fail", users.size() > 0);
	}

	@Test
	public void testGetAlllUsers() {
		List<User2> users = userMBGDao.selectByExample(null);
		assertNotNull("UserMBGMapper.selectByExample() fail", users);
		assertTrue("UserMBGMapper.selectByExample() fail", users.size() > 0);
	}
}
