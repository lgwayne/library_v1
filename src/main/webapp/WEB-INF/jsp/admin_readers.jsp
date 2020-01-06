<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>全部读者</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <script src="../../static/js/jquery-3.2.1.js"></script>
    <script src="../../static/js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="../../static/img/u1.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<c:if test="${!empty info}">
    <script>alert("${info}");window.location.href="allreaders.html"</script>
</c:if>

<div style="position: relative;top: 15%">
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


<div class="panel panel-default" style="position:relative;top: 80px;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            全部读者
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover" >
            <thead>
            <tr>
                <th>读者号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>地址</th>
                <th>电话</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="reader">
                <tr>
                    <td>${reader.readerId}</td>
                    <td>${reader.name}</td>
                    <td>${reader.sex}</td>
                    <%--<td><c:out value="${reader.birth}"></c:out></td>--%>
                    <td><fmt:formatDate value="${reader.birth}" dateStyle="medium" /></td>
                    <td>${reader.address}</td>
                    <td>${reader.phone}</td>
                    <td><a href="reader_edit.html?readerId=${reader.readerId}"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                    <td><a href="reader_delete.html?readerId=${reader.readerId}"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>
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
                        <a href="${pageContext.request.contextPath}/allreaders.html?page=1&size=5" aria-label="Previous">首页</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/allreaders.html?page=${pageInfo.pageNum-1}&size=5">上一页</a></li>
                    <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                        <li class="page"><a href="${pageContext.request.contextPath}/allreaders.html?page=${pageNum}&size=5">${pageNum}</a></li>
                    </c:forEach>

                    <li><a href="${pageContext.request.contextPath}allreaders.html?page=${pageInfo.pageNum+1}&size=5">下一页</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/allreaders.html?page=${pageInfo.pages}&size=5" aria-label="Next">尾页</a>
                    </li>
                </ul>
            </nav>
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
