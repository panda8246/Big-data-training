package web_log_view;

import base.DatabaseBase;
import base.DatabaseBaseImpl;
import base.DatabaseConnect;
import base.ToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "WebLogViewServlet", value = "/weblog")
public class WebLogViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseBase operate = new DatabaseBaseImpl();

        //执行sql语句得到返回数据
        ResultSet resultSet = null;
        ArrayList<WebLogModel> models = new ArrayList<>();
        try {
            resultSet = operate.SelectAll("t_avgpv_num");
            while(resultSet.next()){
                WebLogModel model = new WebLogModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getFloat("avgPvNum"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //数据转为json
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Float> data = new ArrayList<>();
        for(WebLogModel m : models) {
            dates.add(m.getDataStr());
            data.add(m.getAvgPvNum());
        }
        WebLogGojo webgojo = new WebLogGojo(dates, data);
        String json = ToJson.Conver(webgojo);

        //将数据返回给前端
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
