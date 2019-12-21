package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.giohang;
import com.example.myapplication.model.khachhang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<giohang> manggiohang;
    public static ArrayList<khachhang> mangkh;
    private khachhang kh;
    private String ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_information,R.id.navigation_notification)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if (manggiohang != null){

//            Toast.makeText(MainActivity.this, "giỏ hàng trống", Toast.LENGTH_SHORT).show();
        }else {
            manggiohang = new ArrayList<>();
        }
        if (mangkh==null){
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
            kh = (khachhang) getIntent().getSerializableExtra("login");
            int ma_kh = kh.getMakh();
            String us = kh.getUsername();
            String pw = kh.getPassword();
            ten = kh.getName();
            String email = kh.getEmail();
            int sdt = kh.getPhone();
            String diachi = kh.getAddress();
            int status = 1;
            mangkh = new ArrayList<>();
            mangkh.add(new khachhang(ma_kh,us,pw,ten,email,sdt,diachi,status));
            if (mangkh == null){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }

    }

}
