package edgar.mybatis.learning1;

import edgar.mybatis.learning1.mybatis.GenericTokenParser;
import edgar.mybatis.learning1.mybatis.ParameterMappingTokenHandler;
import edgar.mybatis.learning1.mybatis.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
@Slf4j
public class GenericTokenParserTest {
    GenericTokenParser parser;
    TokenHandler tokenHandler;

    @BeforeEach
    void before() {
        tokenHandler = new ParameterMappingTokenHandler();
        parser = new GenericTokenParser(tokenHandler);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
    'select * from tbl_user3 where id = #{id}', 'select * from tbl_user3 where id = ?'
    'select * from tbl_user3 where username = #{username}', 'select * from tbl_user3 where username = ?'
    'insert into tbl_user values(null, #{username}, #{password}, #{age}, #{sex}, #{email})', 'insert into tbl_user values(null, ?, ?, ?, ?, ?)'
    'update tbl_user set username = #{username}, password = #{password}, age = #{age}, sex = #{sex}, email = #{email}  where id = #{id}', 'update tbl_user set username = ?, password = ?, age = ?, sex = ?, email = ?  where id = ?'
    'delete from tbl_user where id = #{id}', 'delete from tbl_user where id = ?'
    'delete from tbl_user where id in (${ids})', 'delete from tbl_user where id in (?)'
    """)
    void testParse(String sql, String expectedSQL) {
        String sqlParsed = parser.parse(sql);
        Assertions.assertEquals(expectedSQL, sqlParsed);
    }
}
