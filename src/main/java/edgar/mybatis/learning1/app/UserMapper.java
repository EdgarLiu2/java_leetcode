package edgar.mybatis.learning1.app;

import edgar.mybatis.learning1.mybatis.Param;
import edgar.mybatis.learning1.mybatis.Select;

import java.util.List;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
public interface UserMapper {

    @Select("select * from tbl_user3")
    List<User> getUsers();

    @Select("select * from tbl_user3 where username = #{username}")
    List<User> getUsersByUsername(@Param("username") String username);

    @Select("select * from tbl_user3 where id = #{id}")
    User getUserById(@Param("id") Long id);
}
