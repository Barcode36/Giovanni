package com.example.giovanni.giovanni.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NOME = "Rubrica.db";
    public static final String CONTACTS_TABLE_NOME = "contacts";
    public static final String CONTACTS_COLUMN_NOME = "nome";
    public static final String CONTACTS_COLUMN_COGNOME = "cognome";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NOME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contacts " + "(id integer primary key, nome text, cognome text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public void insertContact (String nome, String cognome) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("cognome", cognome);
        database.insert("contacts", null, contentValues);
    }

    public Cursor getData(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery( "select * from contacts where id="+id+"", null );
    }

    public Cursor getDataByName(String nome) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "select * from contacts where nome='"+nome+"'";
        return database.rawQuery(query, null);
    }

    public int getRowsCount() {
        SQLiteDatabase database = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(database, CONTACTS_TABLE_NOME);
    }

    public void updateContact (Integer id, String nome, String cognome) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("cognome", cognome);
        database.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void deleteContact (String nome) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("contacts", "nome = ? ", new String[] { nome });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> list = new ArrayList<String>();

        // hp = new HashMap();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor =  database.rawQuery( "select * from contacts", null );
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_NOME)));
            cursor.moveToNext();
        }
        return list;
    }
}