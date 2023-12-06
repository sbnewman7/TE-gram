package com.techelevator.controller;

import com.techelevator.dao.PhotoDao;
import com.techelevator.model.Photo;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{userId}/photos")
//    public List<Photo> getPhotosByUserId(@RequestParam int userId) {
    public List<Photo> getPhotosByUserId(@PathVariable int userId) {
        return photoDao.getPhotosByUserId(userId);
    }


}