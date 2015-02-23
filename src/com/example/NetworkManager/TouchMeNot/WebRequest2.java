package com.example.NetworkManager.TouchMeNot;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;

import com.example.NetworkManager.RequestType;


public class WebRequest2 {

	private String URL;
	private int method = 1;
	private boolean callbackEnabled = false;
	private Map<String,String> param;
	private Map<String, String> header;
	private RequestType requestType;
	private WebConnectionCallbacks callbacks;
	private boolean doInAsyncTask = false;
	private String requestTag = "REQUEST_TAG";

	/**
	 * Used when callback to the activity is needed.
	 * @param activity
	 * @param pURL
	 * @param type
	 */
	public WebRequest2(Activity activity,String pURL, RequestType type) {
		this.setURL(pURL);
		this.setRequestType(type);
		this.callbacks = (WebConnectionCallbacks) activity;
		this.callbackEnabled = true;
	}
	
	/**
	 * Used when callback is not needed. For example: update functions
	 * @param pURL
	 * @param type
	 */
	public WebRequest2(String pURL, RequestType type){
		this.setURL(pURL);
		this.setRequestType(type);
		this.callbackEnabled = false;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Map<String,String> getParam() {
		return param;
	}

	public void setParam(Map<String,String> param) {
		this.param = param;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public void addParam(String pKey, String pValue)
	{
		if(this.param==null)
		{
			this.param = new HashMap<String,String>();
		}
		this.param.put(pKey,pValue);
	}

	public boolean isDoInAsyncTask() {
		return doInAsyncTask;
	}

	public void setDoInAsyncTask(boolean doInAsyncTask) {
		this.doInAsyncTask = doInAsyncTask;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	
	public void onComplete(JSONObject response){
		if(callbacks!=null)
			callbacks.onRequestComplete(requestType, response);
	}

	public void onError(String errorResponse){
		if(callbacks!=null)
			callbacks.onRequestError(requestType, errorResponse);
	}

	public int getMethod() {
		return method;
	}

	/**
	 * method == 0 => GET
	 * method == 1 => POST
	 * method == 2 => PUT
	 * method == 4 => Delete
	 * @param method
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	public boolean isCallbackEnabled() {
		return callbackEnabled;
	}

	public void setCallbackEnabled(boolean callbackEnabled) {
		this.callbackEnabled = callbackEnabled;
	}

	public String getRequestTag() {
		return requestTag;
	}

	public void setRequestTag(String requestTag) {
		this.requestTag = requestTag;
	}


}
