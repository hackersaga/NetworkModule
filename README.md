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


### Advice ###
* "com.example.NetworkManager.TouchMeNot" => No need to touch this part. It will be as it is.
* If "Copying" is an issue, then just delete/modify comments in all the files. In case, I will take your side ;)

### Coming soon... ###
* Image uploader
* Image downloader

### Didn't feel need of the following: ###
* Http Headers