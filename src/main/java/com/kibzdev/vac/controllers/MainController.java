package com.kibzdev.vac.controllers;


import com.kibzdev.vac.models.UserModel;
import com.kibzdev.vac.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/android/v1/vac/")
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "register-user", method = RequestMethod.POST)
    public Map<String, Object> registerUser(@RequestBody UserModel userModel) {
        HashMap<String, Object> response = userService.registerUser(userModel, "register");

        return response;
    }

    @RequestMapping(value = "login-user", method = RequestMethod.POST)
    public Map<String, Object> loginUser(@RequestBody UserModel userModel) {

        return userService.registerUser(userModel, "login");
    }

}
