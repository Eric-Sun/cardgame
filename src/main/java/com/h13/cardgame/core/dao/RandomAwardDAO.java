package com.h13.cardgame.core.dao;

import com.h13.cardgame.cache.co.RandomAwardCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 下午4:47
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class RandomAwardDAO {

    @Autowired
    JdbcTemplate j;


    public List<RandomAwardCO> getAll() {
        String sql = "select * from random_award";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<RandomAwardCO>(RandomAwardCO.class));
    }

    public RandomAwardCO get(long id) {
        String sql = "select * from random_award where id=?";
        List<RandomAwardCO> list = j.query(sql, new Object[]{id}, new BeanPropertyRowMapper<RandomAwardCO>(RandomAwardCO.class));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
