package base;

import java.sql.ResultSet;

/**
 * 此接口定义一些数据库基础操作
 */
public interface DatabaseBase {

    public ResultSet ExcuteSQL(String sql);

    public ResultSet SelectAll(String tableName);

}
