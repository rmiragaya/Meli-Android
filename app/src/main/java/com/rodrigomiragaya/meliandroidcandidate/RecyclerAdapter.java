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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    private Context context;
    private ArrayList<Producto> productoList;
    private OnItemClickListener mListener;
    //for Proce Format
    private NumberFormat formatter = new DecimalFormat("#,###");

    /* interface for onclick on product in recyclerView */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public RecyclerAdapter(Context context, ArrayList<Producto> productoList) {
        this.productoList = new ArrayList<>(productoList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Producto objetoCurrent = productoList.get(i);
        holder.tituloPublicacion.setText(objetoCurrent.getTitulo());
        //for price format
        int b = Math.round(objetoCurrent.getPrecio());
        String numberPriceFormat = formatter.format(b);
        holder.precio.setText( "$ " + numberPriceFormat);
        Picasso.get().load(objetoCurrent.getThumbnail()).fit().centerInside().placeholder(R.drawable.common_full_open_on_phone).into(holder.imagenProducto);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    /* update recycler */
    public void updateData(ArrayList<Producto> productoList){
        this.productoList = productoList;
        this.notifyDataSetChanged();
    }

    /* clear recycler */
    public void clearData(){
        updateData(new ArrayList<Producto>());
    }
}
