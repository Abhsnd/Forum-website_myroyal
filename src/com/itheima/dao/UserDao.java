package com.itheima.dao;

import com.itheima.entity.User;

import java.util.List;

public class UserDao extends BaseDao {

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 返回查询结果
     */
    public User getUserByName(String userName) {
        String sql = "select * from bbs_user where userName = ?";
        Object[] params = {userName};
        User user = (User) this.findOne(sql, User.class, params);
        return user;
    }

    /**
     * 插入新用户
     * @param userName 用户名
     * @param userPass 密码
     * @param email 邮箱
     */
    public void addUser(String userName, String userPass, String email) {
        // SQL语句
        String sql = "insert into bbs_user(userName, userPass, email) values (?, ?, ?)";
        // 封装参数
        Object[] params = {userName, userPass, email};
        // 执行插入操作
        this.update(sql, params);
    }

    /**
     * 根据用户名和密码查询用户
     * @param userName 用户名
     * @param userPass 密码
     * @return
     */
    public User getUserByNameAndPass(String userName, String userPass) {
        String sql = "select * from bbs_user where userName = ? and userPass = ?";
        Object[] params = {userName, userPass};
        User user = (User) this.findOne(sql, User.class, params);
        return user;
    }

    /**
     * 更新登录状态
     * @param userName 用户名
     * @param status 登录状态
     */
    public void updateLoginStatus(String userName, int status) {
        String sql = "update bbs_user set loginStatus = ? where userName = ?";
        Object[] params = {status, userName};
        this.update(sql, params);
    }

    /**
     * 查询当前在线用户
     * @return 返回查询结果
     */
    public List<User> getOnlineUsers() {
        String sql = "select * from bbs_user where loginStatus = 1";
        return this.findList(sql, User.class);
    }

    /**
     * 修改用户信息
     * @param email 邮箱
     * @param priUrl 头像
     * @param userName 用户名
     */
    public void updateUserInfo(String email, String picUrl, String userName) {
        String sql = "update bbs_user set email = ?, picUrl = ? where userName = ?";
        Object[] params = {email, picUrl, userName};
        this.update(sql, params);
    }

    /**
     * 修改用户密码
     * @param userName 用户名
     * @param newPassword 新密码
     */
    public void updateUserPwd(String userName, String newPassword) {
        String sql = "update bbs_user set userPass = ? where userName = ?";
        Object[] params = {newPassword, userName};
        this.update(sql, params);
    }
}
