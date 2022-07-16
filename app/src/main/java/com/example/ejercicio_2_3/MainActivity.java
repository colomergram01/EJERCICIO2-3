package com.example.ejercicio_2_3;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ejercicio_2_3.Configuraciones.SQLiteConexion;
import com.example.ejercicio_2_3.Configuraciones.Transacciones;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    static final int PETICION_ACCESO_CAM=201;
    static final int REQUEST_IMAGE_CAPTURE=1;
    CircleImageView circleView;
   ;ContentValues valor;
    Button btnverregistros;
    TextView txtVDescipcion;
    Bitmap imagen;
    byte[] ByteArrayFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView descripciones = (TextView)findViewById(R.id.txtDescripcion);
        CircleImageView circleView = (CircleImageView) findViewById(R.id.perfilImagen);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Obtenerpermisos();
            }
        });

        Button regresar=(Button) findViewById(R.id.btnverListado);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityListados.class);
                startActivity(intent);
            }
        });



        Button btnGuardar=(Button) findViewById(R.id.btnGuardaFotos);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ByteArrayFoto != null && !txtVDescipcion.getText().toString().isEmpty()){
                    GuardarFotos();
                }else{
                    if(ByteArrayFoto == null)
                    {Toast.makeText(getApplicationContext(),"DEBE PRECIONAR EL IMAGEN ", Toast.LENGTH_LONG).show(); circleView.requestFocus();}
                    else if(txtVDescipcion.getText().toString().isEmpty())
                    {Toast.makeText(getApplicationContext(),"DEBE INGRESAR LA DESCRIPCION", Toast.LENGTH_LONG).show(); txtVDescipcion.requestFocus();}
                }
            }
        });


    }

    public void GuardarFotos()
    {
        SQLiteConexion con = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = con.getWritableDatabase();

        valor = new ContentValues();
        valor.put(Transacciones.descripcion,txtVDescipcion.getText().toString());
        valor.put(Transacciones.imagenes,ByteArrayFoto);

        Long resultado = db.insert(Transacciones.tablapersonas_imag,Transacciones.id,valor);
        Toast.makeText(getApplicationContext(),"REGISTRO SE INGRESO CON EXITO! ID:"+resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();
        limpiarPantalla();
        ByteArrayFoto=null;
    }

    private void limpiarPantalla() {
        circleView.setImageResource(R.drawable.ic_person_black);
        txtVDescipcion.setText("");
    }


    private void Obtenerpermisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},PETICION_ACCESO_CAM);
        }
        else{
            ObtenerFoto();
        }
    }

    private void ObtenerFoto() {
        Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(foto.resolveActivity(getPackageManager())!=null){
            startActivityForResult(foto,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PETICION_ACCESO_CAM){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                ObtenerFoto();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"REQUIERE EL PERMISO PARA UTILIZAR LA CAMARA", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imagen = (Bitmap) extras.get("data");
            circleView.setImageBitmap(imagen);
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                imagen.compress(Bitmap.CompressFormat.PNG, 100, bos);
                ByteArrayFoto = bos.toByteArray();
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),"NO SE PUDO CONVERTIR LA FOTO", Toast.LENGTH_LONG).show();
                ByteArrayFoto=null;
                limpiarPantalla();
            }
        }
    }
}

