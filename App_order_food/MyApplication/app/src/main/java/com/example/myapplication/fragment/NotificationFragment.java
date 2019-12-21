package com.example.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.HistoryOrderActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.donhangadapter;
import com.example.myapplication.model.donhang;
import com.example.myapplication.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private int makh;
    private String tenkh;
    private TextView tvlichsu;
    private ListView lvdh;
    public static ArrayList<donhang> mangdonhang;
    private donhangadapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        anhxa();
        getttkh();
        getdata();
        action();
        return view;
    }

    private void action() {
        tvlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getttkh() {
        for (int i=0; i< MainActivity.mangkh.size();i++){
            makh = MainActivity.mangkh.get(i).getMakh();
            tenkh = MainActivity.mangkh.get(i).getName();
        }
    }

    private void getdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urldonhang, new Response.Listener<String>() {
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
                        mangdonhang.add(new donhang(madh,diachi,tongtien,4,null));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    private void anhxa() {
        tvlichsu = view.findViewById(R.id.tvlichsu);
        lvdh     = view.findViewById(R.id.lvdonhang);
        mangdonhang = new ArrayList<>();
        adapter = new donhangadapter(getActivity().getApplicationContext(),mangdonhang);
        lvdh.setAdapter(adapter);
        lvdh.setClickable(false);

    }
}
