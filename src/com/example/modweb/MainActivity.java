package com.example.modweb;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.NetworkManager.Marketplace;
import com.example.NetworkManager.NetworkService;
import com.example.NetworkManager.RequestType;
import com.example.NetworkManager.TouchMeNot.WebConnectionCallbacks;

public class MainActivity extends ActionBarActivity implements WebConnectionCallbacks{

	private Marketplace marketplace;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		marketplace = new Marketplace(this);
		Button b = (Button) findViewById(R.id.textview);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(new Intent(MainActivity.this, NetworkService.class));
				marketplace.testAsynchronous(MainActivity.this, RequestType.RequestTypeTesting);
				Log.d("Debug", "Request fired.");
			}
		});
		
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
