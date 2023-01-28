package edgar.mybatis.learning1.mybatis;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public interface TokenHandler {
    String handleToken(int index, String property, String fullGroup);
}
