package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcFavoriteDao implements FavoriteDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFavoriteDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean getUserFavorited(int userId, int photoId) {
        final String sql = "SELECT COUNT(*) FROM photo_favorites WHERE user_id = ? AND photo_id = ?;";
        int numOfRows;
        try{
            numOfRows = this.jdbcTemplate.queryForObject(sql, int.class, userId,photoId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return numOfRows != 0;
    }


    @Override
    public void addFavorite(int userId, int photoId) {
        final String sql = "INSERT INTO photo_favorites(" +
                " photo_id, user_id)" +
                " VALUES (?, ?);";
        try{
            this.jdbcTemplate.update(sql, userId, photoId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation");
        }
    }

    @Override
    public void removeFavorite(int photoId, int userId) {
        final String sql = "DELETE FROM photo_favorites" +
                " WHERE user_id = ? AND photo_id = ?;";
        try{
            this.jdbcTemplate.update(sql, userId, photoId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation");
        }
    }
}