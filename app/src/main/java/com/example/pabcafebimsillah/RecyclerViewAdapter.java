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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList namaList;
    private ArrayList alamatList;
    private ArrayList linkgmapList;
    private ArrayList kodeList;
    private ArrayList ratingList;
    private ArrayList hargamulaiList;
    private ArrayList fotoList;

    private Context context;
    RecyclerView mRecyclerView;


    //Membuat Konstruktor pada Class RecyclerViewAdapter
    RecyclerViewAdapter(ListFragment listFragment, ArrayList namaList, ArrayList alamatList, ArrayList linkgmapList,ArrayList kodeList, ArrayList ratingList,ArrayList hargamulaiList, ArrayList fotoList) {
        this.namaList= namaList;
        this.alamatList = alamatList;
        this.linkgmapList = linkgmapList;
        this.kodeList = kodeList;
        this.ratingList = ratingList;
        this.hargamulaiList = hargamulaiList;
        this.fotoList = fotoList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Detail, Nama, Alamat, Linkgmap,Kode,Rating, HargaMulai;
        private CircularImageView Foto;
        private ImageButton Delete;


        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            mRecyclerView = itemView.findViewById(R.id.recycler);
            Nama = itemView.findViewById(R.id.NamaCafeListView);
            Alamat = itemView.findViewById(R.id.Alamatlistview);
            Linkgmap = itemView.findViewById(R.id.LinkGmaplistview);
            Kode = itemView.findViewById(R.id.KodeCafelistview);
            Rating = itemView.findViewById(R.id.RatinglistView);
            HargaMulai = itemView.findViewById(R.id.HargaMulaiListView);
            Detail = itemView.findViewById(R.id.textSelengkapnya);
            Foto = itemView.findViewById(R.id.image);
            Delete = itemView.findViewById(R.id.delete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String Nama = (String) namaList.get(position);//Mengambil data (Judul) sesuai dengan posisi yang telah ditentukan
        final String Alamat = (String) alamatList.get(position);
        final String Linkgmap = (String) linkgmapList.get(position);
        final String Kode = (String) kodeList.get(position);
        final String Rating = (String) ratingList.get(position);
        final Double HargaMulai = (Double) hargamulaiList.get(position);
        final String Foto = (String) fotoList.get(position);
        holder.Nama.setText(Nama);
        holder.Alamat.setText(Alamat);
        holder.Linkgmap.setText(Linkgmap);
        holder.Kode.setText(Kode);
        holder.Rating.setText(Rating);
//        holder.Harga.setText(Harga);
        holder.HargaMulai.setText(formatRupiah.format((double)HargaMulai));
        holder.Foto.setImageURI(Uri.parse(Foto));

//      Panggil Onclik Hapus untuk Hapus db dan juga recyclveiw didalam showdialog
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showDialog();
            }

//          dialog hapus
            private void showDialog(){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

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
                                databasesemuacafepabnya getDatabase = new databasesemuacafepabnya(context);
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = databasesemuacafepabnya.KolomCafeNugas.KodeCafeCN + " LIKE ?";
                                //Menentukan Judul Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {holder.Kode.getText().toString()};
                                DeleteData.delete(databasesemuacafepabnya.KolomCafeNugas.NamaTabelCN, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = kodeList.indexOf(Kode);
                                kodeList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(context,"Data Dihapus",Toast.LENGTH_SHORT).show();

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
        holder.Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent dataForm = new Intent(view.getContext(), DetailActivity.class);
                dataForm.putExtra("SendKode", holder.Kode.getText().toString());
                context.startActivity(dataForm);
                ((Activity)context).finish();
                }
            });
    }
    @Override
    public int getItemCount() {
        return kodeList.size();
    }
}
