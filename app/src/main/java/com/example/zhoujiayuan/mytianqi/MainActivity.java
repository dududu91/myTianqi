package com.example.zhoujiayuan.mytianqi;

import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.graphics.Bitmap.Config.RGB_565;

public class MainActivity extends AppCompatActivity {

    Button getLocation_btn;
    LocationManager locationManager;
    TextView tv_location_result;
    Button btn_getwhether;
    TextView tv_whetherResult;
    ImageView imageView;
    ListView listview;
    String golbalAddress;

    double weidu;
    double jingdu;
    double gaodu;

    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createView();

    }

    public void createView() {
        //getLocation_btn = (Button) this.findViewById(R.id.getlocation);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        tv_location_result = (TextView) this.findViewById(R.id.tv_location_result);
        // btn_getwhether = (Button) this.findViewById(R.id.btn_getwhether);
        tv_whetherResult = (TextView) this.findViewById(R.id.tv_whetherResult);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        listview = (ListView) this.findViewById(R.id.listview);
        mRequestQueue = Volley.newRequestQueue(this);

        View view = new View(getApplicationContext());

        getLocationByGPS(view);
    }


    public void getLocationByGPS(View v) {
        String bestLocator = locationManager.getBestProvider(new Criteria(), true);

        if (bestLocator == null) {
            Toast.makeText(getApplicationContext(), "无法定位请开启GPS", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "定位中....", Toast.LENGTH_SHORT).show();
            locationManager.requestLocationUpdates(bestLocator, 0, 0, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    weidu = location.getLatitude();
                    jingdu = location.getLongitude();
                    gaodu = location.getAltitude();
                    DecimalFormat decimalFormat = new DecimalFormat("######0.0");
                    tv_location_result.setText("");

                    String result = "维度:" + decimalFormat.format(weidu) + "\n" + "经度:" + decimalFormat.format(jingdu) + "\n" +
                            "高度:" + decimalFormat.format(gaodu);


                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.CHINA);
                    try {

                        List<Address> addressList = geocoder.getFromLocation(weidu, jingdu, 1);
                        tv_location_result.setText("您所在的位置是:\n" + addressList.get(0).getAddressLine(1) + addressList.get(0).getFeatureName());
                        golbalAddress =  addressList.get(0).getAddressLine(1).substring(1,3);

                        System.out.println("golbalAddress"+golbalAddress);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }

            });

            View view = new View(getApplicationContext());
            getWhether(view);
        }

    }

    public void getWhether(View v) {
        Map<String, String> querys = new HashMap<String, String>();

        if(golbalAddress == null){
            golbalAddress = "北京";
        }

        String request_url = "http://api.map.baidu.com/telematics/v3/weather?location="+golbalAddress+"&output=json&ak=GuZriL3rkm1MUnyTyfsNGvTC";
        System.out.println("request_url"+request_url);
        JsonRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                request_url,
                new JSONObject(querys),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String date;
                        String results;
                        System.out.println("请求回来的整体结果: " + jsonObject);
                        try {
                            date = jsonObject.getString("date");
                            results = jsonObject.getString("results");

                            JSONArray jasonArray = jsonObject.getJSONArray("results");
                            JSONObject index_ojbect = (JSONObject) jasonArray.get(0);
                            String pm25 = index_ojbect.getString("pm25");

                            System.out.println("------index-------" + index_ojbect.get("index"));
                            System.out.println("------weather_data-------" + index_ojbect.get("weather_data"));


                            JSONArray array = index_ojbect.getJSONArray("weather_data");

                            ArrayList today_weather_list = new ArrayList<>();
                            JSONObject object = (JSONObject) array.get(0);

                            today_weather_list.add(object.get("date"));
                            today_weather_list.add(object.get("dayPictureUrl"));
                            today_weather_list.add(object.get("nightPictureUrl"));
                            today_weather_list.add(object.get("weather"));
                            today_weather_list.add(object.get("wind"));
                            today_weather_list.add(object.get("temperature"));


                            ArrayList<JSONObject> totalDataList = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                totalDataList.add((JSONObject) array.get(i));
                            }

                            for (int j = 0; j < totalDataList.size(); j++) {
                                System.out.println("all data:" + totalDataList.get(j));

                            }


                            myAdapter adapter = new myAdapter(getApplicationContext(), totalDataList);
                            listview.setAdapter(adapter);


                            String city = results.substring(17, 19);
                            tv_whetherResult.setText(city + " PM2.5: " + pm25 + "\n" + today_weather_list.get(0) + today_weather_list.get(3) +
                                    today_weather_list.get(4) + today_weather_list.get(5)
                            );

                            System.out.println("today_weather_list.get(1)" + today_weather_list.get(1));


                            String picUrl = today_weather_list.get(1).toString();
                            getWeatherPic(picUrl);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }


        );

        mRequestQueue.add(jsonRequest);
        mRequestQueue.start();

    }

    public void getWeatherPic(String url) {

        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                },
                100,
                100,
                RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(R.drawable.abc_ab_share_pack_mtrl_alpha);
                    }
                });

        mRequestQueue.add(imageRequest);
        mRequestQueue.start();

    }


}
