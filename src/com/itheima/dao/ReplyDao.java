package com.itheima.dao;

import com.itheima.entity.Reply;

import java.util.List;

public class ReplyDao extends BaseDao {
    /**
     * 根据评论id查询回复，按升序排序(从新到旧)
     * @param commentId 评论id
     * @return 返回查询结果
     */
    public List<Reply> getReplyByCommentId(String commentId) {
        String sql = "select * from bbs_reply where commentId = ? order by replyTime desc";
        Object[] params = {commentId};
        return this.findList(sql, Reply.class, params);
    }

    /**
     * 保存该评论回复
     * @param commentId 评论编号
     * @param replyContent 回复内容
     * @param userName 回复用户名
     */
    public void addComment(String commentId, String replyContent, String userName) {
        String sql = "insert into bbs_reply (commentId, replyContent, replyUserName) values (?, ?, ?)";
        Object[] params = {commentId, replyContent, userName};
        this.update(sql, params);
    }
}
