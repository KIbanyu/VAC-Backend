package com.kibzdev.vac.repository;

import com.kibzdev.vac.entities.AddPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddPostRepository extends JpaRepository<AddPostEntity, Long> {

    List<AddPostEntity> getDistinctByCategory(String type);
}
