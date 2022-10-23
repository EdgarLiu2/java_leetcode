package edgar.mybatis.mybatisplus;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import edgar.mybatis.mybatisplus.pojo.ModelBase;

public class MybatisPlusGenerator {
	
	private final static String PROJECT_BASE = "/Users/liuzhao/workspace/GitHub/java_leetcode/mybatis";
	private final static String JAVA_BASE = PROJECT_BASE + "/src/main/java";
	private final static String RESOURCE_BASE = PROJECT_BASE + "/src/main/resources";
	private final static String MAPPER_FOLDER = RESOURCE_BASE + "/edgar/mybatis/mybatisplus/generated/mapper";
	

	public static void main(String[] args) {
		/**
		 * https://baomidou.com/pages/981406/
		 */
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT","root","123456")
	    .globalConfig(builder -> {
	        builder.author("liuzhao") // 设置作者
//	            .enableSwagger() // 开启 swagger 模式
//	            .fileOverride() // 覆盖已生成文件
	            .outputDir(JAVA_BASE); // 指定输出目录
	    })
	    .packageConfig(builder -> {
	        builder.parent("edgar.mybatis.mybatisplus") // 设置父包名
	            .moduleName("generated") // 设置父包模块名
	            .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_FOLDER)); // 设置mapperXml生成路径
	    })
	    .strategyConfig(builder -> {
	        builder.addInclude("tbl_user3") // 设置需要生成的表名
	            .addTablePrefix("tbl_"); // 设置过滤表前缀
	    })
	    .strategyConfig(builder -> {
	    	builder
	    	.entityBuilder()
	        .superClass(ModelBase.class)
//	        .versionColumnName("version")
//	        .versionPropertyName("version")
	        ;
	    })
	    .strategyConfig(builder -> {
	        builder
	        .controllerBuilder()
	        .enableRestStyle(); // 开启生成@RestController 控制器
	    })
	    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
	    .execute();

	}

}
