package edgar.mybatis.mybatisplus;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import edgar.mybatis.mybatisplus.pojo.User3;

class User3Test extends TestBase {
	
	@Test
	void testInsert() {
		User3 newUser = new User3().mockIt();
		int result = user3Mapper.insert(newUser);
		Assertions.assertEquals(1, result, "User3Mapper.insert() fail");
	}
	
	@Test
	void testUpdateById() {
		User3 newUser = new User3().mockIt();
		int result = user3Mapper.insert(newUser);
		Assertions.assertEquals(1, result, "User3Mapper.insert() fail");
		
		newUser.setPassword("123");
		result = user3Mapper.updateById(newUser);
		Assertions.assertEquals(1, result, "User3Mapper.updateById() fail");
		
		User3 user = user3Mapper.selectById(newUser.getId());
		Assertions.assertEquals("123", user.getPassword(), "User3Mapper.updateById() fail");
	}
	
	@Test
	void testUpdateByWrapper1() {
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.gt("age", 40)
			.or()
			.like("email", "hotmail");
		
		User3 newUser = new User3();
		newUser.setPassword("123456");
		int result = user3Mapper.update(newUser, wrapper);
		Assertions.assertTrue(result >= 0, "User3Mapper.update() fail");
	}
	
	@Test
	void testUpdateByWrapper2() {
		/*
		 * 如果条件中是先 AND 再 OR，需要借助lambda表达式
		 * UPDATE tbl_user3 SET password=? WHERE (password = ? AND (age <= ? OR email LIKE ?))
		 */
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.eq("password", "123456")
			.and(w -> w.le("age", 40)
					.or()
					.likeLeft("email", "yahoo.com"));
		
		User3 newUser = new User3();
		newUser.setPassword("1234");
		int result = user3Mapper.update(newUser, wrapper);
		Assertions.assertTrue(result >= 0, "User3Mapper.update() fail");
	}
	
	@Test
	void testUpdateByUpdateWrapper() {
		/*
		 * UPDATE tbl_user3 SET password=? WHERE (age < ? AND email LIKE ?)
		 */
		UpdateWrapper<User3> wrapper = new UpdateWrapper<>();
		wrapper.lt("age", 40).like("email", "yahoo");
		wrapper.set("password", "12345");
		
		int result = user3Mapper.update(null, wrapper);
		Assertions.assertTrue(result >= 0, "User3Mapper.update() fail");
	}
	
	@Test
	void testUpdateByLambdaUpdateWrapper() {
		/*
		 * UPDATE tbl_user3 SET password=? WHERE (age < ? AND email LIKE ?)
		 */
		LambdaUpdateWrapper<User3> wrapper = new LambdaUpdateWrapper<>();
		wrapper.lt(User3::getAge, 40).like(User3::getEmail, "yahoo");
		wrapper.set(User3::getPassword, "12345");
		
		int result = user3Mapper.update(null, wrapper);
		Assertions.assertTrue(result >= 0, "User3Mapper.update() fail");
	}
	
	@Test
	void testDeleteById() {
		// 先插入一条记录
		User3 newUser = new User3().mockIt();
		int result = user3Mapper.insert(newUser);
		Assertions.assertEquals(1, result, "User3Mapper.insert() fail");
		
		// 删除新记录
		result = user3Mapper.deleteById(newUser.getId());
		Assertions.assertEquals(1, result, "User3Mapper.deleteById() fail");
	}
	
	@Disabled("Disabled until need to delele all users!")
	@Test
	void testDeleteBatchIds() {
		List<User3> users = user3Mapper.selectList(null);
		List<Long> ids = users.stream().map(User3::getId).limit(3).collect(Collectors.toList());
		int result = user3Mapper.deleteBatchIds(ids);
		Assertions.assertTrue(result > 0 && result <= 3, "User3Mapper.deleteBatchIds() fail");
	}
	
	@Test
	void testDeleteByWrapper() {
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.le("age", 15);
		
		int result = user3Mapper.delete(wrapper);
		Assertions.assertTrue(result >= 0, "User3Mapper.delete() fail");
	}

	@Test
	void testSelectList() {
		List<User3> users = user3Mapper.selectList(null);
		Assertions.assertNotNull(users, "User3Mapper.selectList() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectList() fail");
	}
	
	@Test
	void testSelectListWrapper() {
		/*
		 * SELECT id,username,password,age,sex,email FROM tbl_user3 WHERE (age BETWEEN ? AND ? AND email LIKE ?) ORDER BY age DESC
		 */
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.between("age", 20, 40).likeLeft("email", "gmail.com")
			.orderByDesc("age");
		
		List<User3> users = user3Mapper.selectList(wrapper);
		Assertions.assertNotNull(users, "User3Mapper.selectList() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectList() fail");
	}
	
	@Test
	void testSelectBatchIds() {
		List<User3> users = user3Mapper.selectList(null);
		Assertions.assertNotNull(users, "User3Mapper.selectList() fail");
		if (users.isEmpty()) {
			return;
		}
		
		List<Long> ids = users.stream().map(User3::getId).limit(2).collect(Collectors.toList());
		users = user3Mapper.selectBatchIds(ids);
		Assertions.assertNotNull(users, "User3Mapper.selectBatchIds() fail");
		Assertions.assertEquals(2, users.size(),  "User3Mapper.selectBatchIds() fail");
	}
	
	@Test
	void testSelectMaps() {
		/*
		 * 仅查询部分列，返回值使用Map接收
		 * SELECT age,email FROM tbl_user3 WHERE (age BETWEEN ? AND ? AND email LIKE ?) ORDER BY age DESC
		 */
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.select("age", "email");
		wrapper.between("age", 20, 40).likeLeft("email", "gmail.com")
			.orderByDesc("age");
		
		List<Map<String, Object>> users = user3Mapper.selectMaps(wrapper);
		Assertions.assertNotNull(users, "User3Mapper.selectMaps() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectMaps() fail");
		
		if (users.size() > 0) {
			Set<String> keys = users.get(0).keySet();
			Assertions.assertTrue(keys.contains("age"), "User3Mapper.selectMaps() fail");
			Assertions.assertTrue(keys.contains("email"), "User3Mapper.selectMaps() fail");
		}
	}
	
	@Test
	void testSelectListByCondition() {
		
		/*
		 * 根据condition判断，是否将条件拼入SQL
		 * SELECT id,username,password,age,sex,email FROM tbl_user3 WHERE (age >= ? AND age <= ?)
		 */
		String username = "";
		Integer ageStart = 20;
		Integer ageEnd = 30;
		
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.like(StringUtils.isNotBlank(username), "user_name", username);
		wrapper.ge(ageStart != null, "age", ageStart);
		wrapper.le(ageEnd != null, "age", ageEnd);

		List<User3> users = user3Mapper.selectList(wrapper);
		Assertions.assertNotNull(users, "User3Mapper.selectList() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectList() fail");
	}
	
	@Test
	void testSelectListByLambdaQueryWrapper() {
		
		String username = "";
		Integer ageStart = 20;
		Integer ageEnd = 30;
		
		LambdaQueryWrapper<User3> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(StringUtils.isNotBlank(username), User3::getUsername, username);
		wrapper.ge(ageStart != null, User3::getAge, ageStart);
		wrapper.le(ageEnd != null, User3::getAge, ageEnd);
		
		List<User3> users = user3Mapper.selectList(wrapper);
		Assertions.assertNotNull(users, "User3Mapper.selectList() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectList() fail");
	}
}
