package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;


public class AddMedicine extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button tallenna = (Button) findViewById((R.id.buttonSave));
        EditText medicineName = (EditText) findViewById((R.id.editTextMedicineName));

        tallenna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String laakeNimi = medicineName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("laakeNimi", laakeNimi);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

}

  