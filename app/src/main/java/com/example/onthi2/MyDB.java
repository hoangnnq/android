package com.example.onthi2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class MyDB  extends SQLiteOpenHelper {

    public static final String TableName = "Contact_MaDe";
    public static final String Id = "Id";
    public static final String MaSv = "MaSv";
    public static final String TenSv = "TenSv";

    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create table if not exists "+TableName +"("+Id+" Integer Primary Key Autoincrement, "+MaSv+" Text,"+TenSv+" Text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+TableName);
        onCreate(db);
    }
    public void addTaxi(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Id,contact.getId());
        values.put(MaSv,contact.getMaSv());
        values.put(TenSv,contact.getTenSv());
        db.insert(TableName,null,values);
        db.close();
    }
    public void updateContact(int id,Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Id,contact.getId());
        values.put(MaSv,contact.getMaSv());
        values.put(TenSv,contact.getTenSv());
        db.update(TableName,values,Id+"=?",new String []{String .valueOf(id)});
        db.close();
    }
    public void deleteContact(int id){
        SQLiteDatabase db= getWritableDatabase();
        String sql="Delete From "+TableName+" Where ID="+id;
        db.execSQL(sql);
    }
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> list =new ArrayList<>();
        String sql="Select * from "+TableName+ " ORDER BY "+TenSv+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curor= db.rawQuery(sql,null);
        if(curor!=null){
            while(curor.moveToNext()){
                Contact contact= new Contact(
                        curor.getInt(0),
                        curor.getString(1),
                        curor.getString(2)
                );
                list.add(contact);
            }
        }
        return list;
    }
}
