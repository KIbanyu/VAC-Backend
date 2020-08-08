package com.kibzdev.vac.models;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

/**
 * Created by Itotia Kibanyu on 8/2/2020.
 */
public class Photos {
    private MultipartBody.Part photo;

    public MultipartBody.Part getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartBody.Part photo) {
        this.photo = photo;
    }
}
