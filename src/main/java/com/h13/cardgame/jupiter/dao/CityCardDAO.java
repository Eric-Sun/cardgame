package com.h13.cardgame.jupiter.dao;

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
        String sql = "select * from city_card where id=?";
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
                cc.setAttackMin(rs.getInt(7));
                cc.setAttackMax(rs.getInt(8));
                cc.setDefenceMin(rs.getInt(9));
                cc.setDefenceMax(rs.getInt(10));
                cc.setBaseAttackMin(rs.getInt(11));
                cc.setBaseAttackMax(rs.getInt(12));
                cc.setBaseDefenceMin(rs.getInt(13));
                cc.setBaseDefenceMax(rs.getInt(14));
                cc.setMaxSlot(rs.getInt(15));
                cc.setStatus(rs.getInt(16));
                return cc;
            }
        });
    }


    public CityCardCO add(final CityCardCO card) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into city_card (city_id,name,icon,attack_max,attack_min," +
                "defence_max,defence_min,base_attack_max,base_attack_min,base_defence_max,base_defence_min,create_time,card_id,cur_slot," +
                "max_slot,card_type)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, card.getCityId());
                pstmt.setString(2, card.getName());
                pstmt.setString(3, card.getIcon());
                pstmt.setInt(4, card.getAttackMax());
                pstmt.setInt(5, card.getAttackMin());
                pstmt.setInt(6, card.getDefenceMax());
                pstmt.setInt(7, card.getDefenceMin());
                pstmt.setInt(8, card.getAttackMax());
                pstmt.setInt(9, card.getAttackMin());
                pstmt.setInt(10, card.getDefenceMax());
                pstmt.setInt(11, card.getDefenceMin());
                pstmt.setLong(12, card.getCardId());
                pstmt.setInt(13, card.getCurSlot());
                pstmt.setInt(14, card.getMaxSlot());
                pstmt.setString(15, card.getCardType().name());
                return pstmt;
            }
        }, holder);
        card.setId(holder.getKey().longValue());
        return card;
    }

    public void updateAttributes(CityCardCO cc) {
        String sql = "update city_card set attack_max=?,attack_min=?,defence_max=?,defence_min=?,cur_slot=? where id=? ";
        q.update(sql, new Object[]{cc.getAttackMax(), cc.getAttackMin(), cc.getDefenceMax(), cc.getDefenceMin(), cc.getCurSlot(), cc.getId()});
    }

    public void deleteCityCard(Long cityCardId) {
        String sql = "update city_card set status=-1 where id=?";
        q.update(sql, new Object[]{cityCardId});
    }
}
