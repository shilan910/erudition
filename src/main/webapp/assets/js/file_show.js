/**
 * Created by Administrator on 2016/6/2.
 */
//对象级别的插件开发----------必须在页面刷新时重新执行

$(function(){
    var fileout=new FileOut();
})

;(function($){
    var FileOut=function(){
        //引入依赖的js
        //new_element=document.createElement("script");
        //new_element.setAttribute("type","text/javascript");
        //new_element.setAttribute("src","popwinAll.js");// 在这里引入了a.js
        //document.body.appendChild(new_element);
        var self=this;
        //抽取基础DOM主要围绕字符串来使用
        self.pre_btn='.file-out .pre-btn';
        self.next_btn='.file-out .next-btn';
        self.root='.file-body';
        self.root_element='.body-floor';
        self.currentPopwin;
        self.nextPopwin;

        self.currentIndex;
        self.allIndex;

        //其他参数
        self.canCarousel=true;

        //数据池
        self.fileData;
        self.fileRelations;

        console.log("调用插件");

        $(document).on("click",".body-floor .file-name span",function(event){         //这里的前提是有整体对象    这尼玛没法进行return了
            var me=$(this);

            event.stopPropagation();
            //获取当前页面全部对象个数
            self.allIndex=self.getAllIndex();
            console.log("当前页面全部个数:"+self.allIndex);
            //设置页面中的元素index属性
            self.setIndex();
            //获取当前点击对象的位置
            self.currentIndex=$(this).parents(self.root_element).attr("index");
            console.log("当前点击为第"+self.currentIndex+"个");
            //获取数据
            self.getData(self.currentIndex,"index");
            //渲染数据
            self.renderDOM();            //因为暗含了顺序，所以可以无所顾忌的使用，哈哈哈哈//静态渲染与动态渲染的时间上有问题？
            self.carousel();          //绑定轮播事件，但是还没有特殊化,


            //插入动态数据
            //self.renderData($(this));        //将当前点击传送
        })

        //搜索后的下方推荐
        $(document).on("click",".footRecommend .title",function(event){
            var me=$(this);

            event.stopPropagation();
            //获取当前页面全部对象个数
            self.allIndex=self.getAllIndex_title();
            console.log("title当前页面全部个数:"+self.allIndex);
            //设置页面中的元素index属性
            self.setIndex_title();
            //获取当前点击对象的位置
            self.currentIndex=$(this).attr("index");
            console.log("title当前点击为第"+self.currentIndex+"个");
            //获取数据
            self.getData_title(self.currentIndex,"index");
            //不允许轮播
            self.canCarousel=false;
            //渲染数据
            self.renderDOM();            //因为暗含了顺序，所以可以无所顾忌的使用，哈哈哈哈//静态渲染与动态渲染的时间上有问题？
            //self.carousel();          //绑定轮播事件，但是还没有特殊化,
        })

    };
    FileOut.prototype={
        //根据index获取元素id
        getIdByIndex:function(index){
            var self=this;
            console.log("发送当前的id"+$(self.root_element).eq(index-1).find(".file-name").find("span").eq(0).attr("id"));
            return $(self.root_element).eq(index-1).find(".file-name").find("span").eq(0).attr("id");
        },
        getIdByIndex_title:function(index){
            var self=this;
            console.log("发送当前的id"+$(".footRecommend .title").eq(index-1).attr("value"));
            return $(".footRecommend .title").eq(index-1).attr("value");
        },
        /**
         * 根据配置获取数据(下标或者id)
         * @param index
         * @param opt
         */
        getData_title:function(enter,opt){
            var self=this;
            console.log("开始获取数据");
            //根据下标获取id
            //var file_id = me.attr("id");
            var file_id;
            if(opt==="index"){
                file_id=self.getIdByIndex_title(enter);
            }else if(opt=="id"){
                file_id=enter;
            }

            console.log("获取的id为:"+file_id);
            var file;
            $.ajax({
                url:'/erudition/resources/file/'+file_id,                 //${rootPath}失效
                type:'get',
                async : false, //默认为true 异步
                success:function(data){
                    self.fileData=data.file;
                    self.fileRelations=data.relationfiles;        //获取关联文件
                    console.log("获取的关联文件为:"+data.relationfiles);
                },error:function(){
                    alert("error"+file_id);
                    return "error";
                }
            });
        },
        getData:function(enter,opt){
            var self=this;
            console.log("开始获取数据");
            //根据下标获取id
            //var file_id = me.attr("id");
            var file_id;
            if(opt==="index"){
                file_id=self.getIdByIndex(enter);
            }else if(opt=="id"){
                file_id=enter;
            }

            console.log("获取的id为:"+file_id);
            var file;
            $.ajax({
                url:'/erudition/resources/file/'+file_id,                 //${rootPath}失效
                type:'get',
                async : false, //默认为true 异步
                success:function(data){
                    self.fileData=data.file;
                    self.fileRelations=data.relationfiles;        //获取关联文件
                    console.log("获取的关联文件为:"+data.relationfiles);
                },error:function(){
                    alert("error"+file_id);
                    return "error";
                }
            });
        },
        //初步渲染弹窗
        getAllIndex:function(){
            var self=this;
            return $(self.root).children(self.root_element).length;           //这里动态的子元素个数
        },
        getAllIndex_title:function(){
            var self=this;
            console.log("进入getAllIndex_title")
            //console.log($(".footRecommend").text())
            return $(".footRecommend .title").length;
        },
        setIndex:function(){
            var self=this;
            var num=1;
            $(self.root).children(self.root_element).each(function(){
                $(this).attr("index",num++);
            })
        },
        setIndex_title:function(){
            var self=this;
            var num=1;
            $(".footRecommend .title").each(function(){
                $(this).attr("index",num++);
            })
        },
        renderDOM:function(){
            var self=this;
            var file=self.fileData;
            var fileRelations=self.fileRelations;
            //转换时间戳createTime
            var date = new Date(file.createTime);
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var createDate = Y+M+D+h+m+s;
            //转换文件大小
            var fileSize=self.turnSize(file.size);

            console.log("file.type="+file.type);
            console.log("file.size="+file.size);



            var filename = file.title;
            filename = filename.substring(0,filename.lastIndexOf('.'));
            //alert(filename);
            if(filename=="云计算设计报告")filename = "yun";
            else if(filename=="关系数据库语言")filename = "guanxi";
            else if(filename=="答辩")filename = "dabian";
            else if(filename=="统计")filename = "tongji";
            else if(filename=="设计报告")filename = "sheji";
            filename+=".png";
            //alert(filename);
            var strDom1=['<div class="file-out" style="display: none;" >',
                '        <div class="pre-btn pre-bg"></div>',
                '        <!--<div class="clearfix"></div>-->',
                '        <div class="file-body">',
                '            <div class="content">',
                '                <div class="file">',
                '                    <div class="file-thumbnails">',
                '                        <div class="file-name"><img src="/erudition/assets/images/'+filename+'" alt=""/></div>',
                '                        <div class="file-class">'+file.type+'</div>',
                '                    </div>',
                '                    <div class="file-size">',
                '                        <button class="download file-url">查看文件</button>',
                '                    </div>',
                '                </div>',
                '            </div>',
                '            <!--<div class="clearfix"></div>-->',
                '            <div class="attribute">',
                '                <div class="a-info">',
                '                    <div class="a-first">',
                '                        <div class="file-from">所属文件夹:'+file.categoryName+'</div>',
                '                        <div class="a-close">×</div>',
                '                        <div class="clearfix"></div>',
                '                    </div>',
                '                    <div class="file-name">'+file.title+'</div>',
                //'                    <div class="collected">收藏量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2333</div>',
                '                    <div class="a-third">',
                '                        <div class="file-uptime"><i class="fa fa-clock-o"></i>'+createDate+'</div>',
                '                        <div class="file-people"><i class="fa fa-user"></i>上传人-'+file.creater+'</div>',
                '                    </div>',
                '                </div>',
                '                <div class="line"></div>',
                '                <div class="a-operate">',
                '                    <ul>',
                '                        <li><a href="/erudition/admin/file/download/'+file.id+'"><i class="fa fa-download"></i>&nbsp;&nbsp;下载('+fileSize+')</a></li>',
                '                        <li><a href="#" class="collect"><i class="fa fa-star"></i>&nbsp;&nbsp;收藏</a></li>',
                '                    </ul>',
                '                </div>',
                '                <div class="line"></div>',
                '                <div class="a-related">',
                '                    <ul>',
                '                        <li><a href="#"><i class="fa fa-link"><span id='+file.id+'></i>&nbsp;&nbsp;&nbsp;关联内容</a></li>'].join("");
            //连接关联内容
            for(var i=0 ; i < fileRelations.length ; i++){
                var re = fileRelations[i].title;
                console.log('re= '+re);
                strDom1 = strDom1 + "<li id='"+fileRelations[i].id+"'class='file-related' ><a href='#'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-link'></i>&nbsp;&nbsp;&nbsp;"+
                    fileRelations[i].title+"</a></li>";
            }

            var strDom2=['                        <li><a href="#"><i class="fa fa-tag"></i>&nbsp;&nbsp;&nbsp;标签:&nbsp;&nbsp;'+getKeywords(file.keywords)+'</a></li>',
                '                    </ul>',
                '                </div>',
                '            </div>',
                '        </div>',
                '        <div class="next-btn"></div>',
                '        <!--<div class="clearfix"></div>-->',
                '    </div>'].join("");

            var strDom=strDom1+strDom2;
            //插入到body中
            $("body").append(strDom);           //这里怎么记录当前的这个弹窗呢？
            self.currentPopwin=$(".file-out");       //记录当前弹窗
            //显示并加入遮罩层
            $(".mask").fadeIn();
            $(".file-out").fadeIn();
            //为close与mask绑定事件
            $(".a-close").on("click",function(event){
                event.stopPropagation();
                $(".file-out").fadeOut();
                $(".mask").fadeOut();
                self.currentPopwin.remove();
            })
            $(".mask").on("click",function(event){
                event.stopPropagation();
                $(".file-out").fadeOut();
                $(".mask").fadeOut();
            })
            //为收藏绑定事件
            self.CollectToCommon();
            //为关联绑定事件
            self.HangRelateEvent();
            //为查看绑定事件
            self.HangWatchfile();
            //判断要不要轮播
            if(self.canCarousel==false){
                $(self.pre_btn).removeClass("pre-bg");
                $(self.next_btn).css({      //>>>>>>>>>>>>>>>>>>>>>>>>这里有瑕疵
                    "display":"none"
                })
            }
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
        CollectToCommon:function(){
            var self=this;
            $(".a-operate .collect").click(function(event){
                var file_id =self.getIdByIndex(self.currentIndex);
                $.ajax({
                    url:'/erudition/collection/addtocollection/'+file_id,            //axaj失败
                    type:'get',
                    async:false, //默认为true 异步
                    success:function(data){
                        self.popwin_tips(data.message,data.status);               //没有执行？？？
                    },error:function(){                      //明明插入成功了？？？error
                        console.log("异步传输失败");
                    }
                });
                console.log("添加过程结束");
            })
        },
        popwin_tips:function(message,status){
            var self=this;
            //开始调用弹窗程序
            console.log("调用tip");
            var pop=new Popwin();
            pop.tips(message,status);
        },
        HangRelateEvent:function(){
            var self=this;
            var file_id;
            $(".file-related").click(function(){
                file_id=$(this).attr("id");
                console.log("点击关联的id为:"+file_id);
                self.currentPopwin.remove();
                self.getData(file_id,"id");
                self.renderDOM();
                $(self.pre_btn).removeClass("pre-bg");
                $(self.next_btn).css({      //>>>>>>>>>>>>>>>>>>>>>>>>这里有瑕疵
                    "display":"none"
                })
            })
        },
        HangWatchfile:function(){
            var self=this;
            $(".file-out .file-url").click(function(){
                var title=self.fileData.title;
                var url = title.substring(0,title.lastIndexOf('.'));
               // alert(url);
                if(url=="云计算设计报告")url = "yun";
                else if(url=="关系数据库语言")url = "guanxi";
                else if(url=="答辩")url = "dabian";
                else if(url=="统计")url = "tongji";
                else if(url=="设计报告")url = "sheji";
                var type = title.substring(title.lastIndexOf('.')+1);
               // alert(type);
                if(type=="mp4"){
                    var dom = "<div class='file-watch' style='display: none;'>"+
                       " <div class='close-circle'>×</div>"+
                    "<div class='video'>"+
                    "    <video id='really-cool-video' class='video-js vjs-default-skin'  controls preload='auto' poster='really-cool-video-poster.jpg' data-setup='{}'>"+
                    "    <source src='http://7xpl2y.com1.z0.glb.clouddn.com/asdf.mp4' type='video/mp4'>"+
                    "    <source src='really-cool-video.webm' type='video/webm'>"+
                    "    <p class='vjs-no-js'>"+
                    "    To view this video please enable JavaScript, and consider upgrading to a web browser that"+
                    "    <a href='http://videojs.com/html5-video-support/' target='_blank'>supports HTML5 video</a>"+
                    "</p>"+
                    "</video>"+
                    "</div>"+
                    "</div>";
                }
                else if(type=="mp3"){
                    console.log("点击了mp3")
                    var data={};
                    var html=template("Tmp3",data);
                    console.log(html);
                    $("body").prepend(html);
                    $("#mp3").fadeIn(200);
                    var mp3 = new APlayer({
                        element: document.getElementById('mp3-player'),
                        narrow: false,
                        autoplay: false,
                        showlrc: false,
                        music: {
                            title: 'Sugar',
                            author: 'Maroon 5',
                            url: '/erudition/assets/file/mp3/Sugar.mp3',
                            pic: '/erudition/assets/file/mp3/Maroon5.jpg'
                        }
                    });
                    mp3.init();
                    $("#mp3 .close").click(function(){
                        $(this).parent().fadeOut(200,function(){
                            mp3.pause();
                            $(this).remove();
                        });
                    })

                }
                else{
                    url = "/erudition/assets/file/text/"+url+".pdf";
                    var dom="<div class='file-watch' style='display: none;'>"+
                        "        <div class='close-circle'>×</div>"+
                        "        <iframe width='738' height='523' class='preview-iframe' scrolling='no' frameborder='0' src='"+url+"'></iframe>"+
                        "    </div>";
                }


                console.log(self.fileData.url);
                $('body').append(dom);
                $(".file-watch").fadeIn(400);

                $(".close-circle").click(function(){
                    $(".file-watch").fadeOut(200,function(){
                        $(".file-watch").remove();
                    });
                })
                console.log("添加路径");
            })

        },
        carousel:function(){
            var self=this;
            console.log("轮播启动");
            console.log("当前下标为"+self.currentIndex);
            //判断可不可以next
            if(self.currentIndex!=self.allIndex){
                $(self.next_btn).css({
                    "display":"block"
                });
                $(this.next_btn).click(function(){
                    self.next();
                })
            }else{
                $(self.next_btn).css({
                    "display":"none"
                })
            }
            //判断可不可以pre
            if(self.currentIndex!=1){
                /*$(self.pre_btn).css({
                 "display":"block"
                 });*/
                $(self.pre_btn).addClass("pre-bg");
                $(this.pre_btn).click(function(){
                    self.pre();
                })
            }else{
                $(self.pre_btn).removeClass("pre-bg");
            }
        },
        next:function(){
            var self=this;
            //改变下标
            self.currentIndex++;
            //将之前的DOM抹去
            self.currentPopwin.fadeOut(200).remove();
            //展示下一个DOM
            self.getData(self.currentIndex,"index");
            self.renderDOM();
            self.carousel();            //这里一定是下一个DOM    这里很有问题
            self.nextPopwin=$(".file-out");
            self.nextPopwin.fadeIn(200);
        },
        pre:function(){
            var self=this;
            //改变下标
            self.currentIndex--;
            //将之前的DOM抹去
            self.currentPopwin.fadeOut(200).remove();
            //展示上一个DOM
            self.getData(self.currentIndex,"index");
            self.renderDOM();
            self.carousel();            //这里一定是上一个DOM
            self.nextPopwin=$(".file-out");
            self.nextPopwin.fadeIn(200);
        }
    };
    window["FileOut"]=FileOut;
})(jQuery)

function getKeywords(keyword){
    var temp = keyword.split("#");
    var newkeyword = temp[1];
    return newkeyword;
}
