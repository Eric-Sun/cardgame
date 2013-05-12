package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.DropGroupCO;
import com.h13.cardgame.cache.co.DropGroupDataCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午11:44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DropGroupDAO {

    @Autowired
    JdbcTemplate j;

    public List<DropGroupCO> getAll() {
        String sql = "select * from drop_group";
        return j.query(sql, new Object[]{}, new RowMapper<DropGroupCO>() {
            @Override
            public DropGroupCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                DropGroupCO co = new DropGroupCO();
                co.setId(rs.getInt(1));
                co.setName(rs.getString(2));
                co.setData(JSON.parseObject(rs.getString(3), DropGroupDataCO.class));
                return co;
            }
        });
    }

    public DropGroupCO get(long cardId) {
        String sql = "select * from card where id=?";
        List<DropGroupCO> list = j.query(sql, new Object[]{}, new RowMapper<DropGroupCO>() {
            @Override
            public DropGroupCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                DropGroupCO co = new DropGroupCO();
                co.setId(rs.getInt(1));
                co.setName(rs.getString(2));
                co.setData(JSON.parseObject(rs.getString(3), DropGroupDataCO.class));
                return co;
            }
        });
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

}
