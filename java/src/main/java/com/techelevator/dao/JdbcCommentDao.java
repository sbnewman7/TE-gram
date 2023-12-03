package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getCommentsByPhotoId(int id) {

        final List<Comment> comments = new ArrayList<>();

        final String sql = "SELECT user_id, photo_id, comment_id, comment_text FROM comments WHERE photo_id = ?;";

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                final Comment comment = new Comment();
                comment.setCommentId(results.getInt("comment_id"));
                comment.setPhotoId(results.getInt("photo_id"));
                comment.setUserId(results.getInt("user_id"));
                comment.setTimestamp(LocalDateTime.now());
                comment.setCommentBody(results.getString("comment_text"));

                comments.add(comment);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        }
        return comments;
    }
}
