package com.wj77.okhttprxjava.do_Https;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿


        long t1 = System.nanoTime();//请求发起的时间

        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
//        Logger.e("jrq",String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
//                response.request().url(),
//                responseBody.string(),
//                (t2 - t1) / 1e6d,
//                response.headers()));

        Logger.e("jrq", "==http:"+response.request().url()+"\n方式:"+response.request().method()+"\n时间:"+(t2 - t1) / 1e6d+"\n"+responseBody.string());

        return response;
    }
}
