package com.example.zhoujiayuan.mytianqi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhoujiayuan on 17/1/2.
 */

public class myAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<JSONObject> arrayList;

    public myAdapter(Context mContext, ArrayList<JSONObject> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<JSONObject> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<JSONObject> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if(convertView != null){
            view = convertView;
        }
        else {
            view =  View.inflate(mContext, R.layout.db_listview, null);
        }
        TextView  item_tv_wheather2= (TextView) view.findViewById(R.id.item_tv_wheather_result);


        JSONObject data = arrayList.get(position);


        try {
            item_tv_wheather2.setText(
                    "日期:"+data.get("date").toString()+"\n"+
                    "天气:"+data.get("weather").toString()+"\n"+
                    "风力:"+data.get("wind").toString()+"\n"+
                    "温度:"+data.get("temperature").toString()
            );



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }







}
