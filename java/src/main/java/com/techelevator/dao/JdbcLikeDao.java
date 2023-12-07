package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcLikeDao implements LikeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLikeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean getUserLiked(int userId, int photoId) {
        final String sql = "SELECT COUNT(*) FROM photo_likes WHERE user_id = ? AND photo_id = ?;";
        int numOfRows;
        try{
            numOfRows = this.jdbcTemplate.queryForObject(sql, int.class, userId,photoId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return numOfRows != 0;
    }
}
