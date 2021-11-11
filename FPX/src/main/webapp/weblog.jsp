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

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>第一个 ECharts 实例</title>
    <!-- 引入 echarts.js -->
    <script src="js/echarts.js"></script>
    <script src="js/jquery-3.4.1.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 1000px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '懂的都懂'
            },
            tooltip: {},
            legend: {
                data:['点击量']
            },
            xAxis: {
                data:[]
            },
            yAxis: {},
            series: [{
                name: '点击量',
                type: 'bar',
                data:[]
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        //ajax异步请求
        $.ajax({
            type: "post",
            url: "<%=basePath%>/weblog",
            datatype: "json",
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


    </script>

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main2" style="width: 1000px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('main2'));
        // 指定图表的配置项和数据
        option2 = {

        };
        //ajax异步请求
        $.ajax({
            type: "post",
            url: "<%=basePath%>/flownum",
            datatype: "json",
            success: function (data) {
                myChart2.setOption({
                    title: {
                        text: 'Flow Num'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    legend: {
                        data: ['pVNum', 'uVNum', 'iPNum', 'newUvNum', 'visitNum']
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: data.dates
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: 'pVNum',
                            type: 'line',
                            stack: 'Total',
                            areaStyle: {},
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.pVNum
                        },
                        {
                            name: 'uVNum',
                            type: 'line',
                            stack: 'Total',
                            areaStyle: {},
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.uVNum
                        },
                        {
                            name: 'iPNum',
                            type: 'line',
                            stack: 'Total',
                            areaStyle: {},
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.iPNum
                        },
                        {
                            name: 'newUvNum',
                            type: 'line',
                            stack: 'Total',
                            areaStyle: {},
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.newUvNum
                        },
                        {
                            name: 'visitNum',
                            type: 'line',
                            stack: 'Total',
                            label: {
                                show: true,
                                position: 'top'
                            },
                            areaStyle: {},
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.visitNum
                        }
                    ]

                })
            }
        })


    </script>

</body>
</html>