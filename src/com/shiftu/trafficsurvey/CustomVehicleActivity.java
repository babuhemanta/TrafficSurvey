package com.shiftu.trafficsurvey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.shiftu.trafficsurvey.database.TrafficDatabaseHandler;
import com.shiftu.trafficsurvey.database.TrafficDatabaseHandler.DATA;

public class CustomVehicleActivity extends Activity implements View.OnClickListener{
	/**
	 * 1. create 8X3 image view 2. onclick image increase the count 3. onclick
	 * image show message the date and time
	 */
	public static int myClickCount = 0;
	public static String emp_name,custom_time;
	public static String emp_location;
	public static String emp_updown, custom_date;
	private long userId;
	private long userLocation;//,userupdown;
	final static String path = "trafficsurvey.db";
	
	TextView text1, text2, text3, text4, text5, text6, text7, text8, text9,
			text10, text11, text12, text13, text14, text15, text16, text17,
			text18, text19, text20, text21, text22, text23;
	Intent reportIntent, viewIntentdata;	
	ImageView image1, image2, image3, image4, image5, image6, image7, image8,
			image9, image10, image11, image12, image13, image14, image15,
			image16, image17, image18, image19, image20, image21, image22;	
	String vcount1, vcount2, vcount3, vcount4, vcount5, vcount6, vcount7,
			vcount8, vcount9, vcount10, vcount11, vcount12, vcount13, vcount14,
			vcount15, vcount16, vcount17, vcount18, vcount19, vcount20,
			vcount21, vcount22;
	private TextView mTimeLabel;
    private Button mStartButton;
    private Button mStopButton;
    private Button mResetButton;
    private Handler mHandler = new Handler();
	private long mMillisInitial;
	int Hour, Min, Sec;
	TrafficDatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customvehicle);
		/**
		 *  Get the EmplyeeName, Location and Direction passed from CustomActivity.java which passed from EmployeeActivity.
		 *  Also new Intent values are included like CustomDate and CustomTime
		 */
		Intent intentname = getIntent();		
		emp_name = intentname.getStringExtra("empName");
		emp_location = intentname.getStringExtra("empLocation");
		emp_updown=intentname.getStringExtra("empupdown");
		custom_date=intentname.getStringExtra("customDate");
		custom_time=intentname.getStringExtra("customTime");
		/**
		 * End of getting Intent values from CustomActivity
		 */
		reportIntent = new Intent(CustomVehicleActivity.this, ReportActivity.class);
		viewIntentdata=new Intent(CustomVehicleActivity.this,DataviewActivity.class);
		Log.e("vehicle", emp_name + "." + emp_location);
		System.out.println("Intent values are:" + emp_name + " and "+ emp_location +"and "+custom_date+ " and "+custom_time+ " and "+emp_updown );
		db = new TrafficDatabaseHandler(this);
		userId = db.userInfo(emp_name);
		userLocation = db.locInfo(emp_location);		
		
		vcount1 = Integer.toString(db.getVehicleCount(1));
		vcount2 = Integer.toString(db.getVehicleCount(2));
		vcount3 = Integer.toString(db.getVehicleCount(3));
		vcount4 = Integer.toString(db.getVehicleCount(4));
		vcount5 = Integer.toString(db.getVehicleCount(5));
		vcount6 = Integer.toString(db.getVehicleCount(6));
		vcount7 = Integer.toString(db.getVehicleCount(7));
		vcount8 = Integer.toString(db.getVehicleCount(8));
		vcount9 = Integer.toString(db.getVehicleCount(9));
		vcount10 = Integer.toString(db.getVehicleCount(10));
		vcount11 = Integer.toString(db.getVehicleCount(11));
		vcount12 = Integer.toString(db.getVehicleCount(12));
		vcount13 = Integer.toString(db.getVehicleCount(13));
		vcount14 = Integer.toString(db.getVehicleCount(14));
		vcount15 = Integer.toString(db.getVehicleCount(15));
		vcount16 = Integer.toString(db.getVehicleCount(16));
		vcount17 = Integer.toString(db.getVehicleCount(17));
		vcount18 = Integer.toString(db.getVehicleCount(18));
		vcount19 = Integer.toString(db.getVehicleCount(19));
		vcount20 = Integer.toString(db.getVehicleCount(20));
		vcount21 = Integer.toString(db.getVehicleCount(21));
		vcount22 = Integer.toString(db.getVehicleCount(22));

		text1 = (TextView) findViewById(R.id.text1);
		text2 = (TextView) findViewById(R.id.text2);
		text3 = (TextView) findViewById(R.id.text3);
		text4 = (TextView) findViewById(R.id.text4);
		text5 = (TextView) findViewById(R.id.text5);
		text6 = (TextView) findViewById(R.id.text6);
		text7 = (TextView) findViewById(R.id.text7);
		text8 = (TextView) findViewById(R.id.text8);
		text9 = (TextView) findViewById(R.id.text9);
		text10 = (TextView) findViewById(R.id.text10);
		text11 = (TextView) findViewById(R.id.text11);
		text12 = (TextView) findViewById(R.id.text12);
		text13 = (TextView) findViewById(R.id.text13);
		text14 = (TextView) findViewById(R.id.text14);
		text15 = (TextView) findViewById(R.id.text15);
		text16 = (TextView) findViewById(R.id.text16);
		text17 = (TextView) findViewById(R.id.text17);
		text18 = (TextView) findViewById(R.id.text18);
		text19 = (TextView) findViewById(R.id.text19);
		text20 = (TextView) findViewById(R.id.text20);
		text21 = (TextView) findViewById(R.id.text21);
		text22 = (TextView) findViewById(R.id.text22);
		
		text1.setText(vcount1);
		text2.setText(vcount2);
		text3.setText(vcount3);
		text4.setText(vcount4);
		text5.setText(vcount5);
		text6.setText(vcount6);
		text7.setText(vcount7);
		text8.setText(vcount8);
		text9.setText(vcount9);
		text10.setText(vcount10);
		text11.setText(vcount11);
		text12.setText(vcount12);
		text13.setText(vcount13);
		text14.setText(vcount14);
		text15.setText(vcount15);
		text16.setText(vcount16);
		text17.setText(vcount17);
		text18.setText(vcount18);
		text19.setText(vcount19);
		text20.setText(vcount20);
		text21.setText(vcount21);
		text22.setText(vcount22);
		
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image3 = (ImageView) findViewById(R.id.image3);
		image4 = (ImageView) findViewById(R.id.image4);
		image5 = (ImageView) findViewById(R.id.image5);
		image6 = (ImageView) findViewById(R.id.image6);
		image7 = (ImageView) findViewById(R.id.image7);
		image8 = (ImageView) findViewById(R.id.image8);
		image9 = (ImageView) findViewById(R.id.image9);
		image10 = (ImageView) findViewById(R.id.image10);
		image11 = (ImageView) findViewById(R.id.image11);
		image12 = (ImageView) findViewById(R.id.image12);
		image13 = (ImageView) findViewById(R.id.image13);
		image14 = (ImageView) findViewById(R.id.image14);
		image15 = (ImageView) findViewById(R.id.image15);
		image16 = (ImageView) findViewById(R.id.image16);
		image17 = (ImageView) findViewById(R.id.image17);
		image18 = (ImageView) findViewById(R.id.image18);
		image19 = (ImageView) findViewById(R.id.image19);
		image20 = (ImageView) findViewById(R.id.image20);
		image21 = (ImageView) findViewById(R.id.image21);
		image22 = (ImageView) findViewById(R.id.image22);

		image1.setOnClickListener(this);
		image2.setOnClickListener(this);
		image3.setOnClickListener(this);
		image4.setOnClickListener(this);
		image5.setOnClickListener(this);
		image6.setOnClickListener(this);
		image7.setOnClickListener(this);
		image8.setOnClickListener(this);
		image9.setOnClickListener(this);
		image10.setOnClickListener(this);
		image11.setOnClickListener(this);
		image12.setOnClickListener(this);
		image13.setOnClickListener(this);
		image14.setOnClickListener(this);
		image15.setOnClickListener(this);
		image16.setOnClickListener(this);
		image17.setOnClickListener(this);
		image18.setOnClickListener(this);
		image19.setOnClickListener(this);
		image20.setOnClickListener(this);
		image21.setOnClickListener(this);
		image22.setOnClickListener(this);
		/**
		 * coding for custom Time Calculation
		 */
		 mTimeLabel = (TextView) findViewById(R.id.text23);
         mStartButton=(Button)findViewById(R.id.button1);
         mStopButton=(Button) findViewById(R.id.button2);
         mResetButton=(Button) findViewById(R.id.button3);
         mTimeLabel.setText(custom_time);
         //mStartTime = System.currentTimeMillis();
         mStartButton.setOnClickListener(new Button.OnClickListener(){
  		   @Override
  		   public void onClick(View v) {
  		    // TODO Auto-generated method stub
  			  mStart();
	  		  String split_cutomTime[]=custom_time.split(":");
	          Hour=Integer.parseInt(split_cutomTime[0]);
	          Min=Integer.parseInt(split_cutomTime[1]);
	          Sec=Integer.parseInt(split_cutomTime[2]);
  		  
  		   }});
         mStopButton.setOnClickListener(new Button.OnClickListener(){
   		   @Override
   		   public void onClick(View v) {
   		    // TODO Auto-generated method stub
   		    mPause();
   		   }});
         mResetButton.setOnClickListener(new Button.OnClickListener(){
        	@Override
  		   public void onClick(View v) {
  		    // TODO Auto-generated method stub
  		    mStop();
  		    mTimeLabel.setText(custom_time);
  		   }});
    		   
	}
	private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {             
     	   long millis = SystemClock.uptimeMillis() ;
     	   long diff= millis - mMillisInitial ;
     	   if(diff >= 500 )
     	   {           		 
     		   if(Sec < 59)
     		   {
     			   Sec++;
     		   }
     		   else if(Min < 59 ){
     			   Sec=0;
     			   Min++;
     			   
     		   }else if (Hour < 23)
     		   {
     			   Min=0;
     			   Hour++;
     		   }
     		   else
     		   {
     			   Hour=0;
     		   }
     		   
     		   mTimeLabel.setText(formatDigits(Hour)+":"+formatDigits(Min)+":"+formatDigits(Sec));
     	   }
     	  
     	   mHandler.postAtTime(this, (SystemClock.uptimeMillis() + 700));            	  
        }
        private String formatDigits(long num) {
    		return (num < 10) ? "0" + num : new Long(num).toString();
    		}
     };
     
 
     private void mStart()
     {
         super.onPause();
         mMillisInitial = SystemClock.uptimeMillis() ;
         mHandler.removeCallbacks(mUpdateTimeTask);
         mHandler.postDelayed(mUpdateTimeTask, 700);
     }

     
     private void mPause()
     {
             super.onPause();
             mHandler.removeCallbacks(mUpdateTimeTask);
     }
     private void mStop()
     {
     	super.onStop();
     	//mTimeLabel.setText(custom_time);
     	mHandler.removeCallbacks(mUpdateTimeTask);
     }
     
	@Override
	public void onClick(View v) {
		String data_Dt = custom_date;
		String time_Tm = mTimeLabel.getText().toString();
		
		String str1[]=time_Tm.split(":");
		String timestampvalue=str1[0];
		
		System.out.println("Custom Time Stamp "+timestampvalue);
		System.out.println("custom date  is " + data_Dt);
		
		switch (v.getId()) {

		case R.id.image1:
			System.out.println("Image one  Clicked;");
			System.out.println("1="+userId+" "+data_Dt+" "+time_Tm+" "+userLocation+" "+timestampvalue+" "+emp_updown );
			ContentValues values = new ContentValues();
			values.put(DATA.VEHICLE_ID, 1);
			values.put(DATA.EMPLOYEE_ID, userId);
			values.put(DATA.DATE, data_Dt);
			values.put(DATA.TIME, time_Tm);
			values.put(DATA.LOCATION_ID, userLocation);
			values.put(DATA.TIMESTAMP,timestampvalue);
			values.put(DATA.UP_DOWN,emp_updown);
			
			db.insertVehicleData(values);
			vcount1 = Integer.toString(db.getVehicleCount(1));
			// TextView text1 = (TextView) findViewById(R.id.text1);
			text1.setText(vcount1);
			break;

		case R.id.image2:
			System.out.println("Image two  Clicked;");

			ContentValues values1 = new ContentValues();
			values1.put(DATA.VEHICLE_ID, 2);
			values1.put(DATA.EMPLOYEE_ID, userId);
			values1.put(DATA.DATE, data_Dt);
			values1.put(DATA.TIME, time_Tm);
			values1.put(DATA.LOCATION_ID, userLocation);
			values1.put(DATA.TIMESTAMP,timestampvalue);
			values1.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values1);
			vcount2 = Integer.toString(db.getVehicleCount(2));

			// TextView text2 = (TextView) findViewById(R.id.text2);
			text2.setText(vcount2);

			break;
		case R.id.image3:
			// System.out.println("Image two  Clicked;");

			ContentValues values3 = new ContentValues();
			values3.put(DATA.VEHICLE_ID, 3);
			values3.put(DATA.EMPLOYEE_ID, userId);
			values3.put(DATA.DATE, data_Dt);
			values3.put(DATA.TIME, time_Tm);
			values3.put(DATA.LOCATION_ID, userLocation);
			values3.put(DATA.TIMESTAMP,timestampvalue);
			values3.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values3);
			vcount3 = Integer.toString(db.getVehicleCount(3));
			// TextView text3 = (TextView) findViewById(R.id.text3);
			text3.setText(vcount3);

			break;
		case R.id.image4:
			// System.out.println("Image two  Clicked;");

			ContentValues values4 = new ContentValues();
			values4.put(DATA.VEHICLE_ID, 4);
			values4.put(DATA.EMPLOYEE_ID, userId);
			values4.put(DATA.DATE, data_Dt);
			values4.put(DATA.TIME, time_Tm);
			values4.put(DATA.LOCATION_ID, userLocation);
			values4.put(DATA.TIMESTAMP,timestampvalue);
			values4.put(DATA.UP_DOWN,emp_updown);
			
			db.insertVehicleData(values4);
			vcount4 = Integer.toString(db.getVehicleCount(4));
			// TextView text4 = (TextView) findViewById(R.id.text4);
			text4.setText(vcount4);

			break;
		case R.id.image5:
			// System.out.println("Image two  Clicked;");

			ContentValues values5 = new ContentValues();
			values5.put(DATA.VEHICLE_ID, 5);
			values5.put(DATA.EMPLOYEE_ID, userId);
			values5.put(DATA.DATE, data_Dt);
			values5.put(DATA.TIME, time_Tm);
			values5.put(DATA.LOCATION_ID, userLocation);
			values5.put(DATA.TIMESTAMP,timestampvalue);
			values5.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values5);
			vcount5 = Integer.toString(db.getVehicleCount(5));
			// TextView text5 = (TextView) findViewById(R.id.text5);
			text5.setText(vcount5);

			break;
		case R.id.image6:
			// System.out.println("Image two  Clicked;");

			ContentValues values6 = new ContentValues();
			values6.put(DATA.VEHICLE_ID, 6);
			values6.put(DATA.EMPLOYEE_ID, userId);
			values6.put(DATA.DATE, data_Dt);
			values6.put(DATA.TIME, time_Tm);
			values6.put(DATA.LOCATION_ID, userLocation);
			values6.put(DATA.TIMESTAMP,timestampvalue);
			values6.put(DATA.UP_DOWN,emp_updown);
			
			db.insertVehicleData(values6);
			vcount6 = Integer.toString(db.getVehicleCount(6));
			// TextView text6 = (TextView) findViewById(R.id.text6);
			text6.setText(vcount6);

			break;
		case R.id.image7:
			// System.out.println("Image two  Clicked;");

			ContentValues values7 = new ContentValues();
			values7.put(DATA.VEHICLE_ID, 7);
			values7.put(DATA.EMPLOYEE_ID, userId);
			values7.put(DATA.DATE, data_Dt);
			values7.put(DATA.TIME, time_Tm);
			values7.put(DATA.LOCATION_ID, userLocation);
			values7.put(DATA.TIMESTAMP,timestampvalue);
			values7.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values7);
			vcount7 = Integer.toString(db.getVehicleCount(7));
			// TextView text7 = (TextView) findViewById(R.id.text7);
			text7.setText(vcount7);

			break;
		case R.id.image8:
			// System.out.println("Image two  Clicked;");

			ContentValues values8 = new ContentValues();
			values8.put(DATA.VEHICLE_ID, 8);
			values8.put(DATA.EMPLOYEE_ID, userId);
			values8.put(DATA.DATE, data_Dt);
			values8.put(DATA.TIME, time_Tm);
			values8.put(DATA.LOCATION_ID, userLocation);
			values8.put(DATA.TIMESTAMP,timestampvalue);
			values8.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values8);
			vcount8 = Integer.toString(db.getVehicleCount(8));
			// TextView text8 = (TextView) findViewById(R.id.text8);
			text8.setText(vcount8);

			break;
		case R.id.image9:
			// System.out.println("Image two  Clicked;");

			ContentValues values9 = new ContentValues();
			values9.put(DATA.VEHICLE_ID, 9);
			values9.put(DATA.EMPLOYEE_ID, userId);
			values9.put(DATA.DATE, data_Dt);
			values9.put(DATA.TIME, time_Tm);
			values9.put(DATA.LOCATION_ID, userLocation);
			values9.put(DATA.TIMESTAMP,timestampvalue);
			values9.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values9);
			vcount9 = Integer.toString(db.getVehicleCount(9));
			// TextView text9 = (TextView) findViewById(R.id.text9);
			text9.setText(vcount9);

			break;
		case R.id.image10:
			// System.out.println("Image two  Clicked;");

			ContentValues values10 = new ContentValues();
			values10.put(DATA.VEHICLE_ID, 10);
			values10.put(DATA.EMPLOYEE_ID, userId);
			values10.put(DATA.DATE, data_Dt);
			values10.put(DATA.TIME, time_Tm);
			values10.put(DATA.LOCATION_ID, userLocation);
			values10.put(DATA.TIMESTAMP,timestampvalue);
			values10.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values10);
			vcount10 = Integer.toString(db.getVehicleCount(10));
			// TextView text10 = (TextView) findViewById(R.id.text10);
			text10.setText(vcount10);

			break;

		case R.id.image11:
			// System.out.println("Image two  Clicked;");

			ContentValues values11 = new ContentValues();
			values11.put(DATA.VEHICLE_ID, 11);
			values11.put(DATA.EMPLOYEE_ID, userId);
			values11.put(DATA.DATE, data_Dt);
			values11.put(DATA.TIME, time_Tm);
			values11.put(DATA.LOCATION_ID, userLocation);
			values11.put(DATA.TIMESTAMP,timestampvalue);
			values11.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values11);
			vcount11 = Integer.toString(db.getVehicleCount(11));
			// TextView text11 = (TextView) findViewById(R.id.text11);
			text11.setText(vcount11);

			break;

		case R.id.image12:
			// System.out.println("Image two  Clicked;");

			ContentValues values12 = new ContentValues();
			values12.put(DATA.VEHICLE_ID, 12);
			values12.put(DATA.EMPLOYEE_ID, userId);
			values12.put(DATA.DATE, data_Dt);
			values12.put(DATA.TIME, time_Tm);
			values12.put(DATA.LOCATION_ID, userLocation);
			values12.put(DATA.TIMESTAMP,timestampvalue);
			values12.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values12);
			vcount12 = Integer.toString(db.getVehicleCount(12));
			// TextView text12 = (TextView) findViewById(R.id.text12);
			text12.setText(vcount12);

			break;

		case R.id.image13:
			// System.out.println("Image two  Clicked;");

			ContentValues values13 = new ContentValues();
			values13.put(DATA.VEHICLE_ID, 13);
			values13.put(DATA.EMPLOYEE_ID, userId);
			values13.put(DATA.DATE, data_Dt);
			values13.put(DATA.TIME, time_Tm);
			values13.put(DATA.LOCATION_ID, userLocation);
			values13.put(DATA.TIMESTAMP,timestampvalue);
			values13.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values13);
			vcount13 = Integer.toString(db.getVehicleCount(13));
			// TextView text13 = (TextView) findViewById(R.id.text13);
			text13.setText(vcount13);

			break;

		case R.id.image14:
			// System.out.println("Image two  Clicked;");

			ContentValues values14 = new ContentValues();
			values14.put(DATA.VEHICLE_ID, 14);
			values14.put(DATA.EMPLOYEE_ID, userId);
			values14.put(DATA.DATE, data_Dt);
			values14.put(DATA.TIME, time_Tm);
			values14.put(DATA.LOCATION_ID, userLocation);
			values14.put(DATA.TIMESTAMP,timestampvalue);
			values14.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values14);
			vcount14 = Integer.toString(db.getVehicleCount(14));
			// TextView text14 = (TextView) findViewById(R.id.text14);
			text14.setText(vcount14);

			break;

		case R.id.image15:
			// System.out.println("Image two  Clicked;");

			ContentValues values15 = new ContentValues();
			values15.put(DATA.VEHICLE_ID, 15);
			values15.put(DATA.EMPLOYEE_ID, userId);
			values15.put(DATA.DATE, data_Dt);
			values15.put(DATA.TIME, time_Tm);
			values15.put(DATA.LOCATION_ID, userLocation);
			values15.put(DATA.TIMESTAMP,timestampvalue);
			values15.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values15);
			vcount15 = Integer.toString(db.getVehicleCount(15));
			// TextView text15 = (TextView) findViewById(R.id.text15);
			text15.setText(vcount15);

			break;

		case R.id.image16:
			// System.out.println("Image two  Clicked;");

			ContentValues values16 = new ContentValues();
			values16.put(DATA.VEHICLE_ID, 16);
			values16.put(DATA.EMPLOYEE_ID, userId);
			values16.put(DATA.DATE, data_Dt);
			values16.put(DATA.TIME, time_Tm);
			values16.put(DATA.LOCATION_ID, userLocation);
			values16.put(DATA.TIMESTAMP,timestampvalue);
			values16.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values16);
			vcount16 = Integer.toString(db.getVehicleCount(16));
			// TextView text16 = (TextView) findViewById(R.id.text16);
			text16.setText(vcount16);

			break;

		case R.id.image17:
			// System.out.println("Image two  Clicked;");

			ContentValues values17 = new ContentValues();
			values17.put(DATA.VEHICLE_ID, 17);
			values17.put(DATA.EMPLOYEE_ID, userId);
			values17.put(DATA.DATE, data_Dt);
			values17.put(DATA.TIME, time_Tm);
			values17.put(DATA.LOCATION_ID, userLocation);
			values17.put(DATA.TIMESTAMP,timestampvalue);
			values17.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values17);
			vcount17 = Integer.toString(db.getVehicleCount(17));
			// TextView text17 = (TextView) findViewById(R.id.text17);
			text17.setText(vcount17);

			break;

		case R.id.image18:
			// System.out.println("Image two  Clicked;");

			ContentValues values18 = new ContentValues();
			values18.put(DATA.VEHICLE_ID, 18);
			values18.put(DATA.EMPLOYEE_ID, userId);
			values18.put(DATA.DATE, data_Dt);
			values18.put(DATA.TIME, time_Tm);
			values18.put(DATA.LOCATION_ID, userLocation);
			values18.put(DATA.TIMESTAMP,timestampvalue);
			values18.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values18);
			vcount18 = Integer.toString(db.getVehicleCount(18));
			// TextView text18 = (TextView) findViewById(R.id.text18);
			text18.setText(vcount18);

			break;

		case R.id.image19:
			// System.out.println("Image two  Clicked;");

			ContentValues values19 = new ContentValues();
			values19.put(DATA.VEHICLE_ID, 19);
			values19.put(DATA.EMPLOYEE_ID, userId);
			values19.put(DATA.DATE, data_Dt);
			values19.put(DATA.TIME, time_Tm);
			values19.put(DATA.LOCATION_ID, userLocation);
			values19.put(DATA.TIMESTAMP,timestampvalue);
			values19.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values19);
			vcount19 = Integer.toString(db.getVehicleCount(19));
			// TextView text19 = (TextView) findViewById(R.id.text19);
			text19.setText(vcount19);

			break;

		case R.id.image20:
			// System.out.println("Image two  Clicked;");

			ContentValues values20 = new ContentValues();
			values20.put(DATA.VEHICLE_ID, 20);
			values20.put(DATA.EMPLOYEE_ID, userId);
			values20.put(DATA.DATE, data_Dt);
			values20.put(DATA.TIME, time_Tm);
			values20.put(DATA.LOCATION_ID, userLocation);
			values20.put(DATA.TIMESTAMP,timestampvalue);
			values20.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values20);
			vcount20 = Integer.toString(db.getVehicleCount(20));
			// TextView text20 = (TextView) findViewById(R.id.text20);
			text20.setText(vcount20);

			break;

		case R.id.image21:
			// System.out.println("Image two  Clicked;");

			ContentValues values21 = new ContentValues();
			values21.put(DATA.VEHICLE_ID, 21);
			values21.put(DATA.EMPLOYEE_ID, userId);
			values21.put(DATA.DATE, data_Dt);
			values21.put(DATA.TIME, time_Tm);
			values21.put(DATA.LOCATION_ID, userLocation);
			values21.put(DATA.TIMESTAMP,timestampvalue);
			values21.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values21);
			vcount21 = Integer.toString(db.getVehicleCount(21));
			// TextView text21 = (TextView) findViewById(R.id.text21);
			text21.setText(vcount21);

			break;

		case R.id.image22:
			// System.out.println("Image two  Clicked;");

			ContentValues values22 = new ContentValues();
			values22.put(DATA.VEHICLE_ID, 22);
			values22.put(DATA.EMPLOYEE_ID, userId);
			values22.put(DATA.DATE, data_Dt);
			values22.put(DATA.TIME, time_Tm);
			values22.put(DATA.LOCATION_ID, userLocation);
			values22.put(DATA.TIMESTAMP,timestampvalue);
			values22.put(DATA.UP_DOWN,emp_updown);
			db.insertVehicleData(values22);
			vcount22 = Integer.toString(db.getVehicleCount(22));
			// TextView text22 = (TextView) findViewById(R.id.text22);
			text22.setText(vcount22);

			break;

		default:
			break;
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_vehicle, menu);
		return true;
	}
	/**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        
        switch (item.getItemId())
        {
        case R.id.menu_viewdb:
        	// Single menu item is selected do something
        	CustomVehicleActivity.this.startActivity(viewIntentdata);
            Toast.makeText(CustomVehicleActivity.this, "View DataBase is Selected", Toast.LENGTH_SHORT).show();
            return true;
            //break;
        case R.id.menu_report:
        	//startActivity(reportIntent);
        	CustomVehicleActivity.this.startActivity(reportIntent);
        	Toast.makeText(CustomVehicleActivity.this, "Report is Selected", Toast.LENGTH_SHORT).show();
            return true;
        	//break;
        case R.id.menu_reset:
        	db.deletTablee();

    		text1.setText(vcount1);
    		text2.setText(vcount2);
    		text3.setText(vcount3);
    		text4.setText(vcount4);
    		text5.setText(vcount5);
    		text6.setText(vcount6);
    		text7.setText(vcount7);
    		text8.setText(vcount8);
    		text9.setText(vcount9);
    		text10.setText(vcount10);
    		text11.setText(vcount11);
    		text12.setText(vcount12);
    		text13.setText(vcount13);
    		text14.setText(vcount14);
    		text15.setText(vcount15);
    		text16.setText(vcount16);
    		text17.setText(vcount17);
    		text18.setText(vcount18);
    		text19.setText(vcount19);
    		text20.setText(vcount20);
    		text21.setText(vcount21);
    		text22.setText(vcount22);
    		
        	Toast.makeText(CustomVehicleActivity.this, "Reset is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_exit:
        	finish();
        	Toast.makeText(CustomVehicleActivity.this, "Exit", Toast.LENGTH_SHORT).show();
            return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
        //return true;
    }


}
