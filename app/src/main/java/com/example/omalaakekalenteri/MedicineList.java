package com.example.omalaakekalenteri;

import java.util.ArrayList;
import java.util.List;

/** Class that contains singleton list of foods **/

public class MedicineList {

    private static final MedicineList ourInstance = new MedicineList();
    private List<Medicine> medicines;

    /** Creates new medicinelist singleton **/

    private MedicineList(){
        medicines = new ArrayList<>();
    }

    /** Add list of medicines to singleton **/
    public void addMedicine(List<Medicine> medicines){
        this.medicines = medicines;
    }

    /** Get the medicine list **/

    public List<Medicine> getMedicines(){
        return medicines;
    }

    /** Get medicine list instance **/

    public static MedicineList getInstance(){
        return ourInstance;
    }


}
