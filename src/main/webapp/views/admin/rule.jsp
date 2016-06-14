<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Erudition</title>

    <link rel="stylesheet" href="${assetsPath}/css/app.min.css"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.1/skins/square/blue.css"/>

    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${assetsPath}/js/jquery-accordion-menu.js"></script>
    <script src="${assetsPath}/js/icheck.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <style type="text/css">
        *{box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;}
        body{background:#f0f0f0;}
        /*.content{width:260px;margin:100px auto;}*/
        .filterinput{
            background-color:rgba(249, 244, 244, 0);
            border-radius:15px;
            width:90%;
            height:30px;
            border:thin solid #FFF;
            text-indent:0.5em;
            font-weight:bold;
            color:#FFF;
        }
        #demo-list a{
            overflow:hidden;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            white-space:nowrap;
            width:100%;
        }
    </style>
</head>

<body>
<%--遮罩层--%>
<div class="mask"></div>

<jsp:include page="../common/header.jsp" />

<div class="main flex-row">

    <jsp:include page="../common/admin_sidebar.jsp" />

    <div class="contents flex-8" id="rule">
        <div class="header-all">
            <div class="header flex-row">
                <div class="flex-7 path">
                    规则设置
                </div>

            </div>

            <div class="file-body">
                <!--<div>
                    <form>
                        <lable>当两个文件具有n个相同关键字时，设为关联文件</lable>
                        <input type="text" value="3"/>
                        <input type="submit" value="确认" />
                    </form>
                    <form>
                        <lable>自动删除n天之前的文件</lable>
                        <input type="text" value="15"/>
                        <input type="submit" value="确认" />
                    </form>
                    <form>
                        <lable>用户可以添加n（上限）个文件至常用目录</lable>
                        <input type="text" value="30"/>
                        <input type="submit" value="确认" />
                    </form>
                </div>-->
                <form action="" class="all-floor">
                    <div class="row floor">
                        <div class="col-md-6">
                            <p>
                                <label for="amount">设置关联基数</label>
                                <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                            </p>
                            <div id="slider-range-max"></div>
                        </div>
                        <div class="article col-md-6">
                            <p class="text-danger">选取参数为关键字基础，当基数越大，所需的关联性越强</p>
                        </div>
                    </div>

                    <div class="row floor">
                        <div class="col-md-6">
                            <p>
                                <label for="amount">设置删除期限</label>
                                <input type="text" id="amount-day" readonly style="border:0; color:#f6931f; font-weight:bold;">
                            </p>
                            <div id="slider-range-max-day"></div>
                        </div>
                        <div class="article col-md-6">
                            <p class="text-danger">自动删除设置期限之外的文件，系统会额外保留一部分的临时文件</p>
                        </div>
                    </div>
                    <div class="row floor">
                        <div class="col-md-6">
                            <p>
                                <label for="amount">设置收藏上限</label>
                                <input type="text" id="amount-collect" readonly style="border:0; color:#f6931f; font-weight:bold;">
                            </p>
                            <div id="slider-range-max-collect"></div>
                        </div>
                        <div class="article col-md-6">
                            <p class="text-danger">限制用户从共享目录中收藏到常用目录中的数目</p>
                        </div>
                    </div>
                    <input type="submit" class="zyh-button-grey pull-right" value="提交"/>
                    <div class="clearfix"></div>
                </form>



                <script>
                    $(function() {
                        $( "#slider-range-max" ).slider({
                            range: "max",
                            min: 1,
                            max: 10,
                            value: 2,
                            slide: function( event, ui ) {
                                $( "#amount" ).val( ui.value );
                            }
                        });
                        $( "#amount" ).val( $( "#slider-range-max" ).slider( "value" ) );

                        $( "#slider-range-max-day" ).slider({
                            range: "max",
                            min: 1,
                            max: 10,
                            value: 2,
                            slide: function( event, ui ) {
                                $( "#amount-day" ).val( ui.value );
                            }
                        });
                        $( "#amount-day" ).val( $( "#slider-range-max-day" ).slider( "value" ) );

                        $( "#slider-range-max-collect" ).slider({
                            range: "max",
                            min: 1,
                            max: 10,
                            value: 2,
                            slide: function( event, ui ) {
                                $( "#amount-collect" ).val( ui.value );
                            }
                        });
                        $( "#amount-collect" ).val( $( "#slider-range-max-collect" ).slider( "value" ) );
                    });
                </script>



            </div>
        </div>
    </div>

</div>



