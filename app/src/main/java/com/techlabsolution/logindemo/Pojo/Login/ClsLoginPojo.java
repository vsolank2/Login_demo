
package com.techlabsolution.logindemo.Pojo.Login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsLoginPojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("IMAGE_URL")
    @Expose
    private String imageUrl;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
