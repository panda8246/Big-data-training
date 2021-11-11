package web_log_view;

import base.Daobase;
import base.DatabaseBase;
import base.DatabaseBaseImpl;
import base.ToJson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowNumDao implements Daobase<FlowNumModel> {
    @Override
    public ArrayList<FlowNumModel> SelectAll() {
        ArrayList<FlowNumModel> models = new ArrayList<>();
        DatabaseBase operate = new DatabaseBaseImpl();
        //执行sql语句得到返回数据
        ResultSet resultSet = null;
        try {
            resultSet = operate.SelectAll("t_flow_num");
            while(resultSet.next()){
                FlowNumModel model = new FlowNumModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getInt("pVNum"),
                        resultSet.getInt("uVNum"), resultSet.getInt("ipNum"), resultSet.getInt("newUvNum"), resultSet.getInt("visitNum"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public ArrayList<FlowNumModel> QuerySQL(String sql) {
        ArrayList<FlowNumModel> models = new ArrayList<>();
        DatabaseBase operate = new DatabaseBaseImpl();
        //执行sql语句得到返回数据
        ResultSet resultSet = null;
        try {
            resultSet = operate.QuerySQL(sql);
            while(resultSet.next()){
                FlowNumModel model = new FlowNumModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getInt("pVNum"),
                        resultSet.getInt("uVNum"), resultSet.getInt("ipNum"), resultSet.getInt("newUvNum"), resultSet.getInt("visitNum"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public String ModlesToJson(ArrayList<FlowNumModel> models) {
        //数据转为json
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> pVNum = new ArrayList<>();
        ArrayList<Integer> uVNum = new ArrayList<>();
        ArrayList<Integer> iPNum = new ArrayList<>();
        ArrayList<Integer> newUvNum = new ArrayList<>();
        ArrayList<Integer> visitNum = new ArrayList<>();
        for(FlowNumModel m : models) {
            dates.add(m.getDateStr());
            pVNum.add(m.getpVNum());
            uVNum.add(m.getuVNum());
            iPNum.add(m.getiPNum());
            newUvNum.add(m.getNewUvNum());
            visitNum.add(m.getVisitVNum());
        }
        FlowNumGojo webgojo = new FlowNumGojo(dates, pVNum, uVNum, iPNum, newUvNum, visitNum);
        return ToJson.Conver(webgojo);
    }
}
