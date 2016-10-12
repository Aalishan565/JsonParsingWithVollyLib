package com.jsonparsingwithvollylib;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static  MyApplication sInstance;
	@Override
	public void onCreate() {
		sInstance=this;
		
		super.onCreate();
	}
	public static Context getAppContext(){
		return sInstance.getApplicationContext();
	}
	

}
