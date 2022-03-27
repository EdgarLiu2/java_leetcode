package edgar.mybatis.sgg2022;

import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import edgar.mybatis.guigu2022.generated.mapper.User2Mapper;
import edgar.mybatis.guigu2022.mapper.DepartmentMapper;
import edgar.mybatis.guigu2022.mapper.EmployeeMapper;
import edgar.mybatis.guigu2022.mapper.UserMapper;
import edgar.util.MybatisUtil;
import net.datafaker.Faker;

public class TestBase {

	protected static SqlSession session;
	protected static Faker faker;

	protected static UserMapper userDao;
	protected static EmployeeMapper employeeDao;
	protected static DepartmentMapper departmentDao;
	protected static User2Mapper userMBGDao;

	@BeforeClass
	public static void setUp() {
		// 获取Mybatis session
		session = MybatisUtil.getAutoCommitSession();
		faker = new Faker(new Locale("zh-CN"));

		// 获取Mapper对象
		userDao = session.getMapper(UserMapper.class);
		employeeDao = session.getMapper(EmployeeMapper.class);
		departmentDao = session.getMapper(DepartmentMapper.class);
		userMBGDao = session.getMapper(User2Mapper.class);
	}

	@AfterClass
	public static void tearDown() {
		session.close();
	}

}
