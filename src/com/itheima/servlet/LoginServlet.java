package com.itheima.servlet;

import com.itheima.dao.UserDao;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置输出编码
        response.setContentType("text/html;charset=utf-8");

        // 获取参数
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");

        // 根据用户名和密码查询用户
        User user = this.userDao.getUserByNameAndPass(userName, userPass);

        // 判断user是否为空
        if (user != null) {
            // 将用户名放入session中
            request.getSession().setAttribute("loginUser", user.getUserName());
            // 更新用户状态,1为登录
            this.userDao.updateLoginStatus(userName, 1);
            // 重定向到首页
            response.sendRedirect(request.getContextPath() + "/index.do");
        } else {
            response.getWriter().write("<script>alert('账号或密码错误，登陆失败');history.back()</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
