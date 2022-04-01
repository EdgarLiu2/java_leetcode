package edgar.mybatis.sgg2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edgar.mybatis.guigu2022.generated.pojo.User2;

public class TestPageHelper extends TestBase {
	
	/*
	 * index：当前页的起始索引
	 * pageNum：当前页妈
	 * pageSize：页面大小
	 * index = (pageNum - 1) * pageSize
	 */

	@Test
	public void testDemo1() {
		// 第二页，每页4条数据
		PageHelper.startPage(2, 4);
		List<User2> users = userMBGDao.selectByExample(null);
		assertNotNull("UserMBGMapper.selectByExample() fail", users);
		assertEquals("UserMBGMapper.selectByExample() fail", 4, users.size());
		
		PageInfo<User2> pageInfo = new PageInfo<>(users, 5);
		System.out.println(pageInfo);
	}
}
