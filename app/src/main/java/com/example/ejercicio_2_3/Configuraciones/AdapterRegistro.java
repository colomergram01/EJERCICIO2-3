package com.example.ejercicio_2_3.Configuraciones;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejercicio_2_3.R;
import java.util.List;

public class AdapterRegistro extends RecyclerView.Adapter<AdapterRegistro.ViewHolder>
{
    public List<ModelRegistro>lista;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descripcion;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion=(TextView) itemView.findViewById(R.id.descripcionTv);
            imagen=(ImageView) itemView.findViewById(R.id.perfilImagen);
        }
    }

    public AdapterRegistro(List<ModelRegistro>lista){
        this.lista=lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//le decimos al viewholder que use como card a el layout de item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mostrar_listado,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {//le mandamos la lista a los obj ya creados
        holder.descripcion.setText(lista.get(pos).getDescripcion());
        holder.imagen.setImageBitmap(BitmapFactory.decodeByteArray(lista.get(pos).getImagen(),0,lista.get(pos).getImagen().length));//conversion de byte[] a bitmap
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

}