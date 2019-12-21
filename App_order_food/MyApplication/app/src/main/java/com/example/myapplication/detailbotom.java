package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.model.giohang;
import com.example.myapplication.model.monan;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.CollationElementIterator;
import java.text.DecimalFormat;

public class detailbotom extends BottomSheetDialogFragment {

    View view;
    ImageView imageView,imgtru,imgcong;
    public static TextView tvgia,tvten,tvthem,tvsl;
    private com.example.myapplication.model.monan monan;
    int mamon =0;
    String maloai ="";
    String tenmonan = "";
    Integer gia = 0;
    String hinhanhmonan = "";

    public detailbotom(com.example.myapplication.model.monan monan) {
        this.monan = monan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_bottom_sheet, container,false);

        anhxa();
        getmonan();
        getdata();
        eventbutton();
        return view;
    }

    private void getmonan() {
        mamon = monan.getMamonan();
        maloai = monan.getMaloai();
        tenmonan = monan.getTenmonan();
        gia = monan.getGia();
        hinhanhmonan = monan.getHinhanhmonan();
    }

    private void anhxa() {
        imageView = view.findViewById(R.id.imgmonan1);
        tvgia = view.findViewById(R.id.textviewgia1);
        tvten = view.findViewById(R.id.textviewtenmon1);
        tvthem = view.findViewById(R.id.tvthem);
        imgtru = view.findViewById(R.id.btntru);
        imgcong = view.findViewById(R.id.btncong);
        tvsl = view.findViewById(R.id.tvsoluong);
    }
    public static void giatien(int a,TextView b){

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        b.setText("Thêm món "+decimalFormat.format(a)+"đ");
    }
    private void eventbutton() {
        giatien(monan.getGia(),tvthem);
        imgtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = 0;
                if (Integer.parseInt(tvsl.getText().toString())<=1){
                    detailbotom.this.dismiss();
                } else {
                    sl = Integer.parseInt(tvsl.getText().toString())-1;
                    tvsl.setText(String.valueOf(sl));
                    int tong = monan.getGia()*sl;
                    giatien(tong,tvthem);
                }

            }
        });
        imgcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(tvsl.getText().toString())+1;
                tvsl.setText(String.valueOf(sl));
                imgtru.setVisibility(View.VISIBLE);
                int tong = monan.getGia()*sl;
                giatien(tong,tvthem);
            }
        });
        tvthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0){
                    int sl = Integer.parseInt(tvsl.getText().toString());
                    boolean exit = false;
                    for (int i =0; i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getMama()==mamon){
                            MainActivity.manggiohang.get(i).setSoluong(MainActivity.manggiohang.get(i).getSoluong()+sl);
                            if (MainActivity.manggiohang.get(i).getSoluong()>=10){
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setTongtien(gia*MainActivity.manggiohang.get(i).getSoluong());
                            exit =true;
                        }
                    }
                    if (exit == false){
                        int soluong = Integer.parseInt(tvsl.getText().toString());
                        int tongmoi = soluong * gia;
                        MainActivity.manggiohang.add(new giohang(mamon,tenmonan,gia,hinhanhmonan,soluong,tongmoi));
                    }
                } else {
                    int soluong = Integer.parseInt(tvsl.getText().toString());
                    int tongmoi = soluong * gia;
                    MainActivity.manggiohang.add(new giohang(mamon,tenmonan,gia,hinhanhmonan,soluong,tongmoi));
//                    if (MainActivity.manggiohang.size()<=0){
//                        Toast.makeText(getActivity(), "mảng trống", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(), "mảng có", Toast.LENGTH_SHORT).show();
//                    }

                }
                Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
                detailbotom.this.dismiss();
//                Intent intent = new Intent(getActivity(),CartActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void getdata(){
        if(!monan.getHinhanhmonan().equals("") && !monan.getGia().equals("") && !monan.getTenmonan().equals("")){
            tvten.setText(monan.getTenmonan());
            Picasso.get().load(monan.getHinhanhmonan())
                    .placeholder(R.drawable.ic_panorama_fish_eye_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(imageView);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvgia.setText("Giá: "+decimalFormat.format(monan.getGia())+"đ");
        }else{
//            Toast.makeText(getActivity(), "khong co du lieu", Toast.LENGTH_SHORT).show();
        }
    }
}
