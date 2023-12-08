package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcFavoritesDaoTests extends BaseDaoTests{

    private JdbcFavoriteDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcFavoriteDao(jdbcTemplate);
    }

    @Test
    public void get_favorites_should_return_favorites(){
        int actual = sut.getFavorites(1).size();
        int expected = 1;
        Assert.assertEquals("Get favorites should return correct count",expected,actual);
    }
    @Test
    public void get_favorites_with_incorrect_id_should_return_0(){
        int actual = sut.getFavorites(10).size();
        int expected = 0;
        Assert.assertEquals("Get favorites should return correct count",expected,actual);
    }
}
