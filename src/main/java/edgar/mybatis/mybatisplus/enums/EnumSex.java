package edgar.mybatis.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

@Getter
public enum EnumSex {
	MALE(1, "男"),
	FEMALE(2, "女");
	
	/*
	 * 将@EnumValue对应的值保存到数据库中
	 */
	@EnumValue
	private Integer sex;
	private String name;
	
	private EnumSex(Integer sex, String name) {
		this.sex = sex;
		this.name = name;
	}
	
}
