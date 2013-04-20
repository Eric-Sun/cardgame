package com.h13.cardgame.core.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.SquardCO;
import com.h13.cardgame.core.JdbcQueueTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
public class SquardDAO {

    @Autowired
    JdbcTemplate j;
    @Autowired
    JdbcQueueTemplate q;

    public SquardCO getByCaptainId(final long captainId) {
        String sql = "select id,name,members,max,attack_max,attack_min,defence_max,defence_min from squard where captain_id=?";
        return j.queryForObject(sql, new Object[]{captainId}, new RowMapper<SquardCO>() {
            @Override
            public SquardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                SquardCO o = new SquardCO();
                o.setCaptainId(captainId);
                o.setId(rs.getLong(1));
                o.setName(rs.getString(2));
                o.setMembers(JSON.parseObject(rs.getString(3), List.class));
                o.setMax(rs.getInt(4));
                o.setAttackMax(rs.getInt(5));
                o.setAttackMin(rs.getInt(6));
                o.setDefenceMax(rs.getInt(7));
                o.setDefenceMin(rs.getInt(8));
                return o;
            }
        });
    }

    public void addMember(long ccId, String memberList) {
        String sql = "update squard set members= ? where captain_id=?";
        q.update(sql, new Object[]{ccId, JSON.toJSONString(memberList)});
    }

    public void updateAttributes(long id, int attackMax, int attackMin, int defenceMax, int defenceMin) {
        String sql = "update squard set attack_max=?,attack_min=?,defence_max=?,defence_min=? where id=?";
        q.update(sql, new Object[]{attackMax, attackMin, defenceMax, defenceMin, id});

    }
}
