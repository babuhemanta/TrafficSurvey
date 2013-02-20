package com.shiftu.trafficsurvey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {
	
	private static String TAG = SplashActivity.class.getName();	

    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    String path = "trafficsurvey.db";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);	// Removes title bar
      	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	// Removes notification bar
      	setContentView(R.layout.activity_splash);
      	copyFileOrDir(path);
      	mProgress = (ProgressBar) findViewById(R.id.progressBar1);
      	new Thread(myThread).start();	 
	}
	private Runnable myThread = new Runnable(){
		 
	    @Override
	    public void run() {
	        // TODO Auto-generated method stub
	        while (mProgressStatus<100){
	            try{
	                myHandle.sendMessage(myHandle.obtainMessage());
	                Thread.sleep(50);
	            }
	            catch(Throwable t){
	            	Log.e(TAG, t.getMessage());
	            }
	        }
	      Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
	  	  SplashActivity.this.startActivity(intent);
	  	  SplashActivity.this.finish();
	    }
	 
	    
		Handler myHandle = new Handler(){
	 
	        @Override
	        public void handleMessage(Message msg) {
	            // TODO Auto-generated method stub
	            mProgressStatus++;
	            mProgress.setProgress(mProgressStatus);
	        }
	    };
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}
	private void copyFileOrDir(String path) {
		String fullPath = "/data/data/" + this.getPackageName() + "/" + path;
		File dir = new File(fullPath);
	    AssetManager assetManager = this.getAssets();
	    String assets[] = null;
	    try {
	        assets = assetManager.list(path);
	        if (assets.length == 0) {
	        	if(!dir.exists())
	            copyFile(path);
	        } /*else {
	            
	            
	            if (!dir.exists())
	                dir.mkdir();
	            for (int i = 0; i < assets.length; ++i) {
	                copyFileOrDir(path + "/" + assets[i]);
	            }
	        }*/
	    } catch (IOException ex) {
	        Log.e("tag", "I/O Exception", ex);
	    }
	}
	private void copyFile(String filename) {
	    AssetManager assetManager = this.getAssets();

	    InputStream in = null;
	    OutputStream out = null;
	    try {
	        in = assetManager.open(filename);
	        String newFileName = "/data/data/" + this.getPackageName() + "/" + filename;
	        out = new FileOutputStream(newFileName);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	    } catch (Exception e) {
	        Log.e("tag", e.getMessage());
	    }

	}

}
