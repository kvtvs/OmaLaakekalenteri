package com.example.omalaakekalenteri;

import java.util.Calendar;
import java.util.Date;

public class Medicine {
    private String name;
    private int dosageMg;
    private String activeIngredient;
    private int timesADay;
    private int quantity;
    private int howManyDays;
    private int piecesAtOnce;
    private final Date date;

    public Medicine(String name,  String activeIngredient, int timesADay, int quantity, int dosageMg, int piecesAtOnce) {
        this.name = name;
        this.dosageMg = dosageMg;
        this.activeIngredient = activeIngredient;
        this.timesADay = timesADay;
        this.quantity = quantity;
        this.piecesAtOnce = piecesAtOnce;
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

    public int getPiecesAtOnce(){return piecesAtOnce;}

    public Date getDate(){
        return date;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        if (this.quantity < 0){
            this.quantity = 0;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
