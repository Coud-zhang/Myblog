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
        a:link{color: white;text-decoration: none}
        .pagination{
            margin-top:50px;
            margin-left: 550px;
        }
        #myWechatAndAaLi{
            position: absolute;
            left: 530px;
            top: 260px;
        }
        #gift:hover #myWechatAndAaLi{
             display: block;
        }
    </style>
</head>
<body>
<div id="loadNav"></div>
<c:if test="${ not empty requestScope.bloglist}">
    <c:forEach items="${requestScope.bloglist.list}" var="blog">
        <div class="panel panel-default col-md-8 col-md-offset-2" style="opacity: 0.6;">
            <input type="text" style="display: none" value="${blog.id}">
            <div class="panel-body" >
                <p>${blog.title}</p>
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
                    <a class="btn btn-primary" type="submit" href="${pageContext.request.contextPath}/getBlogByIdToView.action?id=${blog.id}">阅读更多</a>
                </div>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination"></ul>
    <script type="text/javascript">
        var setPaginator = function(pageCurr, pageSum) {
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
                    window.location.href="${pageContext.request.contextPath}/getBlogByPageToView.action?currentPage="+page;
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
    <input type="text" style="display: none" value="${blog.zan}" id="zan">
    <%--面包屑导航--%>
    <div class="container" style="opacity: 0.8">
        <ul class="breadcrumb col-md-8 col-md-offset-2" style="opacity: 0.6">
            <li>当前位置:</li>
            <li><a href="${pageContext.request.contextPath}/toView.action" style="color: #985f0d">Home</a></li>
            <li>BlogDetial</li>
        </ul>
        <div class="panel panel-warning col-md-8 col-md-offset-2">
            <div class="panel-heading">
                <h3 class="panel-title">${blog.title}</h3>
            </div>
            <div class="panel-body">
                <p>${blog.article}</p>
                <div class="col-md-offset-4">
                    <button type="button" class="btn btn-info btn-lg" id="update_zan">
                        <span class="glyphicon glyphicon-heart-empty" id="glyphicon_updatezan"></span>点赞
                    </button>
                    <button type="button" class="btn btn-info btn-lg" id="gift">
                        <span class="glyphicon glyphicon-gift"></span>打赏
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div id="myWechatAndAaLi"style="width: 400px;display: none">
               <img src="static/img/支付宝.jpg" style="float: left;width: 200px;height: 260px;">
               <img src="static/img/微信.jpg" style="float: right;width: 200px;height: 260px;">
    </div>
    <ul class="pager" style="opacity: 0.6">
        <li><a href="" style="color: #009E94">前一篇</a></li>
        <li><a href="${pageContext.request.contextPath}/getBlogByPageToView.action?currentPage=1" style="color: #009E94">返回博客列表</a></li>
        <li><a href="" style="color: #009E94">后一篇</a></li>
    </ul>
    <script type="text/javascript">
        $(document).ready(function () {
            var zan=+document.getElementById("zan").value+1;
            $("#update_zan").on("click",function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/updateBlog.action",
                    data:{"id":${blog.id},"zan":zan},
                    type:"post",
                    datatype:"json",
                    success:function (data) {
                        if(data[0]=="true"){
                             console.log("点赞成功")
                            $("#glyphicon_updatezan").removeClass("glyphicon-heart-empty").addClass("glyphicon-heart")
                        }else{
                            console.log("点赞失败")
                        }
                    }
                })
            })
        })
    </script>
</c:if>
</body>
<script src="static/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="static/js/bootstrap-paginator.js"></script>
<script type="text/javascript">
    $("#loadNav").load("nav.html");
</script>
</html>