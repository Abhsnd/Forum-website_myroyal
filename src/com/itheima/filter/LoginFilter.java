package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/getUser.do", "/saveArticle.do", "/saveComment.do", "/saveReply.do", "/saveUserInfo.do", "/saveUserPwd.do", "/admin/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
//        request.setCharacterEncoding("utf-8");
        String user = (String) request.getSession().getAttribute("loginUser");
        // 判断用户是否登录
        if (user == null || "".equals(user)) {  // 未登录
//            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('请您先登录'); history.back()</script>");
        } else {    // 已登录
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
