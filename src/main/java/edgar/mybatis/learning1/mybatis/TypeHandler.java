package edgar.mybatis.learning1.mybatis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public interface TypeHandler<T> {
    void setParameter(PreparedStatement preparedStatement, int index, T value) throws SQLException;
    T getColumnValue(ResultSet rs, String columnName) throws SQLException;
}
