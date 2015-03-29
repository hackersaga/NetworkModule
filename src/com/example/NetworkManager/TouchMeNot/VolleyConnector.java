
package com.example.NetworkManager.TouchMeNot;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

public class VolleyConnector {

	private WebRequest webRequest;
	private Context mContext;
	public VolleyConnector(Context pContext, WebRequest pWebRequest){
		this.webRequest = pWebRequest;
		this.mContext = pContext;

	}

	public JSONObject start(){
		if(webRequest.isAsynchronous()){
			StringRequest jsonObjReq = new StringRequest(webRequest.getMethod(),
					webRequest.getURL(),
					new Response.Listener<String>() {
	
				@Override
				public void onResponse(String response) {
					if(webRequest.isCallbackEnabled()){
						try{
							JSONObject jobj = new JSONObject(response);
							webRequest.onComplete(jobj);
						}catch(Exception e){
							Log.e("ERROR","JSON PARSING ERROR. Look into VolleyConnector.start().");
							Log.d("Server Response",response);
							e.printStackTrace();
						}
						
					}
				}
			}, new Response.ErrorListener() {
	
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.d(webRequest.getRequestTag(), "Error: " + error.getMessage());
					if(webRequest.isCallbackEnabled()){
						webRequest.onError(error.getMessage());
					}
				};
	
			}){
				 @Override
				 protected Map<String,String> getParams(){ 
					 return webRequest.getParam();
				 }
			};
			VolleySingleton.getInstance(mContext).addToRequestQueue(jsonObjReq, webRequest.getRequestTag());
			return null;
		}
		else{
			RequestFuture<String> future = RequestFuture.newFuture();
			StringRequest request = new StringRequest(webRequest.getMethod(), webRequest.getURL(),
					future, future){
				 @Override
				 protected Map<String,String> getParams(){ 
					 return webRequest.getParam();
				 }
			};
			VolleySingleton.getInstance(mContext).addToRequestQueue(request, webRequest.getRequestTag());
			String response = null;
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
				Log.d("RESPONSE2", response);
				try {
					JSONObject jobj = new JSONObject(response.toString());
					return jobj;
				} catch (Exception e) {
					Log.e("ERROR","JSON PARSING ERROR 2. Look into VolleyConnector.start(): else part!.");
					Log.d("Server Response 2",response);
					e.printStackTrace();
				}
			}
			else{
				Log.d("RESPONSE2", "Oops! moment.");
			}
			
			return null;
		}
	}

	
}
