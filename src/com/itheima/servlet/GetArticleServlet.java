package com.itheima.servlet;

import com.itheima.dao.ArticleDao;
import com.itheima.dao.CommentDao;
import com.itheima.dao.ReplyDao;
import com.itheima.dao.UserDao;
import com.itheima.entity.Article;
import com.itheima.entity.Comment;
import com.itheima.entity.Reply;
import com.itheima.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetArticleServlet", urlPatterns = "/getArticle.do")
public class GetArticleServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private ArticleDao articleDao = new ArticleDao();
    private CommentDao commentDao = new CommentDao();
    private ReplyDao replyDao = new ReplyDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取参数
        String articleId = request.getParameter("articleId");
        String orderType = request.getParameter("orderType");
        String commentContent = request.getParameter("commentContent");
        // 点击帖子增加一次浏览量
        articleDao.addbrowseCountByArticleId(articleId);
        // 判断orderType是否为空,默认升序
        if (orderType == null || "".equals(orderType)) {
            orderType = "asc";
        }
        // 根据articleId查询帖子
        Article article = articleDao.getArticleByarticleId(articleId);
        // 根据senderName查询用户
        User user = userDao.getUserByName(article.getSenderName());
        // 判断评论关键字是否为空
        if (commentContent == null || "".equals(commentContent)) {
            commentContent = "";
        }
        // 根据articleId和commentContent内容关键字模糊查询评论
        List<Comment> comments = commentDao.getCommentByArticleId(articleId, orderType, "%"+commentContent+"%");
        // 新建commentsMap，使用commentsMap封装评论信息、层主信息、楼中楼信息
        List<Map<String, Object>> commentsMap = new ArrayList<>();
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                // 根据评论人查询评论人用户信息
                User commentUser = userDao.getUserByName(comment.getCommentUserName());
                // 根据评论id查询回复信息
                List<Reply> replies = replyDao.getReplyByCommentId(comment.getCommentId() + "");
                // repliesMay封装评论人信息、回复信息
                List<Map<String, Object>> repliesMay = new ArrayList<>();
                if (replies != null) {
                    for (int j = 0; j < replies.size(); j++) {
                        Reply reply = replies.get(j);
                        // 根据回复人查询回复人用户信息
                        User replyUser = userDao.getUserByName(reply.getReplyUserName());
                        // 将回复信息、回复人信息封装到replyMap中，并添加到RepliesMap
                        Map<String, Object> replyMap = new HashMap<>();
                        replyMap.put("reply", reply);
                        replyMap.put("replyUser", replyUser);
                        repliesMay.add(replyMap);
                    }
                }
                // 将评论信息、层主信息、楼中楼信息(repliesMap)封装到commentMap中，并添加到commentsMap
                Map<String, Object> commentMap = new HashMap<>();
                commentMap.put("comment", comment);
                commentMap.put("commentUser", commentUser);
                commentMap.put("repliesMay", repliesMay);
                commentsMap.add(commentMap);
            }
        }
        // 将参数放进request中
        request.setAttribute("articleId", articleId);
        request.setAttribute("article", article);
        request.setAttribute("user", user);
        request.setAttribute("commentsMap", commentsMap);
        // 转发到 article_detail.jsp 页面
        request.getRequestDispatcher("/article_detail.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
