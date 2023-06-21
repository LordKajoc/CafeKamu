package com.example.pabcafebimsillah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class UpdateActivity extends AppCompatActivity {
    private databasesemuacafepabnya MyDatabase;
    private EditText  NewKodeCafe, NewNama, NewHargaMaks, NewRating, NewAlamat, NewDeskripsi, NewHargaMulai, NewLinkGmap;
    private CircularImageView NewImage;
    private String getNewKode;
    private Button Update, Open;
    private String KodeSend = "KODE";
    private ImageView Back;
    Uri resultUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        MyDatabase = new databasesemuacafepabnya(getBaseContext());

        NewKodeCafe = findViewById(R.id.NewformKodecafenya);
        NewNama = findViewById(R.id.NewformNamaCafe);
        NewHargaMaks = findViewById(R.id.NewformHargaMaks);
        NewRating = findViewById(R.id.NewformRating);
        NewAlamat = findViewById(R.id.NewformAlamat);
        NewHargaMulai = findViewById(R.id.NewformHargaMulai);
        NewDeskripsi = findViewById(R.id.NewformDeskripsi);
        NewImage = findViewById(R.id.Newimage_profile);
        NewLinkGmap = findViewById(R.id.NewformLinkGmapupdate);

        Back = findViewById(R.id.backupdatenugas);

        Bundle extras = getIntent().getExtras();
        getNewKode = extras.getString(KodeSend);
        Update = findViewById(R.id.btnUpdate);
        Open = findViewById(R.id.NewbtnOpen);

        SQLiteDatabase ReadDb = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadDb.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN + " WHERE " + databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN + "=" + getNewKode, null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if (cursor.moveToFirst()) {
            String KodeCafe = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN));
            String Nama = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.NamaCafeCN));
            String HargaMaks = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.HargaMaksCN));
            String Rating = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.RatingCN));
            String Alamat = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.AlamatCN));
            String Deskripsi = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.DeskripsiCN));
            String HargaMulai = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.HargaCN));
            String Foto = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.FotoCN));
            String LinkGmap = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNugas.LinkGmapCN));

            NewKodeCafe.setText(KodeCafe);
            NewNama.setText(Nama);
            NewHargaMaks.setText(HargaMaks);
            NewRating.setText(Rating);
            NewAlamat.setText(Alamat);
            NewDeskripsi.setText(Deskripsi);
            NewHargaMulai.setText(HargaMulai);
            NewImage.setImageURI(Uri.parse(Foto));
            NewLinkGmap.setText(LinkGmap);

            Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpdateData();
                    startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                }
            });
            Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                finish();
                }
            });
            Open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CropImage.startPickImageActivity(UpdateActivity.this);
                }
            });
        }
    }

    private void setUpdateData() {
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();

            String getKodeCafe = NewKodeCafe.getText().toString().trim();
            String getNama = NewNama.getText().toString().trim();
            String getHargaMaks = NewHargaMaks.getText().toString().trim();
            String getRating = NewRating.getText().toString().trim();
            String getAlamat = NewAlamat.getText().toString().trim();
            String getDeskripsi = NewDeskripsi.getText().toString().trim();
            String getHargaMulai = NewHargaMulai.getText().toString().trim();
            String getLinkGmap = NewLinkGmap.getText().toString().trim();

            //Memasukan Data baru pada
            ContentValues values = new ContentValues();
            values.put(databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN, getKodeCafe);
            values.put(databasesemuacafepabnya.KolomCafeNugas.NamaCafeCN, getNama);
            values.put(databasesemuacafepabnya.KolomCafeNugas.HargaMaksCN, getHargaMaks);
            values.put(databasesemuacafepabnya.KolomCafeNugas.RatingCN, getRating);
            values.put(databasesemuacafepabnya.KolomCafeNugas.AlamatCN, getAlamat);
            values.put(databasesemuacafepabnya.KolomCafeNugas.DeskripsiCN, getDeskripsi);
            values.put(databasesemuacafepabnya.KolomCafeNugas.HargaCN, getHargaMulai);
            values.put(databasesemuacafepabnya.KolomCafeNugas.FotoCN, String.valueOf(resultUri));
            values.put(databasesemuacafepabnya.KolomCafeNugas.LinkGmapCN, getLinkGmap);

            //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan Nama
            String selection = databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN + " LIKE ?";
            String[] selectionArgs = {getNewKode};
            ReadData.update(databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN, values, selection, selectionArgs);
            Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
        }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                resultUri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , 0);
            } else {
                startCrop(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                NewImage.setImageURI(result.getUri());
                resultUri = result.getUri();
            }
        }
    }

    private void startCrop(Uri imageuri) {
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(3, 4)
                .start(this);
        resultUri = imageuri;
    }
}