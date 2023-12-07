package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

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
    @Test
    public void if_not_liked_get_user_liked_returns_false(){
        Assert.assertFalse(sut.getUserLiked(1,3));
    }
    @Test
    public void get_like_count_returns_like_count(){
        int expected = 1;
        int actual = sut.getLikeCount(1);
        Assert.assertEquals("Get like count should return correct count",expected,actual);
    }
    @Test
    public void add_like_should_add_like(){
        sut.addLike(1,1);
        int expected = 2;
        int actual = sut.getLikeCount(1);
        Assert.assertEquals("Add like should add like",expected,actual);
    }
    @Test
    public void remove_like_should_remove_like(){
        sut.removeLike(1,1);
        int actual = sut.getLikeCount(1);
        Assert.assertEquals("remove like should remove like",0,actual);
    }
}
