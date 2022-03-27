package edgar.mybatis.guigu2022.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edgar.mybatis.guigu2022.pojo.Department;

public interface DepartmentMapper {

	/**
	 * 添加部门信息
	 * 
	 * @param department
	 * @return
	 */
	int insertDepartment(Department department);

	/**
	 * 根据id查询部门
	 * 
	 * @param id
	 * @return
	 */
	Department getDepartmentById(@Param("id") Integer id);
	
	/**
	 * 根据id查询部门和所有员工信息
	 * 
	 * @param id
	 * @return
	 */
	Department getDepartmentEmployeeById(@Param("id") Integer id);

	/**
	 * 查询所有Department
	 * 
	 * @return
	 */
	List<Department> getAllDepartments();
}
