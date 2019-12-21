package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.InformationFragment;
import com.example.myapplication.model.khachhang;
import com.example.myapplication.ultil.server;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "khoa";
    ProgressDialog dialog;
    Toolbar toolbar;
    Button btnlogin,btnfb,btnsdt,btngg;
    EditText edtname,edtpass;
    TextView tvdangky,tvquenmk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        acction();

    }

    private void acction() {
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtname.getText().toString().trim();
                String password = edtpass.getText().toString().trim();
                loginAccount(username, password);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loginAccount(final String username, final String password) {
        if (checkEditText(edtname) && checkEditText(edtpass)){
            dialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, server.urllogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    String message = "";
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.d(TAG, response);
                        if (jsonObject.getInt("success") == 1){
                            khachhang kh = new khachhang();
                            kh.setMakh(jsonObject.getInt("ma_kh"));
                            kh.setUsername(jsonObject.getString("taikhoan"));
                            kh.setPassword(jsonObject.getString("matkhau"));
                            kh.setName(jsonObject.getString("tenkh"));
                            kh.setEmail(jsonObject.getString("email"));
                            kh.setPhone(jsonObject.getInt("sodienthoai"));
                            kh.setAddress(jsonObject.getString("diachi"));
//                            kh.setStatus(jsonObject.getBoolean("status"));
                            message = jsonObject.getString("message");
                            Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("login", kh);
                            startActivity(intent);
                        } else {
                            message = jsonObject.getString("message");
                            Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    dialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("taikhoan", username);
                    hashMap.put("matkhau", password);
                    return hashMap;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarlogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnfb = findViewById(R.id.btnloginfb);
        btngg = findViewById(R.id.btnlogingoogle);
        btnsdt = findViewById(R.id.btnloginsdt);
        btnlogin = findViewById(R.id.buttonLogin);
        edtname = findViewById(R.id.editTextTK);
        edtpass = findViewById(R.id.editTextPW);
        tvdangky = findViewById(R.id.textViewdangki);
        tvquenmk = findViewById(R.id.textViewquenmk);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Đang đăng nhập");
        dialog.setCanceledOnTouchOutside(false);
    }
    /**
     * Check input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}
