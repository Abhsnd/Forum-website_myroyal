<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户登录</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-responsive.min.css" />
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            height:450px;
            background-color: #333366;
            background-image: url('../assets/img/background.png');
            background-position: center;
            background-repeat: no-repeat;
        }
        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>
</head>
<body>
<div class="form-signin" style="text-align:center;">
    <h3 class="form-signin-heading">王者荣耀论坛管理系统</h3>
    <form action="/admin/login" method="post">
        <input type="text" name="userName" class="input-block-level" placeholder="请输入账号">
        <input type="password" name="userPass" class="input-block-level" placeholder="请输入密码">
        <button class="btn btn-large btn-primary" id="submitBtn" type="submit"
                onclick="location='admin_index.jsp'"  style="width:250px;">登录</button><br/>
        <span style="color:red;"></span>
    </form>
</div>
</body>
</html>