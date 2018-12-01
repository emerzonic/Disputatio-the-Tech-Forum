package com.emerzonic.SpringApp.util;

import com.emerzonic.SpringApp.entity.Like;

import java.util.HashMap;
import java.util.Map;

public class HandleLike implements Toggleable{

    public boolean toggleLike(Map likes, Like newLike) {
        boolean feedback = true;
        if ( likes== null) {
          likes = new HashMap<String, Like>();
        }
        String authorKey = newLike.getAuthor();
        boolean hasLike = likes.containsKey(authorKey);
        if (!hasLike) {
            likes.put(authorKey, newLike);
            feedback = false;
        } else {
            likes.remove(authorKey);
        }
        return feedback;
    }
}
