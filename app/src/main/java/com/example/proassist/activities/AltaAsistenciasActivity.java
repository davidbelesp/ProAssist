package com.example.proassist.activities;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proassist.R;

import java.util.Calendar;

public class AltaAsistenciasActivity extends AppCompatActivity {

    private Switch hora1;
    private Switch hora2;
    private Switch hora3;
    private Switch hora4;
    private Switch hora5;
    private Switch hora6;

    private Button btnAlta;

    private EditText fechaPicker;

    private String fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_asistencias);
        
        loadElements();
        
        setEvents();
    }

    private void setEvents() {
        this.btnAlta.setOnClickListener(e -> {
            Toast.makeText(this, String.valueOf(hora1.isChecked()), Toast.LENGTH_SHORT).show();
        });


        this.fechaPicker.setOnTouchListener(this::onTouch);
    }

    private void loadElements() {
        this.btnAlta = findViewById(R.id.btnAltaFaltas);
        this.hora1 = findViewById(R.id.switch1hora);
        this.hora2 = findViewById(R.id.switch2hora);
        this.hora3 = findViewById(R.id.switch3hora);
        this.hora4 = findViewById(R.id.switch4hora);
        this.hora5 = findViewById(R.id.switch5hora);
        this.hora6 = findViewById(R.id.switch6hora);

        this.fechaPicker = findViewById(R.id.tvFechaPicker);
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
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        fechaPicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
        return false;
    }
}