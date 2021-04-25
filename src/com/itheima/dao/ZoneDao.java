package com.itheima.dao;

import com.itheima.entity.Zone;

import java.util.List;

/**
 * 查询所有分区
 */
public class ZoneDao extends BaseDao {
    public List<Zone> listZone() {
        String sql = "select * from bbs_zone order by isDef asc";
        return this.findList(sql, Zone.class);
    }
}
