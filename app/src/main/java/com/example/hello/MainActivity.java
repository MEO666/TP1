package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spiner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.convert, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);

        Spinner spiner2 = findViewById(R.id.spinner2);
        spiner2.setAdapter(adapter);
        spiner2.setOnItemSelectedListener(this);
        String selectedValue = spiner.getItemAtPosition(spiner.getSelectedItemPosition()).toString(); // valeur de l'élément sélectionné

        Button button = findViewById(R.id.button1);
        EditText edit = findViewById(R.id.plainText1);
        TextView text = findViewById(R.id.textView1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount = Double.parseDouble(edit.getText().toString()); // Récupérer le montant entré dans l'EditText
                double result = convertCurrency(spiner.getSelectedItem().toString(), spiner2.getSelectedItem().toString(), amount); // Convertir le montant
                text.setText(String.format("%.2f", result)); // Afficher le résultat dans le TextView
            }
        });

    }
    private double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        double result = 0;
        double rate = 0;

        // Define conversion rates based on the selected currencies
        if (fromCurrency.equals("DH") && toCurrency.equals("EURO")) {
            rate = 0.09;
        }
        else if (fromCurrency.equals("DH") && toCurrency.equals("DOLLAR")) {
            rate = 0.11;
        }
        else if (fromCurrency.equals("EURO") && toCurrency.equals("DH")) {
            rate = 11.12;
        }
        else if (fromCurrency.equals("EURO") && toCurrency.equals("DOLLAR")) {
            rate = 1.19;
        }
        else if (fromCurrency.equals("DOLLAR") && toCurrency.equals("DH")) {
            rate = 8.73;
        }
        else if (fromCurrency.equals("DOLLAR") && toCurrency.equals("EURO")) {
            rate = 0.11;
        }
        // Convert the amount by multiplying it with the conversion rate
        result = amount * rate;

        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}