package com.techelevator.dao;

import com.techelevator.exception.DaoException;
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

        final String sql = "SELECT photo_id, caption, pic_url \n" +
                "FROM photo_feed";

        final List<Photo> photos = new ArrayList<>();

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                final Photo photo = new Photo();
                photo.setId(results.getInt("photo_id"));
                photo.setCaption(results.getString("caption"));
                photo.setPhotoUrl(results.getString("pic_url"));
                photo.setDatePublished(LocalDateTime.now());

                photos.add(photo);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return photos;
    }
}