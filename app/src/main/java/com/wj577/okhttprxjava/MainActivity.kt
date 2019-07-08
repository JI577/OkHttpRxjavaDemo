package com.wj577.okhttprxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wj77.okhttprxjava.do_Https.CreateRequestBodyUtil
import com.wj77.okhttprxjava.do_Https.HttpRxListener
import com.wj77.okhttprxjava.do_Https.Logger
import com.wj77.okhttprxjava.do_Https.RtRxOkHttp
import doext.module.do_Https.BaseApi

import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.HashMap

class MainActivity : AppCompatActivity(), HttpRxListener<Object> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData() {
        //   以 / 结尾
        var http = "http://v.juhe.cn/" //域名
        val apiService = RtRxOkHttp.getInstance().getApiService(http, Api::class.java) as Api
        tvHttp.setOnClickListener {
            //普通post
            val hashMap = HashMap<String, String>()
            hashMap.put("key", "4e22aa0316f95c8e28274e5117340a92")
            val homeTypeNet = apiService.getCityNet(hashMap)
            RtRxOkHttp.getInstance().createRtRx(this, homeTypeNet, this, 1)
        }
        tvFile.setOnClickListener {
            //以文件方式上传
            //文件上传 file
//            var file = File("")
//            val fileBody = CreateRequestBodyUtil.createRequestBody("ww", file)
//            val apiService = RtRxOkHttp.getInstance().getApiService(http, Api::class.java) as Api
//            val updateHeadNet = apiService.getUpdatePicNet(fileBody)
//            RtRxOkHttp.getInstance().createRtRx(this, updateHeadNet, this, 3)
        }

    }

    override fun httpResponse(info: Object?, isSuccess: Boolean, sort: Int) {
        if (isSuccess) {//接口连接成功
            when (sort) {
                1 -> {//获取城市list
                    val info1 = info as Info
                    Logger.e("jrq", "----info----" + info1.error_code)
                }
                3 -> {//文件上传
                    Logger.e("jrq", "----info---" + info.toString())
                }
            }
        } else {//接口访问失败


        }


    }
}
