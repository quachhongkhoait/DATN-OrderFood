package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapter.monanadapter;
import com.example.myapplication.model.monan;
import com.example.myapplication.model.quanan;
import com.example.myapplication.ultil.server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailRestaurantActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageViewqa;
    TextView textViewtenqa,textViewdcqa,textViewthoigian;
    RecyclerView recyclerView;
    monanadapter monanadapter;
    ArrayList<monan> mangma;
    int maqa=0;
    View foodterview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        anhxa();
        action();
        getdataquan();
        getdatamon();
        loadmoredata();

    }

    private void loadmoredata() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void getdatamon() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urlquananhtheomaqa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int mama = 0;
                String maloai = "";
                String tenma = "";
                int giama = 0;
                String hama = "";
                if (response != null){
                    try {
                        mangma.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i =0;i<jsonArray.length();i++){
                            JSONObject jsonObject =  jsonArray.getJSONObject(i);
                            mama = jsonObject.getInt("mamonan");
                            maloai = jsonObject.getString("maloai");
                            tenma = jsonObject.getString("tenmonan");
                            giama = jsonObject.getInt("gia");
                            hama = jsonObject.getString("hinhanhmonan");

                            mangma.add(new monan(mama,maloai,tenma,giama,hama));
                            monanadapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
                param.put("maqa",String.valueOf(maqa));

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getdataquan() {
        String tenqa = "";
        String hinhqa = "";
        String diachiqa = "";
        String thoigian = "";
        quanan quanan = (com.example.myapplication.model.quanan) getIntent().getSerializableExtra("quanan");
        maqa = quanan.getMaqa();
        tenqa = quanan.getTenqa();
        hinhqa = quanan.getHinhqa();
        diachiqa = quanan.getDiachiqa();
        textViewtenqa.setText(tenqa);
        textViewdcqa.setText(diachiqa);
        textViewthoigian.setText(maqa+"");
        Picasso.get().load(hinhqa)
                .placeholder(R.drawable.ic_panorama_fish_eye_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(imageViewqa);
        toolbar.setTitle(tenqa);
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
        toolbar = findViewById(R.id.toolbarquan);
        imageViewqa = findViewById(R.id.imgquanan);
        textViewtenqa = findViewById(R.id.tvtenquanan);
        textViewdcqa = findViewById(R.id.tvdiachiquanan);
        textViewthoigian = findViewById(R.id.tvthoigianquanan);
        recyclerView = findViewById(R.id.recyclerviewmontrongquanan);
        mangma = new ArrayList<>();
        monanadapter = new monanadapter(DetailRestaurantActivity.this, mangma);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailRestaurantActivity.this,1));
        recyclerView.setAdapter(monanadapter);
        monanadapter.notifyDataSetChanged();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        foodterview = inflater.inflate(R.layout.progressbar,null);
    }
}
