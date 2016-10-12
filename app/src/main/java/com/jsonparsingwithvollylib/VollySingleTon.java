package com.jsonparsingwithvollylib;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VollySingleTon {
	public static VollySingleTon sInstance = null;
	private RequestQueue mRequestQueue;

	private VollySingleTon() {
		mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
	}

	public static VollySingleTon getInstance() {
		if (sInstance == null) {
			sInstance = new VollySingleTon();
		}
		return sInstance;

	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

}
