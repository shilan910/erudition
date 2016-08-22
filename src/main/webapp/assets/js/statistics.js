/**
 * Created by Administrator on 2016/8/21.
 */
$(function () {
    var Statistics1=new Statistics();
});
;(function ($) {
    var Statistics=function(){
        var self=this;
        self.hitInit();

        $(document).on("click","#hit_getwin",function(event){
            self.hitInit();
        })
        $(document).on("click","#collect_getwin", function (event) {
            self.collectInit();
        })

    };
    Statistics.prototype={
        hitInit:function(){
            var self=this;
            var data={};
            var html=template("Tcollectsta",data);
            $(".header-all").empty();
            $(".header-all").append(html);
            $("#hit_getwin").addClass("active");
            self.hithaveDayGraph();
            //为collect中绑定事件
            $(".dayB").click(function(){
                //$("#hitGraph").remove();
                self.hithaveDayGraph();
            })
            $(".weekB").click(function(){
                self.hithaveWeekGraph();
            })
            $(".monthB").click(function(){
                self.hithaveMonthGraph();
            })
        },
        collectInit:function(){
            var self=this;
            var data={};
            var html=template("Tcollectsta",data);
            $(".header-all").empty();
            $(".header-all").append(html);
            $("#collect_getwin").addClass("active");
            self.collecthaveDayGraph();
            //为collect中绑定事件
            $(".dayB").click(function(){
                //$("#hitGraph").remove();
                self.collecthaveDayGraph();
            })
            $(".weekB").click(function(){
                self.collecthaveWeekGraph();
            })
            $(".monthB").click(function(){
                self.collecthaveMonthGraph();
            })
        },

        hithaveDayGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '日点击量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '点击量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '点击量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },
        hithaveWeekGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '周点击量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '点击量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '点击量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },
        hithaveMonthGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '月点击量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '点击量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '点击量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },
        collecthaveDayGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '日收藏量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '收藏量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '收藏量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },
        collecthaveWeekGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '周收藏量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '收藏量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '收藏量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },
        collecthaveMonthGraph:function(){
            var self=this;
            //清除
            $(".allGraph").empty();
            var html='<div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>';
            $(".allGraph").append(html);
            $('#Graph').fadeIn(200);

            $('#Graph').highcharts({
                chart: { zoomType: 'xy' },
                title: { text: '月收藏量统计图' },
                xAxis: [{
                    categories: ['A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L'],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    title: {
                        text: '',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '收藏量',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} 次',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
//            opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '收藏量',
                    type: 'column',
                    yAxis: 1,
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54],
                    tooltip: {
                        valueSuffix: ' 次'
                    }
                }]
            });
        },

    };
    window["Statistics"]=Statistics;
})(jQuery)