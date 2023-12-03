package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Photo;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

        final String sql = "SELECT movie_id, title, poster_path, release_date, length_minutes\n" +
                "FROM movie \n" +
                "LIMIT 25;\n";

        final List<Photo> photos = new ArrayList<>();

        try {

            final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                final Photo photo = new Photo();
                photo.setId(results.getInt("movie_id"));
                photo.setCaption(results.getString("title"));
                photo.setPhotoUrl(results.getString("poster_path"));

                if (results.getDate("release_date") != null) {
                    photo.setReleaseDate(results.getDate("release_date").toLocalDate());
                }

                photo.setRunningTimeInMinutes(results.getInt("length_minutes"));
                photos.add(photo);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return photos;
    }
}