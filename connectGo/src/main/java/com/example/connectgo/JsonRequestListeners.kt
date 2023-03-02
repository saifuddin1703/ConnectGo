package com.example.connectgo

import org.json.JSONObject

interface JsonRequestListeners {
    fun onSuccess(response : JSONObject)
    fun onFailure(exception: java.lang.Exception)
}