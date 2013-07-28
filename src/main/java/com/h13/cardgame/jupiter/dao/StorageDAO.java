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


    public StorageCO getByCid(long cid) {
        String sql = "select id,city_id,s_max,s_current,scard_data,e_max,e_current,ecard_data,captain_card_data,captain_current,captain_max" +
                " from storage where city_id=?";
        return j.queryForObject(sql, new Object[]{cid}, new RowMapper<StorageCO>() {
            @Override
            public StorageCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                StorageCO p = new StorageCO();
                p.setId(rs.getInt(1));
                p.setCityId(rs.getInt(2));
                p.setSMax(rs.getInt(3));
                p.setSCurrent(rs.getInt(4));
                p.setSCardData(JSON.parseObject(rs.getString(5), Map.class));
                p.setEMax(rs.getInt(6));
                p.setECurrent(rs.getInt(7));
                p.setECardData(JSON.parseObject(rs.getString(8), Map.class));
                p.setCaptainCardData(JSON.parseObject(rs.getString(9), Map.class));
                p.setCaptainCurrent(rs.getInt(10));
                p.setCaptainMax(rs.getInt(11));
                return p;
            }
        });
    }

    public void updateSCardData(long cid, int current, String cardData) {
        String sql = "update storage set scard_data=?,s_current=? where city_id=?";
        q.update(sql, new Object[]{cardData, current, cid});
    }

    public void updateCaptainCardData(long cid, int current, String cardData) {
        String sql = "update storage set captain_card_data=?,captain_current=? where city_id=?";
        q.update(sql, new Object[]{cardData, current, cid});
    }

    public void updateECardData(long cid, int current, String cardData) {
        String sql = "update storage set ecard_data=?,e_current=? where city_id=?";
        q.update(sql, new Object[]{cardData, current, cid});
    }


    /**
     * 为一个city创建一个仓库
     */
    public StorageCO create(final StorageCO storageCO) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into storage(city_id,s_max,s_current,scard_data,e_max,e_current,ecard_data,captain_card_data,captain_current,captain_max" +
                ",create_time) values (?,?,?,?,?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, storageCO.getCityId());
                pstmt.setInt(2, storageCO.getSMax());
                pstmt.setInt(3, storageCO.getSCurrent());
                pstmt.setString(4, JSON.toJSONString(storageCO.getSCardData()));
                pstmt.setInt(5, storageCO.getEMax());
                pstmt.setInt(6, storageCO.getECurrent());
                pstmt.setString(7, JSON.toJSONString(storageCO.getECardData()));
                pstmt.setString(8, JSON.toJSONString(storageCO.getCaptainCardData()));
                pstmt.setInt(9, storageCO.getCaptainCurrent());
                pstmt.setInt(10,storageCO.getCaptainMax());
                return pstmt;
            }
        }, holder);
        storageCO.setId(holder.getKey().longValue());
        return storageCO;
    }
}
