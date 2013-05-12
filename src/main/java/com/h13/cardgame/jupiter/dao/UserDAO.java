package com.h13.cardgame.jupiter.dao;

import com.h13.cardgame.jupiter.JdbcQueueTemplate;
import com.h13.cardgame.jupiter.exceptions.MailExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameOrPwdErrorException;
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

    public long create(final String mail, final String name, final String pwd) throws UserNameExistedException, MailExistedException {
        String s1 = "select count(id) from user where name=?";
        if (j.queryForInt(s1, new Object[]{name}) > 0) {
            throw new UserNameExistedException("name existed. name=" + name);
        }

        String s2 = "select count(id) from user where mail=?";
        if (j.queryForInt(s2, new Object[]{mail}) > 0) {
            throw new MailExistedException("mail existed. mail=" + mail);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into user (name,password,mail,create_time) values(?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                pstmt.setString(2, pwd);
                pstmt.setString(3, mail);
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public long login(String mail, String pwd) throws UserNameOrPwdErrorException {
        String sql = "select id from user where mail=? and pwd=?";
        try {
            long id = j.queryForLong(sql, new Object[]{});
            return id;
        } catch (Exception e) {
            throw new UserNameOrPwdErrorException("mail or pwd error. mail=" + mail + " pwd=" + pwd);
        }
    }

    public boolean check(long uid) {
        String sql = "select count(1) from user where id=?";
        int cnt = j.queryForInt(sql, new Object[]{uid});
        if (cnt >= 1)
            return true;
        else
            return false;
    }
}
