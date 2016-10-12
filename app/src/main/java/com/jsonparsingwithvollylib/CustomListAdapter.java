package com.jsonparsingwithvollylib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aalishan on 12/10/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MovieModel> movieList;

    public CustomListAdapter(Context ctx, List<MovieModel> list) {
        movieList = list;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_item, null);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_movie_name);
            holder.tv_year = (TextView) view.findViewById(R.id.tv_year);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(movieList.get(position).getMovie());
        holder.tv_year.setText("Year : " + movieList.get(position).getYear());
        return view;
    }

    public class ViewHolder {
        private TextView tv_name;
        private TextView tv_year;


    }
}
