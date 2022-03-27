package edgar.mybatis.sgg2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edgar.mybatis.guigu2022.pojo.Department;
import edgar.mybatis.guigu2022.pojo.Employee;

public class TestEmployee extends TestBase {

	private static Department testDepartment;
	private static Employee testNewEmployee;

	@BeforeClass
	public static void setUp() {
		TestBase.setUp();

		testDepartment = getTestDeplartment();
		testNewEmployee = createTestEmployee();
	}

	private static Department getTestDeplartment() {
		Department department;

		List<Department> departments = departmentDao.getAllDepartments();

		if (departments.size() > 0) {
			// 使用最后一个部门信息作为本轮测试的数据
			department = departments.get(departments.size() - 1);
		} else {
			// 新创建一个
			department = new Department(0, faker.commerce().department());
			departmentDao.insertDepartment(testDepartment);
		}

		return department;
	}

	private static Employee createTestEmployee() {
		Employee newEmployee = new Employee(0,
				faker.name().fullName(),
				faker.number().numberBetween(10, 50),
				1,
				faker.internet().emailAddress());
		newEmployee.setDepartment(testDepartment);
		employeeDao.insertEmployee(newEmployee);

		return newEmployee;
	}

	@AfterClass
	public static void tearDown() {
		deleteTestEmployee();

		TestBase.tearDown();
	}

	private static void deleteTestEmployee() {
		employeeDao.deletEmployeeById(testNewEmployee.getId());
	}

	@Test
	public void testInsertEmployee() {
		Employee employee = new Employee(0,
				faker.name().fullName(),
				faker.number().numberBetween(10, 50),
				1,
				faker.internet().emailAddress());
		employee.setDepartment(testDepartment);
		int result = employeeDao.insertEmployee(employee);
		assertEquals("EmployeeMapper.insertEmployee() fail", 1, result);
	}

	@Test
	public void testGetEmployeeById() {
		Employee employee = employeeDao.getEmployeeById(testNewEmployee.getId());
		assertNotNull("EmployeeMapper.getEmployeeById() fail", employee);
		assertEquals("EmployeeMapper.getEmployeeById() fail", testNewEmployee.getId(), employee.getId());
	}

	@Test
	public void testGetEmployeeDepartmentById() {
		Employee employee = employeeDao.getEmployeeDepartmentById(testNewEmployee.getId());
		assertNotNull("EmployeeMapper.getEmployeeById() fail", employee);
		assertNotNull("EmployeeMapper.getEmployeeById() fail", employee.getDepartment());
		assertEquals("EmployeeMapper.getEmployeeById() fail", testNewEmployee.getId(), employee.getId());
		assertEquals("EmployeeMapper.getEmployeeById() fail", testDepartment.getId(), employee.getDepartment().getId());
	}

	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		assertNotNull("EmployeeMapper.getAllEmployees() fail", employees);
	}
}
