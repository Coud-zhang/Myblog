<%--
  Created by IntelliJ IDEA.
  User: zkq15
  Date: 2018/11/7
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <title>MyBlog</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding-top: 150px;
            font-size: 15px;
        }
    </style>
</head>
<body>
<div id="loadNav"></div>
<div id="photo" style="width: 100%;margin-top: -100px;">
    <img src="../img/山湖.jpeg" class="img-responsive">
</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $("#loadNav").load("nav.html");
</script>
</html>