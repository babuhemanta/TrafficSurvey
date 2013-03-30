package com.shiftu.trafficsurvey;

import java.util.Calendar;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CustomActivity extends Activity {

	//DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	EditText edit1, edit2;
	Calendar myCalendar = Calendar.getInstance();
	Button button;
	public static String emp_name,emp_location, emp_updown;
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) 
		{
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};

	TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			myCalendar.set(Calendar.MINUTE, minute);
			updateLabel();
		}
	};

	private void updateLabel() {		
		
		edit1.setGravity(Gravity.CENTER);
		edit1.setText(new StringBuilder().append(formatDigits(myCalendar.get(Calendar.YEAR))).append("/").append(formatDigits(myCalendar.get(Calendar.MONTH)+1)).append("/").append(formatDigits(myCalendar.get(Calendar.DATE))));	
		edit2.setGravity(Gravity.CENTER);
		edit2.setText(new StringBuilder().append(formatDigits(myCalendar.get(Calendar.HOUR_OF_DAY))).append(":").append(formatDigits(myCalendar.get(Calendar.MINUTE))).append(":").append(formatDigits(myCalendar.get(Calendar.SECOND))));
	}
	@SuppressLint("UseValueOf")
	private String formatDigits(long num) {
		return (num < 10) ? "0" + num : new Long(num).toString();
		}
	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);
	setContentView(R.layout.activity_custom);
	/**
	 * get all the values from the the EmployeeActivity.java 
	 * such as empName, empLocation, empupdown.
	 */
	Intent getIntent=getIntent();
	emp_name = getIntent.getStringExtra("empName");
	emp_location = getIntent.getStringExtra("empLocation");
	emp_updown=getIntent.getStringExtra("empupdown");
	/**
	 * End of getting all values from EmplyeeActivity.java 
	 */
	edit1 = (EditText)findViewById(R.id.editText1);
	edit1.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			new DatePickerDialog(CustomActivity.this, d, myCalendar
					.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
					myCalendar.get(Calendar.DAY_OF_MONTH)).show();
		}
	});

	edit2 = (EditText)findViewById(R.id.editText2);
	edit2.setOnClickListener(new View.OnClickListener() {
		public  void onClick(View v) {
			new TimePickerDialog(CustomActivity.this, t, myCalendar
					.get(Calendar.HOUR_OF_DAY), myCalendar
					.get(Calendar.MINUTE), false).show();
		}
	});
	button = (Button) findViewById(R.id.button1);
	button.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub			
			Intent setIntent = new Intent(CustomActivity.this,CustomVehicleActivity.class);
			setIntent.putExtra("customDate",edit1.getText().toString());
			setIntent.putExtra("customTime", edit2.getText().toString());
			setIntent.putExtra("empName", emp_name);
			setIntent.putExtra("empLocation", emp_location);
			setIntent.putExtra("empupdown", emp_updown);	
            startActivity(setIntent);		
          
		}
	});
	updateLabel();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom, menu);
		return true;
	}

}
