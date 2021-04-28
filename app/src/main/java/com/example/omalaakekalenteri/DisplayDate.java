package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DisplayDate extends AppCompatActivity {
    private int year, month, day, todayDay, todayMonth, todayYear;
    private TextView textViewDate, textViewName;
    private Date chosenDate;
    private final String TAG = "MED_";
    private ArrayList<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_date);
        textViewDate = findViewById(R.id.textViewDate);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String[] split = formattedDate.split("-");
        todayDay = Integer.valueOf(split[0]);
        todayMonth = Integer.valueOf(split[1]);
        todayYear = Integer.valueOf(split[2]);

        Log.d("MED_", formattedDate);
        Bundle bundle = getIntent().getExtras();
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        String chosenDateString = day + "-"+ month + "-" + year;

        SimpleDateFormat chosenDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            chosenDate = chosenDateFormat.parse(chosenDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        medicines = new ArrayList<>();

        for (int i = 0; i < MedicineList.getInstance().getMedicines().size(); i++){
            Date medDate = MedicineList.getInstance().getMedicines().get(i).getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String beforeInString = sdf.format(medDate);
            Log.d(TAG, "TestBefore: " + beforeInString);
            sdf.format(medDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(medDate);
            cal.add(Calendar.DATE, MedicineList.getInstance().getMedicines().get(i).getHowManyDays() -1);
            Date resultDate = new Date(cal.getTimeInMillis());
            String dateInString = sdf.format(resultDate);
            Log.d(TAG,"TestAfter: " + dateInString);
            if (chosenDate.compareTo(resultDate) > 0){
                Log.d(TAG, "After "  + MedicineList.getInstance().getMedicines().get(i).getName() + " ends");

            } else if (chosenDate.compareTo(resultDate) < 0 && chosenDate.compareTo(medDate) > 0){
                Log.d(TAG, "Before "  + MedicineList.getInstance().getMedicines().get(i).getName() + " ends");
                medicines.add(MedicineList.getInstance().getMedicines().get(i));
            } else {
                Log.d(TAG, "Same "  + MedicineList.getInstance().getMedicines().get(i).getName());
            }
        }
        ListView listViewMedicines = findViewById(R.id.listViewDaysMedicines);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, android.R.layout.simple_list_item_1, medicines
        ));

        if (day != 0) {
            textViewDate.setText(day + "." + month + "." + year);
        } else {
            textViewDate.setText(todayDay + "." + todayMonth + "." + todayYear);
        }



    }
}