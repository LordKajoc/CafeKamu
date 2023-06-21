package com.example.pabcafebimsillah;

import androidx.appcompat.app.AppCompatActivity;

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

import java.text.NumberFormat;
import java.util.Locale;


public class DetailActivity extends AppCompatActivity {

    private databasesemuacafepabnya MyDatabase;
    private TextView ShowKodeCafe, ShowNama, ShowHargaMaks, ShowRating, ShowAlamat, ShowDeskripsi, ShowHargaMulai, ShowLinkGmap;
    private ImageView ShowImage, Back;
    private String id ;
    private Button Update;
    private String KodeSend = "KODE";
    private String sendVal = "id";
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra("SendKode");

        MyDatabase = new databasesemuacafepabnya(this);

        Update =    findViewById(R.id.update);
        Back = findViewById(R.id.backdetail);
        ShowKodeCafe = findViewById(R.id.Kodecafedetailactivity);
        ShowImage = findViewById(R.id.imageDetail);
        ShowNama = findViewById(R.id.TextNamaCafedetailactiv);
        ShowHargaMaks = findViewById(R.id.BoxHargaMaks);
        ShowRating = findViewById(R.id.BoxRating);
        ShowAlamat = findViewById(R.id.Alamatcafenugasdetailactiv);
        ShowLinkGmap = findViewById(R.id.LinkGmapcafenugasdetailactiv);
        ShowDeskripsi = findViewById(R.id.DeskripsiText);
        ShowHargaMulai = findViewById(R.id.HargaDetail);

        getData();

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = ShowKodeCafe.getText().toString();
                    if (Kode != null && Kode != ""){
                        Intent i = new Intent(DetailActivity.this, UpdateActivity.class);
                        i.putExtra(KodeSend, Kode);
                        startActivity(i);
                    }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = ShowKodeCafe.getText().toString();
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                intent.putExtra(sendVal,Kode);
                startActivity(intent);
            }
        });
    }
    private void getData(){
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        //masih error
        Cursor cursor = ReadData.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN + " WHERE " + databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN + "=" + id, null);
        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if(cursor.moveToFirst()){
            String Kode = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN));
            String Nama = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.NamaCafeCN));
            String Alamat = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.AlamatCN));
            String LinkGmap = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.LinkGmapCN));
            Double HargaMulai = cursor.getDouble(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.HargaCN));
            String HargaMaks = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.HargaMaksCN));
            String Deskripsi = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.DeskripsiCN));
            String Rating = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.RatingCN));
            String Foto = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.FotoCN));

            ShowKodeCafe.setText(Kode);
            ShowNama.setText("-"+Nama);
            ShowAlamat.setText(Alamat);
            ShowHargaMaks.setText(HargaMaks);
            ShowRating.setText(Rating);
            ShowLinkGmap.setText(LinkGmap);
            ShowDeskripsi.setText(Deskripsi);
            ShowHargaMulai.setText(formatRupiah.format((double)HargaMulai));
            ShowImage.setImageURI(Uri.parse(Foto));
        }
    }
}