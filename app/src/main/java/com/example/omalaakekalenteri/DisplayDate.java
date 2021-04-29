package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

/**
 * @author Mikael Alakari
 * From CalendarView Displays Chosen Day's Medicine info in listView
 */
public class DisplayDate extends AppCompatActivity {
    private int year, month, day, todayDay, todayMonth, todayYear;
    private TextView textViewDate;
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

        //Get intent from calendar30
        Bundle bundle = getIntent().getExtras();
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        String chosenDateString = day + "-"+ month + "-" + year;

        SimpleDateFormat chosenDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (chosenDateString.equals("0-0-0")){
            chosenDateString = todayDay + "-" + todayMonth + "-" + todayYear;

        }
        try {
            chosenDate = chosenDateFormat.parse(chosenDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (day != 0) {
            textViewDate.setText(day + "." + month + "." + year);
        } else {
            textViewDate.setText(todayDay + "." + todayMonth + "." + todayYear);
        }

        medicines = new ArrayList<>();
        //going through the list and checking the dates
        if (MedicineList.getInstance().getMedicines() != null) {
            for (int i = 0; i < MedicineList.getInstance().getMedicines().size(); i++){
                Date medDate = MedicineList.getInstance().getMedicines().get(i).getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String chosenDateInString = sdf.format(chosenDate);
                String beforeInString = sdf.format(medDate);
                Log.d(TAG, chosenDateInString);
                Log.d(TAG, beforeInString);
                sdf.format(medDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(medDate);
                cal.add(Calendar.DATE, MedicineList.getInstance().getMedicines().get(i).getHowManyDays() -1);
                Date resultDate = new Date(cal.getTimeInMillis());


                //adding medicines to listview list
                if (chosenDate.compareTo(resultDate) < 0 && medDate.compareTo(chosenDate) < 0){
                    medicines.add(MedicineList.getInstance().getMedicines().get(i));
                }
                if (chosenDateInString.equals(beforeInString)){
                    medicines.add(MedicineList.getInstance().getMedicines().get(i));
                }
            }
        }

        //listview for selected date's medicines
        ListView listViewDaysMedicines = findViewById(R.id.listViewDaysMedicines);
        listViewDaysMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, android.R.layout.simple_list_item_1, medicines
        ));

        listViewDaysMedicines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d(TAG, "onItemClick(" + i + ")");
                Medicine medicine = MedicineList.getInstance().getMedicine(i);

                String name = medicine.getName();
                String dosage = Integer.toString(medicine.getDosageMg());
                String timesADay = Integer.toString(medicine.getTimesADay());

                Bundle medicineInfo = new Bundle();
                medicineInfo.putString("name", name);
                medicineInfo.putString("dosage", dosage);
                medicineInfo.putString("timesADay", timesADay);

                Intent intent = new Intent(DisplayDate.this, DisplayDateMedicineInfo.class);
                intent.putExtras(medicineInfo);
                startActivity(intent);
            }
        });

    }
}