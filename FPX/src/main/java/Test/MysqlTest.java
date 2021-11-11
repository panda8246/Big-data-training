package Test;

import base.DatabaseConnect;
import com.google.gson.Gson;
import web_log_view.WebLogGojo;
import web_log_view.WebLogModel;

import java.sql.*;
import java.util.ArrayList;

public class MysqlTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DatabaseConnect databaseConnect = DatabaseConnect.getInstance();
        // 创建sql语句处理
        Statement statement = databaseConnect.getStatement();
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
