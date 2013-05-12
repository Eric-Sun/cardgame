package com.h13.cardgame.jupiter.dao;

import com.h13.cardgame.cache.co.ConfigCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ConfigDAO {

    @Autowired
    JdbcTemplate j;

    public List<ConfigCO> loadAll() {
        String sql = "select id,`key`,`value` from config";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<ConfigCO>(ConfigCO.class));

    }


    public ConfigCO get(String confKey) {
        String sql = "select id,`key`,`value` from config where key=?";
        List<ConfigCO> list = j.query(sql, new Object[]{confKey}, new BeanPropertyRowMapper<ConfigCO>(ConfigCO.class));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
