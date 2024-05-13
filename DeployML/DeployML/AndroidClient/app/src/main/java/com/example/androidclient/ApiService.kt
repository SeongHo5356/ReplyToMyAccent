package com.example.andrioidclient2

import org.json.JSONObject
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("app_data/")
    fun requestPOST(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("introduce") introduce: String
    ) : retrofit2.Call<JSONObject>

    @GET("add_data")
    fun requestGET() :retrofit2.Call<JSONObject>
}