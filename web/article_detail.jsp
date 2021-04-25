<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>黑马程序员论坛详情页</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/common-new.css" />
    <link rel="stylesheet" type="text/css" href="css/index.css" />
    <link rel="stylesheet" type="text/css" href="css/search.css" />
    <link rel="stylesheet" type="text/css" href="css/detail.css" />
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
            background-color: none;
        }
        .search-box .txt {
            width: 300px;
            height: 40px;
        }
        .hm-header {
            height: 350px;
        }
        .hm-bbs-info-in h2 {
            height: 100px;
            line-height: 142px;
        }
        .hm-detail-fun {
            height: 100px;
            line-height: 142px;
            margin-top: 0px;
        }
        .new-to-old {
            margin:0;
        }
        .fixedBar {
            bottom:173px
        }
        .detail-page-box a, .detail-page-box span {
            border:none;
            background:none;
            padding: 0 2px;
            font-size: 15px;
            margin:0px;
        }
        .detail-page-box .new-to-old{
            border: 1px solid #d9d9d9;
        }
        .detail-page-box {
            border:none;
            padding: 9px 15px 9px 20px;
        }
    </style>
</head>
<body>

<!-- header部分 -->
<jsp:include page="common/header.jsp" />

<div class="hm-header">
    <%--
    <div class="hm-inner clearfix">
            <div class="hm-header-b">
                <i class="hm-ico-home"></i>
                <a href="${pageContext.request.contextPath}/index.do">首页</a><span>></span>${article.title}
            </div>
        </div>
        --%>
</div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <!-- 帖子标题， 点赞数， 回复数， 搜索 -->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="images/bbs-icon.png" height="80" /></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">${article.title}</h2>
                    <div class="hm-detail-fun l">
                        <span class="icon-like">
                            <a href="${pageContext.request.contextPath}/saveUpvote.do?articleId=${articleId}">
                                <i></i>${article.upvoteCount}
                            </a>
                        </span>
                        <span class="icon-talk">
                                <i></i>${article.replyCount}
                        </span>
                    </div>
                </div>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/getArticle.do?articleId=${articleId}" method="post">
                    <input type="text" name="commentContent" class="txt l" placeholder="请输入关键字">
                    <input type="submit" value="搜索" class="btn l" />
                </form>
            </div>

        </div>
        <div class="detail-page-box clearfix">
            <a href="${pageContext.request.contextPath}/index.do">
                <i class="hm-ico-home"></i>首页
            </a>
            <span> > </span>
            <a href="#">
                ${article.title}
            </a>
            <a class="new-to-old r" href="${pageContext.request.contextPath}/getArticle.do?articleId=${articleId}&orderType=desc" style="font-size:12px;float: right;">
                <i></i>从新到旧查看
            </a>
        </div>
        <div class="detail-box">
            <ul class="detail-floors">
                <!-- 原贴楼（楼主） -->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo">
                            <img src="${user.picUrl != null ? user.picUrl : 'images/default.png'}" />
                        </div>
                        <div class="floorer-name">${user.userName}</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">发帖时间：${article.sendTime}</div>
                            <div class="r">${article.browseCount}次查看</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>
                                    ${article.content}
                                </p>
                            </div>
                            <div class="floor-ans"></div>
                        </div>
                        <span class="icon-comment">
                            <a href="#comment"> <i ></i> 评论</a>
                        </span>
                    </div>
                </li>
                <!-- 评论部分,一楼及以后 -->
                <c:forEach items="${commentsMap}" var="commentMap" varStatus="status">
                    <li class="floor clearfix">
                        <div class="floorer-info l">
                            <div class="floorer-photo">
                                <img src="${commentMap.commentUser.picUrl != null ? commentMap.commentUser.picUrl : 'images/default.png'}" />
                            </div>
                            <div class="floorer-name">
                                ${commentMap.commentUser.userName}
                            </div>
                        </div>
                        <div class="floor-con l">
                            <div class="floor-info clearfix">
                                <div class="floor-time l">回贴时间：${commentMap.comment.commentTime}</div>
                                <div class="r">${status.count}楼</div>
                            </div>
                            <div class="floor-art-ans">
                                <div class="floor-art">
                                    <p>${commentMap.comment.commentContent}</p>
                                </div>
                                <div class="floor-ans">
                                    <ul>
                                        <!-- 回复部分 楼中楼 -->
                                        <c:forEach items="${commentMap.repliesMay}" var="replyMay" >
                                            <li class="clearfix">
                                            <div class="floor-ans-pho l">
                                                <img src="${replyMay.replyUser.picUrl != null ? replyMay.replyUser.picUrl : 'images/default.png'}"/>
                                            </div>
                                            <div class="floor-ans-con l">
                                                <span class="name">${replyMay.replyUser.userName}</span>：${replyMay.reply.replyContent}
                                                <span class="ans-time">${replyMay.reply.replyTime}</span>
                                            </div>
                                        </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(${status.count}, ${commentMap.comment.commentId})"> <i ></i> 回复</a>
                            </span>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="detail-to-comment">
            <div class="tit"><a name="comment">发表评论</a></div>
            <!-- 未登录时候显示<div class="con">您没有登录论坛，请登录后再进行回复</div>-->
            <c:if test="${loginUser == null}">
                <div class="con">您没有登录论坛，请登录后再进行回复</div>
            </c:if>
            <c:if test="${loginUser != null}">
                <!-- 登录后显示评论输入框-->
                <form action="${pageContext.request.contextPath}/saveComment.do?articleId=${articleId}" method="post">
                    <div class="con con-loged">
                        <div class="con-t">
                            <textarea id="content" name="commentContent" placeholder="请在此输入您要回复的信息"></textarea>
                        </div>
                        <div class="con-b">
                            <input type="submit" class="btn" />
                            <span class="num">不能超过5000字</span>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</div>

<!-- 底部 -->
<jsp:include page="common/footer.jsp" />

<!-- 回复弹出框 -->
<form action="${pageContext.request.contextPath}/saveReply.do?articleId=${articleId}" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">回复<span id="floorSpan"></span>楼</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <textarea id="replyContent" name="replyContent" placeholder="回复内容限于40字以内"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="回复"/>
                    <input type="hidden" id="commentId" name="commentId" />
                </div>
            </div>
        </div>
    </div>
</form>

<div class="fixedBar" id="j_fixedBar">
    <a href="#comment" class="newTopic"><span></span>评论</a>
    <a href="#" class="goTop"><i></i><span>返回<br />顶部</span></a>
</div>
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

    //弹出回复框
    function showDialog(num, commentId) {
        $('.pop-box').css('display','block');
        $("#floorSpan").html(num);
        $("#commentId").val(commentId);
    }
</script>