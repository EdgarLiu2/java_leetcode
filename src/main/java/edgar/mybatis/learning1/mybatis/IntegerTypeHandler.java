package edgar.mybatis.learning1.mybatis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public class IntegerTypeHandler implements TypeHandler<Integer> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Integer value) throws SQLException {
        Integer val = Optional.of(value).orElse(0);
        preparedStatement.setInt(index, val);
    }

    @Override
    public Integer getColumnValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName);
    }
}
