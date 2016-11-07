package com.jsonparsingwithvollylib;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VollySingleton {
	public static VollySingleton sInstance = null;
	private RequestQueue mRequestQueue;

	private VollySingleton() {
		mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
	}

	public static VollySingleton getInstance() {
		if (sInstance == null) {
			sInstance = new VollySingleton();
		}
		return sInstance;

	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

}
