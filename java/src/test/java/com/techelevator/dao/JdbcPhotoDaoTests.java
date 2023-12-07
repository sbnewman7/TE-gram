package com.techelevator.dao;


import com.techelevator.model.Photo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class JdbcPhotoDaoTests extends BaseDaoTests {


    private JdbcPhotoDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPhotoDao(jdbcTemplate);
    }

    @Test
    public void addPhoto_should_add_photo(){
        Photo photoToAdd = new Photo();
        photoToAdd.setDatePublished(LocalDateTime.now());
        photoToAdd.setCaption("test");
        photoToAdd.setPhotoUrl("testUrl");
        photoToAdd.setUserId(1);
        int numBefore = sut.getAll().size();
        sut.addPhoto(photoToAdd);
        int numAfter = sut.getAll().size();
        Assert.assertEquals("Add photo should add photo",numBefore+1, numAfter);

    }
}
