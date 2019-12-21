package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapter.donhangadapter;
import com.example.myapplication.fragment.NotificationFragment;
import com.example.myapplication.model.donhang;
import com.example.myapplication.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryOrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lv;
    private int makh;
    private String tenkh;
    public static ArrayList<donhang> mangdonhangls;
    private donhangadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        anhxa();
        action();
        getttkh();
        getdata();
    }
    private void getttkh() {
        for (int i=0; i< MainActivity.mangkh.size();i++){
            makh = MainActivity.mangkh.get(i).getMakh();
            tenkh = MainActivity.mangkh.get(i).getName();
        }
    }

    private void getdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urldonhangls, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int madh = 0;
                int tongtien = 0;
                String diachi = "";
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i =0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        madh = jsonObject.getInt("ma_dondh");
                        tongtien = jsonObject.getInt("tong_gia");
                        diachi = jsonObject.getString("dia_chi");
                        mangdonhangls.add(new donhang(madh,diachi,tongtien,4,null));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("makh",String.valueOf(makh));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void action() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarlichsu);
        lv = findViewById(R.id.lvlsmuahang);
        mangdonhangls = new ArrayList<>();
        adapter = new donhangadapter(getApplicationContext(),mangdonhangls);
        lv.setAdapter(adapter);
    }
}
