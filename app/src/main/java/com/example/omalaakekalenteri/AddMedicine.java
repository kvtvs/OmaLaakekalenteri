package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddMedicine extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button tallenna = (Button) findViewById((R.id.buttonSave));
        EditText medicineName = (EditText) findViewById((R.id.editTextMedicineName));
        EditText activeIngredient = (EditText) findViewById(R.id.editTextActiveIngredient);
        EditText timesADay = (EditText) findViewById(R.id.editTextTimesADay);
        EditText quantity = (EditText) findViewById(R.id.editTextQuantity);
        EditText dosage = (EditText) findViewById(R.id.editTextDosage);

        tallenna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String laakeNimi = medicineName.getText().toString();
                String vaikuttavaAine = activeIngredient.getText().toString();
                int kertaaPaivassa = Integer.parseInt(timesADay.getText().toString());
                int maara = Integer.parseInt(quantity.getText().toString());
                int annostus = Integer.parseInt(dosage.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("laakeNimi", laakeNimi);
                intent.putExtra("vaikuttavaAine", vaikuttavaAine);
                intent.putExtra("kertaaPaivassa", kertaaPaivassa);
                intent.putExtra("maara", maara);
                intent.putExtra("annostus", annostus);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

}

  