<!--文件上传相关-->
<script>
    //监听文件浏览
    $(".file-scan").on("change","input[type='file']",function(){
        var filePath=$(this).val();
        if(filePath.indexOf("mp4")!=-1 || filePath.indexOf("doc")!=-1){    //这里只是检测图片字符串位置
            $(".fileerrorTip").html("").hide();
            var arr=filePath.split('\\');
            var fileName=arr[arr.length-1];
            $(".showFileName").html(fileName);
        }else{
            $(".showFileName").html("您上传文件类型有误！");
//                            $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
            return false
        }
    })

    //进度条动画
    $("#progress").click(function() {
        var num = 50;
        var num_str = num + "%";
        var interval = setInterval(fun1, 20);
        function fun1() {
            $("#progress-bar").width(num_str);
            num = num + 1;
            num_str = num + "%";        //计算的速度快于画画

            $("#progress-bar span").text(num_str);
            if(num==100){
                clearInterval(interval);
            }
        }
    })
</script>

<%--fileUpload:文件上传，至关重要的一句话--%>
<script>
    $('#inputFile').fileupload();
</script>

<%--checkbox插件启动--%>
<script>
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
</script>

<%--上传联动--%>
<script>
    var category_select = $("#category-select"),
            second_category_select = $("#second_category_select"),
            third_category_select = $("#third-category-select");

    category_select.change(function(){
        loadCategory2(category_select.val());
    });

    $(document).on("change","#second-category-select",function(event){
        loadCategory3($("#second-category-select").val());
    });
    function loadCategory2(id){    //id根据1号的option改变来驱动，一定会有id传入,,,,,暂时不要去思考selector
        var url = "/erudition/category/getChildrenCategory/"+id;
        $.getJSON(url , function(data){
            var select="";
            $.each(data,function(i, category){                 //这里有用的i跟category什么意思？，，，放回的
                var option = "<option value='" + category.id + "'>" + category.categoryName + "</option>";
                select=select+option;
            });
            select="<select class='form-control' id='second-category-select' name='cate2'>"+select+"</select>"    //无关的组装
            //将获得的数据插入到#test2的子元素中
            $("#second-select-all").html(select);
            $("#second-select-all").children("select").selectOrDie();     //重新启动
            loadCategory3($("#second-category-select").val());
        });
    }

    function loadCategory3(id){    //给个2号的id
        var url = "/erudition/category/getChildrenCategory/"+id;
        $.getJSON(url , function(data){
            var select="";
            $.each(data,function(i, category){                 //这里有用的i跟category什么意思？，，，放回的
                var option = "<option value='" + category.id + "'>" + category.categoryName + "</option>";
                select=select+option;
            });
            select="<select class='form-control' id='third-category-select' name='cate3'>"+select+"</select>";    //无关的组装

            console.log("这里是第三个目录"+select);

            //将获得的数据插入到#test2的子元素中
            $("#third-select-all").html(select);
            $("#third-select-all").children("select").selectOrDie();     //重新启动
        });
    }
</script>





<%--左侧导航基础模板--%>
<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery("#jquery-accordion-menu").jqueryAccordionMenu();   //启用插件   jQuery等同于$

    });

    $(function(){
        //顶部导航切换
        $("#demo-list li").click(function(){
            $("#demo-list li.active").removeClass("active")
            $(this).addClass("active");
        })
    })
</script>


<!--动态创建搜索表单-->
<script type="text/javascript">
    (function($) {

        //@header 头部元素
        //@list 无需列表
        //创建一个搜素表单
        function filterList(header, list) {
            var form = $("<form>").attr({
                "class":"filterform",
                action:"#"
            }), input = $("<input>").attr({
                "class":"filterinput",
                type:"text"
            });
            $(form).append(input).appendTo(header);
            $(input).change(function() {              //过滤器的具体效果
                var filter = $(this).val();
                if (filter) {
                    $matches = $(list).find("a:Contains(" + filter + ")").parent();
                    $("li", list).not($matches).slideUp();
                    $matches.slideDown();
                } else {
                    $(list).find("li").slideDown();
                }
                return false;
            }).keyup(function() {           //用来监听键盘的效果的
                $(this).change();
            });
        }

        $(function() {                     //最先运行
            filterList($("#form"), $("#demo-list"));
        });

    })(jQuery);
</script>

<!--icheck    radio不能正常使用-->
<script>
    $(document).ready(function(){

        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>

<!--美化option-->
<script>
    $(function(){
        $('select').selectOrDie();
    });
</script>
<%--<script>
    var startSelect=function(){
        $('select').selectOrDie();
    }
</script>--%>
<script src="${assetsPath}/js/select/selectordie.js"></script>
</body>
</html>