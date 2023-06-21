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

public class tambahdatacafenongkrong1 extends AppCompatActivity {
    private EditText KodeCafeCNong, NamaCNong, HargaMaksimalCNong, RatingCNong, AlamatCNong, DeskripsiCNong, HargaMulaiCNong, LinkGmapCNong;
    private String setKodeCafeCNong, setNamaCNong, setHargaMaksCNong, setRatingCNong, setAlamatCNong, setDeskripsiCNong, setHargaMulaiCNong,setLinkGmapCNong;
    private databasesemuacafepabnya dbcafeCNong;
    private Button OpenCNong;
    CircularImageView imageViewCNong;
    Uri resultUriCNong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdatacafenongkrong);
        dbcafeCNong = new databasesemuacafepabnya(this);
        //inisialisasi bentuk rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //inisialisasi elemen pada layout activity tambahcafenongkrong
        Button simpan           = (Button) findViewById(R.id.btnSubmitcafenongkrong);
        OpenCNong               = (Button)findViewById(R.id.btnOpencafenongkrong);
        imageViewCNong          = (CircularImageView)findViewById(R.id.image_profilecafenongkrong);
        KodeCafeCNong           = (EditText)findViewById(R.id.formKodecafenongkrong);
        NamaCNong               = (EditText)findViewById(R.id.formNamaCafenongkrong);
        HargaMaksimalCNong      = (EditText)findViewById(R.id.formHargaMakscafenongkrong);
        RatingCNong             = (EditText)findViewById(R.id.formRatingcafenongkrong);
        HargaMulaiCNong         = (EditText)findViewById(R.id.formHargaMulaicafenongkrong);
        DeskripsiCNong          = (EditText)findViewById(R.id.formDeskripsicafenongkrong);
        AlamatCNong             = (EditText)findViewById(R.id.formAlamatcafenongkrong);
        LinkGmapCNong           = (EditText)findViewById(R.id.formLinkGmapCafeNongkrong);

        //aksi button simpan
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(); //pengambilan data hasil inpput user
                //dilakukan pengecekan ada data yang kosong atau tidak
                //jika iya
                if (KodeCafeCNong.equals("") || setNamaCNong.equals("") || setHargaMaksCNong.equals("") || setRatingCNong.equals("") || setHargaMulaiCNong.equals("") || setDeskripsiCNong.equals("") || setAlamatCNong.equals("") || setLinkGmapCNong.equals("")){
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
        OpenCNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CropImage.activity().setAspectRatio(3,4).getIntent(getApplicationContext());
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData(){
        setKodeCafeCNong = KodeCafeCNong.getText().toString();
        setNamaCNong = NamaCNong.getText().toString();
        setHargaMaksCNong = HargaMaksimalCNong.getText().toString();
        setRatingCNong = RatingCNong.getText().toString();
        setAlamatCNong = AlamatCNong.getText().toString();
        setDeskripsiCNong = DeskripsiCNong.getText().toString();
        setHargaMulaiCNong = HargaMulaiCNong.getText().toString();
        setLinkGmapCNong = LinkGmapCNong.getText().toString();
    }

    //untuk membersihkan input pada kolom
    private void clearData(){
        KodeCafeCNong.setText("");
        NamaCNong.setText("");
        HargaMaksimalCNong.setText("");
        RatingCNong.setText("");
        AlamatCNong.setText("");
        DeskripsiCNong.setText("");
        HargaMulaiCNong.setText("");
        imageViewCNong.setImageResource(R.drawable.ic_picimg);
        LinkGmapCNong.setText("");
    }
    //untuk menympan data pada database
    private void saveData() {
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbcafeCNong.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Judul Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        //untuk menyimpan data pada kolom
        //nama Contentvalues.put(namafiledatabase.namaabsractbasekolom.namakolom,data yang sudah di set)
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong, setKodeCafeCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.NamaCafeCNong, setNamaCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.HargaMaksCNong, setHargaMaksCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.RatingCNong, setRatingCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.AlamatCNong, setAlamatCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.DeskripsiCNong, setDeskripsiCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.HargaCNong, setHargaMulaiCNong);
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.FotoCNong,String.valueOf(resultUriCNong));
        values.put(databasesemuacafepabnya.KolomCafeNongkrong.LinkGmapCNong, setLinkGmapCNong);

        create.insert(databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong, null, values);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode ==  Activity.RESULT_OK) {
                resultUriCNong = result.getUri();
                Log.e("resultUri ->", String.valueOf(resultUriCNong));
                imageViewCNong.setImageURI(resultUriCNong);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.e("error ->", String.valueOf(error));
            }
        }
    }
}