package edgar.mybatis.mybatisplus.pojo;

import java.util.Locale;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ModelBase<T> {

	public static final Faker faker = new Faker(new Locale("zh-CN"));
	
	/*
	 * id属性作为表的主键
	 * @TableId("column_name")
	 * @TableId(type = IdType.AUTO)，使用数据库自增主键
	 */
	@TableId
	protected Long id;
	
	/*
	 * 乐观锁，版本号字段
	 */
	@Version
	protected Integer version;
	
	
	public abstract T mockIt();
}
