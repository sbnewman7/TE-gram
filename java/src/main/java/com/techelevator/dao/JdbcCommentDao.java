package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public boolean addComment(Comment comment) {
        final String sql = "INSERT INTO comments(user_id, photo_id, date_time, comment_text)" +
                " VALUES (?, ?, ?, ?);";
        int numOfRows;
        try{
            numOfRows = jdbcTemplate.update(sql, comment.getUserId(), comment.getPhotoId(), comment.getTimestamp(), comment.getCommentBody());
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation");
        }

        return numOfRows > 0;
    }

    @Override
    public boolean getUserCommentedOnPhoto(int userId, int photoId) {
        final String sql = "SELECT COUNT(*) FROM comments WHERE user_id = ? AND photo_id = ?;";
        int numOfRows;
        try{
            numOfRows = this.jdbcTemplate.queryForObject(sql, int.class, userId,photoId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return numOfRows != 0;
    }
}
