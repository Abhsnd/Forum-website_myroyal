package com.itheima.dao;

import com.itheima.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BaseDao {

    // 创建 QueryRunner 查询对象
    private QueryRunner query = new QueryRunner(DataSourceUtils.getDataSource());

    /**
     * 查询单行记录,封装成相应的javaBean对象，没有参数传递
     * @param sql  查询语句
     * @param clazz 要封装的javaBean对象的class
     * @return
     */
    public Object findOne(String sql, Class clazz){
        try {
            return this.query.query(sql, new BeanHandler(clazz));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询单行记录,封装成相应的javaBean对象，有参数传递
     * @param sql  查询语句
     * @param clazz 要封装的javaBean对象的class
     * @param params  封装的查询参数
     * @return
     */
    public Object findOne(String sql, Class clazz, Object[] params){
        try {
            return this.query.query(sql, new BeanHandler(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询多条记录，封装成相应的javaBean的list集合，没有参数传递
     * @param sql  查询语句
     * @param clazz 要封装的javaBean对象的class
     * @return
     */
    public List findList(String sql, Class clazz){
        try {
            return (List) this.query.query(sql, new BeanListHandler(clazz));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询多条记录，封装成相应的javaBean的list集合，有参数传递
     * @param sql  查询语句
     * @param clazz 要封装的javaBean对象的class
     * @param params  封装的查询参数
     * @return
     */
    public List findList(String sql, Class clazz, Object[] params){
        try {
            return (List) this.query.query(sql, new BeanListHandler(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加、修改、删除操作，没有参数传递
     * @param sql 相应的sql语句
     */
    public void update(String sql){
        try {
            this.query.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加、修改、删除操作，有参数传递
     * @param sql  相应的sql语句
     * @param params 封装的参数
     */
    public void update(String sql, Object[] params){
        try {
            this.query.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计记录数,没有参数传递
     * @param sql  相应的sql语句
     * @return
     */
    public long count(String sql){
        long count = 0;
        try {
            count = (long) this.query.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 统计记录数,有参数传递
     * @param sql  相应的sql语句
     * @param params 封装的参数
     * @return
     */
    public long count(String sql, Object[] params){
        long count = 0;
        try {
            count = (long) this.query.query(sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
