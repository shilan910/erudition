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

<jsp:include page="common/header.jsp" />
<c:set var="secondCates"/>
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

                    <li><a href="#"><i class="fa fa-home"></i>主页 </a></li>

                    <li class="active" ><a href="#"><i class="fa fa-glass"></i>共享目录 </a>
                        <ul class="submenu" id="first-cates">
                            <c:forEach items="${categories}" var="firstCate">
                                <li><a href="#">${firstCate.name}</a>

                                    <ul class="submenu">
                                        <c:forEach items="${firstCate.children}" var="secondCate">
                                            <li><a href="#">${secondCate.name}</a>

                                                <ul class="submenu">
                                                    <c:forEach items="${secondCate.children}" var="thirdCate">
                                                        <li><a href="#">${thirdCate.name}</a></li>
                                                    </c:forEach>
                                                </ul>

                                            </li>
                                        </c:forEach>
                                    </ul>

                                </li>
                            </c:forEach>
                        </ul>

                    </li>


                    <li><a href="#"><i class="fa fa-cog"></i>服务 </a>
                        <ul class="submenu">
                            <li><a href="#">Web Design </a></li>
                            <li><a href="#">Hosting </a></li>
                            <li><a href="#">Design </a>
                                <ul class="submenu">
                                    <li><a href="#">Graphics </a></li>
                                    <li><a href="#">Vectors </a></li>
                                    <li><a href="#">Photoshop </a></li>
                                    <li><a href="#">Fonts </a></li>
                                </ul>
                            </li>
                            <li><a href="#">Consulting </a></li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-home"></i>系统管理 </a></li>
                    <li><a href="#"><i class="fa fa-suitcase"></i>Portfolio </a>
                        <ul class="submenu">
                            <li><a href="#">Web Design </a></li>
                            <li><a href="#">Graphics </a><span class="jquery-accordion-menu-label">10 </span>
                            </li>
                            <li><a href="#">Photoshop </a></li>
                            <li><a href="#">Programming </a></li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-user"></i>About </a></li>
                    <li><a href="#"><i class="fa fa-envelope"></i>Contact </a></li>

                </ul>
                <div class="jquery-accordion-menu-footer">
                    Footer
                </div>
            </div>
        </div>

        <div class="clearfix"></div>
    </div>


    <div class="contents flex-8">
        <div class="header-all">
            <div class="header flex-row">
                <div class="flex-7 path">
                    根目录
                </div>
                <div class="flex-3 search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="该目录下搜索...">
                              <span class="input-group-btn">
                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                              </span>
                    </div>
                </div>
            </div>
            <!--<div class="line"></div>-->
            <div class="file-body">
                <!--<input type="checkbox"/>-->

                <div class="first-floor flex-row">
                    <div class="flex-3">
                        <div>
                            <input type="checkbox"/>
                            <span class="filename">名称</span>
                        </div>
                    </div>
                    <div class="flex-3">
                        大小
                    </div>
                    <div class="flex-3">
                        创建者
                    </div>
                    <div class="flex-3">
                        更新日期
                    </div>

                </div>
                <div class="line"></div>

                <div class="body-floor flex-row">
                    <div class="flex-3 flex-row">
                        <div class="flex-1 checkbox"><input type="checkbox"/></div>
                        <div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>
                        <div class="file-name flex-4"><span><a href="#">一号分类</a></span></div>
                    </div>
                    <div class="flex-3 file-size">
                        <span>1.27MB</span>
                    </div>
                    <div class="flex-3 file-creator">
                        MR.Z
                    </div>
                    <div class="flex-3 file-time">
                        2013.12.06
                    </div>
                </div>
                <div class="line"></div>

                <div class="body-floor flex-row">
                    <div class="flex-3 flex-row">
                        <div class="flex-1 checkbox"><input type="checkbox"/></div>
                        <div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>
                        <div class="file-name flex-4"><span><a href="#">一号分类</a></span></div>
                    </div>
                    <div class="flex-3 file-size">
                        <span>1.27MB</span>
                    </div>
                    <div class="flex-3 file-creator">
                        MR.Z
                    </div>
                    <div class="flex-3 file-time">
                        2013.12.06
                    </div>
                </div>
                <div class="line"></div>

                <div class="body-floor flex-row">
                    <div class="flex-3 flex-row">
                        <div class="flex-1 checkbox"><input type="checkbox"/></div>
                        <div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>
                        <div class="file-name flex-4"><span><a href="#">一号分类</a></span></div>
                    </div>
                    <div class="flex-3 file-size">
                        <span>1.27MB</span>
                    </div>
                    <div class="flex-3 file-creator">
                        MR.Z
                    </div>
                    <div class="flex-3 file-time">
                        2013.12.06
                    </div>
                </div>
                <div class="line"></div>

                <div class="body-floor flex-row">
                    <div class="flex-3 flex-row">
                        <div class="flex-1 checkbox"><input type="checkbox"/></div>
                        <div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>
                        <div class="file-name flex-4"><span><a href="#">一号分类</a></span></div>
                    </div>
                    <div class="flex-3 file-size">
                        <span>1.27MB</span>
                    </div>
                    <div class="flex-3 file-creator">
                        MR.Z
                    </div>
                    <div class="flex-3 file-time">
                        2013.12.06
                    </div>
                </div>
                <div class="line"></div>

                <div class="body-floor flex-row">
                    <div class="flex-3 flex-row">
                        <div class="flex-1 checkbox"><input type="checkbox"/></div>
                        <div class="flex-1 file-image"><i class="fa fa-folder-o fa-3x"></i></div>
                        <div class="file-name flex-4"><span><a href="#">一号分类</a></span></div>
                    </div>
                    <div class="flex-3 file-size">
                        <span>1.27MB</span>
                    </div>
                    <div class="flex-3 file-creator">
                        MR.Z
                    </div>
                    <div class="flex-3 file-time">
                        2013.12.06
                    </div>
                </div>
                <div class="line"></div>
            </div>
        </div>
    </div>

    <!--<div class="clearfix"></div>-->
</div>



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


</body>
</html>