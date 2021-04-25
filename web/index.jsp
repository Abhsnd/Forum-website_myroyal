<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/common-new.css" />
    <link rel="stylesheet" href="css/index.css" />
    <link rel="stylesheet" href="css/search.css" />
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/hm-bbs.js"></script>
    <style>
        body {
            background: url(images/bg.jpg) no-repeat;
            background-position: center top;
            height: 100%;
        }
        .hm-bbs-info {
            position: relative;
        }
        .search-box {
            width: 400px;
            position: absolute;
            right:15px;
            top:30px;
        }
        .search-box .txt {
            width: 300px;
        }
        .hm-header {
            height: 350px;
        }
        .fixedBar {
            bottom:173px
        }
    </style>
</head>
<body>
<!-- header部分 -->
<jsp:include page="common/header.jsp" />

<!-- Banner部分 -->
<div class="hm-header">
</div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="hm-banner">
        </div>
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="images/bbs-icon.png" height="80" /></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">王者荣耀</h2>
                </div>
                <p>
                    <span>今日帖子<strong>${todayArticle}</strong></span> <span>全部帖子<strong>${totalArticle}</strong></span>
                </p>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/index.do?zoneId=${zoneId}" method="get">
                    <input type="text" class="txt l" name="title" placeholder="请输入关键字">
                    <input type="submit" value="搜索" class="btn l" />
                </form>
            </div>
        </div>
        <!-- 导航 -->
        <ul class="hm-bbs-nav border-lrb clearfix">
            <c:forEach items="${zones}" var="zone" >
                <li <c:if test="${zone.zoneId == zoneId}">class="current"</c:if>>
                    <a href="${pageContext.request.contextPath}/index.do?zoneId=${zone.zoneId}"><em></em>${zone.zoneName}</a>
                </li>
            </c:forEach>
        </ul>
        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->
            <div class="list-view l">
                <ul>
                    <c:forEach items="${articles}" var="article">
                        <li <c:if test="${article.isTop == 1}">class="clearfix ding"</c:if>>
                            <div class="hm-index-title">
                                <i class="set-to-top">顶</i> <a href="getArticle.do?articleId=${article.articleId}">${article.title}</a>
                            </div>
                            <div class="hm-index-con">${article.content}</div>
                            <div class="hm-index-info l">
                                <span class="article-username">${article.senderName}</span><span
                                    class="post-time">${article.sendTime}</span>
                            </div>
                            <div class="hm-index-fun r">
                                <span class="icon-like"><i></i>${article.upvoteCount}</span>
                                <span class="icon-talk"><i></i>${article.replyCount}</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- 右侧侧边栏 -->
            <div class="aside l">
                <div class="aside-box">
                    <h3 class="t">
                        <a ahref="javascript:;">在线用户(${onlineUsersSize}) </a>
                    </h3>
                    <ul class="b clearfix">
                        <c:forEach items="${onlineUsers}" var="user" >
                            <li>
                                <div>
                                    <img src="${user.picUrl != null ? user.picUrl : 'images/default.png'}" height="55" />
                                </div>
                                <p>${user.userName}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<jsp:include page="common/footer.jsp" />

<div class="fixedBar" id="j_fixedBar">
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
    <a href="#" class="goTop"><i></i><span>返回<br />顶部</span></a>
</div>

<!-- 发帖弹出框 -->
<form action="${pageContext.request.contextPath}/saveArticle.do?zoneId=${zoneId}" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">主题帖</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_t">
                    <input type="text" id="title" name="title" placeholder="帖子标题" />
                </div>
                <div class="win_bd_b">
                    <textarea id="content" name="content" placeholder="正文"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="发表"/>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
<script type="text/javascript">
    var w,h,className;
    function getSrceenWH(){
        w = $(window).width();
        h = $(window).height();
        $('#dialogBg').width(w).height(h);
    }

    window.onresize = function(){
        getSrceenWH();
    }
    $(window).resize();

    $(function(){
        getSrceenWH();

        //显示弹框
        $('.box #login').click(function(){
            className = $(this).attr('class');
            $('#dialogBg').fadeIn(300);
            $('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
            $('#userName').focus();
            $("#j_fixedBar").hide();
        });

        //关闭弹窗
        $('.closeDialogBtn').click(function(){
            $('#dialogBg').fadeOut(300,function(){
                $('#dialog').addClass('bounceOutUp').fadeOut();
                $("#j_fixedBar").show();
            });
        });
    });
</script>