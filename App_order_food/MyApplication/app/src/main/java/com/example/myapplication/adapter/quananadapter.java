package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailRestaurantActivity;
import com.example.myapplication.R;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.model.quanan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class quananadapter extends RecyclerView.Adapter<quananadapter.viewholder> {
    ArrayList<quanan> arrayListqa;
    Context context;

    public quananadapter(ArrayList<quanan> arrayListqa, Context context) {
        this.arrayListqa = arrayListqa;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_quan,parent,false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.tvtenquan.setText(arrayListqa.get(position).getTenqa());
        holder.tvdiachiquan.setText(arrayListqa.get(position).getDiachiqa());
        Picasso.get().load(arrayListqa.get(position).getHinhqa())
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.imgqa);
    }

    @Override
    public int getItemCount() {
        return arrayListqa.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView tvtenquan,tvdiachiquan;
        ImageView imgqa;

        public viewholder(@NonNull final View itemView) {
            super(itemView);
            tvtenquan = itemView.findViewById(R.id.tvtenquan);
            tvdiachiquan = itemView.findViewById(R.id.tvdiachi);
            imgqa = itemView.findViewById(R.id.imghinhquan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailRestaurantActivity.class);
                    intent.putExtra("quanan", arrayListqa.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
