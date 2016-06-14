/**
 * Created by Administrator on 2016/6/2.
 */
//对象级别的插件开发----------必须在页面刷新时重新执行

;(function($){
    var indexTab=function(){
        var self=this;

        console.log("调用indexTab")
        //共享目录
        $("#demo-list li").click(function() {
            var third_cate_id = $(this).attr("value");
            if(third_cate_id != null){
                //初始化
                $(".header-all").remove();
                alert("开始渲染共享目录")
                self.SharerRederDom();
            }
        })
        //
        $("#collection").click(function () {
            //初始化
            $(".contents .header-all").empty();
            console.log("开始渲染常用目录")
            self.CollectionRederDom();
        })


    };
    indexTab.prototype={
        SharergetData:function(){

        },
        SharerRederDom:function(){
            var self=this;
            var third_cate_id;
            var filestr=self.getDataFiles("/erudition/resources/"+third_cate_id+"/1");
            var str=['<div class="header flex-row">',
                '                <div class="flex-7 path">',
                '                    常用目录',
                '                </div>',
                //'                <%--<div class="flex-3 search">--%>',
                //'                    <%--<div class="input-group">--%>',
                //'                        <%--<input type="text" class="form-control" placeholder="该目录下搜索...">--%>',
                //'                              <%--<span class="input-group-btn">--%>',
                //'                                <%--<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>--%>',
                //'                              <%--</span>--%>',
                //'                    <%--</div>--%>',
                //'                <%--</div>--%>',
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
        getDataFiles:function(url){
            var self=this;
            console.log("开始获取共享目录数据");
            //var url="/erudition/collection/showcollections";
            var filestr=""
            $.ajaxSetup({async: false});
            $.getJSON(url , function(data){
                console.log("data"+data);
                $.each(data,function(i, file){
                    filestr=['                    <div class="body-floor flex-row">',
                        '                        <div class="flex-3 flex-row">',
                        '                            <div class="flex-1 checkbox">',
                        '                                <input type="checkbox"/>',
                        '                            </div>',
                        '                            <div class="flex-1 file-image"><i class="iconfont icon-mp4"></i></div>',
                        '                            <div class="file-name flex-4"><span id="id"><a href="#">'+file.title+'</a></span></div>',
                        '                        </div>',
                        '                        <div class="flex-3 file-size"><span>124kb</span></div>',
                        '                        <div class="flex-3 file-creator">MR.z</div>',
                        '                        <div class="flex-3 file-time">2015.025</div>',
                        '                    </div>',
                        '                    <div class="line"></div>'].join("")+filestr;
                })
            })
            console.log("获取的最终的string"+filestr);
            return filestr;
        },
        CollectionRederDom:function(){
            var self=this;
            var filestr=self.getDataFiles("/erudition/collection/showcollections");
            var str=['<div class="header flex-row">',
                '                <div class="flex-7 path">',
                '                    常用目录',
                '                </div>',
                //'                <%--<div class="flex-3 search">--%>',
                //'                    <%--<div class="input-group">--%>',
                //'                        <%--<input type="text" class="form-control" placeholder="该目录下搜索...">--%>',
                //'                              <%--<span class="input-group-btn">--%>',
                //'                                <%--<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>--%>',
                //'                              <%--</span>--%>',
                //'                    <%--</div>--%>',
                //'                <%--</div>--%>',
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
        }
    };
    window["indexTab"]=indexTab;
})(jQuery)


