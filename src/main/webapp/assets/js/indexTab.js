/**
 * Created by Administrator on 2016/6/2.
 */
//对象级别的插件开发----------必须在页面刷新时重新执行

;(function($){
    var indexTab=function(){
        var self=this;

        console.log("调用indexTab")
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
                self.SharerRederDom(third_cate_id);
            }
        })
        //常用目录
        $("#collection").click(function () {
            //初始化
            $(".contents .header-all").empty();
            console.log("开始渲染常用目录")
            self.CollectionRederDom();
        })


    };
    indexTab.prototype={
        getDataFilesAddlist:function(url){
            var self=this;
            console.log("开始获取共享目录数据");
            var filestr=""
            $.ajaxSetup({async: false});
            $.getJSON(url , function(data){
                console.log("data"+data);
                $.each(data.list,function(i, file){
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
                            '                        <div class="flex-3 file-size"><span>'+size+'</span></div>',
                            '                        <div class="flex-3 file-creator">'+file.creater+'</div>',
                            '                        <div class="flex-3 file-time">'+time+'</div>',
                            '                    </div>',
                            '                    <div class="line"></div>'].join("")+filestr;
                })
            })
            //console.log("获取的最终的string"+filestr);
            return filestr;
        },
        getDataFilesNolist:function(url){
            var self=this;
            console.log("开始获取共享目录数据");
            //var url="/erudition/collection/showcollections";
            var filestr=""
            $.ajaxSetup({async: false});
            $.getJSON(url , function(data){
                console.log("data"+data);
                $.each(data,function(i, file){
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
                            '                        <div class="flex-3 file-size"><span>'+size+'</span></div>',
                            '                        <div class="flex-3 file-creator">'+file.creater+'</div>',
                            '                        <div class="flex-3 file-time">'+time+'</div>',
                            '                    </div>',
                            '                    <div class="line"></div>'].join("")+filestr;
                })
            })
            //console.log("获取的最终的string"+filestr);
            return filestr;
        },
        SharerRederDom:function(third_cate_id){
            var self=this;
            var catelog="共享目录";
            console.log("发送的id为"+third_cate_id);
            //var third_cate_id;
            var filestr=self.getDataFilesAddlist("/erudition/resources/"+third_cate_id+"/1");
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
                '                <nav>',
                '                    <ul class="pagination pull-right">',
                '                        <li><a href="#">上一页</a></li>',
                '                        <li class="active"><a href="#">1</a></li>',
                '                        <li><a href="#">2</a></li>',
                '                        <li><a href="#">3</a></li>',
                '                        <li><a href="#">4</a></li>',
                '                        <li><a href="#">5</a></li>',
                '                        <li>',
                '                            <a href="#">下一页</a>',
                '                        </li>',
                '                    </ul>',
                '                </nav>',
                '            </div>'].join("");
            $(".main .header-all").append(str);
            iCheckready();
        },
        CollectionRederDom:function(){
            var self=this;
            var catelog="常用目录";
            var filestr=self.getDataFilesNolist("/erudition/collection/showcollections");
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
                '                <nav>',
                '                    <ul class="pagination pull-right">',
                '                        <li><a href="#">上一页</a></li>',
                '                        <li class="active"><a href="#">1</a></li>',
                '                        <li><a href="#">2</a></li>',
                '                        <li><a href="#">3</a></li>',
                '                        <li><a href="#">4</a></li>',
                '                        <li><a href="#">5</a></li>',
                '                        <li>',
                '                            <a href="#">下一页</a>',
                '                        </li>',
                '                    </ul>',
                '                </nav>',
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
        RecommendRederDom:function(){
            var self=this;
            file=self.getDataFilesNolist("/erudition/recent");
            console.log("最近浏览得到的为"+file);
            var str=['<div class="header flex-row home">',
                '                <ul class="list-inline">',
                '                    <li class="header-list active" id="recommend_getwin">推荐</li>',
                '                    <li class="header-list" id="history_getwin">最近浏览</li>',
                '                </ul>',
                '            </div>',
                '            <!--<div class="line"></div>-->',
                '            <div class="file-body recommend_win" style="display: block">',
                '                <!--<input type="checkbox"/>-->',
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
                '                    <div class="body-floor flex-row">',
                '                        <div class="flex-3 flex-row">',
                '                            <div class="flex-1 checkbox"><input type="checkbox"/></div>',
                '                            <!--<div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>-->',
                '                            <div class="flex-1 file-image"><i class="iconfont icon-${recommendfile.type}"></i></div>',
                '                            <div class="file-name flex-4"><span><a href="#">${recommendfile.title}</a></span></div>',
                '                        </div>',
                '                        <div class="flex-3 file-size">',
                '                            <span>${recommendfile.size}</span>',
                '                        </div>',
                '                        <div class="flex-3 file-creator">',
                '                                ${recommendfile.creater}',
                '                        </div>',
                '                        <div class="flex-3 file-time">',
                '                                ${recommendfile.createrTime}',
                '                        </div>',
                '                    </div>',
                '                    <div class="line"></div>',
                '            </div>',
                '            <div class="file-body history_win" style="display: none">',
                '                <!--<input type="checkbox"/>-->',
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
                '                <c:forEach var="recentfile" items="${recentFiles}" begin="0" end="8">',
                '                    <div class="body-floor flex-row">',
                '                        <div class="flex-3 flex-row">',
                '                            <div class="flex-1 checkbox"><input type="checkbox"/></div>',
                '                            <!--<div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>-->',
                '                            <div class="flex-1 file-image"><i class="iconfont icon-${recentfile.type}"></i></div>',
                '                            <div class="file-name flex-4"><span><a href="#">${recentfile.title}</a></span></div>',
                '                        </div>',
                '                        <div class="flex-3 file-size">',
                '                            <span>${recentfile.size}</span>',
                '                        </div>',
                '                        <div class="flex-3 file-creator">',
                '                            ${recentfile.creater}',
                '                        </div>',
                '                        <div class="flex-3 file-time">',
                '                            ${recentfile.createrTime}',
                '                        </div>',
                '                    </div>',
                '                    <div class="line"></div>',
                '                </c:forEach>',
                file,
                '            </div>'].join("");
            $(".main .header-all").append(str);
            //绑定点击事件
            $("#recommend_getwin").click(function(){
                $(this).parent().children().each(function(){
                    $(this).removeClass("active");
                })
                $(this).addClass("active");
                $(".history_win").fadeOut(150,function(){
                    $(".recommend_win").fadeIn(150);
                });
            })
            $("#history_getwin").click(function(){
                $(this).parent().children().each(function(){
                    $(this).removeClass("active");
                })
                $(this).addClass("active");
                $(".recommend_win").fadeOut(150,function(){
                    $(".history_win").fadeIn(150);
                });
            })
            iCheckready();
        }
    };
    window["indexTab"]=indexTab;
})(jQuery)


