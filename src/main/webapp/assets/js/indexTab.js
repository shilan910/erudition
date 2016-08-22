/**
 * Created by Administrator on 2016/6/2.
 */
//对象级别的插件开发----------必须在页面刷新时重新执行

//$(function(){
//    $(document).ready(function(){
//        iCheckready();
//    });
//    var iCheckready=function(){
//        console.log("调用了icheck插件")
//        $('input').iCheck({
//            checkboxClass: 'icheckbox_square-blue',
//            radioClass: 'iradio_square-blue',
//            increaseArea: '20%' // optional
//        });
//    };
//})

;(function($){
    var indexTab=function(){
        var self=this;
        self.pageNum=0;

        console.log("调用indexTab");
        //主页推荐
        $("#home").click(function(){
            $(".header-all").empty();
            self.RecommendRederDom();
        })
        //共享目录
        $("#demo-list li").click(function() {
            var third_cate_id = $(this).attr("value");
            if(third_cate_id != null){
                //初始化
                $(".header-all").empty();
                //alert("开始渲染共享目录")
                var third_cate_id = $(this).attr("value");
                self.third_cate_id=third_cate_id;
                self.SharerRederDom(third_cate_id,1);     //初始化页码为1
            }
        })
        //常用目录
        //$(document).on("click","#collection",function(event){
        //    //初始化
        //    $(".contents .header-all").empty();
        //    console.log("开始渲染常用目录")
        //    self.CollectionRederDom();
        //})
        $("#collection").click(function () {
            //初始化
            $(".contents .header-all").empty();
            console.log("开始渲染常用目录")
            self.CollectionRederDom();
        })

        //推荐和浏览记录
        $(document).on("click","#recommend_getwin",function(){
            self.RecommendRederDom();
        })
        $(document).on("click","#history_getwin",function(){
            self.RecentRederDom("1");
        })


    };
    indexTab.prototype={
        getDataFilesAddlist:function(url,page){
            var self=this;
            console.log("开始获取共享目录数据");
            var filestr="";
            self.pageNum=0;

            $.ajax({                   //修改成ajax
                url:url,
                type:'GET',
                data:{page:page},
                async: false,
                success:function(data){
                    console.log("data"+data);
                    //获取控制信息
                    self.pageNow=data.pageNow;
                    self.totalPageCount=data.totalPageCount;
                    self.hasPre=data.hasPre;
                    self.hasNext=data.hasNext;
                    $.each(data.list,function(i, file){

                        self.pageNum++;
                        var size=self.turnSize(file.size);
                        var time=self.turnDate(file.createTime);
                        filestr=['                    <div class="body-floor flex-row">',
                                '                        <div class="flex-3 flex-row">',
                                '                            <div class="flex-1 checkbox">',
                                '                                <input type="checkbox"/>',
                                '                            </div>',
                                '                            <div class="flex-1 file-image"><i class="iconfont icon-'+file.type+'"></i></div>',
                                '                            <div class="file-name flex-4"><span id="'+file.id+'"><a href="#">'+file.title+'</a></span></div>',
                                '                        </div>',
                                '                        <div class="flex-3 file-size"><span>'+file.size+'</span></div>',
                                '                        <div class="flex-3 file-creator">'+file.creater+'</div>',
                                '                        <div class="flex-3 file-time">'+time+'</div>',
                                '                    </div>',
                                '                    <div class="line"></div>'].join("")+filestr;
                    })
                }
            })

            return filestr;
        },
        getDataFilesNolist:function(url,page){
            var self=this;
            var filestr="";
            //$.ajax({
            //    url:url,
            //    type:"GET",
            //    data:{page:page},
            //    async:false,
            //    success:(function(data){
            //        //获取控制信息
            //        self.pageNow=data.pageNow;
            //        self.totalPageCount=data.totalPageCount;
            //        self.hasPre=data.hasPre;
            //        self.hasNext=data.hasNext;
            //        $.each(data.list,function(i, file){
            //            //var size=self.turnSize(file.size);
            //
            //            var time=self.turnDate(file.createTime);
            //            filestr=['                    <div class="body-floor flex-row">',
            //                    '                        <div class="flex-3 flex-row">',
            //                    '                            <div class="flex-1 checkbox">',
            //                    '                                <input type="checkbox"/>',
            //                    '                            </div>',
            //                    '                            <div class="flex-1 file-image"><i class="iconfont icon-'+file.type+'"></i></div>',
            //                    '                            <div class="file-name flex-4"><span id="'+file.id+'"><a href="#">'+file.title+'</a></span></div>',
            //                    '                        </div>',
            //                    '                        <div class="flex-3 file-size"><span>'+file.size+'</span></div>',
            //                    '                        <div class="flex-3 file-creator">'+file.creater+'</div>',
            //                    '                        <div class="flex-3 file-time">'+time+'</div>',
            //                    '                    </div>',
            //                    '                    <div class="line"></div>'].join("")+filestr;
            //        })
            //    })
            //
            //})



            $.ajaxSetup({async: false});
            $.getJSON(url , function(data){
                console.log("data"+data);
                $.each(data,function(i, file){
                    //var size=self.turnSize(file.size);
                    var time=self.turnDate(file.createTime);
                    filestr=['                    <div class="body-floor flex-row">',
                            '                        <div class="flex-3 flex-row">',
                            '                            <div class="flex-1 checkbox">',
                            '                                <input type="checkbox"/>',
                            '                            </div>',
                            '                            <div class="flex-1 file-image"><i class="iconfont icon-'+file.type+'"></i></div>',
                            '                            <div class="file-name flex-4"><span id="'+file.id+'"><a href="#">'+file.title+'</a></span></div>',
                            '                        </div>',
                            '                        <div class="flex-3 file-size"><span>'+file.size+'</span></div>',
                            '                        <div class="flex-3 file-creator">'+file.creater+'</div>',
                            '                        <div class="flex-3 file-time">'+time+'</div>',
                            '                    </div>',
                            '                    <div class="line"></div>'].join("")+filestr;
                })
            })
            console.log("获取的最终的string"+filestr);
            return filestr;
        },
        SharerRederDom:function(third_cate_id,page){
            var self=this;
            var catelog="共享目录";
            console.log("发送的id为"+third_cate_id);
            //var third_cate_id;
            var filestr=self.getDataFilesAddlist("/erudition/resources/"+third_cate_id,page);

            template.config("openTag", "<$");
            template.config("closeTag", "$>");

            //处理分页
            var data={
                root:"/erudition/resources/"+third_cate_id,
                currentPage:self.pageNow,
                nextPage:self.pageNow+1,
                hasPre:self.hasPre,
                hasNext:self.hasNext,
                totalPageCount:self.totalPageCount
            };
            var pageHtml=template("Tpage",data);

            //if(self.pageNum)
            var str=['<div class="header flex-row">',
                '                <div class="flex-7 path">',
                    catelog,
                '                </div>',
                '            </div>',
                '            <div class="file-body" id="file-list">',
                '                <div class="first-floor flex-row">',
                '                    <div class="flex-3">',
                '                        <div>',
                '                            <input type="checkbox"/>',
                '                            <span class="filename">名称</span>',
                '                        </div>',
                '                    </div>',
                '                    <div class="flex-3">大小</div>',
                '                    <div class="flex-3">创建者</div>',
                '                    <div class="flex-3">更新日期</div>',
                '                </div>',
                '                <div class="line"></div>',
                filestr,
                pageHtml,
                '            </div>'].join("");

            $(".main .header-all").append(str);
            iCheckready();
            self.HangSharePage();
        },
        HangSharePage:function(){
            var self=this;
            $(".pagination li a").click(function(){
                if(!$(this).parent().hasClass("disabled")){
                    console.log("没有disabled");
                    var page=$(this).attr("page");
                    console.log("点击了页码"+page);
                    $(".contents .header-all").empty();
                    self.SharerRederDom(self.third_cate_id,page);
                }

            })
        },
        CollectionRederDom:function(){
            var self=this;
            var catelog="常用目录";
            var filestr=self.getDataFilesNolist("/erudition/collection/showcollections");

            //处理分页
            //template.config("openTag", "<$");
            //template.config("closeTag", "$>");
            //var data={
            //    root:"/erudition/resources/"+third_cate_id,
            //    currentPage:self.pageNow,
            //    nextPage:self.pageNow+1,
            //    hasPre:self.hasPre,
            //    hasNext:self.hasNext,
            //    totalPageCount:self.totalPageCount
            //};
            //var pageHtml=template("Tpage",data);

            var str=['<div class="header flex-row">',
                '                <div class="flex-7 path">',
                    catelog,
                '                </div>',
                '            </div>',
                '            <div class="file-body" id="file-list">',
                '                <div class="first-floor flex-row">',
                '                    <div class="flex-3">',
                '                        <div>',
                '                            <input type="checkbox"/>',
                '                            <span class="filename">名称</span>',
                '                        </div>',
                '                    </div>',
                '                    <div class="flex-3">大小</div>',
                '                    <div class="flex-3">创建者</div>',
                '                    <div class="flex-3">更新日期</div>',
                '                </div>',
                '                <div class="line"></div>',
                filestr,
                //'                <nav>',
                //'                    <ul class="pagination pull-right">',
                //'                        <li><a href="#">上一页</a></li>',
                //'                        <li class="active"><a href="#">1</a></li>',
                //'                        <li><a href="#">2</a></li>',
                //'                        <li><a href="#">3</a></li>',
                //'                        <li><a href="#">4</a></li>',
                //'                        <li><a href="#">5</a></li>',
                //'                        <li>',
                //'                            <a href="#">下一页</a>',
                //'                        </li>',
                //'                    </ul>',
                //'                </nav>',
                '            </div>'].join("");
            $(".main .header-all").append(str);
            iCheckready();
        },
        turnDate:function(createTime){
            var date = new Date(createTime);
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var createDate = Y+M+D+h+m+s;
            return createDate;
        },
        turnSize:function(size){
            //var turnedSize;
            var cnt=0 , unit="";
            while(size>=1024){
                size = size / 1024;
                cnt++;
            }
            switch (cnt){
                case 0:unit="B";break;
                case 1:unit="KB";break;
                case 2:unit="MB";break;
                case 3:unit="GB";break;
                case 4:unit="TB";break;
                default : break;
            }
            var size1 = parseFloat(size).toFixed(2)+unit;
            return size1;
        },
        RecommendRederDom:function(){                 //包括推荐和近期浏览
            var self=this;
            var recommendFile=self.getDataFilesNolist("/erudition/recommend");
            $(".main .header-all").empty();

            var str=['<div class="header flex-row home" id="home_header">',
                '                <ul class="list-inline">',
                '                    <li class="header-list active" id="recommend_getwin">推荐</li>',
                '                    <li class="header-list" id="history_getwin">最近浏览</li>',
                '                </ul>',
                '            </div>',
                '            <div class="file-body recommend_win" style="display: none">',
                //'                <!--<input type="checkbox"/>-->',
                '                <div class="first-floor flex-row">',
                '                    <div class="flex-3">',
                '                        <div>',
                '                            <input type="checkbox"/>',
                '                            <span class="filename">名称</span>',
                '                        </div>',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        大小',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        创建者',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        更新日期',
                '                    </div>',
                '                </div>',
                '                <div class="line"></div>',
                recommendFile,
                '            </div>'].join("");
            $(".main .header-all").append(str);
            $(".recommend_win").fadeIn(200);
            iCheckready();
        },
        RecentRederDom:function(page){
            var self=this;
            //var recentFile=self.getDataFilesNolist("/erudition/recent",page);
            var recentFile=self.getDataFilesAddlist("/erudition/recent",page);
            //console.log("recentFIle:"+recentFile);

            template.config("openTag", "<$");
            template.config("closeTag", "$>");

            //处理分页
            var pageData={
                root:"/erudition/recent",
                currentPage:self.pageNow,
                nextPage:self.pageNow+1,
                hasPre:self.hasPre,
                hasNext:self.hasNext,
                totalPageCount:self.totalPageCount
            };
            var pageHtml=template("Tpage",pageData);

            $(".main .header-all").empty();
            var str=['<div class="header flex-row home" id="home_header">',
                '                <ul class="list-inline">',
                '                    <li class="header-list" id="recommend_getwin">推荐</li>',
                '                    <li class="header-list active" id="history_getwin">最近浏览</li>',
                '                </ul>',
                '            </div>',
                '            <div class="file-body history_win" style="display: none">',
                '                <div class="first-floor flex-row">',
                '                    <div class="flex-3">',
                '                        <div>',
                '                            <input type="checkbox"/>',
                '                            <span class="filename">名称</span>',
                '                        </div>',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        大小',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        创建者',
                '                    </div>',
                '                    <div class="flex-3">',
                '                        更新日期',
                '                    </div>',
                '                </div>',
                '                <div class="line"></div>',
                recentFile,
                pageHtml,
                '            </div>'].join("");
            $(".main .header-all").append(str);
            $(".history_win").fadeIn(200);
            iCheckready();
            self.HangRecentPage();
        },
        HangRecentPage:function(){
            var self=this;
            $(".pagination li a").click(function(){
                if(!$(this).parent().hasClass("disabled")){
                    console.log("没有disabled");
                    var page=$(this).attr("page");
                    console.log("点击了页码"+page);
                    $(".contents .header-all").empty();
                    self.RecentRederDom(page);
                }

            })
        },
    };
    window["indexTab"]=indexTab;
})(jQuery)


