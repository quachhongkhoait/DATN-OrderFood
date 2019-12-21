package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.example.myapplication.adapter.giohangadapter;
import com.example.myapplication.fragment.InformationFragment;
import com.example.myapplication.model.khachhang;
import com.example.myapplication.ultil.server;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentOrderActivity extends AppCompatActivity {
    public static int tongtien;
    Gson gson;
    public static TextView tvtongtienpayment;
    private Toolbar toolbar;
    private TextView tvdiachi,tvdatdon;
    private EditText edtghichu;
    private giohangadapter giohangadapter;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_order);
        gson = new Gson();
        anhxa();
        action();
        addinfo();
        evenultil();
        actionbutton();
    }

    private void actionbutton() {

        tvdatdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urldatdon, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PaymentOrderActivity.this, "Đặt hàng thành công!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PaymentOrderActivity.this, MainActivity.class);
                        startActivity(intent);
                        giohangadapter.notifyDataSetChanged();
                        MainActivity.manggiohang.clear();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param = new HashMap<String,String>();
                        String guitt = gson.toJson(MainActivity.mangkh);
                        String guidh = gson.toJson(MainActivity.manggiohang);
                        param.put("tongtien",tongtien+"");
                        param.put("arraykh",guitt);
                        param.put("arrayma",guidh);
                        param.put("ghichu",edtghichu.getText().toString());
                        return param;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(PaymentOrderActivity.this);
                queue.add(stringRequest);
            }
        });
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

    public static void evenultil() {
        tongtien =0;
        for (int i=0;i< MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getTongtien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtongtienpayment.setText(decimalFormat.format(tongtien)+"đ");
    }

    private void addinfo() {
        for (int i=0; i< MainActivity.mangkh.size(); i++){
            tvdiachi.setText(MainActivity.mangkh.get(i).getAddress());
        }
    }

    private void anhxa() {
        tvdiachi = findViewById(R.id.tvdiachipayment);
        edtghichu = findViewById(R.id.edtghichu);
        toolbar = findViewById(R.id.toolbarpayment);
        tvtongtienpayment = findViewById(R.id.tvtongtienpayment);
        tvdatdon = findViewById(R.id.tvdatdonpayment);
        lv =findViewById(R.id.lvpayment);
        giohangadapter = new giohangadapter(PaymentOrderActivity.this,MainActivity.manggiohang);
        lv.setAdapter(giohangadapter);
    }
}
