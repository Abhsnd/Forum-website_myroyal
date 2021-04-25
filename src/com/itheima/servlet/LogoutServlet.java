package com.itheima.servlet;

import com.itheima.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout.do")
public class LogoutServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 从session中获取用户名
        String username = (String) request.getSession().getAttribute("loginUser");
        // 修改登录状态，1登录，0未登录
        this.userDao.updateLoginStatus(username, 0);
        // 销毁session
        request.getSession().invalidate();
        // 重定向到index.jsp页面
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
