package com.kibzdev.globalFarm.services;


import com.kibzdev.globalFarm.entities.AddPostEntity;
import com.kibzdev.globalFarm.models.PostDateRequest;
import com.kibzdev.globalFarm.repository.AddPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddPostService {
    @Autowired
    private AddPostRepository addPostRepository;

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


        HashMap<String, Object> response = new HashMap<>();


        if (type.equalsIgnoreCase("recent"))
        {
            List<AddPostEntity> posts = addPostRepository.getAllByCategoryOrderByIdDesc();
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

