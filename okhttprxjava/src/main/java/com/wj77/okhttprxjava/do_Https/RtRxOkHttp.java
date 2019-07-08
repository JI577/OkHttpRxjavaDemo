package com.wj77.okhttprxjava.do_Https;

import android.content.Context;

import java.util.concurrent.TimeUnit;


import doext.module.do_Https.BaseApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RtRxOkHttp<T, Q> {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
//        private static BaseApi api;
    private Q api;
    private static RtRxOkHttp instance;
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    public static RtRxOkHttp getInstance() {
        if (instance == null) {
            instance = new RtRxOkHttp();
        }
        return instance;
    }

    public void init(String http, Class<Q> apiClass) {
        generateOkhttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(http)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(ResponseConverterFactory.create())
                .build();

        api = retrofit.create(apiClass);
//        api = retrofit.create(BaseApi.class);

    }

    public static void generateOkhttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    //time out
                    .readTimeout(6000 * 10, TimeUnit.SECONDS)
                    .connectTimeout(6000 * 10, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
//                    .addInterceptor(mInterceptor)
                    .build();
        }

    }


    /**
     * 获取apiservice
     *
     * @return
     */
//    public Q getApiService(String http, Class<Q> apiClass) {
//        if (api == null) {
//            init(http, apiClass);
//        }
//        return api;
//    }
    /**
     * 获取apiservice
     *
     * @return
     */
//    public Q getApiService(String http, Class<Q> apiClass) {
//        if (api == null) {
//            init(http, apiClass);
//        }
//        return api;
//    }
  public Q getApiService(String http, Class<Q> apiClass) {
        if (api == null) {
            init(http, apiClass);
        }
        return api;
    }

    public Subscription createRtRx(final Context activity, Observable<T> postHttp, final HttpRxListener listener, final int sort) {

        try {
//            if (!NetworkUtils.isNetAvailable(activity)) {
//                Toast.makeText(activity, "请检查网络", Toast.LENGTH_SHORT).show();
//                return null;
//            }
            final Subscription subscription = postHttp.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<T>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                e.printStackTrace();
//                                Logger.e("jrq", "-----onError--------" + e);
//                                Toast.makeText(MyApp.context, "获取数据失败,请稍后重试", Toast.LENGTH_SHORT).show();
                                listener.httpResponse(null, false, sort);
                            } catch (Exception a) {
                                a.printStackTrace();
                            }

                            e.printStackTrace();

                        }

                        @Override
                        public void onNext(T t) {
                            try {
                                listener.httpResponse(t, true, sort);
//
                            } catch (Exception a) {
                                a.printStackTrace();
                            }

                        }
                    });

            return subscription;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
