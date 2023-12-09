package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFollowDao implements FollowDao {


    private final JdbcTemplate jdbcTemplate;


    public JdbcFollowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getFollowers(int followerUserId) {
        List<Integer> followers = new ArrayList<>();
        final String sql = "SELECT followed_user_id " +
                "FROM followers " +
                "WHERE follower_user_id = ?;";
        try {
            SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, followerUserId);
            while (results.next()) {
                followers.add(results.getInt("followed_user_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return followers;
    }

    @Override
    public boolean getUserFollowed(int followerUserId, int followedUserId) {
        final String sql = "SELECT COUNT(follower_user_id) FROM followers WHERE follower_user_id = ? AND followed_user_id = ?;";
        int numOfRows;
        try {
            numOfRows = this.jdbcTemplate.queryForObject(sql, int.class, photoId, userid);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return numOfRows != 0;
    }

}


