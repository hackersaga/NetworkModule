package com.example.modweb;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.NetworkManager.NetworkService;
import com.example.NetworkManager.RequestType;
import com.example.NetworkManager.TouchMeNot.WebConnectionCallbacks;

public class MainActivity extends ActionBarActivity implements WebConnectionCallbacks{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this, NetworkService.class);
		startService(intent);
	}

	@Override
	public void onRequestComplete(RequestType requestType, JSONObject response) {
		switch (requestType) {
		case RequestTypeTesting:
		{
			Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void onRequestError(RequestType requestType, String errorResponse) {
		// TODO Auto-generated method stub		
	}


}
