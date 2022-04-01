package edgar.mybatis.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends ModelBase<Product> {
	
	private String name;
	private Integer price;

	@Override
	public Product mockIt() {
		this.name = faker.commerce().productName();
		this.price = faker.number().numberBetween(100, 1000);
		
		return this;
	}
}
