package com.h13.cardgame.core.dao;

import com.h13.cardgame.cache.co.CardCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午11:44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CardDAO {

    @Autowired
    JdbcTemplate j;

    public List<CardCO> getAll() {
        String sql = "select * from card";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<CardCO>(CardCO.class));
    }

    public CardCO get(long cardId) {
        String sql = "select * from card where id=?";
        List<CardCO> list = j.query(sql, new Object[]{}, new BeanPropertyRowMapper<CardCO>(CardCO.class));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
