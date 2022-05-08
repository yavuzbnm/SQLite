package com.yavuzselim.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            //veritabanı açıyoruz
            SQLiteDatabase database=this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //tablo oluşturuyoruz
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR , age INT )");
           database.execSQL("INSERT INTO musicians(name,age) VALUES ('yavuz',22)");
           database.execSQL("INSERT INTO musicians(name,age) VALUES ('selim',23)");


            //veritabanın dan okuma yapmak için cursor kullanıyoruz
            //rawQuery kullanarak veritabanında sorgulama yapıyoruz
            Cursor cursor=database.rawQuery("SELECT * FROM musicians",null);


            //hangi sutunlara gidecegimizi yazıyoruz
            //indexler veritabanında belirlediğimiz indexlerle aynı olmak zornda
            int nameIx=cursor.getColumnIndex("name");
            int ageIx=cursor.getColumnIndex("age");
            int idIx=cursor.getColumnIndex("id");


            //cursor ilerlesin okusun bizde yapmak istediklerimizi while döngüsü içine yazıyoruz
            while (cursor.moveToNext()){
                System.out.println("Name:" + cursor.getString(nameIx) );
                System.out.println("Age:" + cursor.getString(ageIx) );
                System.out.println("id:" + cursor.getString(idIx));

            }
            cursor.close();


        } catch (Exception e){
            //hata bulursak hatamızı yazdırır
            e.printStackTrace();

        }
    }
}