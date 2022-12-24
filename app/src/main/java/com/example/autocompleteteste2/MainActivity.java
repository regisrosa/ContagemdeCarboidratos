package com.example.autocompleteteste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView acTextView1;
    EditText et_peso1;
    AutoCompleteTextView acTextView2;
    EditText et_peso2;
    AutoCompleteTextView acTextView3;
    EditText et_peso3;
    AutoCompleteTextView acTextView4;
    EditText et_peso4;
    AutoCompleteTextView acTextView5;
    EditText et_peso5;
    AutoCompleteTextView acTextView6;
    EditText et_peso6;

    String id_1;
    String id_2;
    String id_3;
    String id_4;
    String id_5;
    String id_6;

    String carboPorGrama1;
    String carboPorGrama2;
    String carboPorGrama3;
    String carboPorGrama4;
    String carboPorGrama5;
    String carboPorGrama6;

    List<String> alimentos;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        acTextView1 = findViewById(R.id.acTextView1);
        et_peso1 = findViewById(R.id.et_peso1);
        acTextView2 = findViewById(R.id.acTextView2);
        et_peso2 = findViewById(R.id.et_peso2);
        acTextView3 = findViewById(R.id.acTextView3);
        et_peso3 = findViewById(R.id.et_peso3);
        acTextView4 = findViewById(R.id.acTextView4);
        et_peso4 = findViewById(R.id.et_peso4);
        acTextView5 = findViewById(R.id.acTextView5);
        et_peso5 = findViewById(R.id.et_peso5);
        acTextView6 = findViewById(R.id.acTextView6);
        et_peso6 = findViewById(R.id.et_peso6);


        alimentos = new ArrayList<String>();

        try {
            database = new Database(this);
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM alimentos", null);
            cursor.moveToFirst();
            while (cursor != null) {
                String nome = cursor.getString(1);
                alimentos.add(nome);
                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        database.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alimentos);

        acTextView1.setThreshold(2);
        acTextView1.setAdapter(adapter);
        acTextView2.setThreshold(2);
        acTextView2.setAdapter(adapter);
        acTextView3.setThreshold(2);
        acTextView3.setAdapter(adapter);
        acTextView4.setThreshold(2);
        acTextView4.setAdapter(adapter);
        acTextView5.setThreshold(2);
        acTextView5.setAdapter(adapter);
        acTextView6.setThreshold(2);
        acTextView6.setAdapter(adapter);


    }


    public void telaCadastro(View view) {

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    public void calcular(View view) {

        String nomeAlimento1 = acTextView1.getText().toString();
        String peso1 = et_peso1.getText().toString();

        String nomeAlimento2 = acTextView2.getText().toString();
        String peso2 = et_peso2.getText().toString();

        String nomeAlimento3 = acTextView3.getText().toString();
        String peso3 = et_peso3.getText().toString();

        String nomeAlimento4 = acTextView4.getText().toString();
        String peso4 = et_peso4.getText().toString();

        String nomeAlimento5 = acTextView5.getText().toString();
        String peso5 = et_peso5.getText().toString();

        String nomeAlimento6 = acTextView6.getText().toString();
        String peso6 = et_peso6.getText().toString();

        double resultado1 = 0;
        double resultado2 = 0;
        double resultado3 = 0;
        double resultado4 = 0;
        double resultado5 = 0;
        double resultado6 = 0;

        //------------PROTEÇÃO PARA FAZER O SISTEMA ACEITAR AMBOS OS CAMPOS PREENCHIDOS OU AMBOS VAZIOS--------
        if ((!nomeAlimento1.isEmpty() && peso1.isEmpty()) || (nomeAlimento1.isEmpty() && !peso1.isEmpty())) {
            Toast.makeText(this, "Na linha 1, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else if ((!nomeAlimento2.isEmpty() && peso2.isEmpty()) || (nomeAlimento2.isEmpty() && !peso2.isEmpty())) {
            Toast.makeText(this, "Na linha 2, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else if ((!nomeAlimento3.isEmpty() && peso3.isEmpty()) || (nomeAlimento3.isEmpty() && !peso3.isEmpty())) {
            Toast.makeText(this, "Na linha 3, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else if ((!nomeAlimento4.isEmpty() && peso4.isEmpty()) || (nomeAlimento4.isEmpty() && !peso4.isEmpty())) {
            Toast.makeText(this, "Na linha 4, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else if ((!nomeAlimento5.isEmpty() && peso5.isEmpty()) || (nomeAlimento5.isEmpty() && !peso5.isEmpty())) {
            Toast.makeText(this, "Na linha 5, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else if ((!nomeAlimento6.isEmpty() && peso6.isEmpty()) || (nomeAlimento6.isEmpty() && !peso6.isEmpty())) {
            Toast.makeText(this, "Na linha 6, preencher ambos os campos ou deixá-los em branco!", Toast.LENGTH_SHORT).show();

        } else {

            if (nomeAlimento1.isEmpty() && peso1.isEmpty()) {
                peso1 = "0";
                carboPorGrama1 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento1 + "'", null); //não pode haver espaços entre as aspas simples senão a query dá erro
                    cursor.moveToFirst();
                    carboPorGrama1 = cursor.getString(0); //como a query pede apenas uma coluna, essa coluna é indice: 0
                    resultado1 = Double.valueOf(peso1) * Double.valueOf(carboPorGrama1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (nomeAlimento2.isEmpty() && peso2.isEmpty()) {
                peso2 = "0";
                carboPorGrama2 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento2 + "'", null);
                    cursor.moveToFirst();
                    carboPorGrama2 = cursor.getString(0);
                    resultado2 = Double.valueOf(peso2) * Double.valueOf(carboPorGrama2);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (nomeAlimento3.isEmpty() && peso3.isEmpty()) {
                peso3 = "0";
                carboPorGrama3 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento3 + "'", null);
                    cursor.moveToFirst();
                    carboPorGrama3 = cursor.getString(0);
                    resultado3 = Double.valueOf(peso3) * Double.valueOf(carboPorGrama3);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (nomeAlimento4.isEmpty() && peso4.isEmpty()) {
                peso4 = "0";
                carboPorGrama4 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento4 + "'", null);
                    cursor.moveToFirst();
                    carboPorGrama4 = cursor.getString(0);
                    resultado4 = Double.valueOf(peso4) * Double.valueOf(carboPorGrama4);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (nomeAlimento5.isEmpty() && peso5.isEmpty()) {
                peso5 = "0";
                carboPorGrama5 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento5 + "'", null);
                    cursor.moveToFirst();
                    carboPorGrama5 = cursor.getString(0);
                    resultado5 = Double.valueOf(peso5) * Double.valueOf(carboPorGrama5);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (nomeAlimento6.isEmpty() && peso6.isEmpty()) {
                peso6 = "0";
                carboPorGrama6 = "0";

            } else {

                try {
                    SQLiteDatabase db = database.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT carboidrato_por_grama FROM alimentos WHERE nome = "
                            + "'" + nomeAlimento6 + "'", null);
                    cursor.moveToFirst();
                    carboPorGrama6 = cursor.getString(0);
                    resultado6 = Double.valueOf(peso6) * Double.valueOf(carboPorGrama6);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            database.close();

            Double resultadoFinal = resultado1 + resultado2 + resultado3 + resultado4 + resultado5 + resultado6;

            Intent intent = new Intent(this, ResultadoFinalActivity.class);
            intent.putExtra("RESULTADO_FINAL", resultadoFinal.toString());

            intent.putExtra("PESO_1", peso1);
            intent.putExtra("NOME_ALIMENTO_1", nomeAlimento1);
            intent.putExtra("CARBO_GRAMA_1", carboPorGrama1);

            intent.putExtra("PESO_2", peso2);
            intent.putExtra("NOME_ALIMENTO_2", nomeAlimento2);
            intent.putExtra("CARBO_GRAMA_2", carboPorGrama2);

            intent.putExtra("PESO_3", peso3);
            intent.putExtra("NOME_ALIMENTO_3", nomeAlimento3);
            intent.putExtra("CARBO_GRAMA_3", carboPorGrama3);

            intent.putExtra("PESO_4", peso4);
            intent.putExtra("NOME_ALIMENTO_4", nomeAlimento4);
            intent.putExtra("CARBO_GRAMA_4", carboPorGrama4);

            intent.putExtra("PESO_5", peso5);
            intent.putExtra("NOME_ALIMENTO_5", nomeAlimento5);
            intent.putExtra("CARBO_GRAMA_5", carboPorGrama5);

            intent.putExtra("PESO_6", peso6);
            intent.putExtra("NOME_ALIMENTO_6", nomeAlimento6);
            intent.putExtra("CARBO_GRAMA_6", carboPorGrama6);

            startActivity(intent);

        }

    }


}