package com.kibzdev.globalFarm.controllers;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kibzdev.globalFarm.models.PostDateRequest;
import com.kibzdev.globalFarm.models.UserModel;
import com.kibzdev.globalFarm.services.AddPostService;
import com.kibzdev.globalFarm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> addPost(@RequestPart(value = "file0") MultipartFile multipartFile,
                                       @RequestPart(value = "file1") MultipartFile multipartFile1,
                                       @RequestPart(value = "file2") MultipartFile multipartFile2,
                                       @RequestParam String name,
                                       @RequestParam int count,
                                       @RequestParam String category,
                                       @RequestParam BigDecimal price,
                                       @RequestParam String quantity,
                                       @RequestParam String description,
                                       @RequestParam String location,
                                       @RequestParam long userId,
                                       @RequestParam String phone) {

        PostDateRequest request = new PostDateRequest();
        request.setName(name);
        request.setCategory(category);
        request.setPrice(price);
        request.setQuantity(quantity);
        request.setDescription(description);
        request.setLocation(location);
        request.setPhone(phone);
        request.setCreatedBy(userId);


        String fileUrl = "";
        HashMap<String, Object> response = new HashMap<>();
        List<MultipartFile> receivedImages = new ArrayList<>();
        receivedImages.add(multipartFile);
        receivedImages.add(multipartFile1);
        receivedImages.add(multipartFile2);

        StringBuilder photosUrl = new StringBuilder();
        try {


            for (MultipartFile multipartFile3 : receivedImages)
            {
                //converting multipart file to file
                File file = convertMultiPartToFile(multipartFile3);

                //filename
                String fileName = multipartFile3.getOriginalFilename();
                fileUrl = endpointUrl + "/" + "global" + "/" + fileName;
                photosUrl.append(fileUrl).append(",");
                response = uploadFileTos3bucket(fileName, file, "global/");
                file.delete();
            }


        } catch (Exception e) {

            response.put("status", "01");
            response.put("message", "UploadController().uploadFile().Exception :" + e.getMessage());

            return response;

        }

        if (response.get("status").equals("00")) {

            request.setPhotosList(photosUrl.toString());

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


    @RequestMapping(value = "get-recent-posts", method = RequestMethod.GET)
    public Map<String, Object> getRecentPosts() {

        return addPostService.getPost(type);
    }
}
