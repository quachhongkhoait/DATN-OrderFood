package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.khachhang;
import com.example.myapplication.ultil.server;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "khoa";
    ImageView imgdk;
    EditText edttk,edtmk,edtten,edtemail,edtsdt;
    Button btnxn;
    Toolbar toolbar;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        addevent();


    }

    private void addevent() {
        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edttk.getText().toString().trim();
                String password = edtmk.getText().toString().trim();
                String tenkh = edtten.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
//                int sdt = Integer.parseInt(sodt);
                registeruser(username,password,email,tenkh,sdt);
            }
        });
    }
    private void registeruser(final String username, final String password, final String email, final String name, final String phone){
        if (checkEditText(edttk) && checkEditText(edtmk) && checkEditText(edtemail) && checkEditText(edtten) && checkEditText(edtsdt) && isValidEmail(email)){
            dialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urlregister, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    String message = "";
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int suc= jsonObject.getInt("success");
                        if ( suc == 1){
                            Log.d(TAG, "suc = "+suc);
                            khachhang kh = new khachhang();
                            kh.setUsername(jsonObject.getString("taikhoan"));
                            kh.setEmail(jsonObject.getString("message"));
                            Toast.makeText(RegisterActivity.this, message+"", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            message = jsonObject.getString("message");
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex ){
                        VolleyLog.d(TAG, "Error: " + ex.getMessage());
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("taikhoan", username);
                    hashMap.put("matkhau", password);
                    hashMap.put("email", email);
                    hashMap.put("tenkh",name);
                    hashMap.put("sodienthoai",phone);
                    return hashMap;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }
    }

    private void anhxa() {
        imgdk = findViewById(R.id.imgDK);
        edttk = findViewById(R.id.editTextTKDK);
        edtmk = findViewById(R.id.editTextPWDK);
        btnxn = findViewById(R.id.btnxacnhanDK);
        edtten =findViewById(R.id.editTextTenDK);
        edtsdt = findViewById(R.id.editTextSDTDK);
        edtemail = findViewById(R.id.editTextEMDK);
        toolbar = findViewById(R.id.toolbar);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Đang đăng ký");
        dialog.setCanceledOnTouchOutside(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtemail.setError("Email sai định dạng!");
        }
        return false;
    }
}
