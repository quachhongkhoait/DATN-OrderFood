package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.giohangadapter;
import com.example.myapplication.fragment.InformationFragment;
import com.example.myapplication.model.giohang;
import com.example.myapplication.model.khachhang;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    public static TextView tvtongtiengh;
    private TextView tvdatdongh;
    private giohangadapter giohangadapter;
    private khachhang kh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        anhxa();
        action();
        checkdata();
        evenultil();
        actionbtn();
    }

    private void actionbtn() {
        tvdatdongh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang == null){
                    Toast.makeText(CartActivity.this, "Vui lòng mua hàng trước!!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CartActivity.this,PaymentOrderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void itemcart() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận xóa món này");
                builder.setMessage("Bạn có chắc muốn xóa món này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.manggiohang.size()<=0){

                        }else {
                            MainActivity.manggiohang.remove(position);
                            giohangadapter.notifyDataSetChanged();
                            evenultil();
                            if (MainActivity.manggiohang.size()<=0){
                                Toast.makeText(CartActivity.this, "nullll", Toast.LENGTH_SHORT).show();
                            } else {
                                giohangadapter.notifyDataSetChanged();
                                evenultil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangadapter.notifyDataSetChanged();
                        evenultil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void evenultil() {
        int tongtien =0;
        for (int i=0;i< MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getTongtien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtongtiengh.setText(decimalFormat.format(tongtien)+"đ");
    }

    private void checkdata() {
        if (MainActivity.manggiohang.size()<=0){
            giohangadapter.notifyDataSetChanged();
            Toast.makeText(this, "Giỏ hàng trống!!!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            giohangadapter.notifyDataSetChanged();
        }
    }

    private void action() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbargiohang);
        listView = findViewById(R.id.listViewgiohang);
        tvtongtiengh = findViewById(R.id.tvtongtiengh);
        tvdatdongh = findViewById(R.id.tvdatdongh);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        giohangadapter = new giohangadapter(CartActivity.this,MainActivity.manggiohang);
        listView.setAdapter(giohangadapter);
    }
}
