package edgar.mybatis.mybatisplus;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edgar.mybatis.mybatisplus.pojo.User3;


class User3ServiceTest extends TestBase {
	
	@Test
	void testSaveBatch() {
		List<User3> users = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			User3 user = new User3().mockIt();
			users.add(user);
		}
		boolean result = user3Service.saveBatch(users);
		Assertions.assertTrue(result, "User3Service.saveBatch() fail");
	}
	
	@Test
	void testCount() {
		long count = user3Service.count();
		Assertions.assertTrue(count > 0, "User3Service.count() fail");
	}
}
