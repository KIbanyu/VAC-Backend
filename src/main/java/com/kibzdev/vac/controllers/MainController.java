package com.kibzdev.vac.controllers;


import com.kibzdev.vac.models.PostDateRequest;
import com.kibzdev.vac.models.UserModel;
import com.kibzdev.vac.services.AddPostService;
import com.kibzdev.vac.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping(value = "api/android/v1/global_farm/")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    AddPostService addPostService;

    @RequestMapping(value = "register-user", method = RequestMethod.POST)
    public Map<String, Object> registerUser(@RequestBody UserModel userModel) {
        HashMap<String, Object> response = userService.registerUser(userModel, "register");

        return response;
    }

    @RequestMapping(value = "login-user", method = RequestMethod.POST)
    public Map<String, Object> loginUser(@RequestBody UserModel userModel) {

        return userService.registerUser(userModel, "login");
    }

    @RequestMapping(value = "add-post", method = RequestMethod.POST)
    public Map<String, Object> addPost(@RequestBody PostDateRequest request) {

        return addPostService.addPost(request);
    }

    @RequestMapping(value = "get-posts", method = RequestMethod.GET)
    public Map<String, Object> getPosts(@RequestParam String type) {

        return addPostService.getPost(type);
    }

}
