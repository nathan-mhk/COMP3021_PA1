package pa1.departments;

import pa1.patients.Patient;
import pa1.patients.SurgicalPatient;

public class SurgicalDepartment extends Department {
    /**
     * constructor
     */
    public SurgicalDepartment() {
        // TODO
        //  1. Calls the superclass' constructor
        //  2. set the name of the department to "Surgical"
        //  3. set the fee to 500, this fee will be used in many fee calculations,
        //     including the calculation of penalty the department needs to pay for each dead patient
        super();
        this.name = "Surgical";
        this.fee = 500;
    }

    /**
     * Given potential customers, accept new patients and add the patients
     * to the patientList.
     *
     * @param count number of potential customers
     *
     * @return the number of newly accepted patients
     */
    @Override
    public int acceptPatients (int count) {
        // TODO
        //  0. the argument "int count" indicates the number of patients requesting for a bed (this is the demand)
        //  1. calculate the new empty bed count because of died patients and also the cured patients, this could be done
        //    using Math.max(0,bedCapacity-patientList.size())
        //  2. calculate the max number of patients could be accepted/admitted. Assume every single newly empty bed can
        //    take a new patient. This is the actual number of patients we can take.
        //  3. according to the max number of patient we can take calculated in 2, create MeidicalPatient objects
        //     and add them to the patientList (ArrayList), using the add() method of the ArrayList
        //  4. return an int to indicate the number of patients admitted (this number is the same as the number of
        //    SurgicalPatient objects you put into the patientList (ArrayList)
        int emptyBedCount = Math.max(0, (this.bedCapacity - this.patientList.size()));
        int maxPatient = Math.min(emptyBedCount, count);

        for (int i = 0; i < maxPatient; ++i) {
            this.patientList.add(new SurgicalPatient());
        }
        return maxPatient;
    }
}
