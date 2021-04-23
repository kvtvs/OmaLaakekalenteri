package com.example.omalaakekalenteri;

import java.util.ArrayList;
import java.util.List;

/** Class that contains singleton list of foods **/

public class MedicineList {

    private static MedicineList ourInstance = new MedicineList();
    private List<Medicine> medicines;

    /** Creates new medicinelist singleton **/

    private MedicineList(){
        medicines = new ArrayList<>();
        //test objects
        medicines.add(new Medicine("Burana", "ibuprofeiini", 3, 21, 400));
        medicines.add(new Medicine("Panadol", "parasetamoli", 2, 16, 1000));


    }

    /** Add list of medicines to singleton **/
    public void addMedicine(Medicine medicine){
        this.medicines.add(medicine);
    }

    /** Get the medicine list **/

    public List<Medicine> getMedicines(){
        return medicines;
    }

    /** Get medicine list instance **/

    public static MedicineList getInstance(){
        return ourInstance;
    }

    public Medicine getMedicine(int medicine){
        return medicines.get(medicine);
    }


}
