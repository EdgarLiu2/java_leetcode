package edgar.mybatis.mybatisplus.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
//扫描Mapper接口所在的包
@MapperScan("edgar.mybatis.mybatisplus.mapper")
/**
 * MyBatisPlus相关配置
 * 
 * @author liuzhao
 *
 */
public class MyBatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 添加分页插件
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		// 添加乐观锁插件
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		
		return interceptor;
	}
}
