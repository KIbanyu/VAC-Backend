package com.kibzdev.globalFarm.repository;

import com.kibzdev.globalFarm.entities.AddPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddPostRepository extends JpaRepository<AddPostEntity, Long> {

    List<AddPostEntity> getDistinctByCategory(String type);

    List<AddPostEntity> findAllByOrderByIdDesc();



}
