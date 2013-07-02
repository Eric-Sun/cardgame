package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return j.query(sql, new Object[]{}, new RowMapper<CardCO>() {
            @Override
            public CardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CardCO card = new CardCO();
                card.setId(rs.getLong(1));
                card.setName(rs.getString(2));
                card.setIcon(rs.getString(3));
                card.setCardType(CardType.valueOf(rs.getString(4)));
                card.setSpecData(JSON.parseObject(rs.getString(5), Map.class));
                card.setDesc(rs.getString(6));
                return card;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public CardCO get(long cardId) {
        String sql = "select * from card where id=?";
        List<CardCO> list = j.query(sql, new Object[]{}, new RowMapper<CardCO>() {
            @Override
            public CardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CardCO card = new CardCO();
                card.setId(rs.getLong(1));
                card.setName(rs.getString(2));
                card.setIcon(rs.getString(3));
                card.setCardType(CardType.valueOf(rs.getString(4)));
                card.setSpecData(JSON.parseObject(rs.getString(5), Map.class));
                card.setDesc(rs.getString(6));
                return card;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
