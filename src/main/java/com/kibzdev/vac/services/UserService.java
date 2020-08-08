package com.kibzdev.vac.services;


import com.kibzdev.vac.entities.UserEntity;
import com.kibzdev.vac.models.UserModel;
import com.kibzdev.vac.repository.UserRepository;
import com.kibzdev.vac.utils.ThreeDes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {
    private ThreeDes threeDes;

    @Autowired
    private UserRepository userRepository;

    public HashMap<String, Object> registerUser(UserModel userModel, String type) {

        try {
            threeDes = new ThreeDes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> response = new HashMap<>();
        if (type.equalsIgnoreCase("register")) {
            UserEntity user = new UserEntity();
            user.setPhoneNumber(userModel.getPhoneNumber());
            user.setEmail(userModel.getEmail());
            user.setName(userModel.getName());
            user.setPassword(threeDes.encrypt(userModel.getPassword()));

            Optional<UserEntity> phoneExist = userRepository.findDistinctByPhoneNumber(userModel.getPhoneNumber());

            if (phoneExist.isPresent())
            {
                response.put("status", "01");
                response.put("message", " A user exist with the same phone number");
                return response;
            }

            Optional<UserEntity> emailExist = userRepository.findDistinctByEmail(userModel.getEmail());

            if (emailExist.isPresent())
            {
                response.put("status", "01");
                response.put("message", " A user exist with the same email address");
                return response;
            }

            userRepository.save(user);
            response.put("status", "00");
            response.put("message", "Registered successfully");
            return response;


        } else if (type.equalsIgnoreCase("login")) {

            Optional<UserEntity> phoneNumberExist = userRepository.findDistinctByPhoneNumber(userModel.getPhoneNumber());
            if (phoneNumberExist.isPresent()) {
                UserEntity userLoginResponse = userRepository.findByPhoneNumberAndPassword(userModel.getPhoneNumber(), threeDes.encrypt(userModel.getPassword()));

                if (userLoginResponse == null) {

                    response.put("status", "01");
                    response.put("message", "Wrong phone number or password");

                } else {

                    userLoginResponse = userRepository.findByPhoneNumberAndPassword(userModel.getPhoneNumber(), threeDes.encrypt(userModel.getPassword()));
                    response.put("status", "00");
                    response.put("message", "Login success");
                    response.put("data", userLoginResponse);
                }
            } else {
                response.put("status", "01");
                response.put("message", "Account not found");
            }

//        } else if (type.equalsIgnoreCase("reset")) {
//
//            PhoneNumberVerificationEntity phoneExist = verifyPhoneRepository.findDistinctFirstByPhoneNumber(userModel.getPhone());
//
//            if (null != phoneExist) {
//                if (phoneExist.getVerificationCode().equalsIgnoreCase(userModel.getVerificationCode())) {
//
//                    UserEntity updateUser = userRepository.getUserByPhone(userModel.getPhone());
//                    updateUser.setPassword(threeDes.encrypt(userModel.getPassword()));
//                    userRepository.save(updateUser);
//                    response.put("status", "00");
//                    response.put("message", "Password changed successfully");
//
//
//                } else {
//                    response.put("status", "01");
//                    response.put("message", "Wrong verification code");
//
//                }
//
//            }
//
//
        }

        return response;
    }



}
