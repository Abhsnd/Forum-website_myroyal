package com.itheima.servlet;

import com.itheima.dao.ArticleDao;
import com.itheima.dao.CommentDao;
import com.itheima.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveCommentServlet", urlPatterns = "/saveComment.do")
public class SaveCommentServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private CommentDao commentDao = new CommentDao();
    private ArticleDao articleDao = new ArticleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取参数
        String articleId = request.getParameter("articleId");
        String commentContent = request.getParameter("commentContent");
        // 检查评论框是否为空
        if (commentContent == null || "".equals(commentContent)) {
            response.getWriter().write("<script>alert('评论内容不能为空'); history.back()</script>");
            return;
        }
        // 获取当前用户名
        String userName = (String) request.getSession().getAttribute("loginUser");
        // 保存评论
        commentDao.addComment(articleId, commentContent, userName);
        // 增加帖子的评论数
        articleDao.addreplyCountByArticleId(articleId);
        // 重定向到该帖子
        response.sendRedirect(request.getContextPath() + "/getArticle.do?articleId=" + articleId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
