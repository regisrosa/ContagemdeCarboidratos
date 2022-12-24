package com.example.autocompleteteste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultadoFinalActivity extends AppCompatActivity {

    Database database;
    TextView tvResultadoFinal;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);

        getSupportActionBar().hide();

        tvResultadoFinal = findViewById(R.id.tv_resultadoFinal);

        listView = findViewById(R.id.listView);


        Intent intent = getIntent();
        String resultado_final = intent.getStringExtra("RESULTADO_FINAL");
        Double resultadoFinal = Double.valueOf(resultado_final);

        String peso1 = intent.getStringExtra("PESO_1");
        String nomeAlimento1 = intent.getStringExtra("NOME_ALIMENTO_1");
        String carboPorGrama_1 = intent.getStringExtra("CARBO_GRAMA_1");

        String peso2 = intent.getStringExtra("PESO_2");
        String nomeAlimento2 = intent.getStringExtra("NOME_ALIMENTO_2");
        String carboPorGrama_2 = intent.getStringExtra("CARBO_GRAMA_2");

        String peso3 = intent.getStringExtra("PESO_3");
        String nomeAlimento3 = intent.getStringExtra("NOME_ALIMENTO_3");
        String carboPorGrama_3 = intent.getStringExtra("CARBO_GRAMA_3");

        String peso4 = intent.getStringExtra("PESO_4");
        String nomeAlimento4 = intent.getStringExtra("NOME_ALIMENTO_4");
        String carboPorGrama_4 = intent.getStringExtra("CARBO_GRAMA_4");

        String peso5 = intent.getStringExtra("PESO_5");
        String nomeAlimento5 = intent.getStringExtra("NOME_ALIMENTO_5");
        String carboPorGrama_5 = intent.getStringExtra("CARBO_GRAMA_5");

        String peso6 = intent.getStringExtra("PESO_6");
        String nomeAlimento6 = intent.getStringExtra("NOME_ALIMENTO_6");
        String carboPorGrama_6 = intent.getStringExtra("CARBO_GRAMA_6");

        //-------------setar os campos da activity-------------------

        tvResultadoFinal.setText(resultadoFinal.intValue() + " g"); //passei de double para int para tirar a vírgula

        List<String> dados = new ArrayList<>();

        if(!nomeAlimento1.isEmpty() && !carboPorGrama_1.isEmpty()){
            int res_1 = (int) (Double.valueOf(carboPorGrama_1) * Integer.valueOf(peso1));
            dados.add(nomeAlimento1 + "\nCarboidrato: " + res_1 + "g");
        }
        if(!nomeAlimento2.isEmpty() && !carboPorGrama_2.isEmpty()){
            int res_2 = (int) (Double.valueOf(carboPorGrama_2) * Integer.valueOf(peso2));
            dados.add(nomeAlimento2 + "\nCarboidrato: " + res_2 + "g");
        }
        if(!nomeAlimento3.isEmpty() && !carboPorGrama_3.isEmpty()){
            int res_3 = (int) (Double.valueOf(carboPorGrama_3) * Integer.valueOf(peso3));
            dados.add(nomeAlimento3 + "\nCarboidrato: " + res_3 + "g");
        }
        if(!nomeAlimento4.isEmpty() && !carboPorGrama_4.isEmpty()){
            int res_4 = (int) (Double.valueOf(carboPorGrama_4) * Integer.valueOf(peso4));
            dados.add(nomeAlimento4 + "\nCarboidrato: " + res_4 + "g");
        }
        if(!nomeAlimento5.isEmpty() && !carboPorGrama_5.isEmpty()){
            int res_5 = (int) (Double.valueOf(carboPorGrama_5) * Integer.valueOf(peso5));
            dados.add(nomeAlimento5 + "\nCarboidrato: " + res_5 + "g");
        }
        if(!nomeAlimento6.isEmpty() && !carboPorGrama_6.isEmpty()){
            int res_6 = (int) (Double.valueOf(carboPorGrama_6) * Integer.valueOf(peso6));
            dados.add(nomeAlimento6 + "\nCarboidrato: " + res_6 + "g");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listView.setAdapter(adapter);


    }



    public void voltarInicio(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}