package com.h13.cardgame.jupiter.dao;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.CardCO;
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
 * Created with IntelliJ IDEA.
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

    public List<CityCardCO> getAll() {
        String sql = "select * from captain_card ";
        return j.query(sql, new Object[]{}, new RowMapper<CityCardCO>() {
            @Override
            public CityCardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CityCardCO cc = new CityCardCO();
                cc.setId(rs.getInt(1));
                cc.setCardId(rs.getLong(2));
                cc.setName(rs.getString(3));
                cc.setImg(rs.getString(4));
                cc.setLevel(rs.getInt(5));
                cc.setAttackMin(rs.getInt(6));
                cc.setAttackMax(rs.getInt(7));
                cc.setDefenceMin(rs.getInt(8));
                cc.setDefenceMax(rs.getInt(9));
                return cc;
            }
        });
    }


    public CityCardCO get(long id) {
        String sql = "select * from captain_card where id=?";
        return j.queryForObject(sql, new Object[]{id}, new RowMapper<CityCardCO>() {
            @Override
            public CityCardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CityCardCO cc = new CityCardCO();
                cc.setId(rs.getInt(1));
                cc.setCardId(rs.getLong(2));
                cc.setName(rs.getString(3));
                cc.setImg(rs.getString(4));
                cc.setLevel(rs.getInt(5));
                cc.setAttackMin(rs.getInt(6));
                cc.setAttackMax(rs.getInt(7));
                cc.setDefenceMin(rs.getInt(8));
                cc.setDefenceMax(rs.getInt(9));
                return cc;
            }
        });
    }

    public List<CityCardCO> getByCaptainId(long captainId) {
        String sql = "select * from captain_card where captain_id=?";
        return j.query(sql, new Object[]{captainId}, new RowMapper<CityCardCO>() {
            @Override
            public CityCardCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CityCardCO cc = new CityCardCO();
                cc.setId(rs.getInt(1));
                cc.setCardId(rs.getLong(2));
                cc.setName(rs.getString(3));
                cc.setImg(rs.getString(4));
                cc.setLevel(rs.getInt(5));
                cc.setAttackMin(rs.getInt(6));
                cc.setAttackMax(rs.getInt(7));
                cc.setDefenceMin(rs.getInt(8));
                cc.setDefenceMax(rs.getInt(9));
                return cc;
            }
        });
    }


    public long add(final long captainId, final CardCO card) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into captain_card (card_id,name,img,level,attack_min,attack_max,defence_min,defence_max,create_time)" +
                " values (?,?,?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, card.getId());
                pstmt.setString(2, card.getName());
                pstmt.setString(3, card.getImg());
                pstmt.setInt(4, 1);
                pstmt.setInt(5, card.getAttackMin());
                pstmt.setInt(6, card.getAttackMax());
                pstmt.setInt(7, card.getDefenceMin());
                pstmt.setInt(8, card.getDefenceMax());
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }

}
