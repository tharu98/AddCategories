package com.example.addcategories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CATEGORY";


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String catName,byte[] image){

         SQLiteDatabase database=getWritableDatabase();
         String sql="INSERT INTO CATEGORY VALUES (NULL,?,?)";

        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,catName);
        statement.bindBlob(2,image);

        statement.executeInsert();

    }
public Cursor getData(){
        SQLiteDatabase database=getWritableDatabase();
        Cursor res=database.rawQuery("select * from CATEGORY ",null);
        return res;
}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
