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

    @GetMapping("/users/{userId}/photos")
//    public List<Photo> getPhotosByUserId(@RequestParam int userId) {
    public List<Photo> getPhotosByUserId(@PathVariable int userId) {
        return photoDao.getPhotosByUserId(userId);
    }

    @GetMapping("/photos/{photoId}")
    public Photo getPhotoByPhotoId(@PathVariable int photoId){
        return photoDao.getPhotoByPhotoId(photoId);
    }

    @GetMapping("/following/{followerUserId}/photos")
    public List<Photo> getPhotosByFollowerId(@PathVariable int followerUserId){
        return photoDao.getPhotosByFollowerUserId(followerUserId);
    }

}