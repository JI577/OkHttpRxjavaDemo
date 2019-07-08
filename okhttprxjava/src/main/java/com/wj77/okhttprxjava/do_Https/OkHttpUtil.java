package com.wj77.okhttprxjava.do_Https;


import android.app.Activity;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * FormBody.Builder builder = new FormBody.Builder().add("username", "0796020001");
 * OkHttpUtil.postAsynHttp(this, url1, builder, 1, this);
 */

public class OkHttpUtil {
    private static OkHttpClient mOkHttpClient;
    private static String str;

    public static void getAsynHttp(String url, Callback callback) {
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null) {
            mOkHttpClient = httpBuilder
                    //设置超时
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(mInterceptor)
                    .build();
        }
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);

    }

    public static void postAsynHttp(final Activity activity, String url, final FormBody.Builder builder, final int i, final HttpInterface httpInterface) {
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null) {
            mOkHttpClient = httpBuilder
                    //设置超时
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(mInterceptor)
                    .build();
        }
        /**参数的追加*/
//        builder.add("curpage", "1").add("pagesize", "10");
//
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if (((FormBody) request.body()).size() > 0) {
            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int j = 0; j < body.size(); j++) {
                        sb.append(body.encodedName(j) + "=" + body.encodedValue(j) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Logger.e("jrq", "---访问--参数-" + sb.toString());
                }
            }
        }


        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("jrp", "--网络错误-onFailure-" + e);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (httpInterface != null) {
                                httpInterface.succeed(i, str, false);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                try {
                    str = response.body().string();
                    Logger.e("jrq", "---访问--结果-" + str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (httpInterface != null) {
                                httpInterface.succeed(i, str, true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

        });
    }

    public static void postHttp(String url, final FormBody.Builder builder, Callback callback) {
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null) {
            mOkHttpClient = httpBuilder
                    //设置超时
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
        /**参数的追加*/
//        builder.add("curpage", "1").add("pagesize", "10");
//
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if (((FormBody) request.body()).size() > 0) {
            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int j = 0; j < body.size(); j++) {
                        sb.append(body.encodedName(j) + "=" + body.encodedValue(j) + "&");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Logger.e("jrq", "---访问--参数-" + sb.toString());
                }
            }
        }


        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
    private static Interceptor mInterceptor=new Interceptor(){
        @Override
        public Response intercept(Chain chain)throws IOException{
            Request request=chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding","gzip, deflate")
                    .addHeader("Connection","keep-alive")
                    .addHeader("Accept","*/*")
                    .addHeader("X-HB-Client-Type","ayb-android")
                    .addHeader("Content-Type","multipart/form-data; boundary=7db372eb000e2")
                    .build();
            return chain.proceed(request);
        }
    };
    /**
     * 成功回调的接口
     */
    public interface HttpInterface {
        void succeed(int i, String result, boolean isSuccess);

    }
}
