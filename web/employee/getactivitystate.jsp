<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>بررسی وضعیت سرور</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />

        <script type="text/javascript" src="../theme/js/jquery-1.10.2.js"></script>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>      

        <script type="text/javascript">
            
            var chartdata=[];
            var farsidate=[];
            var strSMSChart="";
            var arrSMSChart=[];
            var arr =[];
            
            $(function () {
                $(document).ready(function() {
                    //////////////////////
                    var RowTag="";

                    $.ajax({
                        url: './getactivitychart.do',
                        type: 'POST',
                        success: function (response) {
                            strSMSChart= response;
                            
                            arr= strSMSChart.split("|");
                            chartdata=[];
                            farsidate=[];

                            Highcharts.setOptions({
                                global: {
                                    useUTC: false
                                }
                            });
                            var chart;
                            chart =  GetChart("column");
                        }
                    });

                    ///////////////
                    setInterval( function() {
                        var RowTag="";

                        $.ajax({
                            url: './getactivitychart.do',
                            type: 'POST',
                            success: function (response) {
                                strSMSChart= response;
                                arr= strSMSChart.split("|");
                                chartdata=[];
                                farsidate=[];                           
                                Highcharts.setOptions({
                                    global: {
                                        useUTC: false
                                    }
                                });
                                chart =  GetChart("column");
                            }
                        });

                    }, 60000); 
                }
            );
            }
        );
            ////////////////////////////////////

            function GetChart(ChartType)
            {
                var chart0 = 
                    new Highcharts.Chart(
                {
                    chart: {
                        renderTo: 'art-chart-container',
                        type: ChartType,
                        marginRight: 10,
                        events: {
                            load: function() {
 
                            }
                        }
                    },
                    title: {
                        text: 'چارت تعداد نشست های فعال برنامه'
                    },
                    plotOptions:
                        {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        },
                        series:{
                            animation:false
                        }
                    },
                    xAxis: {
                        categories:
                            (function() {
                            farsidate=[];
                            for( i = 7; i <= 13; i++) {
                                farsidate.push([
                                    arr[i]
                                ]);
                            }

                            return farsidate;
                        })()
                        ,
                        labels: {
                            rotation:-15
                        }
                    },
                    yAxis: {
                        title: {
                            text: ''
                        },
                        plotLines: [{
                                value: 0,
                                width: 1,
                                color: '#ff0000'
                            }]
                    },
                    tooltip: {
                        enabled: false
                    },
                    legend: {
                        enabled: false
                    },
                    exporting: {
                        enabled: false
                    },
                    series : [
                        {
                            name : '',
                            data : 
                                (function() {
                                
                                for( i = 0; i <= 6; i++) {
                                    chartdata.push([
                                        arr[i]*1
                                    ]);
                                }
                                return chartdata;
                            })(),
                            dataLabels: {
                                enabled: true,
                                rotation: 0,
                                color: '#000000',
                                align: 'center',
                                x: 30,
                                y: -10,
                                style: {
                                    fontSize: '18px',
                                    fontFamily: 'BYekan'
                                }
                            }
                        }
                    ]
                }
            );
                return chart0;
            }

        </script>
    </head>
    <body>
        <script src="../theme/js/highcharts.js"></script>
        <script src="../theme/js/exporting.js"></script>  

        <div id="art-chart-container" style="width: 100%; height: 100%;">

        </div>
    </body>
</html>
