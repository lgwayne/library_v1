<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>全部图书信息</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <script src="../../static/js/jquery-3.2.1.js"></script>
    <script src="../../static/js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="../../static/img/book1.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>

<div style="padding: 70px 550px 10px">
    <form   method="post" action="querybook.html?page=1&size=5" class="form-inline"  id="searchform">
        <div class="input-group">
           <input type="text" placeholder="输入图书名/作者名" class="form-control" id="search" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("请输入关键字");
                return false;
            }
        })
    </script>
</div>
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
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            全部图书
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>ISBN</th>
                <th>价格</th>
                <th>剩余数量</th>
                <th>详情</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.number}</td>
                <td><a href="admin_book_detail.html?bookId=<c:out value="${book.bookId}"></c:out>">
                    <button type="button" class="btn btn-success btn-xs">详情</button>
                </a></td>
                <td><a href="updatebook.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                <td><a href="deletebook.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>
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
                        <a href="${pageContext.request.contextPath}/admin_books.html?page=1&size=5" aria-label="Previous">首页</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/admin_books.html?page=${pageInfo.pageNum-1}&size=5">上一页</a></li>
                    <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                        <li class="page"><a href="${pageContext.request.contextPath}/admin_books.html?page=${pageNum}&size=5">${pageNum}</a></li>
                    </c:forEach>

                    <li><a href="${pageContext.request.contextPath}/admin_books.html?page=${pageInfo.pageNum+1}&size=5">下一页</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin_books.html?page=${pageInfo.pages}&size=5" aria-label="Next">尾页</a>
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
