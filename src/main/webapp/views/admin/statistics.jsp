<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Erudition</title>
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

<jsp:include page="../common/header.jsp" />

<div class="main flex-row">
  <jsp:include page="../common/admin_sidebar.jsp" />

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
