package com.h13.cardgame.jupiter.dao;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.co.CaptainTitleCO;
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
public class CaptainTitleDAO {

    @Autowired
    JdbcTemplate j;

    public List<CaptainTitleCO> getAll() {
        String sql = "select id,name,rate,exp from captain_title";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<CaptainTitleCO>(CaptainTitleCO.class));
    }


}



