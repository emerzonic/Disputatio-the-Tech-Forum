package com.emerzonic.SpringApp.DAO;

import com.emerzonic.SpringApp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {


}