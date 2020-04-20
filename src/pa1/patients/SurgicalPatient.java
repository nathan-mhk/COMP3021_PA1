package pa1.patients;

public class SurgicalPatient extends Patient {
    /**
     * constructor
     */
    public SurgicalPatient() {
        // TODO
        //  1. Calls the superclass' constructor
        //  2. set the category of the patient to "Surgical"
        //  3. set the deathRate to 0.2
        super();
        this.category = "Surgical";
        this.deathRate = 0.2;
    }
}
