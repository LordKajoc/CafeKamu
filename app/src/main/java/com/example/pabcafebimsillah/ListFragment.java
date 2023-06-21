package com.example.pabcafebimsillah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;

public class ListFragment extends Fragment {
    private databasesemuacafepabnya MyDatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private ArrayList NamaList;
    private ArrayList AlamatList;
    private ArrayList LinkgmapList;
    private ArrayList KodeCafeCNList;
    private ArrayList RatingCafeCnList;
    private ArrayList HargaMulaiList;
    private ArrayList FotoList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NamaList = new ArrayList<>();
                AlamatList = new ArrayList<>();
                LinkgmapList = new ArrayList<>();
                KodeCafeCNList = new ArrayList<>();
                RatingCafeCnList = new ArrayList<>();
                HargaMulaiList = new ArrayList<>();
                FotoList = new ArrayList<>();
                MyDatabase = new databasesemuacafepabnya(getActivity().getBaseContext());
                recyclerView = view.findViewById(R.id.recycler);
                getData();
                //terakhir disini
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                adapter = new RecyclerViewAdapter(ListFragment.this, NamaList, AlamatList, LinkgmapList, KodeCafeCNList, RatingCafeCnList,HargaMulaiList, FotoList);
                //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);

                //Membuat Underline pada Setiap Item Didalam List
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
                recyclerView.addItemDecoration(itemDecoration);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        NamaList = new ArrayList<>();
        AlamatList = new ArrayList<>();
        LinkgmapList = new ArrayList<>();
        KodeCafeCNList = new ArrayList<>();
        RatingCafeCnList = new ArrayList<>();
        HargaMulaiList = new ArrayList<>();
        FotoList = new ArrayList<>();

        MyDatabase = new databasesemuacafepabnya(getActivity().getBaseContext());
        recyclerView = view.findViewById(R.id.recycler);
        getData();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(ListFragment.this,NamaList, AlamatList, LinkgmapList, KodeCafeCNList,RatingCafeCnList, HargaMulaiList, FotoList);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
        return view;
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    protected void getData() {
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN, null);
        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        //Melooping Sesuai Dengan Jumlah Data (Count) pada cursor
        for (int count = 0; count < cursor.getCount(); count++) {

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir
//            Mengambil data dari sesuai kolom array
            KodeCafeCNList.add(cursor.getString(0).toString());
            NamaList.add(cursor.getString(1).toString() + " -");
            AlamatList.add(cursor.getString(2).toString());
            LinkgmapList.add(cursor.getString(3).toString());
            HargaMulaiList.add(cursor.getDouble(4));
            RatingCafeCnList.add(cursor.getString(7).toString());
            FotoList.add(cursor.getString(8).toString());
        }
    }
}