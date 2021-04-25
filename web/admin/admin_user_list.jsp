<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<body>
<form class="form-inline definewidth m20" action="" method="post">
    用户名：<input name="userName" class="abc input-default" value=""></input>&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>邮箱</th>
        <th>头像</th>
        <th>角色</th>
        <th>登录状态</th>
        <th>最后登录时间</th>
        <th>发言状态</th>
        <th style="text-align:center;">操作</th>
    </tr>
    </thead>
    <tr>
        <td>1</td>
        <td>admin</td>
        <td></td>
        <td></td>
        <td>管理员</td>
        <td>已登录</td>
        <td>2017-5-15 14:00:00</td>
        <td><font color="green">正常</font></td>
        <td style="text-align:center;">
            <a href="#1/11">屏蔽</a>
        </td>
    </tr>
    <tr>
        <td>2</td>
        <td>scott</td>
        <td>scott@itcast.cn</td>
        <td></td>
        <td>普通用户</td>
        <td>已登录</td>
        <td>2017-5-15 15:00:00</td>
        <td><font color="red">已屏蔽</font></td>
        <td style="text-align:center;">
            <a href="#1/11">解除</a>
        </td>
    </tr>
</table>
</body>
</html>