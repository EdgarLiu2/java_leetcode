package edgar.mybatis.mybatisplus.pojo;


import edgar.mybatis.mybatisplus.enums.EnumSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class User3 extends ModelBase<User3> {

	// 设置对应字段列名
//	@TableField("user_name")
	private String username;
	private String password;
	private Integer age;
	private EnumSex sex;
	private String email;
	
	@Override
	public User3 mockIt() {
		this.setId(null);
		this.username = faker.name().fullName();
		this.password = faker.internet().password(8, 16, true, true, true);
		this.age = faker.number().numberBetween(10, 51);
		this.sex = faker.number().numberBetween(1, 3) == 1 ? EnumSex.MALE : EnumSex.FEMALE;
		this.email = faker.internet().emailAddress();
		
		return this;
	}

}
