package edgar.mybatis.learning1.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterMapping {
    private int index;
    private String property;
    private String fullPlaceHolder;
}
