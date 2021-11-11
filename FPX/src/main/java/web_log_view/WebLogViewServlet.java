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

        WebLogDao dao = new WebLogDao();

        String json = dao.ModlesToJson(dao.SelectAll());

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
