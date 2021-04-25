package com.itheima.dao;

import com.itheima.entity.Comment;

import java.util.List;

public class CommentDao extends BaseDao {
    /**
     * 根据articleId和commentContent内容关键字模糊查询评论
     * @param articleId 帖子编号
     * @param orderType 排序类型 升序: asc 降序: desc
     * @param commentContent 评论内容关键字模糊查询
     * @return 返回查询评论列表
     */
    public List<Comment> getCommentByArticleId(String articleId, String orderType, String commentContent) {
        String sql = "select * from bbs_comment where articleId = ? and commentContent like ? order by commentTime " + orderType;
        Object[] params = {articleId, commentContent};
        return this.findList(sql, Comment.class, params);
    }

    /**
     * 保存该帖子评论
     * @param articleId 帖子编号
     * @param commentContent 评论内容
     * @param userName 评论用户名
     */
    public void addComment(String articleId, String commentContent, String userName) {
        String sql = "insert into bbs_comment (articleId, commentContent, commentUserName) values (?, ?, ?)";
        Object[] params = {articleId, commentContent, userName};
        this.update(sql, params);
    }
}
