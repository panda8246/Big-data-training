# Web Log View

java数据可视化  
 
JDBC
===
<br>
连接数据库： 

```
private String Url = "jdbc:mysql://127.0.0.1:3306/数据库名称";
private String User = "数据库账号";
private String Password = "数据库密码";
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
```     
<br>
执行sql语句，查询返回值： 

```
String sql = "SELECT * FROM t_avgpv_num";
ResultSet resultSet = statement.executeQuery(sql);
ArrayList<WebLogModel> models = new ArrayList<WebLogModel>();
while(resultSet.next()){
    WebLogModel model = new WebLogModel(resultSet.getString("id"), resultSet.getString("dateStr"), resultSet.getFloat("avgPvNum"));
    models.add(model);
}
System.out.println(models);
```

<br>

对象转为JSON
===
<br>
将obj转为json字符串：  

```
ArrayList<String> dates = new ArrayList<>();
ArrayList<Float> data = new ArrayList<>();
for(WebLogModel m : models) {
    dates.add(m.getDataStr());
    data.add(m.getAvgPvNum());
}
// GOJO的对象，用于转换Json对象
Gson gson = new Gson();
//待转换的对象
WebLogGojo webgojo = new WebLogGojo(dates, data);
//转换
String json = gson.toJson(webgojo);
System.out.println(json);
```

在Servlet中将JSON暴露给前端:  
```
    //将数据返回给前端
    resp.setContentType("text/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter writer = resp.getWriter();
    writer.write(json);
    writer.flush();
    writer.close();
```
<br>

前端Echarts数据可视化
===
在html中引入echarts.js文件  

```
    <!-- 引入 echarts.js -->
    <script src="js/echarts.js"></script>
```
柱形图例子：
```
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 800px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
 
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '第一个 ECharts 实例'
            },
            tooltip: {},
            legend: {
                data:['点击量']
            },
            xAxis: {
                data:["20130919","20130920","20130921","20130922","20130923","20130924","20130925"]
            },
            yAxis: {},
            series: [{
                name: '点击量',
                type: 'bar',
                data:[13.4,17.6,15.2,21.1,16.9,18.1,18.6]
            }]
        };
 
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
```
<br>

Ajax动态请求
===
用ajax实现动态请求更新页面的信息，前端请求json，后端连接数据库查询，返回json给前端  

要使用ajax，先引入它的js文件（在本地的js文件夹下存在，也可以写网络地址一CDN方式引入）

```
<script src="js/jquery-3.4.1.js"></script>
```

<br>
使用ajax异步请求

```
    //ajax异步请求
    $.ajax({
        type: "post",
        //这里是servlet的value
        url: "weblog",
        datatype: "json",
        //请求成功回调函数
        success:function (data) {
            myChart.setOption({
                xAxis : {
                    data : data.dates
                },
                series : [ {
                    // 根据名字对应到相应的系列
                    name : 'PV量',
                    data : data.data
                } ]
            })
        }
    })
```

<br>
后端servlet处理请求：

```
//这里配置servlet的value，用于和前端的请求关联
@WebServlet(name = "WebLogViewServlet", value = "/weblog")
public class WebLogViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取数据库连接
        // 创建sql语句处理
        //执行sql语句得到返回数据
        //数据转为json
        String json = gson.toJson(webgojo);

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
```

