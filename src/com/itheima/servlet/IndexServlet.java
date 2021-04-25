package com.itheima.servlet;

import com.itheima.dao.ArticleDao;
import com.itheima.dao.UserDao;
import com.itheima.dao.ZoneDao;
import com.itheima.entity.Article;
import com.itheima.entity.User;
import com.itheima.entity.Zone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index.do")
public class IndexServlet extends HttpServlet {
    private ZoneDao zoneDao = new ZoneDao();
    private ArticleDao articleDao = new ArticleDao();
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 获取分区
        List<Zone> zones = zoneDao.listZone();
        // 获得要查询的分区zoneId
        String zoneId = request.getParameter("zoneId");
        // 获取搜索框关键字
        String title = request.getParameter("title");
        // 获取今天帖子总数
        long todayArticle = articleDao.getTodayArticle();
        // 获取全部帖子总数
        long totalArticle = articleDao.getTotalArticle();

        //判断zoneId是否为空，如果为空，获得分区中第一条记录的zoneId
        if (zoneId == null || "".equals(zoneId)) {
            zoneId = zones.get(0).getZoneId() + "";
        }

        // 判断关键字是否为空
        if (title == null || "".equals(title)) {
            title = ""; // 置空
        }
        // 根据zoneId查询帖子
        List<Article> articles = articleDao.getArticleByZoneId(zoneId, "%"+title+"%");

        // 查询当前在线用户
        List<User> onlineUsers = userDao.getOnlineUsers();
        // 获得在线用户总数
        long onlineUsersSize = onlineUsers.size();

        // 将查询到的帖子放到request中
        request.setAttribute("articles", articles);
        // 将参数放到request中
        request.setAttribute("zones", zones);
        // 将zoneId放到request中
        request.setAttribute("zoneId", zoneId);
        // 将todayArticle放到request中
        request.setAttribute("todayArticle", todayArticle);
        // 将totalArticle放到request中
        request.setAttribute("totalArticle", totalArticle);
        // 将onlineUsers放到request中
        request.setAttribute("onlineUsers", onlineUsers);
        // 将onlineUsersSize放到request中
        request.setAttribute("onlineUsersSize", onlineUsersSize);

        // 请求转发到首页
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
