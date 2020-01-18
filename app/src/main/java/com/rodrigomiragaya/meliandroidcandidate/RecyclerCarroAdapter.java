package com.rodrigomiragaya.meliandroidcandidate;

import android.content.Context;
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

public class RecyclerCarroAdapter extends RecyclerView.Adapter<RecyclerCarroAdapter.ViewHolder>  {
    private static final String TAG = "RecyclerCarroAdapter";

    private Context context;
    private ArrayList<Producto> productoList;

    ImageView imagenProducto;
    TextView tituloPublicacion, precio;
    ConstraintLayout mLayout;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
