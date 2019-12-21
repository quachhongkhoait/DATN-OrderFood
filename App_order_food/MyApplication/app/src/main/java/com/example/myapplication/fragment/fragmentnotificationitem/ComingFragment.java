package com.example.myapplication.fragment.fragmentnotificationitem;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.khachhang;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComingFragment extends Fragment {

    private View view;
    private khachhang kh;
    public ComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        kh = new khachhang();
        kh = (khachhang) intent.getSerializableExtra("login");

        if (kh == null){
            view = inflater.inflate(R.layout.input_login, container, false);
            TextView tvdn = view.findViewById(R.id.chualogin);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentt = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intentt);
                }
            });
        } else {
            view = inflater.inflate(R.layout.fragment_coming, container, false);

        }

        return view;
    }

}
