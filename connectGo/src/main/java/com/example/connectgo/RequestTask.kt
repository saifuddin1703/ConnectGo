package com.example.connectgo

import android.util.Log
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLEncoder
import java.util.*
import javax.net.ssl.HttpsURLConnection

class RequestTask(private val request : Request) : Runnable {
    override fun run() {
        // setting the url
        val url = URL(request.url)

        // opening the connection to the url
        val connection = url.openConnection() as HttpsURLConnection
        try{

            connection.doInput = true
            // setting the method
            connection.requestMethod = request.method

            // setting the headers
            request.header.forEach { entry ->
                connection.setRequestProperty(entry.key, entry.value)
            }

            // setting the body
            request.body?.let { body ->
                connection.doOutput = true
                val outputStream = connection.outputStream
                outputStream.write(body)
            }

            Log.d("BEFORE",connection.requestMethod)
//            connection.query
            connection.connect()

            Log.d("AFTER",connection.requestMethod)
            val responseCode = connection.responseCode

            val isSuccess = responseCode == HttpsURLConnection.HTTP_OK
            val ips = if (isSuccess) connection.inputStream else connection.errorStream

            val scanner = Scanner(ips)

            scanner.useDelimiter("\\A")

            val responseString = if (scanner.hasNext()) scanner.next() else ""

            Log.d("DEBUG",connection.headerFields.toString())
            if (isSuccess) {
                val response = JSONObject(responseString)
                request.sendResponse(response, null)
            }else{
                Log.d("DEBUG",connection.requestMethod)
                request.sendResponse(null,java.lang.Exception(responseString))
            }

        }catch (e : Exception){
            request.sendResponse(null,e)
        }
        finally {
            connection.disconnect()
        }

    }

}