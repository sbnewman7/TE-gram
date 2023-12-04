package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import com.techelevator.model.Photo;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPhotoDao implements PhotoDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPhotoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Photo> getAll() {

        final String sql = "SELECT photo_id, caption, pic_url FROM photo_feed";

        final List<Photo> photos = new ArrayList<>();

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                final Photo photo = new Photo();
                photo.setId(results.getInt("photo_id"));
                photo.setCaption(results.getString("caption"));
                photo.setPhotoUrl(results.getString("pic_url"));
                photo.setDatePublished(LocalDateTime.now());
                photo.setComments(getCommentsByPhotoId(results.getInt("photo_id")));

                photos.add(photo);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return photos;
    }

    @Override
    public ArrayList<Comment> getCommentsByPhotoId(int id) {

        final ArrayList<Comment> comments = new ArrayList<>();

        final String sql = "SELECT user_id, photo_id, comment_id, comment_text FROM comments WHERE photo_id = ? ORDER BY date_time DESC;";

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