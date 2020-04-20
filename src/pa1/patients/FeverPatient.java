package pa1.patients;

public class FeverPatient extends Patient {
    /**
     * constructor
     */
    public FeverPatient() {
        // TODO
        //  1. Calls the superclass' constructor
        //  2. set the category of the patient to "Fever"
        //  3. set the deathRate to 0.04
        super();
        this.category = "Fever";
        this.deathRate = 0.04;
    }
}
