package edgar.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MybatisUtil.class);
	
	public static final String DEFAULT_MYBATIS_CONFIG = "mybatis/mybatis-config.xml";
	private static SqlSessionFactory factory;
	
	private MybatisUtil() {
		
	}

	public static SqlSessionFactory getFactory(String config) throws IOException {
		if (factory != null) {
			return factory;
		}
		
		// 加载配置文件
		InputStream is = Resources.getResourceAsStream(config);
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(is);
		
		return factory;
	}
	
	public static SqlSession getAutoCommitSession() {
		SqlSessionFactory factory;
		SqlSession session = null;
		
		try {
			factory = MybatisUtil.getFactory(DEFAULT_MYBATIS_CONFIG);
			
			if (factory != null) {
				session = factory.openSession(true);
			}
		} catch (IOException e) {
			logger.error("Can't initialize Mybatis session", e);
		}
		
		
		return session;
	}
}
