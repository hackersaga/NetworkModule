package com.example.NetworkManager.TouchMeNot;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;

import com.example.NetworkManager.RequestType;

public class WebRequest {

	private String mURL;
	private int method = 1;
	private HashMap<String, String> mParam;
	private HashMap<String, String> mHeader;
	private RequestType mRequestType;
	private WebConnectionCallbacks mCallbacks;
	private boolean isAsynchronous = true;
	private String requestTag = "REQUEST_TAG";

	/**
	 * @param activity: Reference to the activity which will be getting callbacks.
	 * @param pURL: Url
	 * @param pRequestType: Unique identity of the request.
	 * @inheritDoc Used when callback to the activity is needed.
	 */
	public WebRequest(Activity pActivity,String pURL, RequestType pRequestType) {
		this.mURL = pURL;
		this.mRequestType = pRequestType;
		this.mParam = new HashMap<String, String>();
		try{
			this.mCallbacks = (WebConnectionCallbacks) pActivity;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param pURL: Url
	 * @param pRequestType: Unique identity of the request.
	 * @inheritDoc Used when callback is not needed. For example: update functions
	 */
	public WebRequest(String pURL, RequestType pRequestType){
		this.mURL = pURL;
		this.mRequestType = pRequestType;
		this.isAsynchronous = false;
		this.mParam = new HashMap<String, String>();
	}

	public HashMap<String, String> getHeader() {
		return mHeader;
	}
	

	public void setHeader(HashMap<String, String> header) {
		this.mHeader = header;
	}

	public String getURL() {
		return mURL;
	}

	public HashMap<String, String> getParam() {
		return mParam;
	}

	public void addParam(String pKey, String pValue)
	{
		if(this.mParam==null){
			this.mParam = new HashMap<String,String>();
		}
		this.mParam.put(pKey, pValue);
	
	}

	public boolean isCallbackEnabled(){
		if(mCallbacks==null){
			return false;
		}
		return true;
	}

	public boolean isAsynchronous() {
		return isAsynchronous;
	}

	public RequestType getRequestType() {
		return mRequestType;
	}

	public void onComplete(JSONObject response){
		if(mCallbacks!=null)
			mCallbacks.onRequestComplete(mRequestType, response);
	}

	public void onError(String errorResponse){
		if(mCallbacks!=null)
			mCallbacks.onRequestError(mRequestType, errorResponse);
	}

	public int getMethod() {
		return method;
	}

	/**
	 * @param method
	 * @inheritDoc method == 0 => GET
	 * @inheritDoc method == 1 => POST
	 * @inheritDoc method == 2 => PUT
	 * @inheritDoc method == 4 => Delete
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	public String getRequestTag() {
		return requestTag;
	}

	public void setRequestTag(String requestTag) {
		this.requestTag = requestTag;
	}


}
