package com.smartmobileproject.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Backgroundservice.BackgroundService;
import com.Backgroundservice.BootReceiver;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.misc.AsyncTask;
import com.android.volley.misc.Exif;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.smartmobileproject.function.ExifInfo;
import com.smartmobileproject.function.Item;
import com.smartmobileproject.function.KaKaoMap_funtion;
import com.smartmobileproject.function.LocationService;
import com.smartmobileproject.function.UploadImage;
import com.smartmobileproject.function.downloadservice;
import com.smartmobileproject.function.getFileList;
import com.smartmobileproject.function.itemAdapter;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MapActivity extends AppCompatActivity {
    double longtitude;
    double latitude;
    LocationManager locationManager;
    LocationListener locationListener;
    Location location;
    MapPoint mapPoint;
    KaKaoMap_funtion kaKaoMap_funtion;
    private Intent mBackgroundServiceIntent;
    private BackgroundService mBackgroundService;
    String email;
    String name;
    getFileList getfile = new getFileList();

    RecyclerView recyclerView;
    ArrayList<Item> items= new ArrayList<>();
    itemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        kaKaoMap_funtion = new KaKaoMap_funtion();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("툴바", String.valueOf(toolbar));
        MapView mapView = new MapView(this);
        mBackgroundService = new BackgroundService(getApplicationContext());
        mBackgroundServiceIntent = new Intent(getApplicationContext(), mBackgroundService.getClass());
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        //새로 추가
        recyclerView=findViewById(R.id.recycler);
        adapter= new itemAdapter(this, items);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //

        Log.d("name", name);
        final String url = "https://phpproject-cparr.run.goorm.io/insetDB.php";
        int i = 0;
        //ExifInfo exifinfo = new ExifInfo();
        Log.d("email", email);
        String[] filelist = getfile.getimageList();
        try {
            UploadImage(filelist, email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!BootReceiver.isServiceRunning(this, mBackgroundService.getClass())) {
            startService(mBackgroundServiceIntent);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                longtitude = location.getLongitude();
                latitude = location.getLatitude();
                Log.d("long", String.valueOf(longtitude));
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longtitude), true);
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
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("권한설정", "권한설정을 해주세요.");
        }

        if (LocationManager.GPS_PROVIDER != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1000, locationListener);
        } else if (LocationManager.NETWORK_PROVIDER != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1000, locationListener);
        } else {
            Log.d("위치정보", "위치정보를 불러올수가 없습니다......ㅠㅠㅠ");
        }
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        //mapPoint = MapPoint.mapPointWithGeoCoord(37.55,127);
        Log.d("map", String.valueOf(mapPoint));

        //kaKaoMap_funtion.addCustomMarker(mapView,mapPoint);
        mapViewContainer.addView(mapView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar, menu);
        Log.d("툴바", "1");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menubutton:
                Intent NewActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(NewActivity);
                break;
        }
        Log.d("툴바", "2");
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        setLocation();
        Log.d("onDestroy", "실행");
    }

    private void setLocation() {
        Context context = getApplicationContext();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(calendar.SECOND, 1);
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                int hasFineLocationPermission = ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION);
                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

                } else if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 2000, (LocationListener) this);

                    if (locationManager != null) {
                        double longtitude = location.getLongitude();
                        Log.d("longtitude", String.valueOf(longtitude));
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2000, (LocationListener) this);
                        if (locationManager != null) {
                            double longtitude = location.getLongitude();
                            Log.d("longtitude", String.valueOf(longtitude));
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d("@@@", "" + e.toString());
        }

    }

    private void UploadImage(String[] imgList, String email) throws IOException {
        Float inlongtitude;
        Float inlatitude;
        String todaydate;
        int i = 0;
        String date;
        String imgpath;
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd");
        String nowdate = format.format(now);
        while (true) {

            imgpath = "/storage/emulated/0/DCIM/CAMERA/" + imgList[i];
            Log.d("imgpath", imgpath);
            ExifInterface exifinterface = new ExifInterface(imgpath);
            ExifInfo exifinfo = new ExifInfo(exifinterface);
            date = exifinfo.ExifDate();
            Log.d("hellodate", date);

            Log.d("nowdate", nowdate);
            String[] date_array = date.split(" ");
            Log.d("nowdate", date_array[0]);
            inlongtitude = exifinfo.Exiflongtitude();
            if (inlongtitude == null)
                inlongtitude = 0.00f;
            Log.d("inlong", String.valueOf(inlongtitude));
            inlatitude = exifinfo.Exiflatitude();
            if (inlatitude == null)
                inlatitude = 0.00f;
            Upload(imgpath, inlongtitude, inlatitude, email, date);

            i++;
            if (i == imgList.length)
                break;
        }
    }

    public void Upload(String imgPath, Float longtitude, Float latitude, String email, String date) {

        String serverUrl = "https://phpproject-cparr.run.goorm.io/UploadImage.php";

        SimpleMultiPartRequest smpr = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hellosuccess", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hellofail", String.valueOf(error));

            }
        });


        smpr.addStringParam("longtitude", String.valueOf(longtitude));
        smpr.addStringParam("latitude", String.valueOf(latitude));
        smpr.addStringParam("email", email);
        smpr.addStringParam("date", date);
        smpr.addStringParam("path", imgPath);
        Log.d("img", imgPath);
        smpr.addFile("img", imgPath);
        Log.d("hellodate", date);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.d("smpr", String.valueOf(smpr));
        requestQueue.add(smpr);

    }

    public void clickLoad(View view) {

        String serverUrl="https://phpproject-cparr.run.goorm.io/download.php";


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MapActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


                //파라미터로 응답받은 결과 JsonArray를 분석

                items.clear();
                adapter.notifyDataSetChanged();
                try {

                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        String email=jsonObject.getString("email");
                        String longtitude=jsonObject.getString("longtitude");
                        String latitude=jsonObject.getString("latitude");
                        String imgPath=jsonObject.getString("imgPath");
                        String date=jsonObject.getString("date");

                        //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                        imgPath = "https://phpproject-cparr.run.goorm.io/"+imgPath;

                        items.add(0,new Item(email, longtitude, latitude, imgPath));
                        adapter.notifyItemInserted(0);
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }
}









