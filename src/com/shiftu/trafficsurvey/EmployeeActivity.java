package com.shiftu.trafficsurvey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EmployeeActivity extends Activity {

	Button mButtonnext;
	Intent mIntentUserInfo;
	EditText empName, empLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		
				
		
		mButtonnext=(Button) findViewById(R.id.button1);
		empName = (EditText) findViewById(R.id.editText1);
		empLocation = (EditText) findViewById(R.id.editText2);
		//String eName = empName.getText().toString();
		//String eLocation = empLocation.getText().toString();		
		mButtonnext.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIntentUserInfo=new Intent(EmployeeActivity.this, VehicleActivity.class);
				mIntentUserInfo.putExtra("empName", empName.getText().toString());
				mIntentUserInfo.putExtra("empLocation", empLocation.getText().toString());
				Log.e("User", empName.getText()+"and"+empLocation.getText());
				startActivity(mIntentUserInfo);
				
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_employee, menu);
		return true;
	}

}
