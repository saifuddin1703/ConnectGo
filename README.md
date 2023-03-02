<h1 align="center" id="title">ConnectGo</h1>

<p id="description">A simple networking library help to make network calls and get response in JSON from android</p>

  

<h2>🛠️Add to your project :</h2>

<br>
<p>1. Add the JitPack repository to your build file</p>

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
<br>
<p>2. Add the dependency</p>

```gradle
dependencies { 	
        implementation 'com.github.saifuddin1703:ConnectGo:1.0.0' 
	}
```
<br>

<h2>How to use :</h2>

<br>
<h3>Make simple get request</h3>
<br>

```kotlin
ConnectGo.request
            .setURL("https://example.com/api/entpoint")
            .setMethod("GET")
            .setListeners(listeners = object : JsonRequestListeners{
                override fun onSuccess(response: JSONObject) {
                    Log.d("TAG",response.toString())
                }

                override fun onFailure(exception: Exception) {
                    Log.d("ERROR",exception.toString())
                }

            })
            .makeRequest()
```


<br>
<h3>Set the body of the request</h3>
<br>

```kotlin
//  create a JSON body
val body = JSONObject()
body.put("username" ,"user22@gmail.com")
body.put("password" ,"password")

ConnectGo.request
            .setURL("https://example.com/api/entpoint")
            .setMethod("POST")
            .setBody(body)
            .setListeners(listeners = object : JsonRequestListeners{
                override fun onSuccess(response: JSONObject) {
                    Log.d("TAG",response.toString())
                }

                override fun onFailure(exception: Exception) {
                    Log.d("ERROR",exception.toString())
                }

            })
            .makeRequest()
```

<br>
<h3>Set the headers</h3>
<br>

```kotlin
//  create a hashmap of headers
val header = HashMap<String,String>()
header.put("apiKey" ,"key")

ConnectGo.request
            .setURL("https://example.com/api/entpoint")
            .setMethod("GET")
            .setHeaders(headers)
            .setListeners(listeners = object : JsonRequestListeners{
                override fun onSuccess(response: JSONObject) {
                    Log.d("TAG",response.toString())
                }

                override fun onFailure(exception: Exception) {
                    Log.d("ERROR",exception.toString())
                }

            })
            .makeRequest()
```

<br>
<h3>Add Query parameters</h3>
<br>

```kotlin
//  create a HashMap of parameters
val params = HashMap<String,String>()
header.put("country" ,"in")

ConnectGo.request
            .setURL("https://example.com/api/entpoint")
            .setMethod("GET")
            .setParams(params)
            .setListeners(listeners = object : JsonRequestListeners{
                override fun onSuccess(response: JSONObject) {
                    Log.d("TAG",response.toString())
                }

                override fun onFailure(exception: Exception) {
                    Log.d("ERROR",exception.toString())
                }

            })
            .makeRequest()
```
