package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.TroopCO;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TroopDAO {

    @Autowired
    JdbcTemplate j;
    @Autowired
    JdbcQueueTemplate q;

    public TroopCO getByCaptainId(final long cityId) {
        String sql = "select id,name,members,max_slot,attack_max,attack_min,defence_max,defence_min,cur_slot from troop where city_id=?";
        return j.queryForObject(sql, new Object[]{cityId}, new RowMapper<TroopCO>() {
            @Override
            public TroopCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                TroopCO o = new TroopCO();
                o.setCityId(cityId);
                o.setId(rs.getLong(1));
                o.setName(rs.getString(2));
                o.setMembers(JSON.parseObject(rs.getString(3), String[].class));
                o.setMaxSlot(rs.getInt(4));
                o.setAttackMax(rs.getInt(5));
                o.setAttackMin(rs.getInt(6));
                o.setDefenceMax(rs.getInt(7));
                o.setDefenceMin(rs.getInt(8));
                o.setCurSlot(rs.getInt(9));
                return o;
            }
        });
    }

    public void addMember(long cityId, String[] memberList) {
        String sql = "update troop set members= ?,cur_slot=cur_slot+1 where city_id=?";
        q.update(sql, new Object[]{JSON.toJSONString(memberList), cityId});
    }

    public void removeMember(long cityId, String[] memberList) {
        String sql = "update troop set members=?,cur_slot=cur_slot-1 where city_id=?";
        q.update(sql, new Object[]{JSON.toJSONString(memberList), cityId});
    }

    public void updateAttributes(long id, int attackMax, int attackMin, int defenceMax, int defenceMin) {
        String sql = "update troop set attack_max=?,attack_min=?,defence_max=?,defence_min=? where id=?";
        q.update(sql, new Object[]{attackMax, attackMin, defenceMax, defenceMin, id});

    }

    public TroopCO create(final TroopCO troop) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into troop( city_id,name,members,max_slot,cur_slot,attack_max,attack_min,defence_max,defence_min,create_time) " +
                " values (?,?,?,?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, troop.getCityId());
                pstmt.setString(2, troop.getName());
                pstmt.setString(3, JSON.toJSONString(troop.getMembers()));
                pstmt.setInt(4, troop.getMaxSlot());
                pstmt.setInt(5, troop.getCurSlot());
                pstmt.setInt(6, troop.getAttackMax());
                pstmt.setInt(7, troop.getAttackMin());
                pstmt.setInt(8, troop.getDefenceMax());
                pstmt.setInt(9, troop.getDefenceMin());
                return pstmt;
            }
        }, holder);
        troop.setId(holder.getKey().longValue());
        return troop;
    }
}
