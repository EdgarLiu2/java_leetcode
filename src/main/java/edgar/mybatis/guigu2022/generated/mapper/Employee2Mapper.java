package edgar.mybatis.guigu2022.generated.mapper;

import edgar.mybatis.guigu2022.generated.pojo.Employee2;
import edgar.mybatis.guigu2022.generated.pojo.Employee2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Employee2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    long countByExample(Employee2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int deleteByExample(Employee2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int insert(Employee2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int insertSelective(Employee2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    List<Employee2> selectByExample(Employee2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    Employee2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByExampleSelective(@Param("row") Employee2 row, @Param("example") Employee2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByExample(@Param("row") Employee2 row, @Param("example") Employee2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByPrimaryKeySelective(Employee2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_employee
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByPrimaryKey(Employee2 row);
}