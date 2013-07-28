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
        String sql = "select id,level,exp,energy,e_storage_size,s_storage_size,troop_size," +
                "captain_storage_size from level";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<LevelCO>(
                LevelCO.class));
    }


    public LevelCO get(int level) {
        String sql = "select id,level,exp,energy,s_storage_size,e_storage_size,troop_size,captain_storage_size" +
                " from level where level=?";
        List<LevelCO> list = j.query(sql, new Object[]{level}, new BeanPropertyRowMapper<LevelCO>(
                LevelCO.class));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
