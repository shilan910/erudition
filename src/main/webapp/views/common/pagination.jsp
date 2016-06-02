<%--
  Created by IntelliJ IDEA.
  User: jeff
  Date: 16/3/1
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<nav>
    <ul class="pagination">
        <c:choose>
            <c:when test="${page.hasPre}"><li><a href="?page=${currentPage-1}">上一页</a></li></c:when>
            <c:otherwise><li class="disabled"><a href="#">上一页</a></li></c:otherwise>
        </c:choose>
        <c:if test="${currentPage != 1}" >
            <li><a href="?page=1">1</a></li>
        </c:if>

        <c:choose>
            <c:when test="${currentPage <= 5}">
                <c:forEach var="i" begin="2" end="${currentPage-1}">
                    <li><a href="?page=${i}">${i}</a></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li class="disabled"><a href="#">...</a></li>
                <c:forEach var="i" begin="${currentPage-2}"
                           end="${currentPage-1}">
                    <li><a href="?page=${i}">${i}</a></li>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <li class="active"><a href="?page=${currentPage}">${currentPage}</a></li>

        <c:choose>
            <c:when test="${currentPage >= page.totalPageCount - 4 || page.totalPageCount - 4 <= 0}">
                <c:forEach var="i" begin="${currentPage+1}" end="${page.totalPageCount}">
                    <li><a href="?page=${i}">${i}</a></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="${currentPage+1}" end="${currentPage+2}">
                    <li><a href="?page=${i}">${i}</a></li>
                </c:forEach>
                <li class="disabled"><a href="#">...</a></li>
                <li><a href="?page=${page.totalPageCount}">${page.totalPageCount}</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page.hasNext}"><li><a href="?page=${currentPage+1}">下一页</a></li></c:when>
            <c:otherwise><li class="disabled"><a href="#">下一页</a></li></c:otherwise>
        </c:choose>

    </ul>
</nav>

