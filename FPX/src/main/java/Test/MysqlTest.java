package Test;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.SqlStatement;

import java.sql.*;
import java.util.ArrayList;

public class MysqlTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/web_log_view";
        String user = "root";
        String  password = "131420";
        // 链接数据库
        Connection connection = DriverManager.getConnection(url, user, password);
        // 创建sql语句处理
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM t_avgpv_num";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<WebLogModel> models = new ArrayList<WebLogModel>();
        while(resultSet.next()){
            WebLogModel model = new WebLogModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getFloat("avgPvNum"));
            models.add(model);
        }
        System.out.println(models);
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Float> data = new ArrayList<>();
        for(WebLogModel m : models) {
            dates.add(m.getDataStr());
            data.add(m.getAvgPvNum());
        }
        Gson gson = new Gson();
        WebLogGojo webgojo = new WebLogGojo(dates, data);
        String json = gson.toJson(webgojo);
        System.out.println(json);
    }

}
