package com.example.pabcafebimsillah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class databasesemuacafepabnya extends SQLiteOpenHelper {
    private static SQLiteDatabase db;

    static abstract class KolomCafeNugas implements BaseColumns {
        static final String NamaTabelCN = "DataCafeNugas";
        static final String KodeCafeCN = "KodeCafeNugas";
        static final String NamaCafeCN = "NamaCafeNugas";
        static final String AlamatCN = "AlamatCafeNugas";
        static final String LinkGmapCN = "LinkMapCafeNugas";
        static final String HargaCN = "HargaMulaiCafeNugas";
        static final String HargaMaksCN = "HargaMaksimalCafeNugas";
        static final String DeskripsiCN = "DeskripsiCafeNugas";
        static final String RatingCN = "RatingCafeNugas";
        static final String FotoCN = "GambarPicCafeNugas";
    }


    static abstract class KolomCafeNongkrong implements BaseColumns {
        static final String NamaTabelCNong = "DataCafeNongkrong";
        static final String KodeCafeCNong = "KodeCafeNongkrong";
        static final String NamaCafeCNong = "NamaCafeNongkrong";
        static final String AlamatCNong = "AlamatCafeNongkrong";
        static final String LinkGmapCNong = "LinkMapCafeNongkrong";
        static final String HargaCNong = "HargaMulaiCafeNongkrong";
        static final String HargaMaksCNong = "HargaMaksimalCafeNongkrong";
        static final String DeskripsiCNong = "DeskripsiCafeNongkrong";
        static final String RatingCNong = "RatingCafeNongkrong";
        static final String FotoCNong = "GambarPicCafeNongkrong";
    }

    private static final String NamaDatabse = "databasesemuacafedisini.db";
    private static final int VersiDatabase = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + KolomCafeNugas.NamaTabelCN +
            "(" + KolomCafeNugas.KodeCafeCN + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + KolomCafeNugas.NamaCafeCN + " TEXT NOT NULL, "
            + KolomCafeNugas.AlamatCN + " TEXT NOT NULL, "
            + KolomCafeNugas.LinkGmapCN + " TEXT NOT NULL, "
            + KolomCafeNugas.HargaCN + " TEXT NOT NULL, "
            + KolomCafeNugas.HargaMaksCN + " TEXT NOT NULL, "
            + KolomCafeNugas.DeskripsiCN + " TEXT NOT NULL, "
            + KolomCafeNugas.RatingCN + " TEXT NOT NULL,"
            + KolomCafeNugas.FotoCN + " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + KolomCafeNugas.NamaTabelCN;

    private static final String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + KolomCafeNongkrong.NamaTabelCNong +
            "(" + KolomCafeNongkrong.KodeCafeCNong + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + KolomCafeNongkrong.NamaCafeCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.AlamatCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.LinkGmapCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.HargaCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.HargaMaksCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.DeskripsiCNong + " TEXT NOT NULL, "
            + KolomCafeNongkrong.RatingCNong + " TEXT NOT NULL,"
            + KolomCafeNongkrong.FotoCNong + " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS " + KolomCafeNongkrong.NamaTabelCNong;

    databasesemuacafepabnya(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        //db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

}

