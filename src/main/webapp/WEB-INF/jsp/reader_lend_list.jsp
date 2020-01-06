<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>我的借还</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <script src="../../static/js/jquery-3.2.1.js"></script>
    <script src="../../static/js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('reader_header.html');
        })
    </script>
</head>
<body background="../../static/img/lizhi.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>

<div class="panel panel-default" style="width: 90%;margin-left: 5%;margin-top: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            我的借还日志
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>图书名</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="alog" varStatus="i">
                <tr>
                    <td>${alog.book.name}</td>
                    <td><fmt:formatDate value="${alog.lendDate}" dateStyle="medium" /></td>
                    <td><fmt:formatDate value="${alog.backDate}" dateStyle="medium" /></td>

                    <c:if test="${empty alog.backDate}">
                        <td>未还</td>
                    </c:if>
                    <c:if test="${!empty alog.backDate}">
                        <td>已还</td>
                    </c:if>
                    <c:if test="">
                        <td>超期</td>
                    </c:if>
                </tr>

            </c:forEach>
            </tbody>
        </table>

        <div class="panel-footer" style="background-color: #fff ">
            <div class="pull-left" style="float: left">
                <div class="form-group form-inline">
                    <span>当前第${pageInfo.pageNum}/${pageInfo.pages} 页，共${pageInfo.total}条数据</span>
                </div>
            </div>

            <nav style="text-align: center">
                <ul class="pagination" style="margin-right: 20%" >
                    <li>
                        <a href="${pageContext.request.contextPath}/mylend.html?page=1&size=5" aria-label="Previous">首页</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/mylend.html?page=${pageInfo.pageNum-1}&size=5">上一页</a></li>
                    <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                        <li class="page"><a href="${pageContext.request.contextPath}/mylend.html?page=${pageNum}&size=5">${pageNum}</a></li>
                    </c:forEach>

                    <li><a href="${pageContext.request.contextPath}/mylend.html?page=${pageInfo.pageNum+1}&size=5">下一页</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/mylend.html?page=${pageInfo.pages}&size=5" aria-label="Next">尾页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    </div>
</div>
<script>
    if (${pageInfo.pageNum}==${pageInfo.pages}){
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pageInfo.pageNum} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
</script>
</body>
</html>
