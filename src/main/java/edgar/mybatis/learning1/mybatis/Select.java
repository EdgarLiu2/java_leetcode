package edgar.mybatis.learning1.mybatis;


import java.lang.annotation.*;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Select {
    String value();
}
