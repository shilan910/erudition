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

    <div class="contents flex-8 file-upload">
        <div class="header-all">
            <div class="header flex-row">
                <div class="flex-7 path">
                    添加文件
                </div>

            </div>

                <%--文件上传表单--%>
                <div class="form-upload">
                    <form action="/erudition/admin/file/upload" method="post" enctype="multipart/form-data">
                        <div class="select">
                            <div class="directory">
                                <span>&nbsp;&nbsp;&nbsp;一级目录&nbsp;&nbsp;&nbsp;</span>
                                <select class="form-control" id="category-select" name="cate1">
                                    <option selected value="">请选择</option>
                                    <c:forEach items="${firstCategorys}" var="category">
                                        <option value="${category.id}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="directory">
                                <span>二级目录</span>
                                <select class="form-control" id="second-category-select" name="cate2">
                                    <option selected value="">请选择</option>
                                </select>
                            </div>
                            <div class="directory">
                                <span>三级目录</span>
                                <select class="form-control" id="third-category-select" name="cate3">
                                    <option selected value="">请选择</option>
                                </select>
                            </div>
                        </div>

                        <%--<div class="form-group inputFile">
                            <label for="inputFile">上传视频</label>
                            <input type="file" id="inputFile" name="files" value="" multiple />
                            <p class="help-block">支持MP4格式</p>
                        </div>
                        <input type="submit" class="btn btn-success btn-course" value="上传" />--%>

                        <label>上传视频：</label>
                        <div class="form-group inputFile input-file">
                            <br/>
                            <a href="javascript:;" class="file-scan">
                                <input type="file" id="inputFile" name="files" value="浏览" multiple />选择文件
                            </a>
                            <label class="showFileName">未选择文件</label>
                        </div>
                        <p class="help-block pull-left">支持MP4格式</p>
                        <input type="submit" class="btn btn-primary btn-course pull-right" value="上传" id="progress" />
                        <div class="clearfix"></div>


                    </form>

                    <div class="progress">
                        <div id="progress-bar" class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                            <!--<span class="sr-only">20% 完成</span>-->
                            <span>20% Complete</span>
                        </div>
                    </div>


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

<%--目录--%>
<script>
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    var category_select = $("#category-select"),
            second_category_select = $("#second-category-select"),
            third_category_select = $("#third-category-select");

    category_select.change(function(){

            loadCategory(category_select.val(),second_category_select);
//        } else {
//            clearCategory(second_category_select);
//            clearCategory(third_category_select);
//        }
    });

    //zqh:new

    second_category_select.change(function(){
//        if(second_category_select.val() != "") {
            loadCategory(second_category_select.val(),third_category_select);
//        } else {
//            clearCategory(third_category_select);
//        }
    });



    function loadCategory(id , selector){
        var url = "/erudition/category/getChildrenCategory/"+id;
        $.getJSON(url , function(data){
            selector.empty();
            $.each(data,function(i, category){
                var option = "<option value='" + category.id + "'>" + category.categoryName + "</option>";
                selector.append(option);
            });
        });
    }

    function clearCategory(selector) {
        selector.empty();
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

</body>
</html>