package web_log_view;

import base.Daobase;
import base.DatabaseBase;
import base.DatabaseBaseImpl;
import base.ToJson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库操作类
 */
public class WebLogDao implements Daobase<WebLogModel> {
    @Override
    public ArrayList<WebLogModel> SelectAll() {

        ArrayList<WebLogModel> models = new ArrayList<>();
        DatabaseBase operate = new DatabaseBaseImpl();
        //执行sql语句得到返回数据
        ResultSet resultSet = null;
        try {
            resultSet = operate.SelectAll("t_avgpv_num");
            while(resultSet.next()){
                WebLogModel model = new WebLogModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getFloat("avgPvNum"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public ArrayList<WebLogModel> QuerySQL(String sql) {
        ArrayList<WebLogModel> models = new ArrayList<>();
        DatabaseBase operate = new DatabaseBaseImpl();
        //执行sql语句得到返回数据
        ResultSet resultSet = null;
        try {
            resultSet = operate.QuerySQL(sql);
            while(resultSet.next()){
                WebLogModel model = new WebLogModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getFloat("avgPvNum"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public String ModlesToJson(ArrayList<WebLogModel> models) {
        //数据转为json
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Float> data = new ArrayList<>();
        for(WebLogModel m : models) {
            dates.add(m.getDataStr());
            data.add(m.getAvgPvNum());
        }
        WebLogGojo webgojo = new WebLogGojo(dates, data);
        return ToJson.Conver(webgojo);
    }
}
