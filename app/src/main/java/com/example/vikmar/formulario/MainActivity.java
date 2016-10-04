package com.example.vikmar.formulario;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText et1, et2, et3, et4, et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd2 = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        String nombre = et2.getText().toString();
        String descripcion = et3.getText().toString();
        String unidades = et4.getText().toString();
        String valor = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("unidades", unidades);
        registro.put("valor", valor);
        bd2.insert("votantes", null, registro);
        bd2.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        Toast.makeText(this, "Se cargaron los datos correctamente",
                Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd2 = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        Cursor fila = bd2.rawQuery(
                "select nombre,descripcion,unidades,valor  from votantes where codigo =" + codigo, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No existe dicho dato en el formulario",
                    Toast.LENGTH_SHORT).show();
        bd2.close();

    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd2 = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        int cant = bd2.delete("votantes", "codigo=" + codigo, null);
        bd2.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borro los datos del formulario",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe dicho dato en el formulario",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd2 = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        String nombre = et2.getText().toString();
        String descripcion = et3.getText().toString();
        String unidades = et4.getText().toString();
        String valor = et5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("unidades", unidades);
        registro.put("valor", valor);
        int cant = bd2.update("votantes", registro, "codigo=" + codigo, null);
        bd2.close();
        if (cant == 1)
            Toast.makeText(this, "Se modificaron los datos en el formulario", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe dicho dato en el formulario",
                    Toast.LENGTH_SHORT).show();
    }

    public void limpiar (View v){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
    }

}