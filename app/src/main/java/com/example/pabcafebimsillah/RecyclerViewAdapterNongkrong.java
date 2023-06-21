package com.example.pabcafebimsillah;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecyclerViewAdapterNongkrong extends RecyclerView.Adapter<RecyclerViewAdapterNongkrong.ViewHolder>{
    private ArrayList namaListCNong;
    private ArrayList alamatListCNong;
    private ArrayList linkgmapListCNong;
    private ArrayList kodeListCNong;
    private ArrayList ratingListCNong;
    private ArrayList hargamulaiListCNong;
    private ArrayList fotoListCNong;

    private Context contextCNong;
    RecyclerView mRecyclerViewCNong;


    //Membuat Konstruktor pada Class RecyclerViewAdapter
    RecyclerViewAdapterNongkrong(ListFragmentCNong listFragmentCNong, ArrayList namaListCNong, ArrayList alamatListCNong, ArrayList linkgmapListCNong, ArrayList kodeListCNong, ArrayList ratingListCNong, ArrayList hargamulaiListCNong, ArrayList fotoListCNong) {
        this.namaListCNong= namaListCNong;
        this.alamatListCNong = alamatListCNong;
        this.linkgmapListCNong = linkgmapListCNong;
        this.kodeListCNong = kodeListCNong;
        this.ratingListCNong = ratingListCNong;
        this.hargamulaiListCNong = hargamulaiListCNong;
        this.fotoListCNong = fotoListCNong;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView DetailCNong, NamaCNong, AlamatCNong, LinkgmapCNong,KodeCNong,RatingCNong, HargaMulaiCNong;
        private CircularImageView FotoCNong;
        private ImageButton DeleteCNong;


        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            contextCNong = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            mRecyclerViewCNong = itemView.findViewById(R.id.recyclerCNong);
            NamaCNong = itemView.findViewById(R.id.NamaCafeListViewCNong);
            AlamatCNong = itemView.findViewById(R.id.AlamatlistviewCNong);
            LinkgmapCNong = itemView.findViewById(R.id.LinkGmaplistviewCNong);
            KodeCNong = itemView.findViewById(R.id.KodeCafelistviewCNong);
            RatingCNong = itemView.findViewById(R.id.RatinglistViewCNong);
            HargaMulaiCNong = itemView.findViewById(R.id.HargaMulaiListViewCNong);
            DetailCNong = itemView.findViewById(R.id.textSelengkapnyaCNong);
            FotoCNong = itemView.findViewById(R.id.imagerecyclercnong);
            DeleteCNong = itemView.findViewById(R.id.deleterecyclercnong);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recyclercnong, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String NamaCNong = (String) namaListCNong.get(position);//Mengambil data (Judul) sesuai dengan posisi yang telah ditentukan
        final String AlamatCNong = (String) alamatListCNong.get(position);
        final String LinkgmapCNong = (String) linkgmapListCNong.get(position);
        final String KodeCNong = (String) kodeListCNong.get(position);
        final String RatingCNong = (String) ratingListCNong.get(position);
        final Double HargaMulaiCNong = (Double) hargamulaiListCNong.get(position);
        final String FotoCNong = (String) fotoListCNong.get(position);
        holder.NamaCNong.setText(NamaCNong);
        holder.AlamatCNong.setText(AlamatCNong);
        holder.LinkgmapCNong.setText(LinkgmapCNong);
        holder.KodeCNong.setText(KodeCNong);
        holder.RatingCNong.setText(RatingCNong);
//        holder.Harga.setText(Harga);
        holder.HargaMulaiCNong.setText(formatRupiah.format((double)HargaMulaiCNong));
        holder.FotoCNong.setImageURI(Uri.parse(FotoCNong));

//      Panggil Onclik Hapus untuk Hapus db dan juga recyclveiw didalam showdialog
        holder.DeleteCNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showDialog();
            }

//          dialog hapus
            private void showDialog(){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(contextCNong);

                // set title dialog
                alertDialogBuilder.setTitle("Apakah Anda Ingin Menghapus Data ini?");

                // set pesan dari dialog
                alertDialogBuilder
                        .setIcon(R.drawable.ic_delete)
                        .setCancelable(false)
                        .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, maka akan menutup activity ini

                                //Menghapus Data Dari Database
                                databasesemuacafepabnya getDatabase = new databasesemuacafepabnya(contextCNong);
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = databasesemuacafepabnya.KolomCafeNongkrong.KodeCafeCNong + " LIKE ?";
                                //Menentukan Judul Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {holder.KodeCNong.getText().toString()};
                                DeleteData.delete(databasesemuacafepabnya.KolomCafeNongkrong.NamaTabelCNong, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = kodeListCNong.indexOf(KodeCNong);
                                kodeListCNong.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(contextCNong,"Data Dihapus",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });

//        onklik see detail
        holder.DetailCNong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent dataForm = new Intent(view.getContext(), DetailActivityCNong.class);
                dataForm.putExtra("SendKodecnong", holder.KodeCNong.getText().toString());
                contextCNong.startActivity(dataForm);
                ((Activity)contextCNong).finish();
                }
            });
    }
    @Override
    public int getItemCount() {
        return kodeListCNong.size();
    }
}
