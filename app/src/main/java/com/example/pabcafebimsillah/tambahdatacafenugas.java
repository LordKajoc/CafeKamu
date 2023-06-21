package com.example.pabcafebimsillah;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.NumberFormat;
import java.util.Locale;

public class tambahdatacafenugas extends AppCompatActivity {
    private EditText KodeCafeCN, NamaCN, HargaMaksimalCN, RatingCN, AlamatCN, DeskripsiCN, HargaMulaiCN, LinkGmapCN;
    private String setKodeCafeCN, setNamaCN, setHargaMaksCN, setRatingCN, setAlamatCN, setDeskripsiCN, setHargaMulaiCN,setLinkGmapCN;
    private databasesemuacafepabnya dbcafeCN;
    private Button Open;
    CircularImageView imageView;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdatacafenugas);
        dbcafeCN = new databasesemuacafepabnya(this);
        //inisialisasi bentuk rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //inisialisasi elemen pada layout activity tambahcafenugas
        Button simpan       = (Button) findViewById(R.id.btnSubmitcafenugas);
        Open                = (Button)findViewById(R.id.btnOpencafenugas);
        imageView           = (CircularImageView)findViewById(R.id.image_profilecafenugas);
        KodeCafeCN            = (EditText)findViewById(R.id.formKodecafenugas);
        NamaCN                = (EditText)findViewById(R.id.formNamaCafenugas);
        HargaMaksimalCN       = (EditText)findViewById(R.id.formHargaMakscafenugas);
        RatingCN              = (EditText)findViewById(R.id.formRatingcafenugas);
        HargaMulaiCN          = (EditText)findViewById(R.id.formHargaMulaicafenugas);
        DeskripsiCN           = (EditText)findViewById(R.id.formDeskripsicafenugas);
        AlamatCN              = (EditText)findViewById(R.id.formAlamatcafenugas);
        LinkGmapCN            = (EditText)findViewById(R.id.formLinkGmapCafeNugas);

        //aksi button simpan
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(); //pengambilan data hasil inpput user
                //dilakukan pengecekan ada data yang kosong atau tidak
                //jika iya
                if (KodeCafeCN.equals("") || setNamaCN.equals("") || setHargaMaksCN.equals("") || setRatingCN.equals("") || setHargaMulaiCN.equals("") || setDeskripsiCN.equals("") || setAlamatCN.equals("") || setLinkGmapCN.equals("")){
                    Toast.makeText(getApplicationContext(),"Data Cafe Belum Lengkap atau Belum diisi, Lengkapi Dahulu!", Toast.LENGTH_SHORT).show();
                }else { // jika tidak
                    setData(); //data di ambil
                    saveData(); //data disimpan
                    Toast.makeText(getApplicationContext(),"Data Cafe Tersimpan", Toast.LENGTH_SHORT).show();
                    clearData();//bersihkan data yang disimpan
                    finish(); //kembali ke mainactivity
                }
            }
        });
        //aksi button buka galeri
        Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CropImage.activity().setAspectRatio(3,4).getIntent(getApplicationContext());
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData(){
        setKodeCafeCN = KodeCafeCN.getText().toString();
        setNamaCN = NamaCN.getText().toString();
        setHargaMaksCN = HargaMaksimalCN.getText().toString();
        setRatingCN = RatingCN.getText().toString();
        setAlamatCN = AlamatCN.getText().toString();
        setDeskripsiCN = DeskripsiCN.getText().toString();
        setHargaMulaiCN = HargaMulaiCN.getText().toString();
        setLinkGmapCN = LinkGmapCN.getText().toString();
    }

    //untuk membersihkan input pada kolom
    private void clearData(){
        KodeCafeCN.setText("");
        NamaCN.setText("");
        HargaMaksimalCN.setText("");
        RatingCN.setText("");
        AlamatCN.setText("");
        DeskripsiCN.setText("");
        HargaMulaiCN.setText("");
        imageView.setImageResource(R.drawable.ic_picimg);
        LinkGmapCN.setText("");
    }
    //untuk menympan data pada database
    private void saveData() {
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbcafeCN.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Judul Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        //untuk menyimpan data pada kolom
        //nama Contentvalues.put(namafiledatabase.namaabsractbasekolom.namakolom,data yang sudah di set)
        values.put(databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN, setKodeCafeCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.NamaCafeCN, setNamaCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.HargaMaksCN, setHargaMaksCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.RatingCN, setRatingCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.AlamatCN, setAlamatCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.DeskripsiCN, setDeskripsiCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.HargaCN, setHargaMulaiCN);
        values.put(databasesemuacafepabnya.KolomCafeNugas.FotoCN,String.valueOf(resultUri));
        values.put(databasesemuacafepabnya.KolomCafeNugas.LinkGmapCN, setLinkGmapCN);

        create.insert(databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN, null, values);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode ==  Activity.RESULT_OK) {
                resultUri = result.getUri();
                Log.e("resultUri ->", String.valueOf(resultUri));
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.e("error ->", String.valueOf(error));
            }
        }
    }
}