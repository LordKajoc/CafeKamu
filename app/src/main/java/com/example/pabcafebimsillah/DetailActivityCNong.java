package com.example.pabcafebimsillah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;


public class DetailActivityCNong extends AppCompatActivity {

    private databasesemuacafepabnya MyDatabaseCNong;
    private TextView ShowKodeCafeCNong, ShowNamaCNong, ShowHargaMaksCNong, ShowRatingCNong, ShowAlamatCNong, ShowDeskripsiCNong, ShowHargaMulaiCNong, ShowLinkGmapCNong;
    private ImageView ShowImageCNong, BackCNong;
    private String id ;
    private Button UpdateCNong;
    private String KodeSendCNong = "KODECNong";
    private String sendVal2 = "id2";
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcnong);
        id = getIntent().getStringExtra("SendKodecnong");

        MyDatabaseCNong = new databasesemuacafepabnya(this);

        UpdateCNong =    findViewById(R.id.updateCNong);
        BackCNong = findViewById(R.id.backCNong);
        ShowKodeCafeCNong = findViewById(R.id.KodecafedetailactivityCNong);
        ShowImageCNong = findViewById(R.id.imageDetailCNong);
        ShowNamaCNong = findViewById(R.id.TextNamaCafedetailactivCNong);
        ShowHargaMaksCNong = findViewById(R.id.BoxHargaMaksCNong);
        ShowRatingCNong = findViewById(R.id.BoxRatingCNong);
        ShowAlamatCNong = findViewById(R.id.AlamatcafenugasdetailactivCNong);
        ShowLinkGmapCNong = findViewById(R.id.LinkGmapcafenugasdetailactivCNong);
        ShowDeskripsiCNong = findViewById(R.id.DeskripsiTextCNong);
        ShowHargaMulaiCNong = findViewById(R.id.HargaDetailCNong);

        getData();

        UpdateCNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = ShowKodeCafeCNong.getText().toString();
                    if (Kode != null && Kode != ""){
                        Intent i = new Intent(DetailActivityCNong.this, UpdateActivityCNong.class);
                        i.putExtra(KodeSendCNong, Kode);
                        startActivity(i);
                    }
            }
        });

        BackCNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = ShowKodeCafeCNong.getText().toString();
                Intent intent = new Intent(DetailActivityCNong.this,MainActivity.class);
                intent.putExtra(sendVal2,Kode);
                startActivity(intent);
            }
        });
    }
    private void getData(){
        SQLiteDatabase ReadData = MyDatabaseCNong.getReadableDatabase();
        //masih error
        Cursor cursor = ReadData.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong + " WHERE " + databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong + "=" + id, null);
        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if(cursor.moveToFirst()){
            String KodeCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong));
            String NamaCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.NamaCafeCNong));
            String AlamatCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.AlamatCNong));
            String LinkGmapCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.LinkGmapCNong));
            Double HargaMulaiCNong = cursor.getDouble(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.HargaCNong));
            String HargaMaksCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.HargaMaksCNong));
            String DeskripsiCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.DeskripsiCNong));
            String RatingCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.RatingCNong));
            String FotoCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.FotoCNong));

            ShowKodeCafeCNong.setText(KodeCNong);
            ShowNamaCNong.setText("-"+NamaCNong);
            ShowAlamatCNong.setText(AlamatCNong);
            ShowHargaMaksCNong.setText(HargaMaksCNong);
            ShowRatingCNong.setText(RatingCNong);
            ShowLinkGmapCNong.setText(LinkGmapCNong);
            ShowDeskripsiCNong.setText(DeskripsiCNong);
            ShowHargaMulaiCNong.setText(formatRupiah.format((double)HargaMulaiCNong));
            ShowImageCNong.setImageURI(Uri.parse(FotoCNong));
        }
    }
}