package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityTaskStatusCO;
import com.h13.cardgame.jupiter.JdbcQueueTemplate;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
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
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CityDAO {

    @Autowired
    JdbcTemplate j;

    @Autowired
    JdbcQueueTemplate q;

    public CityCO get(long cid) throws UserNotExistsException {
        String sql = "select id,user_id,name,level,exp,energy,gold,silver,task_status,cooldown_status from city where id=?";
        List<CityCO> list = j.query(sql, new Object[]{cid}, new RowMapper<CityCO>() {
            @Override
            public CityCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CityCO co = new CityCO();
                co.setId(rs.getLong(1));
                co.setUserId(rs.getLong(2));
                co.setName(rs.getString(3));
                co.setLevel(rs.getInt(4));
                co.setExp(rs.getInt(5));
                co.setEnergy(rs.getInt(6));
                co.setGold(rs.getInt(7));
                co.setSilver(rs.getInt(8));
                co.setTaskStatus(JSON.parseObject(rs.getString(9), CityTaskStatusCO.class));
                co.setCooldownStatus(JSON.parseObject(rs.getString(10), Map.class));
                return co;
            }
        });
        if (list.size() == 0)
            throw new UserNotExistsException("captainId=" + cid);
        else
            return list.get(0);
    }


    public void updateEnergy(long cid, int energy) {
        String sql = "update city set energy=? where id=?";
        q.update(sql, new Object[]{energy, cid});
    }

    public long create(final CityCO city) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into city (user_id,name,level,energy,gold,silver," +
                "task_status,create_time,exp,cooldown_status)" +
                " values (?,?,?,?,?,?,?,now(),?,?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, city.getUserId());
                pstmt.setString(2, city.getName());
                pstmt.setInt(3, city.getLevel());
                pstmt.setInt(4, city.getEnergy());
                pstmt.setInt(5, city.getGold());
                pstmt.setInt(6, city.getSilver());
                pstmt.setString(7, JSON.toJSONString(city.getTaskStatus()));
                pstmt.setInt(8, city.getExp());
                pstmt.setString(9, JSON.toJSONString(city.getCooldownStatus()));
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public void updateReward(long id, int exp, int silver) {
        String sql = "update city set exp=exp+?, silver=silver+? where id=?";
        q.update(sql, new Object[]{exp, silver, id});
    }

    public List<Long> searchAttackTarget(long cid, int fromLevel, int toLevel, int pageNum, int pageSize) {
        String sql = "select id from city where level between ? and ? and id<>? limit " + (pageNum - 1) * pageSize + "," + pageSize;
        return j.queryForList(sql, new Object[]{fromLevel, toLevel, cid}, Long.class);
    }

    public boolean checkCityExists(long uid) {
        String sql = "select count(1) from city where user_id=?";
        int count = j.queryForInt(sql, new Object[]{uid});
        if (count == 0)
            return false;
        else
            return true;
    }

    public void updateSilver(CityCO city) {
        String sql = "update city set silver=? where id=?";
        q.update(sql, new Object[]{city.getSilver(), city.getId()});
    }

    public void updateCooldownStatus(long id, String data) {
        String sql = "update city set cooldown_status=? where id=?";
        q.update(sql, new Object[]{data,id });
    }
}
