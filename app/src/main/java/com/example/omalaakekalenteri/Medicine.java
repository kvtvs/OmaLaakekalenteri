package com.example.omalaakekalenteri;

public class Medicine {
    private String name;
    private int dosageMg;
    private String activeIngredient;
    private int timesADay;
    private int quantity;

    public Medicine(String name,  String activeIngredient, int timesADay, int quantity, int dosageMg) {
        this.name = name;
        this.dosageMg = dosageMg;
        this.activeIngredient = activeIngredient;
        this.timesADay = timesADay;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return name;
    }
}
