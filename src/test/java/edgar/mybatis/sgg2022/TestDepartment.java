package edgar.mybatis.sgg2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edgar.mybatis.guigu2022.pojo.Department;

public class TestDepartment extends TestBase {

	@Test
	public void testInsertDepartment() {
		Department department = new Department(0, faker.commerce().department());
		int result = departmentDao.insertDepartment(department);
		assertEquals("DepartmentMapper.insertDepartment() fail", 1, result);
	}

	@Test
	public void testGetDepartmentEmployeeById() {
		Department department = departmentDao.getDepartmentEmployeeById(3);
		assertNotNull("DepartmentMapper.getDepartmentEmployeeById() fail", department);
		assertTrue("DepartmentMapper.getDepartmentEmployeeById() fail", department.getEmployees().size() > 0);
	}
	
	@Test
	public void testGetAllDepartments() {
		List<Department> departments = departmentDao.getAllDepartments();
		assertNotNull("DepartmentMapper.getAllDepartments() fail", departments);
		assertTrue("DepartmentMapper.getAllDepartments() fail", departments.size() > 0);
	}
}
