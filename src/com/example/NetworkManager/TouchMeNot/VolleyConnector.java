
package com.example.NetworkManager.TouchMeNot;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

public class VolleyConnector {

	private WebRequest webRequest;
	private Context mContext;
	public VolleyConnector(Context pContext, WebRequest pWebRequest){
		this.webRequest = pWebRequest;
		this.mContext = pContext;

	}

	public JSONObject start(){
		if(webRequest.isAsynchronous()){
			JsonObjectRequest jsonObjReq = new JsonObjectRequest(webRequest.getMethod(),
					webRequest.getURL(), webRequest.getParam(),
					new Response.Listener<JSONObject>() {
	
				@Override
				public void onResponse(JSONObject response) {
					if(webRequest.isCallbackEnabled()){
						webRequest.onComplete(response);
					}
				}
			}, new Response.ErrorListener() {
	
				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.d(webRequest.getRequestTag(), "Error: " + error.getMessage());
					if(webRequest.isCallbackEnabled()){
						webRequest.onError(error.getMessage());
					}
				};
	
			}){
				@Override
				public int getMethod(){
					return webRequest.getMethod();
				}
				
				@Override
				public HashMap<String, String> getHeaders(){
					return webRequest.getHeader();
				}
			};
			VolleySingleton.getInstance(mContext).addToRequestQueue(jsonObjReq, webRequest.getRequestTag());
			return null;
		}
		else{
			RequestFuture<JSONObject> future = RequestFuture.newFuture();
			JsonObjectRequest request = new JsonObjectRequest(webRequest.getURL(), webRequest.getParam(), future, future);
			VolleySingleton.getInstance(mContext).addToRequestQueue(request, webRequest.getRequestTag());
			JSONObject response = null;
			System.out.println("Being");
			long start = System.currentTimeMillis();
			try{
				response = future.get(10, TimeUnit.SECONDS);
				
				System.out.println("time taken: "+ (System.currentTimeMillis() - start));
			}
			catch(Exception e){
				  e.printStackTrace();
			}
			if(response!=null){
				Log.d("RESPONSE2", response.toString());
				
			}
			else{
				Log.d("RESPONSE2", "Oops! moment.");
			}
			return response;
		}
	}

	
}
