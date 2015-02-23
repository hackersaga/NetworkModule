
package com.example.NetworkManager.TouchMeNot;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class VolleyConnector {

	private WebRequest webRequest;
	private Context mContext;
	public VolleyConnector(Context pContext, WebRequest pWebRequest){
		this.webRequest = pWebRequest;
		this.mContext = pContext;

	}

	public void start(){
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
		};
		
		VolleySingleton.getInstance(mContext).addToRequestQueue(jsonObjReq, webRequest.getRequestTag());
	}

	
}
