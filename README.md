# Android Network Module #


### Objective ###
To keep all the network calls related code at one place. For more modularized, maintainable, easy-to-scale, easy-to-read code.

### Assumptions ###
* Response of a request is JSONObject always, not a general string, not even JSONArray. The following will work.

	
```
#!python

    {
		"SUCCESS":1,
		"MESSAGE": "Blah Blah",
		"DATA":[...],
		...so on...
	}
```


### How to set up: ###
1. Copy "Volley.jar" from libs folder to your libs folder.
2. Copy "com.example.NetworkManager" and "com.example.NetworkManager.ChuiMui" package to your project.
3. Add Network permission in the "AndroidManifest.xml" file:

```
#!xml

	<uses-permission android:name="android.permission.INTERNET" />
```

4.  Add "NetworkService" to Manifest file

```
#!xml

	<service android:name="com.example.NetworkManager.NetworkService" ></service>
```


### How to use ###
1. Define your RequestType in RequestType.java
2. Implement you web request method in "Marketplace.java". To guide you, there are two example methods in that file, namely, *testAsynchronous()* and *testSynchronous()* methods. Most of the time you will need asynchronous types of request. As long as you are going to fire web request from any of the activity or fragment or ui classes, you will most probably need testAsynchronous() method. I have written an example in MainActivity.java. Following which, you will be able to implement the web request. Still, I am describing further steps below.
3. Implement "WebConnectionCallbacks" interface in your activity file. and just call the marketplace method you have implemented. Refer to MainActivity.java for example.

Hope it helps! In case of some clarification, you can trin-trin me at 9899637620.


### Advice ###
* "com.example.NetworkManager.TouchMeNot" => No need to touch this part. It will be as it is.
* If "Copying" is an issue, then just delete/modify comments in all the files. Just in case, I will take your side ;)

### Coming soon... ###
* Image uploader
* Image downloader

### Didn't feel need of the following: ###
* Http Headers