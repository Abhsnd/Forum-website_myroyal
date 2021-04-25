<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 头部 -->
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l">
        </div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${loginUser == null}">
                    <a href="javascript:;" id="login" class="to-login">游客登录</a>
                    <a href="${pageContext.request.contextPath}/register.jsp">【新用户注册】</a>
                </c:if>
                <c:if test="${loginUser != null}">
                    欢迎<a href="${pageContext.request.contextPath}/getUser.do?method=userInfo" style="margin-right:0px;padding:0px 5px;color:blue;">&nbsp;${loginUser}&nbsp;</a>回来！
                    <a href="${pageContext.request.contextPath}/logout.do">【注销】</a>
                </c:if>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="${pageContext.request.contextPath}/images/ico.png" />
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action="${pageContext.request.contextPath}/login.do" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName"
                                           class="ipt" /></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt" /></li>
                            <li><input type="submit" value="登录" class="submitBtn" /></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>