package com.emerzonic.SpringApp.util;

import com.emerzonic.SpringApp.entity.Like;

import java.util.HashMap;
import java.util.Map;

public class HandleLike implements Toggleable{

    public boolean toggleLike(Map likes, Like newLike) {
        boolean feedback = true;
        if ( likes == null) {
          likes = new HashMap<String, Like>();
        }
        String key = newLike.getAuthor();
        boolean hasLike = likes.containsKey( key );
        if (!hasLike) {
            likes.put(key, newLike);
            feedback = false;
        } else {
            likes.remove(key);
        }
        return feedback;
    }
}
