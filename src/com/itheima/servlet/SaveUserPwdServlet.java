package com.itheima.servlet;

import com.itheima.dao.UserDao;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveUserPwdServlet", urlPatterns = "/saveUserPwd.do")
public class SaveUserPwdServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取参数
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        // 获取当前用户名
        String userName = (String) request.getSession().getAttribute("loginUser");
        // 根据用户名和旧密码查询用户
        User user = userDao.getUserByNameAndPass(userName, oldPassword);
        // 判断用户是否为空
        if (user != null) {
            userDao.updateUserPwd(userName, newPassword);
            request.setAttribute("message", "修改密码成功！");
        } else {
            request.setAttribute("message", "修改密码失败！");
        }
        User newUser = userDao.getUserByName(userName);
        request.setAttribute("user", newUser);
        // 转发到user_pwd.jsp页面
        request.getRequestDispatcher("/user_pwd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
