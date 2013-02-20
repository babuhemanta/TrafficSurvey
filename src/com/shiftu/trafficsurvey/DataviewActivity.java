package com.shiftu.trafficsurvey;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class DataviewActivity extends Activity {
    /** Called when the activity is first created. */
	
	
	SQLiteDatabase database;
	
    private static String DBNAME = "/trafficsurvey.db";    
    private static String PATH = "/data/data/";
    
    TableLayout tableLayout;
    TableRow row;
    TextView vehicle;
    TextView date;
    TextView time;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataview);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout1);
        
        //createDB();
        //insertValues();
        displayDB();
    }
    
    
	private void displayDB() {
		// TODO Auto-generated method stub
		database=openOrCreateDatabase(PATH+this.getPackageName()+DBNAME, Context.MODE_PRIVATE, null);
		if(database!=null)
		{
			Cursor cursor=database.rawQuery("SELECT v.v_name,d._date,d._time from vehicle_info v,data_info d WHERE v.v_id = d.v_id", null);
			
			Integer index0=cursor.getColumnIndex("v_name");
			Integer index1 = cursor.getColumnIndex("_date");	
            Integer index2 = cursor.getColumnIndex("_time");
            if(cursor.getCount()>0)
            {
            	cursor.moveToFirst();
            	do
            	{
            		row=new TableRow(this);
            	    row.setId(100);
            		row.setLayoutParams(new LayoutParams(
                            LayoutParams.FILL_PARENT,
                            LayoutParams.WRAP_CONTENT));  
            		
            		/*Setting up the first coloumn parameters*/
    				vehicle=new TextView(this);
    				vehicle.setText(cursor.getString(index0));
    				vehicle.setTextSize(16);
    				vehicle.setPadding(0, 5, 5, 0);
    				vehicle.setLayoutParams(new LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(vehicle); //adding coloumn to row

    				/*Setting up the second coloumn parameters*/			
    				date=new TextView(this);
    				date.setText(cursor.getString(index1));
    				date.setPadding(0, 5, 5, 0);
    				date.setTextSize(16);
    				date.setLayoutParams(new LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(date); //adding coloumn to row
    				
    				/*Setting up the third coloumn parameters*/
    				time=new TextView(this);
    				time.setText(cursor.getString(index2));
    				time.setPadding(0, 5, 5, 0);
    				time.setTextSize(16);
    				time.setLayoutParams(new LayoutParams(
    	                    LayoutParams.WRAP_CONTENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(time); //adding coloumn to row

    				/*Adding the row to the tablelayout*/
    				tableLayout.addView(row,new TableLayout.LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));    				
            	}while(cursor.moveToNext());
            	database.close();
            }
            else
            {
            	Toast.makeText(getBaseContext(), "NOT AVAILABLE", Toast.LENGTH_LONG).show();
            }
		}
	}
}
