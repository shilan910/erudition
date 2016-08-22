<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title></title>
</head>
<link rel="stylesheet" href="${assetsPath}/css/app.min.css"/>


<link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.1/skins/square/blue.css"/>
<link href="//vjs.zencdn.net/5.8/video-js.min.css" rel="stylesheet">
<!--<link rel="stylesheet" href="./css/square/blue.css"/>-->
<!--<link rel="stylesheet" href="//cdn.bootcss.com/iCheck/1.0.2/skins/flat/blue.css"/>-->

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${assetsPath}/js/jquery-accordion-menu.js"></script>
<script src="${assetsPath}/js/icheck.js"></script>
<script src="${assetsPath}/js/template-native.js"></script>

<!--hcharts-->
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="${assetsPath}/js/statistics.js"></script>

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
<!--<div class="popwin_tips">-->
<!--<div>这里是消息</div>-->
<!--</div>-->

<header>
  <div class="container-fluid">

    <div class="row">
      <div class="col-md-4 logo">
        Erudition
      </div>
      <div class="col-md-4 search">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="搜索内容...">
                              <span class="input-group-btn">
                                <button class="btn btn-default" type="button">搜索</button>
                              </span>
        </div><!-- /input-group -->
      </div>
      <div class="col-md-4 button-group">
        <span class="login"><a href="javascript:void(0)" onclick="openLogin()">登录</a></span>
        <span class="registe"><a href="javascript:void(0)" onclick="openRegister()">注册</a></span>
      </div>


      <div id="modal">
        <!--弹出式登录框-->
        <div class="modal-dialog" id="login">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" onclick="closeLogin()">x</button>
              <h1 class="text-center text-primary">登录</h1>
            </div>
            <div class="modal-body center-block">
              <form action="" class="form center-block">
                <div class="input-group">
                  <!--<label for="examInputEmail1">邮箱:</label>-->
                  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                  <input type="text" class="form-control input-lg" id="examInputEmail1" name="username"
                         placeholder="请输入您的用户名"/>
                </div>
                <div class="input-group">
                  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                  <input type="password" class="form-control input-lg" id="examInputPassword1" name="password"
                         placeholder="请输入您的密码"/>
                </div>

                <div class="form-group">
                  <input type="submit" class="btn btn-primary btn-lg btn-block" value="登录" id="login-in">
                  <span><a href="javascript:void(0)" style="text-align: left">找回密码</a></span>
                  <span><a href="javascript:void(0)" class="pull-right re-register">注册</a></span>
                </div>
              </form>
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
        <!--注册框-->
        <div class="modal-dialog" id="register">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" onclick="closeRegister()">x</button>
              <h1 class="text-center text-primary">注册</h1>
            </div>
            <div class="modal-body center-block">
              <form action="" class="form center-block">
                <div class="input-group">
                  <!--<label for="examInputEmail1">邮箱:</label>-->
                  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                  <input type="email" class="form-control input-lg" id="username"
                         placeholder="请输入您的用户名"/>
                </div>
                <div class="input-group">
                  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                  <input type="password" class="form-control input-lg" id="password1"
                         placeholder="请输入您的密码"/>
                </div>
                <div class="input-group">
                  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                  <input type="password" class="form-control input-lg" id="password2"
                         placeholder="确认密码"/>
                </div>
                <div class="input-group">
                  <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                  <input type="password" class="form-control input-lg" id="password2"
                         placeholder="邮箱"/>
                </div>

                <div class="form-group">
                  <button class="btn btn-primary btn-lg btn-block">注册</button>
                  <span><a href="javascript:void(0)" style="text-align: left">找回密码</a></span>
                  <span><a href="javascript:void(0)" class="pull-right re-login">登录</a></span>
                </div>
              </form>
            </div>
            <div class="modal-footer">

            </div>
          </div>
        </div>
        <!--遮罩层-->
        <div class="modal-dialog-mask"></div>
      </div>


    </div>
  </div>

</header>

<div class="main flex-row">
  <div class="flex-2">
    <div class="nav">
      <div id="jquery-accordion-menu" class="jquery-accordion-menu white">
        <div id="user-image">
          <a href=""><img src="${assetsPath}/images/user.jpg" alt="" class="img-circle"/></a>
          <div class="user-name">当前用户</div>
        </div>

        <div class="jquery-accordion-menu-header" id="form"></div>                 <!--//里面的form是动态添加的-->
        <ul id="demo-list" class="nav-list">

          <li><a href="#"><i class="fa fa-home"></i>主页 </a></li>
          <li><a href="#"><i class="fa fa-glass"></i>共享目录 </a></li>
          <li><a href="#"><i class="fa fa-file-image-o"></i>个人收藏 </a><span class="jquery-accordion-menu-label">
                12 </span></li>
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
          <li class="active"><a href="#"><i class="fa fa-home"></i>统计分析 </a></li>

        </ul>
        <div class="jquery-accordion-menu-footer">
          Footer
        </div>
      </div>
    </div>

    <div class="clearfix"></div>
  </div>


  <div class="contents flex-8" id="statistics">
    <div class="header-all">
      <div class="header flex-row">
        <!--<div class="flex-7 path">-->
        <!--点击量/收藏量-->
        <!--</div>-->
        <ul class="list-inline">
          <li class="header-list active" id="hit_getwin">点击量</li>
          <li class="header-list" id="collect_getwin">收藏量</li>
        </ul>
      </div>
      <div class="button-group">
        <div class="clearfix"></div>
        <button class="dayB zyh-button-blue pull-left">按日查看</button>
        <button class="weekB zyh-button-blue pull-left">按周查看</button>
        <button class="monthB zyh-button-blue pull-left">按月查看</button>
      </div>
      <div class="allGraph">
        <div id="Graph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="Tcollectsta">
  <div class="header flex-row">
    <ul class="list-inline">
      <li class="header-list" id="hit_getwin">点击量</li>
      <li class="header-list" id="collect_getwin">收藏量</li>
    </ul>
  </div>
  <div class="button-group">
    <div class="clearfix"></div>
    <button class="dayB zyh-button-blue pull-left">按日查看</button>
    <button class="weekB zyh-button-blue pull-left">按周查看</button>
    <button class="monthB zyh-button-blue pull-left">按月查看</button>
  </div>
  <div class="allGraph">
    <div id="collectGraph-day" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
  </div>
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
