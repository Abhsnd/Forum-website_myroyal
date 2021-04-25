<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>黑马程序员论坛首页</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/common-new.css" />
    <link rel="stylesheet" href="css/user_info.css" />
    <link rel="stylesheet" href="css/search.css" />
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/hm-bbs.js"></script>
    <style type="text/css">
        .hm-header-b {
            border-bottom: 1px solid #d9d9d9;
        }
    </style>
</head>
<body>
<!-- 头部 -->
<jsp:include page="common/header.jsp" />

<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;">
                    <img src="images/logo.png" height="64" width="168" alt="" />
                </a>
            </h1>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l" />
                </form>
            </div>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="${pageContext.request.contextPath}/index.do">首页</a><span>></span>个人信息
        </div>
    </div>
</div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="user-info clearfix">
            <div class="user-info-t" style="height:20px;"></div>
            <div class="user-info-l l">
                <div class="user-info-l-t">
                    <img src="${user.picUrl != null ? user.picUrl : 'images/default.png'}" />
                    <div class="username">${user.userName}</div>
                </div>
                <ul class="user-info-l-b">
                    <li class="cur"><i class="info-icon"></i>我的资料</li>
                    <li><i class="safe-icon"></i>修改密码</li>
                </ul>
            </div>
            <div class="user-info-r r">
                <ul class="clearfix hd">
                    <li class="cur"><a href="${pageContext.request.contextPath}/getUser.do?method=userInfo">个人信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/getUser.do?method=userPwd">修改密码</a></li>
                </ul>
                <form action="${pageContext.request.contextPath}/saveUserInfo.do" method="post" enctype="multipart/form-data">
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l"><i class="red">*</i>用户名：</div>
                            <div class="info-r"><input type="text" class="txt" value="${user.userName}" readonly="readonly"/></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l">邮箱地址：</div>
                            <div class="info-r"><input type="text" name="email" class="txt" value="${user.email}"/></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l">上传头像：</div>
                            <div class="info-r"><input type="file" name="picUrl" class="file-btn"/></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                                <input type="submit" class="btn" value="保存" />
                                <span style="color:red;">${message}</span>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<jsp:include page="common/footer.jsp" />

</body>
</html>