package com.gastongimenez.www.gestion;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Tonga on 20/04/2015.
 */
public class CrearProducto extends ActionBarActivity {
    EditText nombreProducto;
    EditText precioProducto;
    EditText cantidadProducto;
    Spinner listaCategorias;
    SQLiteDatabase bd;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_producto);
        contexto = getApplicationContext();

        // Obtengo referencias a controles
        nombreProducto = (EditText) findViewById(R.id.nombreProductoIngreso);
       // precioProducto = (EditText) findViewById(R.id.precioProductoIngreso);
       // cantidadProducto = (EditText) findViewById(R.id.cantidadProductosIngreso);

        // Abro base
        BaseDeDatos baseLocal = new BaseDeDatos(this);
        bd = baseLocal.getReadableDatabase();

        // Cargo categorías
        AdaptadorItemLineaString adaptadorCategorias = new AdaptadorItemLineaString(contexto);
        adaptadorCategorias.completarValoresSQL("select * from categorias","nombre");
        listaCategorias = (Spinner) findViewById(R.id.categoriaProductoIngreso);
        listaCategorias.setAdapter(adaptadorCategorias);
        //listaCategorias.setSelection(0);

    }
    public void guardarProducto(View v){
        if (!nombreProducto.getText().toString().isEmpty()){
            try {
                bd.execSQL("INSERT INTO productos VALUES (null,'" +
                        nombreProducto.getText().toString() +
                        "','" +
                        cantidadProducto.getText().toString() +
                        "','" +
                        precioProducto.getText().toString() +
                        "')");
                Intent intent = new Intent(getApplicationContext(), MenuProductos.class);
                startActivity(intent);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuProductos.class);
        startActivity(intent);
    }

}
