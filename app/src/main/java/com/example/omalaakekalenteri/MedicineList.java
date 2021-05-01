package com.example.omalaakekalenteri;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kata Sara-aho, Mikael Alakari
 * Class that contains singleton list of medicine
  */

public class MedicineList {

    private static MedicineList ourInstance = new MedicineList();
    private List<Medicine> medicines;

    /** Creates new medicinelist singleton **/

    private MedicineList(){
        medicines = new ArrayList<>();
        //test objects
        /*
        medicines.add(new Medicine("Burana", "ibuprofeiini", 3, 21, 400, 3));

        medicines.add(new Medicine("Panadol", "parasetamoli", 2, 16, 1000, 4));
        medicines.add(new Medicine("TestMed", "test", 1, 2, 10, 1));


         */
    }

    /** Add list of medicines to singleton **/
    public void addMedicine(Medicine medicine){
        medicines.add(medicine);
    }

    /** Get the medicine list **/

    public List<Medicine> getMedicines(){
        return medicines;
    }

    /** Get medicine list instance **/

    public static MedicineList getInstance(){
        return ourInstance;
    }

    /**
     *
     * @param medicine
     * @return medicine from ArrayList
     */
    public Medicine getMedicine(int medicine){
        return medicines.get(medicine);
    }

    /**
     * sets the ArrayList with a list of Medicines
     * @param medicines
     */
    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
