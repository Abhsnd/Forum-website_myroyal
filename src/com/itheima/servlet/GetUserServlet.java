package com.itheima.servlet;

import com.itheima.dao.UserDao;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetUserServlet", urlPatterns = "/getUser.do")
public class GetUserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取当前用户名
        String userName = (String) request.getSession().getAttribute("loginUser");
        // 查询用户信息
        User user = userDao.getUserByName(userName);
        request.setAttribute("user", user);

        // 获取当前访问方式
        String method = request.getParameter("method");
        if ("userInfo".equals(method)) {
            request.getRequestDispatcher("/user_info.jsp").forward(request, response);
        } else if ("userPwd".equals(method)) {
            request.getRequestDispatcher("/user_pwd.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
