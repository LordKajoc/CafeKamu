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

public class ListFragmentCNong extends Fragment {
    private databasesemuacafepabnya MyDatabaseCNong;
    private RecyclerView recyclerViewCNong;
    private RecyclerView.Adapter adapterCNong;
    private SwipeRefreshLayout swipeRefreshLayoutCNong;
    //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
    private RecyclerView.LayoutManager layoutManagerCNong = new LinearLayoutManager(getActivity());
    private ArrayList NamaListCNong;
    private ArrayList AlamatListCNong;
    private ArrayList LinkgmapListCNong;
    private ArrayList KodeCafeCNListCNong;
    private ArrayList RatingCafeCnListCNong;
    private ArrayList HargaMulaiListCNong;
    private ArrayList FotoListCNong;

    public ListFragmentCNong() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listcnong, container, false);

        swipeRefreshLayoutCNong = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshCNong);

        swipeRefreshLayoutCNong.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NamaListCNong = new ArrayList<>();
                AlamatListCNong = new ArrayList<>();
                LinkgmapListCNong = new ArrayList<>();
                KodeCafeCNListCNong = new ArrayList<>();
                RatingCafeCnListCNong = new ArrayList<>();
                HargaMulaiListCNong = new ArrayList<>();
                FotoListCNong = new ArrayList<>();
                MyDatabaseCNong = new databasesemuacafepabnya(getActivity().getBaseContext());
                recyclerViewCNong = view.findViewById(R.id.recyclerCNong);
                getData();
                //terakhir disini
                layoutManagerCNong = new LinearLayoutManager(getActivity());
                recyclerViewCNong.setLayoutManager(layoutManagerCNong);
                recyclerViewCNong.setHasFixedSize(true);
                adapterCNong = new RecyclerViewAdapterNongkrong(ListFragmentCNong.this, NamaListCNong, AlamatListCNong, LinkgmapListCNong, KodeCafeCNListCNong, RatingCafeCnListCNong,HargaMulaiListCNong, FotoListCNong);
                //Memasang Adapter pada RecyclerView
                recyclerViewCNong.setAdapter(adapterCNong);

                //Membuat Underline pada Setiap Item Didalam List
                DividerItemDecoration itemDecorationCNong = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecorationCNong.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
                recyclerViewCNong.addItemDecoration(itemDecorationCNong);
                swipeRefreshLayoutCNong.setRefreshing(false);
            }
        });

        NamaListCNong = new ArrayList<>();
        AlamatListCNong = new ArrayList<>();
        LinkgmapListCNong = new ArrayList<>();
        KodeCafeCNListCNong = new ArrayList<>();
        RatingCafeCnListCNong = new ArrayList<>();
        HargaMulaiListCNong = new ArrayList<>();
        FotoListCNong = new ArrayList<>();

        MyDatabaseCNong = new databasesemuacafepabnya(getActivity().getBaseContext());
        recyclerViewCNong = view.findViewById(R.id.recyclerCNong);
        getData();

        layoutManagerCNong = new LinearLayoutManager(getActivity());
        recyclerViewCNong.setLayoutManager(layoutManagerCNong);
        recyclerViewCNong.setHasFixedSize(true);
        adapterCNong = new RecyclerViewAdapterNongkrong(ListFragmentCNong.this,NamaListCNong, AlamatListCNong, LinkgmapListCNong, KodeCafeCNListCNong,RatingCafeCnListCNong, HargaMulaiListCNong, FotoListCNong);
        //Memasang Adapter pada RecyclerView
        recyclerViewCNong.setAdapter(adapterCNong);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecorationCNong = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorationCNong.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
        recyclerViewCNong.addItemDecoration(itemDecorationCNong);
        return view;
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    protected void getData() {
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = MyDatabaseCNong.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong, null);
        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        //Melooping Sesuai Dengan Jumlah Data (Count) pada cursor
        for (int count = 0; count < cursor.getCount(); count++) {

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir
//            Mengambil data dari sesuai kolom array
            KodeCafeCNListCNong.add(cursor.getString(0).toString());
            NamaListCNong.add(cursor.getString(1).toString() + " -");
            AlamatListCNong.add(cursor.getString(2).toString());
            LinkgmapListCNong.add(cursor.getString(3).toString());
            HargaMulaiListCNong.add(cursor.getDouble(4));
            RatingCafeCnListCNong.add(cursor.getString(7).toString());
            FotoListCNong.add(cursor.getString(8).toString());
        }
    }
}