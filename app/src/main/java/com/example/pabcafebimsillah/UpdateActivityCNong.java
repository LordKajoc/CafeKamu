package com.example.pabcafebimsillah;

import android.Manifest;
import android.annotation.SuppressLint;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class UpdateActivityCNong extends AppCompatActivity {
    private databasesemuacafepabnya MyDatabaseCNong;
    private EditText  NewKodeCafeCNong, NewNamaCNong, NewHargaMaksCNong, NewRatingCNong, NewAlamatCNong, NewDeskripsiCNong, NewHargaMulaiCNong, NewLinkGmapCNong;
    private CircularImageView NewImageCNong;
    private String getNewKodeCNong;
    private Button UpdateCNong, OpenCNong;
    private String KodeSendCNong = "KODECNong";
    private ImageView BackCNong;
    Uri resultUriCNong;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecnong);
        MyDatabaseCNong = new databasesemuacafepabnya(getBaseContext());

        NewKodeCafeCNong = findViewById(R.id.NewformKodecafenyaCNong);
        NewNamaCNong = findViewById(R.id.NewformNamaCafeCNong);
        NewHargaMaksCNong = findViewById(R.id.NewformHargaMaksCNong);
        NewRatingCNong = findViewById(R.id.NewformRatingCNong);
        NewAlamatCNong = findViewById(R.id.NewformAlamatCNong);
        NewHargaMulaiCNong = findViewById(R.id.NewformHargaMulaiCNong);
        NewDeskripsiCNong = findViewById(R.id.NewformDeskripsiCNong);
        NewImageCNong = findViewById(R.id.Newimage_profileCNong);
        NewLinkGmapCNong = findViewById(R.id.NewformLinkGmapupdateCNong);

        BackCNong = findViewById(R.id.backCNong);

        Bundle extras = getIntent().getExtras();
        getNewKodeCNong = extras.getString(KodeSendCNong);
        UpdateCNong = findViewById(R.id.btnUpdateCNong);
        OpenCNong = findViewById(R.id.NewbtnOpenCNong);

        SQLiteDatabase ReadDb = MyDatabaseCNong.getReadableDatabase();
        Cursor cursor = ReadDb.rawQuery("SELECT * FROM " + databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong + " WHERE " + databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong + "=" + getNewKodeCNong, null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if (cursor.moveToFirst()) {
            String KodeCafeCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong));
            String NamaCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.NamaCafeCNong));
            String HargaMaksCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.HargaMaksCNong));
            String RatingCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.RatingCNong));
            String AlamatCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.AlamatCNong));
            String DeskripsiCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.DeskripsiCNong));
            String HargaMulaiCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.HargaCNong));
            String FotoCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.FotoCNong));
            String LinkGmapCNong = cursor.getString(cursor.getColumnIndex(databasesemuacafepabnya.KolomCafeNongkrong.LinkGmapCNong));

            NewKodeCafeCNong.setText(KodeCafeCNong);
            NewNamaCNong.setText(NamaCNong);
            NewHargaMaksCNong.setText(HargaMaksCNong);
            NewRatingCNong.setText(RatingCNong);
            NewAlamatCNong.setText(AlamatCNong);
            NewDeskripsiCNong.setText(DeskripsiCNong);
            NewHargaMulaiCNong.setText(HargaMulaiCNong);
            NewImageCNong.setImageURI(Uri.parse(FotoCNong));
            NewLinkGmapCNong.setText(LinkGmapCNong);

            UpdateCNong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpdateData();
                    startActivity(new Intent(UpdateActivityCNong.this, MainActivity.class));
                }
            });
            BackCNong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                finish();
                }
            });
            OpenCNong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CropImage.startPickImageActivity(UpdateActivityCNong.this);
                }
            });
        }
    }

    private void setUpdateData() {
        SQLiteDatabase ReadData = MyDatabaseCNong.getReadableDatabase();

            String getKodeCafeCNong = NewKodeCafeCNong.getText().toString().trim();
            String getNamaCNong = NewNamaCNong.getText().toString().trim();
            String getHargaMaksCNong = NewHargaMaksCNong.getText().toString().trim();
            String getRatingCNong = NewRatingCNong.getText().toString().trim();
            String getAlamatCNong = NewAlamatCNong.getText().toString().trim();
            String getDeskripsiCNong = NewDeskripsiCNong.getText().toString().trim();
            String getHargaMulaiCNong = NewHargaMulaiCNong.getText().toString().trim();
            String getLinkGmapCNong = NewLinkGmapCNong.getText().toString().trim();

            //Memasukan Data baru pada
            ContentValues values = new ContentValues();
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong, getKodeCafeCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.NamaCafeCNong, getNamaCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.HargaMaksCNong, getHargaMaksCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.RatingCNong, getRatingCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.AlamatCNong, getAlamatCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.DeskripsiCNong, getDeskripsiCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.HargaCNong, getHargaMulaiCNong);
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.FotoCNong, String.valueOf(resultUriCNong));
            values.put(databasesemuacafepabnya.KolomCafeNongkrong.LinkGmapCNong, getLinkGmapCNong);

            //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan Nama
            String selection = databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong + " LIKE ?";
            String[] selectionArgs = {getNewKodeCNong};
            ReadData.update(databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong, values, selection, selectionArgs);
            Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
        }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri imageuriCNong = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuriCNong)) {
                resultUriCNong = imageuriCNong;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , 0);
            } else {
                startCrop(imageuriCNong);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                NewImageCNong.setImageURI(result.getUri());
                resultUriCNong = result.getUri();
            }
        }
    }

    private void startCrop(Uri imageuriCNong) {
        CropImage.activity(imageuriCNong)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(3, 4)
                .start(this);
        resultUriCNong = imageuriCNong;
    }
}