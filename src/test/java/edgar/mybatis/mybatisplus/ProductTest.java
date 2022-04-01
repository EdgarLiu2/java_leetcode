package edgar.mybatis.mybatisplus;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import edgar.mybatis.mybatisplus.pojo.Product;

class ProductTest extends TestBase {

	@Test
	void testInsert() {
		Product newProduct = new Product().mockIt();
		int result = productMapper.insert(newProduct);
		Assertions.assertEquals(1, result, "ProductMapper.insert() fail");
	}
	
	@Test
	void testRaceCondition() {
		List<Product> products = productMapper.selectList(null);
		if (products.size() == 0) {
			return;
		}
		
		// User1
		Product productUser1 = products.get(0);
		int user1ExpectedPrice = productUser1.getPrice() + 50;
		
		// User2
		Product productUser2 = productMapper.selectById(productUser1.getId());
		int user2ExpectedPrice = productUser1.getPrice() - 30;
		
		// User1 operation
		productUser1.setPrice(user1ExpectedPrice);
		productMapper.updateById(productUser1);
		
		// User2 operation
		productUser2.setPrice(user2ExpectedPrice);
		productMapper.updateById(productUser2);
		
		// User3
		Product productUser3 = productMapper.selectById(productUser1.getId());
		Assertions.assertEquals(user1ExpectedPrice, productUser3.getPrice(), "Data race in ProductMapper.updateById()");
	}
}
