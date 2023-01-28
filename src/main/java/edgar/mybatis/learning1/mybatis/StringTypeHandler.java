package edgar.mybatis.learning1.mybatis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public class StringTypeHandler implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, String value) throws SQLException {
        String val = Optional.ofNullable(value).orElse("");
        preparedStatement.setString(index, val);
    }

    @Override
    public String getColumnValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }
}
