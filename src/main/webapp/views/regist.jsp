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
    <script src="//cdn.bootcss.com/jquery/2.2.0/jquery.js"></script>
    <script src="//cdn.bootcss.com/jqueryui/1.11.0/jquery-ui.js"></script>    <!--用于颜色动画，类加载动画-->
</head>
<body>
<div class="mask"></div>
<div class="wrap">
    <div class="login-bg">
        <div class="up"></div>
        <div class="down"></div>
    </div>
    <div id="login-box">
        <div class="login-header">
        </div>
        <div class="form-all">
            <form action="${rootPath}/user/regist" method="post">      <%--默认为get方法--%>
                <div class="user-group">
                    <div class="user-input">
                        <span>用户名:</span>
                        <input type="text"  placeholder="请输入用户名" name="username"/><i class="fa fa-user fa-2x"></i>

                        <div class="tip">${reusernmaemessage}</div>
                    </div>
                </div>
                
                <div class="password-group">
                    <div class="password-input">
                        <span>密码:</span>
                        <input type="password" placeholder="请输入密码" name="password"/><i class="fa fa-lock fa-2x"></i>
                        <div class="tip">${repasswordmessage}</div>
                    </div>
                </div>

                <div class="password-group">
                    <div class="password-input">
                        <span>确认密码:</span>
                        <input type="password" placeholder="请输入密码" name="password2"/><i class="fa fa-lock fa-2x"></i>
                        <div class="tip">${recodemessage}</div>
                    </div>
                </div>

                <%--<div class="codes-group">--%>
                    <%--<div class="codes-input">--%>
                        <%--<span>验证码</span>--%>
                        <%--<div class="clearfix"></div>--%>
                        <%--<input id="imgObj" type="text" name="codenum" placeholder="验证码"/>--%>
                        <%--<img src="code.html" alt="">--%>
                        <%--&lt;%&ndash;<img src="http://img.mukewang.com/545308540001678401500040.jpg" alt="">&ndash;%&gt;--%>
                        <%--<div class="clearfix"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="smbutton-group">
                    <div class="smbutton-input">
                        <div class="checked">
                            <input type="checkbox" checked/>
                            <span>记住密码</span>
                        </div>
                        <div class="forget">
                            <a href="${rootPath}/index">登录</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>
                <button class="btn btn-primary center-block" type="submit">注册</button>
            </form>
        </div>
        <div class="login-footer"></div>
    </div>

</div>


<script>

    var blue="#3498DB";
    var grey2="#bdc3c7";
    var toColor=function(obj,color,status){
        if(status){
            obj.addClass("input-focus",200);
        }else{
            obj.removeClass("input-focus",200);
        }
        obj.siblings("i").eq(0).animate({    //jquery对象，，，[0]为DOM对象
            color: color,
        },400)
    }
    //获得焦点
    $("input[type='text']").focus(function(){
        toColor($(this),blue,true);
    })
    //失去焦点
    $("input[type='text']").blur(function(){
        toColor($(this),grey2,false);
    })


    //用来更换图片
    function changeImg() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }
    //时间戳
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 17);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</body>
</html>