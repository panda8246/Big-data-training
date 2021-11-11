<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    /*
     * basePath：http://localhost:8080/web_log_view_project_war_exploded/
     * request.getScheme()：http
     * request.getServerName()：localhost
     * request.getServerPort()：8080
     * request.getContextPath()：web_log_view_project_war_exploded
     * */
%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<title>假面骑士Build</title>
<body>
<h1>Rabbit! Tank! Best Match!</h1>
<h1>Are You Ready?</h1>
<h1>变身!</h1>
<h2></h2>
</body>
</html>
