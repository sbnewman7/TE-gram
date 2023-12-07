package com.techelevator.controller;

import com.techelevator.dao.PhotoDao;
import com.techelevator.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    @PostMapping("/photos")
    @ResponseStatus(HttpStatus.CREATED)
    public int addPhoto(@RequestBody Photo photo) {
        photo.setDatePublished(LocalDateTime.now());
        return photoDao.addPhoto(photo);

    }

}