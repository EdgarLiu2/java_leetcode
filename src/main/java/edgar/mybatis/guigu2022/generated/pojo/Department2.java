package edgar.mybatis.guigu2022.generated.pojo;

public class Department2 {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_department.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_department.name
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_department.id
     *
     * @return the value of tbl_department.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_department.id
     *
     * @param id the value for tbl_department.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_department.name
     *
     * @return the value of tbl_department.name
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_department.name
     *
     * @param name the value for tbl_department.name
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}