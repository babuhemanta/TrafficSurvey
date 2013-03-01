package com.shiftu.trafficsurvey;


import com.shiftu.trafficsurvey.database.TrafficDatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class EmployeeActivity extends Activity {

	Button mButtonnext;
	Intent mIntentUserInfo, nIntentUserInfo;
	EditText empName, empLocation;
	CheckBox mCheckBoxnew;
	Spinner mSpinner1, mSpinner2;
	
	Intent reportIntent;
	TrafficDatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		
		db = new TrafficDatabaseHandler(this);
		reportIntent = new Intent(EmployeeActivity.this, ReportActivity.class);
		mButtonnext=(Button) findViewById(R.id.button1);
		empName = (EditText) findViewById(R.id.editText1);
		empLocation = (EditText) findViewById(R.id.editText2);
		mCheckBoxnew =(CheckBox) findViewById(R.id.checkBox1);
		mSpinner1=(Spinner) findViewById(R.id.spinner1);
		mSpinner2=(Spinner) findViewById(R.id.spinner2);
		
		SharedPreferences settings=getSharedPreferences("Myprefs", 0);
		empLocation.setText(settings.getString("tvalue", ""));	
		
		mButtonnext.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String type = mSpinner2.getSelectedItem().toString();
				if(type.equalsIgnoreCase("Video"))
				{
					System.out.println("Spinner1:"+mSpinner1.getSelectedItem());
					System.out.println("spinner2:"+mSpinner2.getSelectedItem().toString());
					nIntentUserInfo=new Intent(EmployeeActivity.this, CustomActivity.class);
					nIntentUserInfo.putExtra("empName", empName.getText().toString());
					nIntentUserInfo.putExtra("empLocation", empLocation.getText().toString());
					nIntentUserInfo.putExtra("empupdown", mSpinner1.getSelectedItem().toString());				
					Log.e("User", empName.getText()+"and"+empLocation.getText());
					startActivity(nIntentUserInfo);
				}else
				{					
					mIntentUserInfo=new Intent(EmployeeActivity.this, VehicleActivity.class);
					mIntentUserInfo.putExtra("empName", empName.getText().toString());
					mIntentUserInfo.putExtra("empLocation", empLocation.getText().toString());
					mIntentUserInfo.putExtra("empupdown", mSpinner1.getSelectedItem().toString());				
					Log.e("User", empName.getText()+"and"+empLocation.getText());
					startActivity(mIntentUserInfo);
				}
				
				
			}
		});
		
		mCheckBoxnew.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(mCheckBoxnew.isChecked()==true)
				{
					Toast.makeText(EmployeeActivity.this, " First you generate Report ", Toast.LENGTH_SHORT).show();
					EmployeeActivity.this.startActivity(reportIntent);
					db.deletTablee();
					empLocation.setText("");
					Toast.makeText(EmployeeActivity.this, "Databae Reset", Toast.LENGTH_SHORT).show();
					
					
				}
				
			}
		});
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences settings=getSharedPreferences("Myprefs", 0);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("tvalue", empLocation.getText().toString());
		editor.commit();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences settings=getSharedPreferences("Myprefs", 0);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("tvalue", empLocation.getText().toString());
		editor.commit();
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		SharedPreferences settings=getSharedPreferences("Myprefs", 0);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("tvalue", empLocation.getText().toString());
		editor.commit();
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences settings=getSharedPreferences("Myprefs", 0);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("tvalue", empLocation.getText().toString());
		editor.commit();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_employee, menu);
		return true;
	}

}
