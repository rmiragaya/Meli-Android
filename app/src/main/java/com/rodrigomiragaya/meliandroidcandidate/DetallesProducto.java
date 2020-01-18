package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Singl.CarroComprasSingleton;
import com.squareup.picasso.Picasso;

import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.DETALLE_PRODUCTO;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_PRECIO;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_THUMBNAIL;
import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.EXTRA_TITULO;

public class DetallesProducto extends AppCompatActivity {
    private static final String TAG = "DetallesProducto";
    private TextView tituloProducto, precio, state, descripcionProducto;
    private ImageView mercadoPago;
    private Producto producto;
    private FloatingActionButton addProducto;
    private CarroComprasSingleton carroComprasSingleton = CarroComprasSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        Intent intent = getIntent();
        producto =intent.getParcelableExtra(DETALLE_PRODUCTO);
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

        descripcionProducto = findViewById(R.id.descripcionProductoId);
        //como no tengo acceso a la descripcion del producto, la creo yo
        descripcionProducto.setText(getResources().getString(R.string.descripcion_producto));

        //muestra logo MPago si el producto lo tiene.
        mercadoPago = findViewById(R.id.mercadoPagoId);
        if (producto.isMercadopago()){
            mercadoPago.setVisibility(View.VISIBLE);
        }

        addProducto = findViewById(R.id.fabAddProducto);
        addProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carroComprasSingleton.addProductoAlCarro(producto);
                Toast.makeText(DetallesProducto.this, "Agregado a tu carro de compras!!!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, carroComprasSingleton.getListaCarro().size() + "productos ");

            }
        });






    }
}
