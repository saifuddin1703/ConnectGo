package com.example.connectgo

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import org.json.JSONObject
import java.net.URLEncoder

class Request {

     var url : String? = null
     var method : String = "GET"
     var header : HashMap<String,String> = hashMapOf()
     var body : ByteArray? = null
     var params : HashMap<String,String>? = null

     private var responseListeners : JsonRequestListeners? = null

    fun setURL(url : String): Request {
        this.url = url
        return this
    }

    fun setParams(params : HashMap<String,String>): Request {
        this.url += "?${getQuery(params)}"
        return this
    }

    private fun getQuery(params : java.util.HashMap<String, String>): String {
        val result = StringBuilder()
        var first = true

        params.forEach {entry->
            if (first) first = false else result.append("&")
            result.append(URLEncoder.encode(entry.key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(entry.value, "UTF-8"))
        }

        return result.toString()
    }

    fun setMethod(method : String): Request {
        this.method = method
        return this
    }

    fun setHeaders(headers : HashMap<String,String>): Request {
        this.header.clear()
        this.header["Accept"] = "application/json"
        this.header.putAll(headers)
        return this
    }

    fun setBody(body : JSONObject): Request {
        val jsonString = body.toString()

        this.body = jsonString.toByteArray()

        this.header["Content-Type"] = "application/json"
        return this
    }

    fun setListeners(listeners: JsonRequestListeners): Request {
        this.responseListeners = listeners
        return this
    }

    fun sendResponse(response : JSONObject?, error : java.lang.Exception?){
        if (error == null){
            responseListeners?.onSuccess(response!!)
        }else{
            responseListeners?.onFailure(error)
        }
    }

    @Throws(Exception::class)
    fun makeRequest(){
        // create request
        if (url == null) throw Exception("Must specify an URL")

        val handlerThread = HandlerThread("Network thread",android.os.Process.THREAD_PRIORITY_DEFAULT)

        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        handler.post(RequestTask(this))
    }


}