package com.itheima.entity;

/**
 * 点赞
 */
public class Upvote {

    private Integer upvoteArticleId; // 帖子编号
    private String upvoteUserName; // 点赞人
    private Integer isUpvote; // 点赞状态，0代表未点赞，1代表已点赞（默认）

    public Integer getUpvoteArticleId() {
        return upvoteArticleId;
    }

    public void setUpvoteArticleId(Integer upvoteArticleId) {
        this.upvoteArticleId = upvoteArticleId;
    }

    public String getUpvoteUserName() {
        return upvoteUserName;
    }

    public void setUpvoteUserName(String upvoteUserName) {
        this.upvoteUserName = upvoteUserName;
    }

    public Integer getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(Integer isUpvote) {
        this.isUpvote = isUpvote;
    }

    @Override
    public String toString() {
        return "Upvote{" +
                "upvoteArticleId=" + upvoteArticleId +
                ", upvoteUserName='" + upvoteUserName + '\'' +
                ", isUpvote=" + isUpvote +
                '}';
    }
}
