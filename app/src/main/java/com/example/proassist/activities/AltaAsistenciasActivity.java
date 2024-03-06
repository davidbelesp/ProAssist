package com.example.proassist.activities;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.proassist.R;
import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.model.AddFaltaDto;
import com.example.proassist.model.Falta;
import com.example.proassist.model.User;
import com.example.proassist.services.CrearFaltaDiaCService;
import com.example.proassist.services.CrearFaltaService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AltaAsistenciasActivity extends AppCompatActivity implements TaskCompleted {

    private Switch hora1;
    private Switch hora2;
    private Switch hora3;
    private Switch hora4;
    private Switch hora5;
    private Switch hora6;

    private Switch diaCompleto;

    private Button btnAlta;

    private Toolbar toolbar;

    private EditText fechaPicker;
    private TextView tvBienvenida;

    private String fecha;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_asistencias);
        
        loadElements();
        
        setEvents();

        setData();
    }

    private void setData() {
        this.user = (User) getIntent().getSerializableExtra("user");

        this.tvBienvenida.setText("Bienvenid@ " + user.getNombre());
    }

    private void setEvents() {
        this.btnAlta.setOnClickListener(e -> {
            Toast.makeText(this, String.valueOf(hora1.isChecked()), Toast.LENGTH_SHORT).show();
        });

        this.diaCompleto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)this.toggleButtons(false);
            else this.toggleButtons(true);
        });

        this.fechaPicker.setOnClickListener(this::onTouch);

        this.btnAlta.setOnClickListener(e -> darAlta());

        this.toolbar.setNavigationOnClickListener(e -> {finish();});
    }

    private void darAlta() {

        if(this.fecha == null) {
            Toast.makeText(this, "Introduce una fecha", Toast.LENGTH_SHORT).show();
            return;
        }

        String inFecha = this.fecha;
        Integer idProfesor = this.user.getId();

        if(this.diaCompleto.isChecked()){
            AddFaltaDto newFalta = new AddFaltaDto(idProfesor, inFecha);

            Toast.makeText(this, newFalta.toString(), Toast.LENGTH_SHORT).show();
            Log.i("FALTAS INFO", newFalta.toString());
            new CrearFaltaDiaCService(this).execute(newFalta);
            return;
        }


        Switch[] switches = {hora1,hora2,hora3,hora4,hora5,hora6};
        List<AddFaltaDto> faltas = new ArrayList<>();

        for (int i = 1; i <= switches.length; i++){
            if(!switches[i-1].isChecked()) continue;

            faltas.add(new AddFaltaDto(this.user.getId(), this.fecha, String.valueOf(i)));
        }

        for (AddFaltaDto falta: faltas) {
            new CrearFaltaService(this).execute(falta);
        }

        Log.i("FALTAS INFO", faltas.toString());



    }

    private void onTouch(View view) {
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                AltaAsistenciasActivity.this,
                (v, year1, monthOfYear, dayOfMonth) -> {
                    // on below line we are setting date to our text view.
                    this.fecha = getFecha(dayOfMonth, monthOfYear +1, year1);
                    fechaPicker.setText(this.fecha);

                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }

    private void loadElements() {
        this.btnAlta = findViewById(R.id.btnAltaFaltas);
        this.hora1 = findViewById(R.id.switch1hora);
        this.hora2 = findViewById(R.id.switch2hora);
        this.hora3 = findViewById(R.id.switch3hora);
        this.hora4 = findViewById(R.id.switch4hora);
        this.hora5 = findViewById(R.id.switch5hora);
        this.hora6 = findViewById(R.id.switch6hora);
        this.diaCompleto = findViewById(R.id.switchDiaCompleto);

        this.fechaPicker = findViewById(R.id.tvFechaPicker);
        this.tvBienvenida = findViewById(R.id.tvAltaNombreProfesor);

        this.toolbar = findViewById(R.id.toolbarAltaAsistencias);
    }

    private String formatNumber(int number){
        return Integer.valueOf(number) < 10 ? "0"+ number : String.valueOf(number);
    }

    private String getFecha(int day,int month, int year){
        return year + "-" + formatNumber(month) + "-" + formatNumber(day);
    }


    private boolean onTouch(View v, MotionEvent event) {
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                AltaAsistenciasActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // on below line we are setting date to our text view.
                    fechaPicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);

                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
        return false;
    }

    private void toggleButtons(boolean state){
        Switch[] switches = {hora1,hora2,hora3,hora4,hora5,hora6};
        if(state){
            for (Switch sw : switches) {
                sw.setEnabled(true);
                sw.setChecked(false);
            }
        } else {
            for (Switch sw : switches) {
                sw.setEnabled(false);
                sw.setChecked(false);
            }
        }
    };

    @Override
    public void onTaskCompleted(String s) {

    }
}