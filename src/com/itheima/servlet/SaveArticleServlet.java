package com.itheima.servlet;

import com.itheima.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveArticleServlet", urlPatterns = "/saveArticle.do")
public class SaveArticleServlet extends HttpServlet {
    private ArticleDao articleDao = new ArticleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取参数
        String zoneId = request.getParameter("zoneId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String userName = (String) request.getSession().getAttribute("loginUser");

        if (userName == null) {
            response.getWriter().write("<script>alert('请您先登录后再发帖');history.back()</script>");
            return;
        } else if (title == null || "".equals(title)) {
            response.getWriter().write("<script>alert('帖子标题不能为空');history.back()</script>");
            return;
        }
        // 保存发表的帖子
        articleDao.saveArticle(title, content, zoneId, userName);
        // 重定向到index.jsp
        response.sendRedirect(request.getContextPath() + "/index.do?zoneId=" + zoneId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
