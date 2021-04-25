<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>论坛管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="../assets/js/bui-min.js"></script>
</head>
<body>
<!-- 头部分 -->
<div class="header">
    <div class="dl-title">
        <img src="../assets/img/top.png"/>
    </div>
</div>

<!-- 内容部分 -->
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title">
                <s class="dl-inform-icon dl-up"></s>
            </div>
        </div>
        <!-- 上面导航栏 -->
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">管理菜单</div></li>
            <li style="float:right;width:150px;color:white;margin-top:10px;">
                <div>
                    登录帐号：<span class="dl-log-user">admin</span>
                    <a href="javascript:;" title="退出系统" class="dl-log-quit" onclick="location='/forum2/admin/logout'">[退出]</a>
                </div>
            </li>
        </ul>
    </div>
    <!-- 下面菜单导航 -->
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>

</body>
</html>
<script>
    BUI.use('common/main', function(){
        /** 定义导航菜单配置信息数组 */
        var config = [{id:'1', // 唯一标识符id
            homePage:'11', // 当前显示的菜单id
            menu:[{text:'系统管理', // 主菜单
                items:[
                    {id:'11',text:'用户管理',href:'admin_user_list.jsp'}
                ]}, {text:'业务管理', // 主菜单
                items:[
                    {id:'21',text:'帖子管理',href:'admin_article_list.jsp'}
                ]}]}];
        /** 显示导航菜单 */
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>