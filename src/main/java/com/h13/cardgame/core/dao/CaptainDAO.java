package com.h13.cardgame.core.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.CaptainTaskCO;
import com.h13.cardgame.core.JdbcQueueTemplate;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
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
 * Date: 13-3-15
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CaptainDAO {

    @Autowired
    JdbcTemplate j;

    @Autowired
    JdbcQueueTemplate q;

    public CaptainCO get(long cid) throws ParameterIllegalException {
        String sql = "select id,user_id,name,level,exp,energy,gold,silver,task_info from captain where id=?";
        List<CaptainCO> list = j.query(sql, new Object[]{cid}, new RowMapper<CaptainCO>() {
            @Override
            public CaptainCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CaptainCO co = new CaptainCO();
                co.setId(rs.getLong(1));
                co.setUserId(rs.getLong(2));
                co.setName(rs.getString(3));
                co.setLevel(rs.getInt(4));
                co.setExp(rs.getInt(5));
                co.setEnergy(rs.getInt(6));
                co.setGold(rs.getInt(7));
                co.setSilver(rs.getInt(8));
                co.setTaskInfo(JSON.parseObject(rs.getString(9), CaptainTaskCO.class));
                return co;
            }
        });
        if (list.size() == 0)
            throw new ParameterIllegalException("captainId=" + cid);
        else
            return list.get(0);
    }


    public void updateEnergy(long cid, int energy) {
        String sql = "update captain set energy=? where id=?";
        q.update(sql, new Object[]{energy, cid});
    }

    public long create(final CaptainCO captain) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into captain (user_id,name,level,energy,gold,silver,task_info,create_time,exp)" +
                " values (?,?,?,?,?,?,?,now(),?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, captain.getId());
                pstmt.setString(2, captain.getName());
                pstmt.setInt(3, captain.getLevel());
                pstmt.setInt(4, captain.getEnergy());
                pstmt.setInt(5, captain.getGold());
                pstmt.setInt(6, captain.getSilver());
                pstmt.setString(7, JSON.toJSONString(captain.getTaskInfo()));
                pstmt.setInt(8, captain.getExp());
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void addTaskResult(long id, int exp, int gold, int silver) {
        String sql = "update captain set exp=exp+?,gold=gold+?,silver=silver+? where id=?";
        q.update(sql, new Object[]{exp, gold, silver, id});
    }
}
