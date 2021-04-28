package com.example.omalaakekalenteri;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMedicine extends AppCompatActivity  {

    private Button tallenna;
    private Button peruuta;
    private EditText medicineName;
    private EditText activeIngredient;
    private EditText timesADay;
    private EditText quantity;
    private EditText dosage;
    private EditText pieces;

    private final String TAG = "MED_";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        tallenna = (Button) findViewById((R.id.buttonSave));
        peruuta = (Button) findViewById(R.id.buttonCancel);
        medicineName = (EditText) findViewById((R.id.editTextMedicineName));
        activeIngredient = (EditText) findViewById(R.id.editTextActiveIngredient);
        timesADay = (EditText) findViewById(R.id.editTextTimesADay);
        quantity = (EditText) findViewById(R.id.editTextQuantity);
        dosage = (EditText) findViewById(R.id.editTextDosage);
        pieces = (EditText) findViewById(R.id.editTextPieces);


    /** Setting save-button to false-state (non-clickable) and adding text listener to text fields that check that all field will have content **/
        tallenna.setEnabled(false);

        medicineName.addTextChangedListener(textWatcher);
        activeIngredient.addTextChangedListener(textWatcher);
        timesADay.addTextChangedListener(textWatcher);
        quantity.addTextChangedListener(textWatcher);
        dosage.addTextChangedListener(textWatcher);
        pieces.addTextChangedListener(textWatcher);

        /** When all the fields have user input, the user can press save-button **/
        tallenna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                /** Turning all the inputs to strings **/
                String laakeNimi = medicineName.getText().toString();
                Log.d(TAG, laakeNimi);
                String vaikuttavaAine = activeIngredient.getText().toString();
                int kertaaPaivassa = Integer.parseInt(timesADay.getText().toString());
                int maara = Integer.parseInt(quantity.getText().toString());
                int annostus = Integer.parseInt(dosage.getText().toString());
                int kappaleMaara = Integer.parseInt(pieces.getText().toString());


                Intent intent = new Intent(AddMedicine.this, DisplayMedicineList.class);
                /** All info will be put to intent **/
                intent.putExtra("laakeNimi", laakeNimi);
                intent.putExtra("vaikuttavaAine", vaikuttavaAine);
                intent.putExtra("kertaaPaivassa", kertaaPaivassa);
                intent.putExtra("maara", maara);
                intent.putExtra("annostus", annostus);
                intent.putExtra("kappaleMaara", kappaleMaara);
                /** When button is pressed, it will send result-info to onActivityResult in Mainactivity and start new Activity **/
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });

        /** If Cancel-button is pressed, it will just go back to medicine list and it sends result-info to onActivityResult in MainActivity **/
        peruuta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent intent = new Intent(AddMedicine.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });


    }


    /** Creating TextWatcher that checks that all fields have content **/
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            String laakeNimi = medicineName.getText().toString();
            String vaikuttavaAine = activeIngredient.getText().toString();
            String kertaaPaivassaString = timesADay.getText().toString();
            String maaraString = quantity.getText().toString();
            String annostusString = dosage.getText().toString();
            String kappaleString = pieces.getText().toString();

            if(!laakeNimi.isEmpty() && !vaikuttavaAine.isEmpty() && !kertaaPaivassaString.isEmpty() && !maaraString.isEmpty() && !annostusString.isEmpty() && !kappaleString.isEmpty()){
                /** Check that all fields are not empty **/
                /** If all fields have content, set save button state to true (clickable) **/
                tallenna.setEnabled(true);
            } else {
                /** If all fields are not filled, the button stays in false state **/
                tallenna.setEnabled(false);
            }
        }
        @Override
        public void afterTextChanged(Editable s){

        }
    };



}

  