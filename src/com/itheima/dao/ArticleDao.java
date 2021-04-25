package com.itheima.dao;

import com.itheima.entity.Article;

import java.util.List;

public class ArticleDao extends BaseDao {
    /**
     * 根据分区id和关键字查询帖子
     * @param zoneId 分区id
     * @param title 关键字
     * @return 返回查询帖子结果
     */
    public List<Article> getArticleByZoneId(String zoneId, String title) {
        String sql = "select * from bbs_article where zoneId = ? and title like ? order by isTop desc, sendTime desc, replyCount desc";
        Object[] params = {zoneId, title};
        return this.findList(sql, Article.class, params);
    }

    /**
     * 查询今日发帖数
     * @return 返回查询结果
     */
    public long getTodayArticle() {
        String sql = "select count(*) from bbs_article where to_days(sendTime) = to_days(now())";
        return this.count(sql);
    }

    /**
     * 查询全部帖子总数
     * @return 返回查询结果
     */
    public long getTotalArticle() {
        String sql = "select count(*) from bbs_article";
        return this.count(sql);
    }

    /**
     * 保存发表的帖子
     * @param title 标题
     * @param content 内容
     * @param zoneId 分区id
     * @param userName 发送者姓名
     */
    public void saveArticle(String title, String content, String zoneId, String userName) {
        String sql = "insert into bbs_article (title, content, zoneId, senderName) values (?, ?, ?, ?)";
        Object[] params = {title, content, zoneId, userName};
        this.update(sql, params);
    }

    /**
     * 根据articleId查询帖子
     * @param articleId 帖子id
     * @return 返回查询结果
     */
    public Article getArticleByarticleId(String articleId) {
        String sql = "select * from bbs_article where articleId = ?";
        Object[] params = {articleId};
        return (Article) this.findOne(sql, Article.class, params);
    }

    /**
     * 增加评论或回复数
     * @param articleId 帖子编号
     */
    public void addreplyCountByArticleId(String articleId) {
        String sql = "update bbs_article set replyCount = replyCount + 1 where articleId = ?";
        Object[] params = {articleId};
        this.update(sql, params);
    }

    /**
     * 增加浏览量
     * @param articleId 帖子编号
     */
    public void addbrowseCountByArticleId(String articleId) {
        String sql = "update bbs_article set browseCount = browseCount + 1 where articleId = ?";
        Object[] params = {articleId};
        this.update(sql, params);
    }

    /**
     * 增加点赞数
     * @param articleId 帖子编号
     */
    public void addupvoteCountByArticleId(String articleId) {
        String sql = "update bbs_article set upvoteCount = upvoteCount + 1 where articleId = ?";
        Object[] params = {articleId};
        this.update(sql, params);
    }
}
