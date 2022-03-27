package edgar.mybatis.guigu2022.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edgar.mybatis.guigu2022.pojo.Employee;

public interface EmployeeMapper {

	/**
	 * 添加员工信息
	 * 
	 * @param employee
	 * @return
	 */
	int insertEmployee(Employee employee);

	/**
	 * 删除员工
	 * 
	 * @param id
	 * @return
	 */
	int deletEmployeeById(@Param("id") Integer id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Employee getEmployeeById(@Param("id") Integer id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Employee getEmployeeDepartmentById(@Param("id") Integer id);

	/**
	 * 查询所有Employee
	 * 
	 * @return
	 */
	List<Employee> getAllEmployees();
	
	/**
	 * 根据deptId查询所有Employee
	 * 
	 * @param deptId
	 * @return
	 */
	List<Employee> getEmployeesByDepartmentId(@Param("deptId") Integer deptId);
}
