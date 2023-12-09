package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CommentDto;
import com.techelevator.model.Photo;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Photo> getPhotosByUserId(int userId) {

        final String sql = "SELECT photo_id, caption, pic_url FROM photo_feed WHERE user_id = ?";

        final List<Photo> photos = new ArrayList<>();

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, userId);
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
    public Photo getPhotoByPhotoId(int photoId) {

        final String sql = "SELECT caption, pic_url FROM photo_feed WHERE photo_id = ?";

        final Photo photo = new Photo();
        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, photoId);
            if (results.next()) {

                photo.setId(photoId);
                photo.setCaption(results.getString("caption"));
                photo.setPhotoUrl(results.getString("pic_url"));
                photo.setDatePublished(LocalDateTime.now());
                photo.setComments(getCommentsByPhotoId(photoId));


            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return photo;
    }

    @Override
    public ArrayList<CommentDto> getCommentsByPhotoId(int id) {

        final ArrayList<CommentDto> comments = new ArrayList<>();

        final String sql = "SELECT c.user_id, photo_id, comment_id, comment_text, username " +
                "FROM comments c JOIN users u ON c.user_id = u.user_id " +
                "WHERE photo_id = ? ORDER BY date_time DESC;";

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                final CommentDto comment = new CommentDto();
                comment.setCommentId(results.getInt("comment_id"));
                comment.setPhotoId(results.getInt("photo_id"));
                comment.setUserId(results.getInt("user_id"));
                comment.setTimestamp(LocalDateTime.now());
                comment.setCommentBody(results.getString("comment_text"));
                comment.setUsername(results.getString("username"));

                comments.add(comment);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        }
        return comments;
    }

    @Override
    public int addPhoto(Photo photo) {
        final String sql = "INSERT INTO photo_feed(user_id, date_time, caption, pic_url) " +
                "VALUES (?, ?, ?, ?) RETURNING photo_id;";
        int photoID = 0;
        try {
            photoID = jdbcTemplate.queryForObject(sql, int.class, photo.getUserId(), photo.getDatePublished(), photo.getCaption(), photo.getPhotoUrl());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return photoID;
    }
}