package edgar.mybatis.guigu2022.generated.pojo;

public class User2 {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.username
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.password
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.age
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private Byte age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.sex
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private Byte sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_user.email
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.id
     *
     * @return the value of tbl_user.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.id
     *
     * @param id the value for tbl_user.id
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.username
     *
     * @return the value of tbl_user.username
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.username
     *
     * @param username the value for tbl_user.username
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.password
     *
     * @return the value of tbl_user.password
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.password
     *
     * @param password the value for tbl_user.password
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.age
     *
     * @return the value of tbl_user.age
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public Byte getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.age
     *
     * @param age the value for tbl_user.age
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.sex
     *
     * @return the value of tbl_user.sex
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.sex
     *
     * @param sex the value for tbl_user.sex
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_user.email
     *
     * @return the value of tbl_user.email
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_user.email
     *
     * @param email the value for tbl_user.email
     *
     * @mbg.generated Sat Mar 26 10:13:24 CST 2022
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	public User2() {
	}

	public User2(Integer id, String username, String password, Byte age, Byte sex, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User2 [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + ", sex=" + sex
				+ ", email=" + email + "]";
	}
    
    
}