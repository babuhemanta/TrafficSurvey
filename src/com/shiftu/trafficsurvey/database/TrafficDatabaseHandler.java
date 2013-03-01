package com.shiftu.trafficsurvey.database;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shiftu.trafficsurvey.VehicleActivity;
import com.shiftu.trafficsurvey.CustomVehicleActivity;


public class TrafficDatabaseHandler {
	public static final String TAG = "TrafficDatabaseHelper";
	public static final boolean DEBUG = true;
	public static final String dbFolder = "Traffic";
	public static final String DATABASE_FILE_PATH = "/data/data";
	public static final String DATABASE_NAME = "trafficsurvey.db";
	static final int DATABASE_VERSION = 1;
	private Context mContext;
	private static TrafficDatabaseHandler sInstance = null;
	SimpleDateFormat sdf;

	public TrafficDatabaseHandler(Context context) {
		// super(context, DATABASE_FILE_PATH+context.getPackageName()+
		// "/"+path,null, DATABASE_VERSION);
		this.mContext = context;
	}

	public static class DATA {
		public static final String TABLE_NAME = "data_info";
		public static final String _ID = "_id";
		public static final String VEHICLE_ID = "v_id";
		public static final String EMPLOYEE_ID = "emp_id";
		public static final String DATE = "_date";
		public static final String TIME = "_time";
		public static final String MOVE = "move";
		public static final String LOCATION_ID = "loc_id";
		public static final String TIMESTAMP = "timestamp";
		public static final String UP_DOWN = "up_down";
	}

	public static class VEHICLE {
		public static final String TABLE_NAME = "vehicle_info";
		public static final String VEHICLE_ID = "v_id";
		public static final String VEHICLE_NAME = "v_name";

	}

	public static class LOCATION {
		public static final String TABLE_NAME = "loc_info";
		public static final String LOCATION_ID = "loc_id";
		public static final String LOCATION_NAME = "loc_name";
	}

	public static class USER {
		public static final String TABLE_NAME = "user_info";
		public static final String EMP_ID = "emp_id";
		public static final String EMP_NAME = "emp_name";
	}

	public long userInfo(String emp_name) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		System.out.println("Values from VehicleActivity "+VehicleActivity.emp_name);
		System.out.println("Values from CustomVehicleActivity "+CustomVehicleActivity.emp_name);
		if(VehicleActivity.emp_name!=null)
		{
			values.put(USER.EMP_NAME, VehicleActivity.emp_name);
		}else
		{
			values.put(USER.EMP_NAME, CustomVehicleActivity.emp_name);
		}
		
		System.out.println("Trafficdatabase " + VehicleActivity.emp_name);
		long rowId = db.insert(USER.TABLE_NAME, null, values);
		db.close();
		db.releaseReference();
		return rowId;

	}

	public long locInfo(String emp_location) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		if(VehicleActivity.emp_location!=null)
		{
			values.put(LOCATION.LOCATION_NAME, VehicleActivity.emp_location);
		}else{
			values.put(LOCATION.LOCATION_NAME, CustomVehicleActivity.emp_location);
		}
		

		System.out.println("Trafficdatabase " + VehicleActivity.emp_location);
		long rowId = db.insert(LOCATION.TABLE_NAME, null, values);
		db.close();
		db.releaseReference();
		return rowId;

	}

	public void deletTablee() {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		try {
			String deleteuser = "DELETE FROM user_info";
			db.execSQL(deleteuser);

			String deleteloc = "DELETE FROM loc_info";
			db.execSQL(deleteloc);

			String deletedata = "DELETE FROM data_info";
			db.execSQL(deletedata);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
			db.releaseReference();
		}

	}

	public Cursor dataviewinfo() {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		Cursor cursor = db
				.rawQuery(
						"SELECT v.v_name,d._date,d._time,d.up_down from vehicle_info v,data_info d WHERE v.v_id = d.v_id",
						null);
		return cursor;

	}

	// @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

		// Create tables again
		onCreate(db);
	}

	public void insertVehicleData(ContentValues values) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		// SQLiteDatabase db = this.getWritableDatabase();
		db.insert(DATA.TABLE_NAME, null, values);
		db.close();
		db.releaseReference();
	}

	public int reportview(int timestam, int vid) {
		int ct = 0;
		String timestam1;
		if (timestam == 1 || timestam == 2 || timestam == 3 || timestam == 4
				|| timestam == 5 || timestam == 6 || timestam == 7
				|| timestam == 8 || timestam == 9) {
			timestam1 = 0 + Integer.toString(timestam);

		} else {
			timestam1 = Integer.toString(timestam);
		}
		String vid1 = Integer.toString(vid);
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		// String sql = "SELECT * FROM data_info WHERE timestamp='" + timestam1
		// + "' " + "and" + "v_id='" + vid1 + "'";
		Cursor cursor = null;
		try {
			cursor = db.rawQuery("SELECT * FROM data_info WHERE timestamp = '"
					+ timestam1 + "' and v_id = '" + vid1 + "'", null);
			ct = cursor.getCount();
		} catch (Exception e) {

		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
				db.close();
			}
		}
		return ct;

	}

	public int getVehicleCount(int vid) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				DATABASE_FILE_PATH + File.separator + mContext.getPackageName()
						+ File.separator + DATABASE_NAME, null);
		Cursor cursor = null;
		String data_Dt;
		String vidd = Integer.toString(vid);
		int ct = 0;
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String currentDateandTime = sdf.format(new Date());
		String str[] = currentDateandTime.split(" ");		
		
		String customDate=CustomVehicleActivity.custom_date;
		if(customDate!=null)
		{
			data_Dt=customDate;
		}else{
			data_Dt = str[0];
		}
		System.out.println("Custom Date is "+customDate);
		// String time_Tm = str[1];
		// String countQuery = "SELECT  * FROM " +
		// VehicleData.TABLE_NAME+"WHERE _date = '2013/2/13' and v_id = 1";
		// SQLiteDatabase db = this.getWritableDatabase();
		try {
			String sql = "SELECT  * FROM " + DATA.TABLE_NAME + " WHERE _date='"
					+ data_Dt + "' and v_id='" + vidd + "'";
			System.out.println(sql);
			cursor = db.rawQuery(sql, null);
			ct = cursor.getCount();
			System.out.println(ct);
			return ct;
		} catch (Exception e) {

		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
				db.close();
			}
		}
		return ct;

		// return count

	}

	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

}
