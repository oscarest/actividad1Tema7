package com.example.pc.actividad1tema7;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('uno',124,'monitor1')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('dos',125,'monitor2')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('tres',126,'monitor3')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('cuatro',127,'monitor4')");
        conn.close();
    }

    public void insertarProducto(View view)
    {

    }

    public void eliminarProducto(View view)
    {

    }
    public void modificarProducto(View view)
    {

    }

    public void buscarProducto(View view)
    {

    }

    public void listarProducto(View view) {
    }
}
