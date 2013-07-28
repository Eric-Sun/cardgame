package com.h13.cardgame.jupiter.dao;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-23
 * Time: 上午10:42
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CaptainLevelDAO {

    @Autowired
    JdbcTemplate j;

    public List<CaptainLevelCO> getAll() {
        String sql = "select id,name,attack_min_rate,attack_max_rate,defence_min_rate,defence_max_rate,exp from captain_level";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<CaptainLevelCO>(CaptainLevelCO.class));
    }


    public CaptainLevelCO get(long id) {
        String sql = "select id,name,attack_min_rate,attack_max_rate,defence_min_rate,defence_max_rate,exp from captain_level where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<CaptainLevelCO>(CaptainLevelCO.class));
    }

}



