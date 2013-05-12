package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.PackageCO;
import com.h13.cardgame.jupiter.JdbcQueueTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-14
 * Time: 上午1:18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PackageDAO {

    @Autowired
    JdbcTemplate j;
    @Autowired
    JdbcQueueTemplate q;

    public List<PackageCO> getAll() {
        String sql = "select * from package";
        return j.query(sql, new Object[]{}, new RowMapper<PackageCO>() {
            @Override
            public PackageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PackageCO p = new PackageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }

    public PackageCO get(long packageId) {
        String sql = "select * from package where id=?";
        return j.queryForObject(sql, new Object[]{packageId}, new RowMapper<PackageCO>() {
            @Override
            public PackageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PackageCO p = new PackageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }

    public PackageCO getByCid(long cid) {
        String sql = "select * from package where captain_id=?";
        return j.queryForObject(sql, new Object[]{cid}, new RowMapper<PackageCO>() {
            @Override
            public PackageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PackageCO p = new PackageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }
}
