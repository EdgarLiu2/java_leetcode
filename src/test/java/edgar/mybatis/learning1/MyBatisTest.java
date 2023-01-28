package edgar.mybatis.learning1;

import edgar.mybatis.learning1.app.User;
import edgar.mybatis.learning1.app.UserMapper;
import edgar.mybatis.learning1.mybatis.MapperProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
@Slf4j
public class MyBatisTest {
    static UserMapper userMapper;

    @BeforeAll
    static void before() {
        userMapper = MapperProxyFactory.getMapper(UserMapper.class);
    }

    @Test
    void testGetUsersByUsername() {
        List<User> users = userMapper.getUsersByUsername("俞哲瀚");
        log.debug("users={}", users);

        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals("俞哲瀚", users.get(0).getUsername());
    }

    @Test
    void testGetUsers() {
        List<User> users = userMapper.getUsers();
        log.debug("users={}", users);

        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void testGetUsersById() {
        User user = userMapper.getUserById(1612779342398521346L);
        log.debug("user={}", user);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(1612779342398521346L, user.getId());
    }
}
