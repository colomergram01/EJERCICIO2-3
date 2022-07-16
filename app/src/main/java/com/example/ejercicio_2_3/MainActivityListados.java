package com.example.ejercicio_2_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_2_3.Configuraciones.AdapterRegistro;
import com.example.ejercicio_2_3.Configuraciones.ModelRegistro;
import com.example.ejercicio_2_3.Configuraciones.SQLiteConexion;
import com.example.ejercicio_2_3.Configuraciones.Transacciones;

import java.util.ArrayList;
import java.util.List;

public class MainActivityListados extends AppCompatActivity {
;

    ListView listofotos;
    ArrayList<ModelRegistro> lista;
    ArrayList<String> ArregloFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista);

        RecyclerView recicleyview=(RecyclerView)findViewById(R.id.viewListadoImagen);//iniciamos el recycle view
        recicleyview.setLayoutManager(new LinearLayoutManager(this));//le damos un tipo de layout a el recycle

        AdapterRegistro recicleyviewadapter=new AdapterRegistro(ObtenerFoto());//creamos o inizializamos el adaptador con los datos de obtener foto
        recicleyview.setAdapter(recicleyviewadapter);



        Button AgregarFoto=(Button) findViewById(R.id.btnAgregarFoto);
        AgregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<ModelRegistro> ObtenerFoto() {
        SQLiteConexion con = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db= con.getReadableDatabase();
        ModelRegistro modalFoto=null;
        lista= new ArrayList<ModelRegistro>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablapersonas_imag, null);

        while(cursor.moveToNext())
        {
            modalFoto = new ModelRegistro();
            modalFoto.setId(cursor.getInt(0));
            modalFoto.setDescripcion(cursor.getString(2));
            modalFoto.setImagen(cursor.getBlob(1));
            lista.add(modalFoto);
        }
        cursor.close();
        return lista;


    }
}