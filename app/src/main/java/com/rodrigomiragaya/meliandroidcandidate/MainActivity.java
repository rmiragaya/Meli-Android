package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rodrigomiragaya.meliandroidcandidate.Interface.MeliApi;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.ViewModels.MainActivityVM;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private static final String BASEURL = "https://api.mercadolibre.com/sites/MLA/";
    public static final String EXTRA_TITULO = "titulo_producto";
    public static final String EXTRA_PRECIO = "precio_producto";
    public static final String EXTRA_THUMBNAIL = "imagen_producto";
    public static final String DETALLE_PRODUCTO = "producto";


    private ProgressBar progressBar;
    private MeliApi meliApi;
    private EditText buscarEditText;
    private ImageView buscarBtn;

    /* Recycler */
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    /* liveData */
    private MainActivityVM mainActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_test);

        progressBar = findViewById(R.id.progressBarId);

        mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);

        mainActivityVM.init();

        mainActivityVM.getProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {

                listaProductos = new ArrayList<>(productos);
                adapter.updateData(listaProductos);
            }
        });

        mainActivityVM.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    mostrarProgressBar();
                } else {
                    ocultarProgessBar();
                }
            }
        });

        buscarBtn = findViewById(R.id.buscarBtnId);

        buscarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                adapter.clearData();
                Log.d(TAG, "buscar " + buscarEditText.getText().toString());
                mainActivityVM.search(buscarEditText.getText().toString());

            }
        });

        buscarEditText = findViewById(R.id.busquedaEditTextId);

        buscarEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscarBtn.callOnClick();
                return false;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        meliApi = retrofit.create(MeliApi.class);


        initRecycler();
    }

    public void initRecycler(){
        Log.d(TAG, "initRecycler: ");
        adapter = new RecyclerAdapter(this, listaProductos);
        recyclerView = findViewById(R.id.recyclerViewLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void mostrarProgressBar(){
        if (progressBar.getVisibility() == View.INVISIBLE){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void ocultarProgessBar(){
        if (progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
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
