<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 2022/2/8
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1&min=${requestScope.min}&max=${requestScope.max}">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&min=${requestScope.min}&max=${requestScope.max}" id="prev">上一页</a>
    </c:if>
    <c:choose>
        <%--当总页码小于等于5时,将所有页码遍历输出--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--当总页码大于5时--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--当当前页码为1~3时--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--当当前页码大于总页码-3是--%>
                <c:when test="${requestScope.page.pageNo > requestScope.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="item">
        <c:if test="${item==requestScope.page.pageNo}">
            【${item}】
        </c:if>
        <c:if test="${item != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${item}&min=${requestScope.min}&max=${requestScope.max}">${item}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&min=${requestScope.min}&max=${requestScope.max}" id="next">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&min=${requestScope.min}&max=${requestScope.max}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input type="number" max="${requestScope.page.pageTotal}" min="1" value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="skip_btn">
</div>
