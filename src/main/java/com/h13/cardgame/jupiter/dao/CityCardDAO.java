package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.JdbcQueueTemplate;
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
import java.util.Map;

/**
 * cityCard 会保存在cache中
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午11:08
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CityCardDAO {

    @Autowired
    JdbcTemplate j;
    @Autowired
    JdbcQueueTemplate q;

    public CityCardCO get(long id) {
        String sql = "select id,city_id,card_id,name,card_type,icon,data,status" +
                "                 from city_card where id=?";
        return j.queryForObject(sql, new Object[]{id}, new RowMapper<CityCardCO>() {
            @Override
            public CityCardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CityCardCO cc = new CityCardCO();
                cc.setId(rs.getInt(1));
                cc.setCityId(rs.getInt(2));
                cc.setCardId(rs.getLong(3));
                cc.setName(rs.getString(4));
                cc.setCardType(CardType.valueOf(rs.getString(5)));
                cc.setIcon(rs.getString(6));
                cc.setData(JSON.parseObject(rs.getString(7), Map.class));
                cc.setStatus(rs.getInt(8));
                return cc;
            }
        });
    }


    public CityCardCO add(final CityCardCO card) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into city_card (city_id,name,icon,card_id,card_type,data,create_time)" +
                " values (?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, card.getCityId());
                pstmt.setString(2, card.getName());
                pstmt.setString(3, card.getIcon());
                pstmt.setLong(4, card.getCardId());
                pstmt.setString(5, card.getCardType().name());
                pstmt.setString(6, JSON.toJSONString(card.getData()));
                return pstmt;
            }
        }, holder);
        card.setId(holder.getKey().longValue());
        return card;
    }

    public void updateData(CityCardCO cityCard) {
        String sql = "update city_card set data=? where id=?";
        q.update(sql, new Object[]{JSON.toJSONString(cityCard.getData()), cityCard.getId()});
    }
}
