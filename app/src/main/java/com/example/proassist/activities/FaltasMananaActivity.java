package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.proassist.R;
import com.example.proassist.adapters.FaltasAdapter;
import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.mappers.FaltaMapper;
import com.example.proassist.model.Falta;
import com.example.proassist.services.FaltaHoyService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaltasMananaActivity extends AppCompatActivity implements TaskCompleted {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private Spinner selectorHora;

    private List<Falta> faltas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_manana);

        loadElements();

        setEvents();

        setData();
    }

    private void loadElements() {
        this.toolbar = findViewById(R.id.toolbarFaltasManana);
        this.recyclerView = findViewById(R.id.recyclerFaltasManana);
        this.selectorHora = findViewById(R.id.spinnerSelectorHora);
    }

    private void setEvents() {
        this.toolbar.setNavigationOnClickListener(v -> finish());

        this.selectorHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    setRecycler(faltas);
                    return;
                }
                setRecycler(faltas.stream().filter(f -> Integer.valueOf(f.getHora()) == position)
                        .collect(Collectors.toList()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setData() {
        new FaltaHoyService(FaltasMananaActivity.this).execute("https://sanmigcea.000webhostapp.com/get-faltas-tomorrow.php");
        setSpinner();
    }

    @Override
    public void onTaskCompleted(String s) {
        try {
            faltas = new FaltaMapper().map(s);

            this.setRecycler(faltas);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRecycler(List<Falta> faltas){
        this.recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        this.recyclerView.setAdapter(new FaltasAdapter(this, faltas));
    }

    private void setSpinner(){
        String[] lista = {"Todo" , "1 Hora", "2 Hora", "3 Hora", "4 Hora", "5 Hora", "6 Hora"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_layour ,R.id.tvSpinner, lista);
        adapter.setDropDownViewResource(R.layout.spinner_custom_layour);
        this.selectorHora.setAdapter(adapter);

    }
}