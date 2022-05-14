package edgar.mybatis.guigu2022.generated.mapper;

import edgar.mybatis.guigu2022.generated.pojo.User2;
import edgar.mybatis.guigu2022.generated.pojo.User2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    long countByExample(User2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int deleteByExample(User2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int insert(User2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int insertSelective(User2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    List<User2> selectByExample(User2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    User2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByExampleSelective(@Param("row") User2 row, @Param("example") User2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByExample(@Param("row") User2 row, @Param("example") User2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByPrimaryKeySelective(User2 row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    int updateByPrimaryKey(User2 row);
}