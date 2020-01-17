package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_PRECIO;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_THUMBNAIL;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_TITULO;

public class DetallesProducto extends AppCompatActivity {
    private static final String TAG = "DetallesProducto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        Intent intent = getIntent();
        String productoThumnail = intent.getStringExtra(EXTRA_THUMBNAIL);
        String productoTitulo = intent.getStringExtra(EXTRA_TITULO);
        float productoPrecio = intent.getFloatExtra(EXTRA_PRECIO, 0);

        ImageView imageView = findViewById(R.id.imagenProductoDetalleId);
        Log.d(TAG, "productoThumnail: " + productoThumnail);
        Picasso.get().load(productoThumnail).fit().centerInside().placeholder(R.drawable.imageplaceholder).into(imageView);

        TextView titulo = findViewById(R.id.titulodetalleproducto);
        titulo.setText(productoTitulo);
    }
}
