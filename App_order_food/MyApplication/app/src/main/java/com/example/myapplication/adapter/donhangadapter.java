package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.donhang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class donhangadapter extends BaseAdapter {
    Context context;
    ArrayList<donhang> arraydonhang;

    public donhangadapter(Context context, ArrayList<donhang> arraydonhang) {
        this.context = context;
        this.arraydonhang = arraydonhang;
    }

    @Override
    public int getCount() {
        return arraydonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydonhang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class viewhold{
        ImageView imgdh;
        TextView tvmadh,tvdiachidh,tvtongmonan,tvtongtien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewhold viewhold = null;
        if (viewhold == null){
            viewhold = new viewhold();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_donhang,null);
            viewhold.imgdh = convertView.findViewById(R.id.imgdh);
            viewhold.tvmadh = convertView.findViewById(R.id.tvmadonhang);
            viewhold.tvdiachidh = convertView.findViewById(R.id.tvdiachidh);
            viewhold.tvtongmonan = convertView.findViewById(R.id.tvtongmonan);
            viewhold.tvtongtien = convertView.findViewById(R.id.tvtongtiendh);
            convertView.setTag(viewhold);
        } else {
            viewhold = (donhangadapter.viewhold) convertView.getTag();
        }
        donhang dh = (donhang) getItem(position);
        viewhold.tvmadh.setText("Mã đơn hàng: #"+dh.getMadh());
        viewhold.tvdiachidh.setText(dh.getDiachi());
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewhold.tvtongtien.setText("Giá: "+decimalFormat.format(dh.getTongtien())+"đ");
        viewhold.tvtongmonan.setText(" ");
        Picasso.get().load(dh.getAnhquanan())
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_error_black_24dp)
                .into(viewhold.imgdh);
        return convertView;
    }
}
