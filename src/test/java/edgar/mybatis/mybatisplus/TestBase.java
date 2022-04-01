package edgar.mybatis.mybatisplus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edgar.mybatis.mybatisplus.mapper.ProductMapper;
import edgar.mybatis.mybatisplus.mapper.User3Mapper;
import edgar.mybatis.mybatisplus.service.impl.User3Service;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE, classes=MybatisPlusApplication.class)
public class TestBase {

	@Autowired
	protected User3Mapper user3Mapper;
	
	@Autowired
	protected ProductMapper productMapper;
	
	@Autowired
	protected User3Service user3Service;
}
