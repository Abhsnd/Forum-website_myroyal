package com.itheima.servlet;

import com.itheima.dao.UserDao;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "SaveUserInfoServlet", urlPatterns = "/saveUserInfo.do")
@MultipartConfig    //  解析上传文件
public class SaveUserInfoServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取参数
        String email = request.getParameter("email");
        Part part = request.getPart("picUrl");
        String picUrl = null;
        // 获取当前用户名
        String userName = (String) request.getSession().getAttribute("loginUser");
        // 获取当前的用户信息
        User user = userDao.getUserByName(userName);
        picUrl = user.getPicUrl();
        // 获取上传文件对象的文件名
        String fileName = part.getSubmittedFileName();
        // 如果用户上传了新头像
        if (fileName != null && !"".equals(fileName)) {
            String uploadPath = request.getServletContext().getRealPath("/upload/images");
            part.write(uploadPath + "/" + fileName);
            picUrl = "upload/images/" + fileName;
        }
        // 更新用户信息
        userDao.updateUserInfo(email, picUrl, userName);
        // 查询最新的用户信息
        user = userDao.getUserByName(userName);
        request.setAttribute("user", user);
        // 将保存成功的提示信息放入request中
        request.setAttribute("message", "用户信息修改成功！");
        // 转发到use_rInfo.jsp页面
        request.getRequestDispatcher("/user_info.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
