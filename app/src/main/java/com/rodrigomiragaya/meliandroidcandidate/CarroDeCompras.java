package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Singl.CarroComprasSingleton;

import java.util.ArrayList;

import static com.rodrigomiragaya.meliandroidcandidate.MainActivity.DETALLE_PRODUCTO;


/**
 * Carro de compras Activity
 * will show all the "productos" added
 * */
public class CarroDeCompras extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "CarroDeCompras";

    /* Recycler */
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private Button comprarBtn;
    private TextView suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_de_compras);

        final CarroComprasSingleton carroComprasSingleton = CarroComprasSingleton.getInstance();
        listaProductos = carroComprasSingleton.getListaCarro();

        /* buy button */
        comprarBtn = findViewById(R.id.comprarBtnId);
        comprarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaProductos.isEmpty()){
                    Toast.makeText(CarroDeCompras.this, "El carro está vacío", Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(CarroDeCompras.this, "CACHIN!", Toast.LENGTH_SHORT).show();
                carroComprasSingleton.vaciarCarro();
                adapter.updateData(carroComprasSingleton.getListaCarro());
                suma.setText( "$0");
            }
        });


        suma = findViewById(R.id.sumaPrecioCarro);

        /* sum of all products in cart */
        suma.setText( "$"+ carroComprasSingleton.sumarCuenta());

        initRecycler();
    }

    public void initRecycler(){
        Log.d(TAG, "initRecycler con productos= " + listaProductos.size());
        adapter = new RecyclerAdapter(this, listaProductos);
        recyclerView = findViewById(R.id.recyclerViewLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(CarroDeCompras.this);
    }

    @Override
    public void onItemClick(int position) {
        Intent detalleProductoIntent = new Intent(this, DetallesProducto.class);
        Producto productoSeleccionado = listaProductos.get(position);
        Log.d(TAG, "productoSeleccionado= " + productoSeleccionado.toString());
        detalleProductoIntent.putExtra(DETALLE_PRODUCTO,productoSeleccionado);
        startActivity(detalleProductoIntent);
    }
}
