package com.example.myapplication.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.CartActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.monanadapter;
import com.example.myapplication.adapter.quananadapter;
import com.example.myapplication.detailbotom;
import com.example.myapplication.model.monan;
import com.example.myapplication.model.quanan;
import com.example.myapplication.ultil.server;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class HomeFragment extends Fragment {

    ProgressDialog dialog;
    View view;
    public ViewFlipper viewFlipper;
    RecyclerView recyclerView,recyclerViewqa;
    ArrayList<monan> mangmonan;
    monanadapter maadapter;
    ImageView imgcart;
    ArrayList<quanan> mangquan;
    quananadapter quanadapter;
    boolean btn = false;

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();

        actionviewflipper();
        getdatamonan();
        getdataquan();
        actionbutton();

        return view;
    }


    private void actionbutton() {

        imgcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getdataquan() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.urlquananhome, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int maqa =0;
                    String tenqa = "";
                    String hinhanhqa = "";
                    String diachiqa = "";
                    for (int i=0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            maqa = jsonObject.getInt("ma_qa");
                            tenqa = jsonObject.getString("ten_qa");
                            hinhanhqa = jsonObject.getString("hinhanh_qa");
                            diachiqa = jsonObject.getString("diachi_qa");
                            mangquan.add(new quanan(maqa,tenqa,hinhanhqa,diachiqa));
                            quanadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getdatamonan() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.urlmonanhome, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int mamon =0;
                    String maloai ="";
                    String tenmonan = "";
                    Integer gia = 0;
                    String hinhanhmonan = "";
                    mangmonan.clear();
                    for (int i =0;i<response.length();i++){
                        try {
                            JSONObject jsonObject =response.getJSONObject(i);
                            mamon = jsonObject.getInt("mamonan");
                            maloai = jsonObject.getString("maloai");
                            tenmonan = jsonObject.getString("tenmonan");
                            gia = jsonObject.getInt("gia");
                            hinhanhmonan = jsonObject.getString("hinhanhmonan");
                            mangmonan.add(new monan(mamon,maloai,tenmonan,gia,hinhanhmonan));
                            maadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Log.d("khoa1", "onErrorResponse: "+e);
                        }
                    }
                }
                else {
//                    Log.d("khoa1", "response != nul");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("khoa1", "VolleyError: "+error);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        viewFlipper = view.findViewById(R.id.viewflipper);
        recyclerView = view.findViewById(R.id.recyclerview);
        imgcart = view.findViewById(R.id.imgcart);
//        TextView textViewhome = view.findViewById(R.id.text_home);
//        textViewhome.setText("hello home");
        mangmonan = new ArrayList<>();
        maadapter = new monanadapter(getActivity(),mangmonan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));//định dạng cột
        recyclerView.setAdapter(maadapter);
        maadapter.notifyDataSetChanged();

        /// cuar quan an
        recyclerViewqa = view.findViewById(R.id.recyclerviewquan);
        recyclerViewqa.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewqa.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(getActivity().getApplicationContext(),linearLayoutManager.getOrientation());
//        recyclerViewqa.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(recyclerViewqa.getContext(),DividerItemDecoration.HORIZONTAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(),R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerViewqa.addItemDecoration(dividerItemDecoration);
//        recyclerViewqa.setItemAnimator(new DefaultItemAnimator());
        mangquan = new ArrayList<>();
        quanadapter = new quananadapter(mangquan,getActivity());
        recyclerViewqa.setAdapter(quanadapter);
        quanadapter.notifyDataSetChanged();
    }

    private void actionviewflipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://images.foody.vn/res/g79/785865/prof/s640x400/foody-upload-api-foody-mobile-foody-upload-api-foo-181031135720.jpg");
        mangquangcao.add("https://images.foody.vn/res/g15/144507/prof/s640x400/foody-upload-api-foody-mobile-money-jpg-181210172151.jpg");
        mangquangcao.add("https://images.foody.vn/res/g21/202689/prof/s640x400/foody-upload-api-foody-mobile-green-brown-bookstor-180622101155.jpg");
        mangquangcao.add("https://images.foody.vn/res/g94/931652/prof/s640x400/foody-upload-api-foody-mobile-foody-upload-api-foo-190620095401.jpg");
        for (int i =0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }

}
