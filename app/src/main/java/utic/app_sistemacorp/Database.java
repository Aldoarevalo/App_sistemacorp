package utic.app_sistemacorp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper
{
    //CREACION DE BASE DE DATOS
    public Database(Context context) { super(context,"bdpmovil9.bd", null,1); }


    @Override public void onCreate(SQLiteDatabase database)
    {
        //creacion de tablas
        database.execSQL("create table grupo(codigo integer primary key autoincrement,descripcion text);");
        database.execSQL("create table marcas(codigo integer primary key autoincrement,descripcion text)");

        database.execSQL("create table articulos(codigo integer primary key autoincrement,descripcion text,precio integer,gru_cod integer,mar_cod integer)");

        database.execSQL("create table usuarios(codigo integer primary key,nombre text,user text,contrasena text)");
        database.execSQL("insert into usuarios values(01,'JUAN PEREZ','admin','123')");
    }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}

