

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
            url: "weblog",
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
</body>
</html>