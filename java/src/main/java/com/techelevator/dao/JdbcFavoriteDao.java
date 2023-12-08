package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcFavoriteDao implements FavoriteDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFavoriteDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Integer> getFavorites(int userId) {
        List<Integer> favorites = new ArrayList<>();
        final String sql = "SELECT photo_id" +
                " FROM photo_favorites" +
                " WHERE user_id = ?;";
        try{
            SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()){
                favorites.add(results.getInt("photo_id"));
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return favorites;
    }
}
