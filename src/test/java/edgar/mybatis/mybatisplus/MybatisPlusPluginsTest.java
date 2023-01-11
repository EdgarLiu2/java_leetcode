package edgar.mybatis.mybatisplus;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import edgar.mybatis.mybatisplus.pojo.User3;

class MybatisPlusPluginsTest extends TestBase {

	@Test
	void testPaginationPlugin() {
		/*
		 * 第二页，每页3条数据
		 * SELECT COUNT(*) AS total FROM tbl_user3
		 * SELECT id,username,password,age,sex,email FROM tbl_user3 LIMIT ?,?
		 */
		int currentPage = 2;
		int pageSize = 3;
		Page<User3> page = new Page<>(currentPage, pageSize);
		user3Mapper.selectPage(page, null);
		
		List<User3> users = page.getRecords();
		Assertions.assertNotNull(users, "User3Mapper.selectPage() fail");
		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectPage() fail");
		// 总页数
		Assertions.assertTrue(page.getPages() > 1, "User3Mapper.selectPage() fail");
		// 总记录数
		Assertions.assertTrue(page.getTotal() > 1, "User3Mapper.selectPage() fail");
	}
	
	@Test
	void testPaginationPluginByAge() {
		int currentPage = 2;
		int pageSize = 3;
		int expectedAge = 30;
		Page<User3> page = new Page<>(currentPage, pageSize);
		QueryWrapper<User3> wrapper = new QueryWrapper<>();
		wrapper.between("age", expectedAge - 5, expectedAge + 5);
		user3Mapper.selectPage(page, wrapper);
		
		List<User3> users = page.getRecords();
//		Assertions.assertNotNull(users, "User3Mapper.selectPage() fail");
//		Assertions.assertTrue(users.size() > 0, "User3Mapper.selectPage() fail");
	}
}
