package com.wj577.okhttprxjava

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import rx.Observable

interface Api {
    /**
     * 例如:  web/file/uploadImage
     */
//    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8", "Accept:application/json;")
    @POST("service/getIpInfo.php")
    fun getHomeTypeNet(@Body body: RequestBody): Observable<Info>

    @GET("thailand/fm")
    fun getCityNet(@QueryMap map: Map<String, String>): Observable<Info>


    //图片上传
    @Multipart
    @POST("接口")
    fun getUpdatePicNet(@Part file: MultipartBody.Part): Observable<String>
}

