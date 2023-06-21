package com.example.pabcafebimsillah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class TambahFragment extends Fragment {

    public TambahFragment() {
        // Required empty public constructor
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);
        //inisialisasi dan aksi element pada fragment tambah
        Button cafenugas       = view.findViewById(R.id.btncafenugastf);
        cafenugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataForm = new Intent(view.getContext(), tambahdatacafenugas.class);
                startActivity(dataForm);
            }
        });
        //inisialisasi dan aksi element pada fragment tambah
        Button cafenongkrong       = view.findViewById(R.id.btncafenongkrongtf);
        cafenongkrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataForm = new Intent(view.getContext(), tambahdatacafenongkrong1.class);
                startActivity(dataForm);
            }
        });
        return view;
    }
}
