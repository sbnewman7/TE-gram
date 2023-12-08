package com.techelevator.dao;

import com.techelevator.model.Comment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class JdbcCommentDaoTests extends BaseDaoTests{

    private JdbcCommentDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCommentDao(jdbcTemplate);
    }

    @Test
    public void add_comments_should_add_comment(){
        Comment comment = new Comment();
        comment.setTimestamp(LocalDateTime.now());
        comment.setPhotoId(1);
        comment.setCommentBody("test");
        comment.setUserId(3);
        sut.addComment(comment);
        boolean actual = sut.getUserCommentedOnPhoto(3,1);
        Assert.assertEquals("Add comment should add comment",true, actual);

    }
    @Test
    public void get_user_commented_on_photo_should_return_true_if_comment(){
        Assert.assertTrue(sut.getUserCommentedOnPhoto(1,1));
    }
    @Test
    public void get_user_commented_on_photo_should_return_false_if_no_comment(){
        Assert.assertFalse(sut.getUserCommentedOnPhoto(10,1));
    }
}
