package com.example.connectgo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.connectgo.ui.theme.ConnectGoTheme
import org.json.JSONObject
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val body = JSONObject()

        body.put("username" ,"user22@gmail.com")
        body.put("password" ,"password")
        ConnectGo.request
            .setURL("https://bookzone-production.up.railway.app/api/auth/signup")
            .setMethod("POST")
            .setBody(body)
//            .setHeaders(hashMapOf(Pair(
//                "user-agent","Mozilla/5.0"
//            )))
            .setListeners(listeners = object : JsonRequestListeners{
                override fun onSuccess(response: JSONObject) {
                    Log.d("TAG",response.toString())
                }

                override fun onFailure(exception: Exception) {
                    Log.d("ERROR",exception.toString())
                }

            })
            .makeRequest()
        setContent {
            ConnectGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConnectGoTheme {
        Greeting("Android")
    }
}