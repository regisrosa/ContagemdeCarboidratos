package com.example.autocompleteteste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome, etCarboPorGrama;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();

        etNome = findViewById(R.id.et_nome);
        etCarboPorGrama = findViewById(R.id.et_carbo_por_grama);


    }

    public void salvar(View view){

        try {

            database = new Database(this);
            SQLiteDatabase db = database.getWritableDatabase();

            String nome = etNome.getText().toString();
            String carboPorGrama = etCarboPorGrama.getText().toString();

            ContentValues values = new ContentValues();
            values.put("nome", nome.toUpperCase());
            values.put("carboidrato_por_grama", carboPorGrama.toUpperCase());

            db.insert("alimentos", null, values);
            db.close();

            Toast.makeText(this, "Alimento inserido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}