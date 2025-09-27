package com.marealbs.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MareAlbs.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_PORTE = "porte";
    public static final String COL_PORTE_NOM = "nom";
    public static final String COL_PORTE_DATE = "date";
    public static final String COL_PORTE_ETAT = "etat_porte";

    public static final String TABLE_EMPLOYEUR = "employeur";
    public static final String COL_EMPLOYEUR_CIN = "cin";
    public static final String COL_EMPLOYEUR_NOM = "nom";
    public static final String COL_EMPLOYEUR_PRENOM = "prenom";
    public static final String COL_EMPLOYEUR_NGT = "ngt";
    public static final String COL_EMPLOYEUR_DENSITE = "densite";


    public static final String TABLE_MORET = "moret";
    public static final String COL_MORET_ID = "id";
    public static final String COL_MORET_STATUS = "status";
    public static final String COL_MORET_DESTINATION = "destination";
    public static final String COL_MORET_BASSINS = "bassins";

    // Table Bassin
    public static final String TABLE_BASSIN = "bassin";
    public static final String COL_BASSIN_ID = "id";
    public static final String COL_BASSIN_STATUS = "status";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPorteTable = "CREATE TABLE " + TABLE_PORTE + " (" +
                COL_PORTE_NOM + " TEXT PRIMARY KEY, " +
                COL_PORTE_DATE + " TEXT, " +
                COL_PORTE_ETAT + " INTEGER CHECK(" + COL_PORTE_ETAT + " IN (0, 50, 100)));";
        db.execSQL(createPorteTable);


        String createEmployeurTable = "CREATE TABLE " + TABLE_EMPLOYEUR + " (" +
                COL_EMPLOYEUR_CIN + " INTEGER PRIMARY KEY, " +
                COL_EMPLOYEUR_NOM + " TEXT, " +
                COL_EMPLOYEUR_PRENOM + " TEXT, " +
                COL_EMPLOYEUR_NGT + " INTEGER, " +
                COL_EMPLOYEUR_DENSITE + " INTEGER);";
        db.execSQL(createEmployeurTable);


        String createMoretTable = "CREATE TABLE " + TABLE_MORET + " (" +
                COL_MORET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MORET_STATUS + " INTEGER, " +
                COL_MORET_DESTINATION + " TEXT, " +
                COL_MORET_BASSINS + " TEXT);";
        db.execSQL(createMoretTable);

        String createBassinTable = "CREATE TABLE " + TABLE_BASSIN + " (" +
                COL_BASSIN_ID + " INTEGER PRIMARY KEY, " +
                COL_BASSIN_STATUS + " INTEGER);";
        db.execSQL(createBassinTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PORTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MORET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASSIN);
        onCreate(db);
    }


    public boolean insertPorte(String nom, String date, int etat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_PORTE_NOM, nom);
        cv.put(COL_PORTE_DATE, date);
        cv.put(COL_PORTE_ETAT, etat);
        long result = db.insert(TABLE_PORTE, null, cv);
        db.close();
        return result != -1;
    }


    public boolean insertEmployeur(int cin, String nom, String prenom, int ngt, int densite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_EMPLOYEUR_CIN, cin);
        cv.put(COL_EMPLOYEUR_NOM, nom);
        cv.put(COL_EMPLOYEUR_PRENOM, prenom);
        cv.put(COL_EMPLOYEUR_NGT, ngt);
        cv.put(COL_EMPLOYEUR_DENSITE, densite);
        long result = db.insert(TABLE_EMPLOYEUR, null, cv);
        db.close();
        return result != -1;
    }


    public boolean insertMoret(boolean status, String destination, ArrayList<Integer> bassins) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_MORET_STATUS, status ? 1 : 0);
        cv.put(COL_MORET_DESTINATION, destination);

        JSONArray jsonArray = new JSONArray(bassins);
        cv.put(COL_MORET_BASSINS, jsonArray.toString());
        long result = db.insert(TABLE_MORET, null, cv);
        db.close();
        return result != -1;
    }

    public boolean insertBassin(int id, boolean status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_BASSIN_ID, id);
        cv.put(COL_BASSIN_STATUS, status ? 1 : 0);
        long result = db.insert(TABLE_BASSIN, null, cv);
        db.close();
        return result != -1;
    }
}