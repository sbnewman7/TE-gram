package com.techelevator.controller;

import com.techelevator.dao.PhotoDao;
import com.techelevator.model.Photo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PhotosController {

    private final PhotoDao photoDao;

    public PhotosController(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @GetMapping("/photos")
    public List<Photo> getPhotos() {
        return photoDao.getAll();
    }

}