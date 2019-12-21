package com.example.myapplication.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.khachhang;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    private View view;
    private TextView tvtaikhoan, tvemail, tvsdt, tvtenkh, tvdxuat;


    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information, container, false);
        anhxa();
        setinfo();
        action();
        return view;
    }

    private void action() {
        tvdxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity(),R.style.DialogeTheme);
//                builder.setTitle("Xác nhận xóa món này");
                builder.setMessage(Html.fromHtml("<font color='#F3F51414'>Bạn có chắc muốn đăng xuất không ?</font>"));

                builder.setPositiveButton(Html.fromHtml("<font color='#F3F51414'>Có</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.mangkh.clear();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // hủy trong stack
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton(Html.fromHtml("<font color='#F3F51414'>Không</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }
    private void setinfo() {
        for (int i=0;i<MainActivity.mangkh.size();i++){

            tvtaikhoan.setText(MainActivity.mangkh.get(i).getName());
            tvemail.setText("0"+MainActivity.mangkh.get(i).getPhone());
            tvtenkh.setText(MainActivity.mangkh.get(i).getEmail());
            tvsdt.setText(MainActivity.mangkh.get(i).getUsername());
        }
    }

    private void anhxa() {
        tvtaikhoan = view.findViewById(R.id.tvthongtintk);
        tvemail = view.findViewById(R.id.tvthongtinemail);
        tvsdt = view.findViewById(R.id.tvthongtinsdt);
        tvtenkh = view.findViewById(R.id.tvthongtinten);
        tvdxuat = view.findViewById(R.id.tvdangxuat);
//        arraykh = new ArrayList<>();
//        arraykh.add(new khachhang(kh.getUsername(),kh.getEmail(),kh.getName(),kh.getPhone()));
    }

}
