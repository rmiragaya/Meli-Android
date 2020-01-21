package com.rodrigomiragaya.meliandroidcandidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.ViewModels.MainActivityVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    public static final String DETALLE_PRODUCTO = "producto";


    private LottieAnimationView buscando, sinResultados;
    private EditText buscarEditText;
    private ImageView buscarBtn, carro;

    /* Recycler */
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    /* liveData */
    private MainActivityVM mainActivityVM;

    /* Buttons List */
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        /* bindViews */
        buscando = findViewById(R.id.buscandoLottie);
        sinResultados = findViewById(R.id.sinResultados);
        buscarBtn = findViewById(R.id.buscarBtnId);
        carro = findViewById(R.id.carroBtn);
        buscarEditText = findViewById(R.id.busquedaEditTextId);
        radioGroup = findViewById(R.id.categoriasRadioGroup);

        /* Observable List */
        mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);
        mainActivityVM.init();
        mainActivityVM.getProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                listaProductos = new ArrayList<>(productos);

                /* if no results */
                if (listaProductos.isEmpty()){
                    showLottieSinResultados();
                }

                /* update list */
                adapter.updateData(listaProductos);
            }
        });

        /* Observable when load */
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

        /* Search */
        buscarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (busquedaVacia()){
                    buscarEditText.setError(Html.fromHtml("<font color='red'>Que desea?</font>"));
                    return;
                }
                cerrarNoResultados();
                closeKeyboard();
                adapter.clearData();
                Log.d(TAG, "buscar " + buscarEditText.getText().toString());
                mainActivityVM.search(buscarEditText.getText().toString(), getCategoria());
            }
        });

        /* "Enter" action del keyboard */
        buscarEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscarBtn.callOnClick();
                return false;
            }
        });

        initRecycler();

        /* Carro de compras */
        carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CarroDeCompras.class);
                startActivity(intent);
            }
        });
    }

    /* Check wich radiobutton is selectecd, get name and return Category Code */
    private String getCategoria(){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        String categoria;
        switch (radioButton.getText().toString().toLowerCase()){

            case "accesorios vehiculos":
                categoria = "MLA5725";
                break;
            case "animales":
                categoria = "MLA1071";
                break;
            case "antiguedades":
                categoria = "MLA1367";
                break;
            case "artesanias":
                categoria = "MLA1368";
                break;
            case "automotores":
                categoria = "MLA1743";
                break;
            case "bebés":
                categoria = "MLA1384";
                break;
            case "celulares":
                categoria = "MLA1051";
                break;
            case "hobbies":
                categoria = "MLA1798";
                break;
            case "computación":
                categoria = "MLA1648";
                break;
            case "video juegos":
                categoria = "MLA1144";
                break;
            case "cámaras":
                categoria = "MLA1039";
                break;
            case "delicatessen":
                categoria = "MLA1403";
                break;
            case "deportes":
                categoria = "MLA1276";
                break;
            case "electrodomésticos":
                categoria = "MLA5726";
                break;
            case "electrónica":
                categoria = "MLA1000";
                break;
            case "eventos":
                categoria = "MLA2547";
                break;
            case "hogar y jardin":
                categoria = "MLA1574";
                break;
            case "industrias":
                categoria = "MLA1499";
                break;
            case "inmuebles":
                categoria = "MLA1459";
                break;
            default:
                categoria = "";
        }

        Log.d(TAG, "getCategoria return " + categoria);
        return categoria;
    }

    private boolean busquedaVacia(){
        return (buscarEditText.getText().toString().equalsIgnoreCase(""));
    }

    /* Show Lottie when no results "https://github.com/airbnb/lottie-android" */
    private void showLottieSinResultados(){
        Toast.makeText(MainActivity.this, "No se encontraron Resultados", Toast.LENGTH_SHORT).show();
        sinResultados.setVisibility(View.VISIBLE);
        sinResultados.playAnimation();
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

    private void cerrarNoResultados(){
        if (sinResultados.getVisibility() == View.VISIBLE){
            sinResultados.setVisibility(View.GONE);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void mostrarProgressBar(){
        if (buscando.getVisibility() == View.INVISIBLE){
            buscando.setVisibility(View.VISIBLE);
        }
    }

    private void ocultarProgessBar(){
        if (buscando.getVisibility() == View.VISIBLE){
            buscando.setVisibility(View.INVISIBLE);
        }
    }

    /* callback of interface from RecyclerV */
    @Override
    public void onItemClick(int position) {
        Intent detalleProductoIntent = new Intent(this, DetallesProducto.class);
        Producto productoSeleccionado = listaProductos.get(position);
        Log.d(TAG, "productoSeleccionado= " + productoSeleccionado.toString());
        detalleProductoIntent.putExtra(DETALLE_PRODUCTO,productoSeleccionado);
        startActivity(detalleProductoIntent);
    }


}
