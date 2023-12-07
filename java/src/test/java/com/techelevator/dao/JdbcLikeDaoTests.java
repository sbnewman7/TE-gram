package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcLikeDaoTests extends BaseDaoTests {



    private JdbcLikeDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcLikeDao(jdbcTemplate);
    }

    @Test
    public void get_user_liked_returns_user_liked(){
        Assert.assertTrue(sut.getUserLiked(1, 1));
    }
}
