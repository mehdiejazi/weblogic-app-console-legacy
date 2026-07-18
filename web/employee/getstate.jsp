<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="controller.util.WebParamUtil"%>
<%@page  pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title>بررسی وضعیت سرور</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--<script type="text/javascript" src="../theme/jquery.js"></script>-->

        <script type="text/javascript" src="../theme/script.js"></script>

        <!--<script type="text/javascript" src="../theme/js/jquery.min.js"></script>-->
        <script type="text/javascript" src="../theme/js/jquery-1.10.2.js"></script>
        <link href="../theme/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>        
        <script type="text/javascript">
            var chartdata=[];
            var farsidate=[];
            var strInner = "";
            var strHeader='<tr><td class="art-table-content-header">تاریخ</td><td class="art-table-content-header">زمان</td><td class="art-table-content-header">وضعیت سرور</td><td class="art-table-content-header">وضعیت اپلیکشن</td><td class="art-table-content-header">صفخه ابتدایی</td><td class="art-table-content-header">کاربران آنلاین</td></tr>';
            var strSMSChart="";
            var arrSMSChart=[];
            var arr =[];
            //$(function () {
            $(document).ready(function() {
                {
                    strHeader='<tr><td class="art-table-content-header">تاریخ</td><td class="art-table-content-header">زمان</td><td class="art-table-content-header">وضعیت سرور</td><td class="art-table-content-header">وضعیت اپلیکشن</td><td class="art-table-content-header">صفخه ابتدایی</td><td class="art-table-content-header">کاربران آنلاین</td></tr>';
                    var RowTag="";

                    $.ajax({
                        url: './getstatetable.do',
                        type: 'POST',
                        success: function (response) {
                            $("#tblContent").html(strHeader + response);
                        }
                    });
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
                }
                setInterval( function() {
                    var RowTag="";

                    $.ajax({
                        url: './getstatetable.do',
                        type: 'POST',
                        success: function (response) {
                            $("#tblContent").html(strHeader + response);
                        }
                    });
                    Highcharts.setOptions({
                        global: {
                            useUTC: false
                        }
                    });
                    $.ajax({
                        url: './getactivitychart.do',
                        type: 'POST',
                        success: function (response) {
                            strSMSChart= response;
                        }
                    });
                    arr= strSMSChart.split("|");
                    chartdata=[];
                    farsidate=[];
                    var chart;
                    chart =  GetChart("column");
                }, 60000);   
                var RowTag="";
                $.ajax({
                    url: './getstatetable.do',
                    type: 'POST',
                    success: function (response) {
                        strInner = response+strInner;

                        $("#tblContent").html(response);
                    }
                });
            });


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
                        }
                    },
                    xAxis: {
                        categories:
                            (function() {

                            //alert(arr);

                            farsidate=[];
                            for( i = 7; i <= 13; i++) {
                                farsidate.push([
                                    arr[i]
                                ]);
                            }
                            //alert(farsidate);
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
                        formatter: function() {
                            return '<div style="direction:rtl!important;">'+                                    
                                Highcharts.numberFormat(this.y, 0)+"</div>";
                        }
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
                            })()
                        }
                    ]
                });


                return chart0;
            }

        </script>
    </head>
    <body>
        <script src="../theme/js/highcharts.js"></script>
        <script src="../theme/js/exporting.js"></script>

        <div id="art-main">
            <div class="art-header">
                <jsp:include page="../theme/header.jsp"/>
            </div>
            <div class="art-nav">
                <jsp:include page="../theme/navigation.jsp"/>
            </div>
            <div class="art-sheet">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">

                        <div class="art-post-body">
                            <div class="art-post-inner art-article">
                                <h2 class="art-postheader">
                                    بررسی وضعیت سرور
                                </h2>
                                <div class="art-postcontent">
                                    <!-- content -->

                                    <table id="tblContent" class="art-table-content">
                                        <tr><td class="art-table-content-header">تاریخ</td><td class="art-table-content-header">زمان</td><td class="art-table-content-header">وضعیت سرور</td><td class="art-table-content-header">وضعیت اپلیکشن</td><td class="art-table-content-header">صفخه ابتدایی</td><td class="art-table-content-header">کاربران آنلاین</td></tr>
                                    </table>
                                    <br />
                                    <br />
                                    <div id="art-chart-container">

                                    </div>
                                    <!-- /content -->
                                </div>
                                <div class="cleared"></div>

                            </div>
                            <div class="cleared"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="art-footer">
                <jsp:include page="../theme/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
