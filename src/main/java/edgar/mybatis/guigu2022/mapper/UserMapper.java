package edgar.mybatis.guigu2022.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import edgar.mybatis.guigu2022.pojo.User;
import edgar.mybatis.guigu2022.query.UserQuery;

public interface UserMapper {
	/**
	 * Mybatis获取变量值的两种方式
	 * ${}：字符串拼接，有SQL注入风险
	 * #{}：占位符赋值，优先使用
	 */

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(User user);
	
	/**
	 * 批量添加用户信息
	 * 
	 * @param users
	 * @return
	 */
	int batchInsertUsers(@Param("users") List<User> users);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	int deleteUserById(@Param("id") Integer id);

	/**
	 * 删除用户
	 * 
	 * @param username
	 * @return
	 */
	int deleteUserByUsername(@Param("username") String username);

	/**
	 * 批量删除用户
	 * 
	 * @param ids 用逗号拼接的id，例如1,2,3
	 * @return
	 */
	int deleteUserByIds(@Param("ids") String ids);
	
	/**
	 * 批量删除用户
	 * 
	 * @param ids
	 * @return
	 */
	int deleteUserByIdArray(@Param("ids") Integer[] ids);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(@Param("id") Integer id);

	/**
	 * 根据name查询
	 * 
	 * @param username
	 * @return
	 */
	List<User> getUsersByUsername(@Param("username") String username);

	/**
	 * 根据email模糊查询
	 * 
	 * @param email
	 * @return
	 */
	List<User> getUsersByEmailLike(@Param("email") String email);
	
	/**
	 * 跟进user查询条件，进行复杂查询
	 * 
	 * @param userQuery
	 * @return
	 */
	List<User> getUsersByQuery(UserQuery userQuery);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> getAllUsers();

	/**
	 * 查询所有用户 使用@MapKey注解，将用户对象放到Map中
	 * 
	 * @return
	 */
	@MapKey("id")
	Map<Integer, User> getAllUsersToMap();

	/**
	 * 查询当前用户数
	 * 
	 * @return
	 */
	Integer getUserCount();

	/**
	 * 检查登录用户名和密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User checkLogin(@Param("username") String username, @Param("password") String password);
}
