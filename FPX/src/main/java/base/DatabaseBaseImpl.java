package base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBaseImpl implements DatabaseBase {

    @Override
    public ResultSet ExcuteSQL(String sql) {
        Statement statement = DatabaseConnect.getInstance().getStatement();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet SelectAll(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return ExcuteSQL(sql);
    }
}
