package com.emerzonic.SpringApp.repository;

import com.emerzonic.SpringApp.entity.Post;
import org.springframework.data.repository.CrudRepository;



public interface PostRepository extends CrudRepository <Post, Integer>{


}