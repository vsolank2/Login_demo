package com.techlabsolution.logindemo;


import com.techlabsolution.logindemo.Pojo.Login.ClsLoginPojo;
import com.techlabsolution.logindemo.Pojo.Singup.ClsSingupPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<ClsLoginPojo> login(@Field("email") String email,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<ClsSingupPojo> signup(@Field("mobile") String mobile,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("shopname") String shopname,
                               @Field("licence_number") String licence_number,
                               @Field("licence_number1") String licence_number1,
                               @Field("gst_no") String gst_no,
                               @Field("address") String address,
                               @Field("licence_image") String licence_image);

}