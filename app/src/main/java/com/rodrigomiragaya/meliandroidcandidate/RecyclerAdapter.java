package com.rodrigomiragaya.meliandroidcandidate;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<Producto> productoList;


    public RecyclerAdapter(ArrayList<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tituloPublicacion.setText(productoList.get(i).getTitulo());
        holder.precio.setText( "$" + productoList.get(i).getPrecio().toString());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagenProducto;
        TextView tituloPublicacion, precio;
        ConstraintLayout mLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProducto = itemView.findViewById(R.id.imagenPublicacionId);
            tituloPublicacion = itemView.findViewById(R.id.tituloPublicacionId);
            precio = itemView.findViewById(R.id.precioPublicacionId);
            mLayout = itemView.findViewById(R.id.racyclerProductoLayout);

        }
    }

    public void updateData(ArrayList<Producto> productoList){
        this.productoList = productoList;
        this.notifyDataSetChanged();
    }
}
