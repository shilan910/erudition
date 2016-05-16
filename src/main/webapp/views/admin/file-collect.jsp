<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件管理</title>
</head>
<link rel="stylesheet" href="${assetsPath}/css/app.min.css"/>

<link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.1/skins/square/blue.css"/>
<!--<link rel="stylesheet" href="./css/square/blue.css"/>-->
<!--<link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.2/skins/flat/blue.css"/>-->

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

<body>
<div class="mask"></div>

<jsp:include page="../common/header.jsp" />

<div class="main flex-row">

    <div class="flex-2">
        <div class="nav">
            <div id="jquery-accordion-menu" class="jquery-accordion-menu white">
                <div id="user-image">
                    <a href=""><img src="${assetsPath}/images/user.jpg" alt="" class="img-circle"/></a>
                    <div class="user-name">${username}</div>
                </div>

                <div class="jquery-accordion-menu-header" id="form"></div>                 <!--//里面的form是动态添加的-->
                <ul id="demo-list">

                    <li class="active" ><a href="${rootPath}/admin/filecollect/file"><i class="fa fa-home"></i>文件管理</a></li>

                    <li><a href="#"><i class="fa fa-glass"></i>文件上传</a></li>


                    <li><a href="#"><i class="fa fa-suitcase"></i>规则设置</a></li>

                    <%--zqh : 此处前端需要修改--%>

                </ul>
                <%--<div class="jquery-accordion-menu-footer">--%>
                <%--Footer--%>
                <%--</div>--%>
            </div>
        </div>

        <div class="clearfix"></div>
    </div>


    <div class="contents flex-8 file-collect">
        <div class="button-group" style="">
            <button class="carrynews">创建新文件夹</button>
            <button class="removeall">清空文件夹</button>
            <button class="remove" id="removebutton">删除文件夹</button>
        </div>
        <br/>
        <div  class="alldom">
            <ul id="divall">
                <c:if test="${cateLayer!=3}">
                <c:forEach var="cate" items="${adminCates}">
                    <li ondblclick="openFile(${cate.id})"><input type="text" class="changename" value="${cate.categoryName}"/></li>
                </c:forEach>
                </c:if>
                <c:if test="${cateLayer==3}">
                    <div class='first-floor flex-row'>
                        <div class='flex-3'>
                            <div>
                                <input type='checkbox'/><span class='filename'>名称</span>
                            </div>
                        </div>
                        <div class='flex-3'>大小</div>
                        <div class='flex-3'>创建者</div>
                        <div class='flex-3'>更新日期</div>
                    </div>
                    <div class='line'></div>
                    <c:forEach var="files" items="${adminCates.list}">
                        <div class='body-floor flex-row'><div class='flex-3 flex-row'>
                            <div class='flex-1 checkbox'><input type='checkbox'/></div>
                            <div class='flex-1 file-image'><i class='fa fa-folder-o fa-3x'></i></div>
                            <div class='file-name flex-4'><span><a href='#'>${files.title}</a></span></div></div>
                            <div class='flex-3 file-size'><span>1.27MB</span></div>
                            <div class='flex-3 file-creator'>${files.creater}</div>
                            <div class='flex-3 file-time'>${files.createTime}</div>
                        </div>
                        <div class='line'></div>
                    </c:forEach>
                </c:if>
            </ul>
        </div>

        <div style=" clear:both;"></div>

        <div class="menu-zdy" id="menu">

            <div class="menu-one">
                <a href="#nogo" id="changename">修改文件夹名称</a>
            </div>

            <div class="menu-two">
                <a href="#nogo" id="removethispc">删除此文件</a>
            </div>

        </div>
    </div>

    <!--<div class="clearfix"></div>-->
</div>




<script>
    function openFile(cateId){
        window.location.href = "/erudition/admin/filecollect/categoey/"+cateId;
    }

</script>




<!--//动态创建搜索表单-->
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

<script src="${assetsPath}/js/file-view.js" charset="utf-8"></script>
</body>
</html>