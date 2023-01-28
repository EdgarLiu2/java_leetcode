package edgar.mybatis.learning1.mybatis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public class LongTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Long value) throws SQLException {
        Long val = Optional.of(value).orElse(0L);
        preparedStatement.setLong(index, val);
    }

    @Override
    public Long getColumnValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }
}
