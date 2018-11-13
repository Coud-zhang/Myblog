<%--
  Created by IntelliJ IDEA.
  User: zkq15
  Date: 2018/11/7
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <title>MyBlog</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <script src="static/js/jquery-3.3.1.min.js"></script>
    <style>
        body{
            padding-top: 150px;
            font-size: 15px;
            font-weight: bold;
            background: url("static/img/山湖.jpeg");
            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            -moz-background-size: cover;
            -ms-background-size: cover;
        }
        a:link{color: black;text-decoration: none}
        .pagination{
            margin-top:50px;
            margin-left: 550px;
        }
    </style>
</head>
<body>
<div id="loadNav"></div>
<c:if test="${ not empty requestScope.bloglist}">
    <c:forEach items="${requestScope.bloglist.list}" var="blog">
        <div class="panel panel-default col-md-8 col-md-offset-2" style="opacity: 0.6;">
            <input type="text" style="display: none" value="${blog.id}">
            <div class="panel-body">
                    ${blog.title}
            </div>
            <div class="panel-footer">
                <div class="col-md-2">
                    <span class="glyphicon glyphicon-time"></span>  ${blog.data}
                </div>
                <div class="col-md-2 col-md-offset-1" >
                    <span class="glyphicon glyphicon-thumbs-up"></span> ${blog.zan}
                </div>
                <div class="col-md-2 col-md-offset-1">
                    <span class="glyphicon glyphicon-tags"></span>&nbsp;${blog.label}
                </div>
                <div class="col-md-2 col-md-offset-2">
                    <a class="btn btn-primary" type="submit" href="/getBlogByIdToView.action?id=${blog.id}">阅读更多</a>
                </div>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination"></ul>
    <script type="text/javascript">
        var setPaginator = function(pageCurr, pageSum, callback) {
            $('.pagination').bootstrapPaginator({ // 这个方法调用时，自动在.pagination添加分页li
                /*当前使用的是3版本的bootstrap*/
                bootstrapMajorVersion: 3,
                /*配置的字体大小是小号*/
                size: 'big',
                /*当前页*/
                currentPage: pageCurr,
                /*一共多少页*/
                totalPages: pageSum,
                /*页面上最多显示几个含数字的分页按钮*/
                numberOfPages: pageSum,
                /*设置显示的样式，默认是箭头	*/
                itemTexts: function(type, page, current) {
                    switch(type) {
                        case "first": return "首页";
                        case "prev": return "上一页";
                        case "next": return "下一页";
                        case "last": return "末页";
                        case "page": return page;
                    }
                },
                //添加监听事件
                //page是点击当前页数
                onPageClicked: function(event, originalEvent, type, page) {
                    window.location.href="/getBlogByPageToView.action?currentPage="+page;
                }
            });
        }

        $(document).ready(function () {
            setPaginator(${requestScope.bloglist.currentPage},${requestScope.bloglist.totalPage})
        })
        // var oDiv = document.getElementsByClassName("serachMore");
        // for (var i = 0; i < oDiv.length; i++) {
        //     (function(i) {
        //         oDiv[i].onclick = function() {
        //             var adiv=document.getElementsByClassName("panel")[i];
        //             readlog(adiv);
        //         }
        //     })(i)
        // }
        // function readlog(adiv) {
        //     var id=adiv.childNodes[1].value;
        //     var  readbutton=adiv.childNodes[5].childNodes[7].childNodes[1];
        //     $(readbutton).on("click",function () {
        //         $.ajax({
        //             url:"/getBlogByIdToView.action",
        //             type:"post",
        //             datatype:"json",
        //             data: {"id":id}
        //         })
        //     })
        // }
    </script>
</c:if>
<c:if test="${not empty requestScope.blog}">
    ${blog.id}
</c:if>
</body>
<script src="static/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="static/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="static/js/BlogView.js"></script>
<script type="text/javascript">
    $("#loadNav").load("nav.html");
</script>
</html>