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
    <%--<link rel="stylesheet" href="/erudition/assets/css/app.min.css"/>--%>

<%--<link rel="stylesheet" href="${assetsPath}/css/index.css"/>--%>

    <link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.1/skins/square/blue.css"/>
    <!--<link rel="stylesheet" href="./css/square/blue.css"/>-->
    <!--<link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.2/skins/flat/blue.css"/>-->

    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${assetsPath}/js/jquery-accordion-menu.js"></script>
    <script src="${assetsPath}/js/icheck.js"></script>
</head>

<body>

<jsp:include page=".././common/header.jsp" />

<div class="main flex-row">
    <div class="flex-2">
        <div class="nav">
            <div id="jquery-accordion-menu" class="jquery-accordion-menu white">
                <div id="user-image">
                    <a href=""><img src="${assetsPath}/images/user.jpg" alt="" class="img-circle"/></a>
                    <div class="user-name">当前用户</div>
                </div>

                <div class="jquery-accordion-menu-header" id="form"></div>                 <!--//里面的form是动态添加的-->
                <ul id="demo-list">

                    <li class="active"><a href="#"><i class="fa fa-glass"></i>共享目录 </a></li>
                    <li><a href="#"><i class="fa fa-home"></i>系统管理 </a>
                        <ul class="submenu">
                            <li><a href="#">目录管理</a></li>
                            <li><a href="#">文件管理</a></li>
                            <li><a href="${rootPath}/file/upload">文件上传</a></li>
                            <li><a href="#">设定规则</a></li>
                        </ul>
                    </li>

                </ul>
                <div class="jquery-accordion-menu-footer">
                    Footer
                </div>
            </div>
        </div>

        <div class="clearfix"></div>
    </div>


    <div class="contents flex-8">
        <div class="content-course">

            <!--  课程添加   -->
            <h2 class="dash-head">文件上传</h2>
            <form class="video-add" name="form1" id="form1" action="${rootPath}/admin/resource/add"
                  method="post" enctype="multipart/form-data" target="if" onsubmit="return go()">
                <div class="form-group form-info">
                    <label for="courseName">文件名称</label>
                    <input type="text" class="form-control" id="courseName" name="title" value="">
                </div>
                <div class="select">
                    <div class="">
                        <span>&nbsp;&nbsp;&nbsp;分&nbsp;&nbsp;类&nbsp;&nbsp;&nbsp;</span>
                        <select class="form-control" id="category-select" name="cate1">
                            <option selected value="">请选择</option>
                            <c:forEach items="${firstCategorys}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="">
                        <span>阶&nbsp;&nbsp;段</span>
                        <select class="form-control" id="second-category-select" name="cate2" disabled="disabled">
                            <c:forEach items="${categoryList}" var="category2" >
                                <c:if test="${categoryList != null}">
                                    <option value="${category2.id}">${category2.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="">
                        <span>学&nbsp;&nbsp;科</span>
                        <select class="form-control" id="third-category-select" name="cate3" disabled="disabled">
                            <c:forEach items="${categoryList}" var="category3">
                                <c:if test="${categoryList != null}">
                                    <option value="${category3.id}">${category3.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group inputFile">
                    <label for="inputFile">上传视频</label>
                    <input type="file" id="inputFile" name="video" value=""/>
                    <p class="help-block">支持MP4格式</p>
                </div>
                <input type="submit" class="btn btn-success btn-course" value="上传" />
            </form>
            <!--  END 课程添加-->
            <iframe id="if" name="if" src="" style="display: none"></iframe>

        </div>
    </div>

</div>




<%--fileUpload:文件上传，至关重要的一句话--%>
<script>
    $('#inputFile').fileupload();
</script>

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
        if(category_select.val() == 1) {
//                       if(second_category_select.attr("disabled")=="disabled"){
            second_category_select.attr("disabled",false);
            third_category_select.attr("disabled",false);

//                       }
            loadCategory(category_select.val(),second_category_select);

        } else {
            second_category_select.attr("disabled","disabled");
            third_category_select.attr("disabled","disabled");
            clearCategory(second_category_select);
            clearCategory(third_category_select);
        }
    });

    //zqh:new

    second_category_select.change(function(){
        if(second_category_select.val() != "") {
            loadCategory(second_category_select.val(),third_category_select);
        } else {
            clearCategory(third_category_select);
        }
    });



    function loadCategory(id , selector){
//            var url = "http://" + host + uri + id + ".json";
        var url = "http://localhost:8080/erudition/category/getChildrenCategory/"+id;
        $.getJSON(url , function(data){
            selector.empty();
            $.each(data,function(i, category){
                var option = "<option value='" + category.id + "'>" + category.name + "</option>";
                selector.append(option);
            });
        });
    }

    function clearCategory(selector) {
        selector.empty();
    }
</script>



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