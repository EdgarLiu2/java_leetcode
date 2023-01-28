package edgar.mybatis.learning1.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private Integer age;
    private String password;
    private Integer sex;
    private String email;
    private Integer version;
}
