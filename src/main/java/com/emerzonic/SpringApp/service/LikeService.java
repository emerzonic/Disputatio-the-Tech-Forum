package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.Like;

public interface LikeService {


	public void toggleLike(Like like);

	public void addOrDeleteLike(Boolean status, Like like);

}
