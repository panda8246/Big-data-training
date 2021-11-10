package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 链接数据库单例
 */

public class DatabaseConnect {

    private String Url = "jdbc:mysql://127.0.0.1:3306/web_log_view";
    private String User = "root";
    private String Password = "131420";

    private static DatabaseConnect instance = null;

    private Connection connection = null;
    private Statement statement = null;

    private DatabaseConnect(){
        Connect();
    }

    public static DatabaseConnect getInstance() {
        if(instance == null){
            instance = new DatabaseConnect();
        }
        return instance;
    }

    //链接数据库
    private void Connect() {

        try {
            //注册类
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            // 链接数据库
            connection = DriverManager.getConnection(Url, User, Password);
            // 创建sql语句处理
            statement = connection.createStatement();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
