package com.example.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.CartActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.PaymentOrderActivity;
import com.example.myapplication.R;
import com.example.myapplication.detailbotom;
import com.example.myapplication.model.giohang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class giohangadapter extends BaseAdapter {

    Context context;
    ArrayList<giohang> arraygiohang;

    public giohangadapter(Context context, ArrayList<giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class viewholder{
        public TextView tvtengh,tvgiagh,tvslgh,tvxoa;
        public Button btnconggh,btntrugh;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewholder viewholder = null;
        if (convertView == null){
            viewholder = new viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang,null);
            viewholder.tvtengh = convertView.findViewById(R.id.tvtengh);
            viewholder.tvgiagh = convertView.findViewById(R.id.tvgiagh);
            viewholder.tvslgh = convertView.findViewById(R.id.tvslgh);
            viewholder.btnconggh = convertView.findViewById(R.id.btnconggh);
            viewholder.btntrugh = convertView.findViewById(R.id.btntrugh);
            viewholder.tvxoa = convertView.findViewById(R.id.tvxoa);

            convertView.setTag(viewholder);
        } else {
            viewholder = (giohangadapter.viewholder) convertView.getTag();
        }
        final giohang gh = (giohang) getItem(position);
        viewholder.tvtengh.setText(gh.getTenma());
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.tvgiagh.setText("Giá: "+decimalFormat.format(gh.getTongtien())+"đ");
        viewholder.tvslgh.setText(String.valueOf(gh.getSoluong()));
        final giohangadapter.viewholder finalViewholder = viewholder;
        viewholder.btntrugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = 0;
                if (Integer.parseInt(finalViewholder.tvslgh.getText().toString())<=1){
//                    MainActivity.manggiohang.remove(position);
                } else {
                    sl = Integer.parseInt(finalViewholder.tvslgh.getText().toString())-1;
                    finalViewholder.tvslgh.setText(String.valueOf(sl));
                    int slht = MainActivity.manggiohang.get(position).getSoluong();
                    int dongia = MainActivity.manggiohang.get(position).getGiama();
                    long giamoi = dongia * sl;
                    MainActivity.manggiohang.get(position).setSoluong(sl);
                    finalViewholder.tvgiagh.setText("Giá: "+decimalFormat.format(giamoi)+"đ");
                    MainActivity.manggiohang.get(position).setTongtien((int) giamoi);
//                    PaymentOrderActivity.evenultil();
                    tongtam();
                }


            }
        });
        viewholder.btnconggh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(finalViewholder.tvslgh.getText().toString().trim())+1;
                finalViewholder.tvslgh.setText(String.valueOf(sl));
                long dongia = MainActivity.manggiohang.get(position).getGiama();
                long giamoi = dongia * sl;
                MainActivity.manggiohang.get(position).setSoluong(sl);
                finalViewholder.tvgiagh.setText("Giá: "+decimalFormat.format(giamoi)+"đ");
                MainActivity.manggiohang.get(position).setTongtien((int) giamoi);
//                PaymentOrderActivity.evenultil();
                tongtam();
            }
        });
        viewholder.tvxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(context,R.style.DialogeTheme);
//                builder.setTitle("Xác nhận xóa món này");
                builder.setMessage(Html.fromHtml("<font color='#F3F51414'>Bạn có chắc muốn xóa món này</font>"));

                builder.setPositiveButton(Html.fromHtml("<font color='#F3F51414'>Có</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.manggiohang.size()<=0){
                            Toast.makeText(context, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                        }else {
                            removeitem(position);
                            notifyDataSetChanged();
                            tongtam();
                            PaymentOrderActivity.evenultil();
                            if (MainActivity.manggiohang.size()<=0){
                                Toast.makeText(context, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                            } else {
                                notifyDataSetChanged();
                                tongtam();
//                                PaymentOrderActivity.evenultil();
                            }
                        }
                    }
                });

                builder.setNegativeButton(Html.fromHtml("<font color='#F3F51414'>Không</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyDataSetChanged();
                        tongtam();
//                        PaymentOrderActivity.evenultil();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        return convertView;
}
    public void tongtam(){
        int tongtien =0;
        for (int i=0;i< MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getTongtien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        CartActivity.tvtongtiengh.setText(decimalFormat.format(tongtien)+"đ");
    }
    public void removeitem(int position){
        arraygiohang.remove(position);
        notifyDataSetChanged();
    }
}
