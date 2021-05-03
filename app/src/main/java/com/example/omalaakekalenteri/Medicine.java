package com.example.omalaakekalenteri;

import java.util.Calendar;
import java.util.Date;

/**
 * Medicine class for creating Medicine objects
 * @author Mikael Alakari
 *
 */
public class Medicine {
    private String name;
    private int dosageMg;
    private String activeIngredient;
    private int timesADay;
    private int quantity;
    private int howManyDays;
    private int piecesAtOnce;
    private final Date date;

    /**
     * Constructor for Medicine
     * @param name name of the Medicine
     * @param activeIngredient active ingredient in the medicine
     * @param timesADay how many times a day is the medicine consumed
     * @param quantity how many units
     * @param dosageMg dosage in milligrams
     * @param piecesAtOnce how many units at once
     *
     */
    public Medicine(String name,  String activeIngredient, int timesADay, int quantity, int dosageMg, int piecesAtOnce) {
        this.name = name;
        this.dosageMg = dosageMg;
        this.activeIngredient = activeIngredient;
        this.timesADay = timesADay;
        this.quantity = quantity;
        this.piecesAtOnce = piecesAtOnce;
        howManyDays = this.quantity / this.timesADay;
        date = Calendar.getInstance().getTime();

    }

    /**
     *
     * @return name of the medicine
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return dosage of the medicine
     */
    public int getDosageMg() {
        return dosageMg;
    }

    /**
     *
     * @return active ingredient of the medicine
     */
    public String getActiveIngredient() {
        return activeIngredient;
    }

    /**
     *
     * @return how many times a day the medicine is taken
     */
    public int getTimesADay() {
        return timesADay;
    }

    /**
     *
     * @return how many tablets are there
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @return how many days are left of the recipe
     */
    public int getHowManyDays(){
        return howManyDays;
    }

    /**
     *
     * @return how many pieces at once are taken
     */
    public int getPiecesAtOnce(){return piecesAtOnce;}

    /**
     *
     * @return the date when the medicine was created
     */
    public Date getDate(){
        return date;
    }

    /**
     * set the quantity of the medicine when the medicine is taken
     * @param quantity new unit amount to the medicine
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
        if (this.quantity < 0){
            this.quantity = 0;
        }
    }

    @Override
    public String toString() {
        return name.toUpperCase() + " " + dosageMg + "mg";
    }
}
