package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.squareup.picasso.Picasso;

import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.DETALLE_PRODUCTO;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_PRECIO;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_THUMBNAIL;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_TITULO;

public class DetallesProducto extends AppCompatActivity {
    private static final String TAG = "DetallesProducto";
    TextView tituloProducto, precio, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        Intent intent = getIntent();
        Producto producto =intent.getParcelableExtra(DETALLE_PRODUCTO);
        Log.d(TAG, "producto llega a Detalle = " + producto.toString());

        ImageView imageView = findViewById(R.id.imagenProductoDetalleId);
        Picasso.get().load(producto.getThumbnail()).fit().centerInside().placeholder(R.drawable.imageplaceholder).error(R.drawable.imageplaceholder).into(imageView);
        tituloProducto = findViewById(R.id.titulodetalleproducto);
        tituloProducto.setText(producto.getTitulo());

        precio = findViewById(R.id.precioDetalleProducto);
        int b = Math.round(producto.getPrecio());
        precio.setText("$ " + b);

        state = findViewById(R.id.stateDetalleProducto);

        state.setText(producto.getVendorAddres().getProvincia() + "\n" + producto.getVendorAddres().getCiudadOBarrio());




    }
}
