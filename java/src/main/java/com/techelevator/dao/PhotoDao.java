package com.techelevator.dao;

import com.techelevator.model.Photo;

import java.util.List;

public interface PhotoDao {

    /**
     * gets all the movies
     * @return a list of Movie instances
     */
    List<Photo> getAll();

}