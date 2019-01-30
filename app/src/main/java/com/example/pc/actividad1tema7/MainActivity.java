package com.example.pc.actividad1tema7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtCantidad;
    private Spinner lstSeccion;
    String strNombre;
    String strCantidad;
    String strSeccion;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.editText);
        txtCantidad = findViewById(R.id.editText2);
        lstSeccion = findViewById(R.id.spinner);
        String[] secciones = {"Monitor", "DiscoDuro", "Memoria", "Teclado", "Ratón", "Impresora"};
        lstSeccion.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, secciones));
       /* DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('uno',124,'monitor1')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('dos',125,'monitor2')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('tres',126,'monitor3')");
        conn.execSQL("INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('cuatro',127,'monitor4')");
        conn.close();
        */
    }

    public void insertarProducto(View view)
    {
        strNombre = txtNombre.getText().toString();
        strCantidad = txtCantidad.getText().toString();
        strSeccion = lstSeccion.getSelectedItem().toString();
        if(strNombre.equals("") || strCantidad.equals(""))
        {
            mostrarMensaje("Todos los campos son obligatorios");

        }
        else
        {
            String sql = "INSERT INTO PRODUCTO (nombre, cantidad, seccion) VALUES ('" + strNombre + "'," + Integer.parseInt(strCantidad) + ",'" + strSeccion + "' )";
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getWritableDatabase();
            conn.execSQL(sql);
            conn.close();
            mostrarMensaje("El producto" + strNombre + "ha sido insertado");
            limpiarCuadros();
        }
    }

    public void eliminarProducto(View view)
    {
        strNombre = txtNombre.getText().toString();
        if(strNombre.equals(""))
        {
            mostrarMensaje("Todos los campos son obligatorios");
        }
        else
        {
            String sql = "DELETE FROM PRODUCTO WHERE nombre='"+strNombre+"'";
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getReadableDatabase();
            conn.execSQL(sql);
            conn.close();
            mostrarMensaje("Se ha borrado correctamente la fila donde el nombre es " + strNombre );
            limpiarCuadros();
        }
    }
    public void modificarProducto(View view)
    {

    }
    public void buscarProducto(View view)
    {
        strNombre = txtNombre.getText().toString();
        if(strNombre.equals("") )
        {
        mostrarMensaje("Debe indicar el producto a monstrar");
        }
        else
        {
            mostrarMensaje("dentro");
            String sql1 = "SELECT _id, nombre, cantidad, seccion FROM PRODUCTO WHERE nombre LIKE '" + strNombre + "'";
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getReadableDatabase();
            Cursor cursor = conn.rawQuery(sql1,null);
            if(cursor.getCount()==0)
            {
                mostrarMensaje("El producto" + strNombre + "no existe");
            }
            else
            {
                cursor.moveToFirst();
                Long dataIdProducto = cursor.getLong(cursor.getColumnIndex("_id"));
                String dataNombreProducto =cursor.getString(cursor.getColumnIndex("nombre"));
                Integer dataCantidadProducto =cursor.getInt(cursor.getColumnIndex("cantidad"));
                String dataSeccionProducto =cursor.getString(cursor.getColumnIndex("seccion"));
                mostrarMensaje("El id es" + dataIdProducto + ", el nombre es " + dataNombreProducto + ", la cantidad es " + dataCantidadProducto + " y la seccion es " + dataSeccionProducto);
            }
            cursor.close();
            conn.close();

        }


    }
    public void listarProductos(View view)
    {
        EditText resultados = findViewById(R.id.editText3);
        resultados.setText("");
        String sql = "SELECT _id, nombre, cantidad, seccion FROM PRODUCTO ORDER BY nombre ASC";
        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getReadableDatabase();
        Cursor cursor = conn.rawQuery(sql,null);
        if(cursor.getCount()==0)
        {
            mostrarMensaje("La base de datos está vacía");
        }
        else
        {
            cursor.moveToFirst();
            do {
                Long dataIdProducto = cursor.getLong(cursor.getColumnIndex("_id"));
                String dataNombreProducto =cursor.getString(cursor.getColumnIndex("nombre"));
                Integer dataCantidadProducto =cursor.getInt(cursor.getColumnIndex("cantidad"));
                String dataSeccionProducto =cursor.getString(cursor.getColumnIndex("seccion"));
                String msg="El id es" + dataIdProducto + ", el nombre es " + dataNombreProducto + ", la cantidad es " + dataCantidadProducto + " y la seccion es " + dataSeccionProducto;
                resultados.append(msg + "\n");
            }
            while(cursor.moveToNext());
            {

            }
            cursor.close();
            conn.close();
        }

    }
    private void mostrarMensaje(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void limpiarCuadros()
    {
        txtNombre.setText("");
        txtCantidad.setText("");
    }
}

