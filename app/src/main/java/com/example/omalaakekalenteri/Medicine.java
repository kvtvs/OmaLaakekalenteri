package com.example.omalaakekalenteri;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Medicine {
    private String name;
    private int dosageMg;
    private String activeIngredient;
    private int timesADay;
    private int quantity;
    private int howManyDays;
    private final Date date;

    public Medicine(String name,  String activeIngredient, int timesADay, int quantity, int dosageMg) {
        this.name = name;
        this.dosageMg = dosageMg;
        this.activeIngredient = activeIngredient;
        this.timesADay = timesADay;
        this.quantity = quantity;
        howManyDays = quantity / timesADay;
        date = Calendar.getInstance().getTime();

    }

    public String getName() {
        return name;
    }

    public int getDosageMg() {
        return dosageMg;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public int getTimesADay() {
        return timesADay;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getHowManyDays(){
        return howManyDays;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public String toString() {
        return name;
    }
}
