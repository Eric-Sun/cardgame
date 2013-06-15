package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.jupiter.JdbcQueueTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
public class StorageDAO {

    @Autowired
    JdbcTemplate j;
    @Autowired
    JdbcQueueTemplate q;

    public List<StorageCO> getAll() {
        String sql = "select * from storage";
        return j.query(sql, new Object[]{}, new RowMapper<StorageCO>() {
            @Override
            public StorageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                StorageCO p = new StorageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }

    public StorageCO get(long packageId) {
        String sql = "select * from storage where id=?";
        return j.queryForObject(sql, new Object[]{packageId}, new RowMapper<StorageCO>() {
            @Override
            public StorageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                StorageCO p = new StorageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }

    public StorageCO getByCid(long cid) {
        String sql = "select * from storage where city_id=?";
        return j.queryForObject(sql, new Object[]{cid}, new RowMapper<StorageCO>() {
            @Override
            public StorageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                StorageCO p = new StorageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setMax(rs.getInt(3));
                p.setCurrent(rs.getInt(4));
                p.setCardData(JSON.parseObject(rs.getString(5), Map.class));
                return p;
            }
        });
    }

    public void updateCardData(long cid, int current, String cardData) {
        String sql = "update storage set card_data=?,current=? where city_id=?";
        q.update(sql, new Object[]{cardData, current, cid});
    }


    /**
     * 为一个city创建一个仓库
     */
    public StorageCO create(final StorageCO storageCO) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into storage(city_id,max,current,card_data,create_time) values (?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, storageCO.getCityId());
                pstmt.setInt(2, storageCO.getMax());
                pstmt.setInt(3, storageCO.getCurrent());
                pstmt.setString(4, JSON.toJSONString(storageCO.getCardData()));
                return pstmt;
            }
        }, holder);
        storageCO.setId(holder.getKey().longValue());
        return storageCO;
    }
}
