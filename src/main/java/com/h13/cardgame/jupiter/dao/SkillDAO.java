package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.SkillCO;
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
 * Date: 13-7-25
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SkillDAO {

    @Autowired
    JdbcTemplate j;

    public List<SkillCO> getAll() {
        String sql = "select id,name,data from skill";
        return j.query(sql, new Object[]{}, new RowMapper<SkillCO>() {
            @Override
            public SkillCO mapRow(ResultSet rs, int i) throws SQLException {
                SkillCO skill = new SkillCO();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                skill.setData(JSON.parseObject(rs.getString(3), Map.class));
                return skill;
            }
        });
    }

    public SkillCO get(long id) {
        String sql = "select id,name,data from skill where id=?";
        return j.queryForObject(sql, new Object[]{id}, new RowMapper<SkillCO>() {
            @Override
            public SkillCO mapRow(ResultSet rs, int i) throws SQLException {
                SkillCO skill = new SkillCO();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                skill.setData(JSON.parseObject(rs.getString(3), Map.class));
                return skill;
            }
        });
    }

}
