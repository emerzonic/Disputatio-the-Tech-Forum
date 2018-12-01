package com.emerzonic.SpringApp.util;

import com.emerzonic.SpringApp.entity.Like;

import java.util.Map;

public interface Toggleable {
  public boolean toggleLike(Map likes, Like newLike);
}
