/**
 *	TransactionService.java
 *  Created by Sagar Patidar on 01-Sep-2014, 2:29:49 PM
 *	Copyright © 2014 by Cibola Technologies Pvt. Ltd
 *	All rights reserved.
 */

package com.example.NetworkManager;

import android.app.IntentService;
import android.content.Intent;

public class NetworkService extends IntentService {

	public static boolean isRunning = false;
	
	public NetworkService(String name) {
		super(name);
	}
	
	public NetworkService(){
		super("ContactsService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Marketplace marketplace = new Marketplace(getApplicationContext());
		marketplace.testSynchronous(null, RequestType.RequestTypeTesting);
		
	}
	
	
		
}

