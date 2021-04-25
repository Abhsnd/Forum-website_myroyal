package com.itheima.servlet;

import com.itheima.dao.UserDao;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 获取参数
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");
        String email = request.getParameter("email");

        // 判断用户名是否为空
        if (userName == null || "".equals(userName)) {
            response.getWriter().write("<script>alert('用户名不能为空');history.back()</script>");
            return;
        }

        // 判断密码是否为空
        if (userPass == null || "".equals(userPass)) {
            response.getWriter().write("<script>alert('密码不能为空');history.back()</script>");
            return;
        }

        // 根据用户名查询用户
        User user = this.userDao.getUserByName(userName);
        // 判断用户是否存在
        if (user != null) {
            response.getWriter().write("<script>alert('该用户已存在');history.back()</script>");
            return;
        }

        // 保存用户
        this.userDao.addUser(userName, userPass, email);

        // 转发到success.jsp页面
        request.getRequestDispatcher("success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
