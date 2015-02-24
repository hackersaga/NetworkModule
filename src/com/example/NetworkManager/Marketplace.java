/**
 *	Marketplace.java
 *  Created by Sagar Patidar on Dec 11, 2014, 5:25:11 PM
 *	Copyright © 2014 by Share Infotech Pvt. Ltd
 *	All rights reserved.
 */

package com.example.NetworkManager;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;

import com.example.NetworkManager.TouchMeNot.VolleyConnector;
import com.example.NetworkManager.TouchMeNot.WebRequest;

public class Marketplace {

	private Context mContext;
	public static final String DOMAIN_NAME = "http://www.cibola.co.in/";
	
	public Marketplace(Context pContext){
		this.mContext = pContext;
	}
	
	public String getURL(RequestType type ){
		String url = DOMAIN_NAME;
		switch (type) {
			case RequestTypeTesting:
			{
				url += "test.php";
				break;
			}
			default:
				break;
		}

		return url;
	}

	
	public void testAsynchronous(Activity activity, RequestType type){
		WebRequest request = new WebRequest(activity, getURL(RequestType.RequestTypeTesting), RequestType.RequestTypeTesting);
		request.addParam("param", "Romeo");
		
		VolleyConnector connector = new VolleyConnector(mContext, request);
		connector.start();
	}

	public JSONObject testSynchronous(Activity activity, RequestType type){
		WebRequest request = new WebRequest(getURL(RequestType.RequestTypeTesting), RequestType.RequestTypeTesting);
		request.addParam("param", "Romeo");
		
		VolleyConnector connector = new VolleyConnector(mContext, request);
		return connector.start();
	}
	
	
}