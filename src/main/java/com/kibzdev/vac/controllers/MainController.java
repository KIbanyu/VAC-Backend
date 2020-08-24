package com.kibzdev.vac.controllers;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kibzdev.vac.models.PostDateRequest;
import com.kibzdev.vac.models.UserModel;
import com.kibzdev.vac.services.AddPostService;
import com.kibzdev.vac.services.UserService;
import com.kibzdev.vac.configurations.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping(value = "api/android/v1/global_farm/")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    AddPostService addPostService;

    @Autowired
    private AmazonS3 s3client;

    @Value("${endpointUrl}")
    private String endpointUrl;

    @Value("${bucketName}")
    private String bucketName;

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
    public Map<String, Object> addPost(@RequestPart(value = "file") MultipartFile multipartFile,
                                       @RequestParam String name,
                                       @RequestParam String category,
                                       @RequestParam BigDecimal price,
                                       @RequestParam String quantity,
                                       @RequestParam String description,
                                       @RequestParam String location,
                                       @RequestParam String phone) {

        PostDateRequest request = new PostDateRequest();
        request.setName(name);
        request.setCategory(category);
        request.setPrice(price);
        request.setQuantity(quantity);
        request.setDescription(description);
        request.setLocation(location);
        request.setPhone(phone);


        String fileUrl = "";
        HashMap<String, Object> response = new HashMap<>();
        try {


            //converting multipart file to file
            File file = convertMultiPartToFile(multipartFile);

            //filename
            String fileName = multipartFile.getOriginalFilename();

            fileUrl = endpointUrl + "/" + "global" + "/" + fileName;

            response = uploadFileTos3bucket(fileName, file, "global/");

            file.delete();

        } catch (Exception e) {

            response.put("status", "01");
            response.put("message", "UploadController().uploadFile().Exception :" + e.getMessage());

            return response;

        }

        if (response.get("status").equals("00")) {

            request.setPhotosList(fileUrl);

            response = addPostService.addPost(request);
        }


        return response;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    private HashMap<String, Object> uploadFileTos3bucket(String fileName, File file, String folder) {

        HashMap<String, Object> response = new HashMap<>();

        try {
            s3client.putObject(new PutObjectRequest(bucketName, folder + fileName, file));
        } catch (AmazonServiceException e) {

            response.put("status", "01");
            response.put("message", "uploadFileTos3bucket().Uploading failed :" + e.getMessage());
            return response;
        }
        response.put("status", "00");
        response.put("message", "success");
        return response;
    }


    @RequestMapping(value = "get-posts", method = RequestMethod.GET)
    public Map<String, Object> getPosts(@RequestParam String type) {

        return addPostService.getPost(type);
    }

}
