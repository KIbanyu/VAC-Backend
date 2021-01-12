package com.kibzdev.globalFarm.services;


import com.kibzdev.globalFarm.entities.AddPostEntity;
import com.kibzdev.globalFarm.models.PostDateRequest;
import com.kibzdev.globalFarm.repository.AddPostRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import org.slf4j.LoggerFactory;

@Service
public class AddPostService {
    @Autowired
    private AddPostRepository addPostRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HashMap<String, Object> addPost(PostDateRequest request) {
        HashMap<String, Object> response = new HashMap<>();
        AddPostEntity addPostEntity = new AddPostEntity();
        addPostEntity.setCategory(request.getCategory());
        addPostEntity.setDescription(request.getDescription());
        addPostEntity.setLocation(request.getLocation());
        addPostEntity.setName(request.getName());
        addPostEntity.setCreatedBy(request.getCreatedBy());
        addPostEntity.setPhone(request.getPhone());
        addPostEntity.setPrice(request.getPrice());
        addPostEntity.setQuantity(request.getQuantity());
        addPostEntity.setPhotosList(request.getPhotosList());
        addPostRepository.save(addPostEntity);

        response.put("status", "00");
        response.put("message", "Post added successfully");

        return response;

    }

    public HashMap<String, Object>  getPost(String type)
    {

        logger.info("============ TYPE IS " + type + "============");

        HashMap<String, Object> response = new HashMap<>();


        if (type.equalsIgnoreCase("recent"))
        {
            List<AddPostEntity> posts = addPostRepository.findAllByOrderByIdDesc();
            response.put("status", "00");
            response.put("data", posts);
            response.put("message", "Success");


            return response;
        }

        List<AddPostEntity> posts = addPostRepository.getDistinctByCategory(type);
        response.put("status", "00");
        response.put("data", posts);
        response.put("message", "Success");


        return response;
    }

}

