<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="flex-2">
  <div class="nav">
    <div id="jquery-accordion-menu" class="jquery-accordion-menu white">
      <div id="user-image">
        <a href=""><img src="${assetsPath}/images/user.jpg" alt="" class="img-circle"/></a>
        <div class="user-name">${username}</div>
      </div>

      <div class="jquery-accordion-menu-header" id="form"></div>                 <!--//里面的form是动态添加的-->
      <ul id="demo-list">

        <c:if test="${adminSidebarActive == 0}">
          <c:set var="active0" value="active"/>
        </c:if>

        <c:if test="${adminSidebarActive == 1}">
          <c:set var="active1" value="active"/>
        </c:if>

        <c:if test="${adminSidebarActive == 2}">
          <c:set var="active2" value="active"/>
        </c:if>

        <c:if test="${adminSidebarActive == 3}">
          <c:set var="active3" value="active"/>
        </c:if>

        <li class="${active0}"><a href="${rootPath}/admin/filecollect"><i class="fa fa-folder"></i>文件管理</a></li>

        <li class="${active1}"><a href="${rootPath}/admin/file/upload"><i class="fa fa-cloud-upload"></i>文件上传</a></li>

        <li class="${active3}"><a href="${rootPath}/admin/statistics/list"><i class="fa fa-suitcase"></i>统计分析</a></li>

        <li class="${active2}"><a href="${rootPath}/admin/file/rules"><i class="fa fa-suitcase"></i>规则设置</a></li>

      </ul>
    </div>
  </div>
  <div class="clearfix"></div>
</div>