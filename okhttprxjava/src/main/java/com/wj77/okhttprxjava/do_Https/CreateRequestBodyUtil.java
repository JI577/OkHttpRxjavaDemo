package com.wj77.okhttprxjava.do_Https;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class CreateRequestBodyUtil {
    /**
     * 根据Json串生成请求体
     *
     * @param json
     * @return
     */
    public static RequestBody createRequestBody(String json) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }

    /**
     * 根据Json串生成请求体
     *
     * @return
     */
    public static RequestBody createRequestBody(Map map) {
        String body = JSON.toJSONString(map);
//        body = "message=" + body;
        Logger.e("jrq", "==http--参数---" + body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
    }  /**
     * 根据Json串生成请求体
     *
     * @return
     */



    /**
     * 根据Json串生成请求体
     *
     * @return
     */
    public static RequestBody createRequestBody(Object bean) {
        Gson gson = new Gson();
        String body = gson.toJson(bean);
        body = "message=" + body;
        Logger.e("jrq","=========参数="+body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
    }

    /**
     * 根据File生成请求体
     *
     * @return
     */
    public static MultipartBody.Part createRequestBody(String key, File file) {
        RequestBody requestFile = RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }
}
