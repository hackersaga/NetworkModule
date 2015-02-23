package com.example.NetworkManager.TouchMeNot;

import org.json.JSONObject;

import com.example.NetworkManager.RequestType;


public interface WebConnectionCallbacks {

	public void onRequestComplete(RequestType requestType, JSONObject response);
	
	public void onRequestError(RequestType requestType, String errorResponse);
	
}
