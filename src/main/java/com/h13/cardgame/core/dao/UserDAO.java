package com.h13.cardgame.core.dao;

import com.h13.cardgame.core.JdbcQueueTemplate;
import com.h13.cardgame.core.exceptions.UserNameExistedException;
import com.h13.cardgame.core.exceptions.UserNameOrPwdErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-17
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate j;

    @Autowired
    JdbcQueueTemplate q;

    public long create(final String name, final String pwd) throws UserNameExistedException {
        String s1 = "select count(id) from user where name=?";
        if (j.queryForInt(s1, new Object[]{name}) > 0) {
            throw new UserNameExistedException("name existed. name=" + name);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into user (name,password,create_time) values(?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                pstmt.setString(2, pwd);
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public long login(String name, String pwd) throws UserNameOrPwdErrorException {
        String sql = "select id from user where name=? and pwd=?";
        try {
            long id = j.queryForLong(sql, new Object[]{});
            return id;
        } catch (Exception e) {
            throw new UserNameOrPwdErrorException("name or pwd error. name=" + name + " pwd=" + pwd);
        }
    }

}
