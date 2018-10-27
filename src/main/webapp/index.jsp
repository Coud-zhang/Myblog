<%--
  Created by IntelliJ IDEA.
  User: zkq15
  Date: 2018/10/19
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MyBlog</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding-top: 150px;
        }
        #context{
            text-align: center;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">MyBlog</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav" id="navbar-ul">
                <li><a href="#">主页</a></li>
                <li><a href="#">分类</a></li>
                <li><a href="#">关于我</a></li>
            </ul>
            <form class="navbar-form navbar-right " role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
                <button type="submit" class="btn btn-success"><strong>搜索</strong></button>
            </form>
        </div>
    </div>
</nav>
<div class="container" id="context">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2 class="panel-title">博客标题</h2>
        </div>
        <div class="panel-body">
            博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容 博客内容
            博客内容 博客内容 博客内容 博客内容 博客内容 博客内容
        </div>
        <div class="panel-footer">
            博客注解
        </div>
    </div>
</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</html>
