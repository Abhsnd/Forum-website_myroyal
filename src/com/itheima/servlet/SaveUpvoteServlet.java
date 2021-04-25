package com.itheima.servlet;

import com.itheima.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveUpvoteServlet", urlPatterns = "/saveUpvote.do")
public class SaveUpvoteServlet extends HttpServlet {
    private ArticleDao articleDao = new ArticleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参选
        String articleId = request.getParameter("articleId");
        // 增加点赞数
        articleDao.addupvoteCountByArticleId(articleId);
        // 重定向会该帖子
        response.sendRedirect(request.getContextPath() + "/getArticle.do?articleId=" + articleId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
