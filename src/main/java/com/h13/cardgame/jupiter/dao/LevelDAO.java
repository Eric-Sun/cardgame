package com.h13.cardgame.jupiter.dao;

import java.util.List;

import com.h13.cardgame.cache.co.LevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LevelDAO {
    @Autowired
    JdbcTemplate j;

    public List<LevelCO> getAllLevels() {
        String sql = "select id,level,exp,energy from level";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<LevelCO>(
                LevelCO.class));
    }


    public LevelCO get(int level) {
        String sql = "select id,level,exp,energy from level where level=?";
        List<LevelCO> list = j.query(sql, new Object[]{}, new BeanPropertyRowMapper<LevelCO>(
                LevelCO.class));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
