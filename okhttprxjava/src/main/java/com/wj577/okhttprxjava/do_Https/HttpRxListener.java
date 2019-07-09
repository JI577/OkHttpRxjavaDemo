package com.wj77.okhttprxjava.do_Https;

public  interface  HttpRxListener<T> {
    void httpResponse(T info, boolean isSuccess, int sort);
}
