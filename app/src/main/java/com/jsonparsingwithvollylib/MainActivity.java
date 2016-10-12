package com.jsonparsingwithvollylib;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTvJsonData;
    private Button mBtnHitMe;
    private Button mBtnLoadList;
    private ListView mListViewData;
    ProgressDialog pDialog;
    private String TAG = "MainActivity";
    private String url = "http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt";
    private String urlforList = "http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt";
    RequestQueue requestQueue;
    List<MovieModel> mListMovieModel;
    MovieModel mMovieModel;
    CustomListAdapter mCustomListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListMovieModel = new ArrayList<>();

        requestQueue = VollySingleTon.getInstance().getRequestQueue();
        pDialog = new ProgressDialog(MainActivity.this);
        mTvJsonData = (TextView) findViewById(R.id.tv_json_data);
        mBtnHitMe = (Button) findViewById(R.id.btn_hit);
        mBtnLoadList = (Button) findViewById(R.id.btn_load_list);
        mListViewData = (ListView) findViewById(R.id.lv_data);
        mBtnHitMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("loading..");
                pDialog.show();
                callWebService();
            }
        });
        mBtnLoadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("loading..");
                pDialog.show();
                callListDataToLoad();
            }
        });
    }

    private void callListDataToLoad() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlforList, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject jsonObj = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObj.getJSONArray("movies");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mMovieModel = new MovieModel();
                                JSONObject jsonFinalObject = jsonArray.getJSONObject(i);
                                mMovieModel.setMovie(jsonFinalObject.getString("movie"));
                                mMovieModel.setYear(jsonFinalObject.getInt("year"));
                                mListMovieModel.add(mMovieModel);
                            }
                            mCustomListAdapter = new CustomListAdapter(MainActivity.this, mListMovieModel);
                            mListViewData.setAdapter(mCustomListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                pDialog.hide();
            }
        });
        requestQueue.add(jsonObjReq);
    }

    private void callWebService() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject jsonObj = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObj.getJSONArray("movies");
                            JSONObject jsonFinalObject = jsonArray.getJSONObject(0);
                            String movieName = jsonFinalObject.getString("movie");
                            int year = jsonFinalObject.getInt("year");
                            mTvJsonData.setText("Movie :" + movieName + " " + "Year :" + year);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                pDialog.hide();
            }
        });
        requestQueue.add(jsonObjReq);


    }
}
