package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.detailbotom;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.model.monan;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class monanadapter extends RecyclerView.Adapter<monanadapter.itemholder> {
    Context context;
    ArrayList<monan> arrayListmonan;


    public monanadapter(Context context, ArrayList<monan> arrayListmonan) {
        this.context = context;
        this.arrayListmonan = arrayListmonan;
    }


    @NonNull
    @Override
    public itemholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_monan_home,null);
        itemholder item = new itemholder(v);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull itemholder holder, int position) {
        monan monan = arrayListmonan.get(position);
         String ten = monan.getTenmonan();
        holder.tvtenma.setText(ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvgia.setText("->"+decimalFormat.format(monan.getGia())+"đ");
        Picasso.get().load(monan.getHinhanhmonan())
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.imghinhma);

    }

    @Override
    public int getItemCount() {
        return arrayListmonan.size();
    }


    public class itemholder extends RecyclerView.ViewHolder {
        public ImageView imghinhma;
        public TextView tvtenma, tvgia;

        public itemholder(@NonNull final View itemView) {
            super(itemView);
            imghinhma = itemView.findViewById(R.id.imgmonan);
            tvtenma = itemView.findViewById(R.id.textviewtenmon);
            tvgia = itemView.findViewById(R.id.textviewgia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monan monan =arrayListmonan.get(getPosition());
                    if(!monan.getTenmonan().equals("")){
                        FragmentManager fragmentManager=((AppCompatActivity)context).getSupportFragmentManager();
                        detailbotom deltail = new detailbotom(monan);
                        deltail.show(fragmentManager,"deltal botom");
                    }else{
                        Toast.makeText(context, "Không có món!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
