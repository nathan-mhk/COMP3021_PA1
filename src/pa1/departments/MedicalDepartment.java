package pa1.departments;

import pa1.patients.MedicalPatient;
import pa1.patients.Patient;

public class MedicalDepartment extends Department {
    /**
     * constructor
     */
    public MedicalDepartment() {
        // TODO
        //  1. Calls the superclass' constructor
        //  2. set the name of the department to "Medical"
        //  3. set the fee to 200
        super();
        name = "Medical";
        fee = 200;
    }

    /**
     * request to admit/accept "count" number of patients, letting each one of them having a bed
     *
     * @param count number of patients are requesting to get admitted/accepted
     *
     * @return the number of actually accepted patients
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
        //    MedicalPatient objects you put into the patientList (ArrayList)
        int emptyBedCount = Math.max(0, (bedCapacity - patientList.size()));
        int maxPatient = Math.min(emptyBedCount, count);

        for (int i = 0; i < maxPatient; ++i) {
            patientList.add(new MedicalPatient());
        }
        return maxPatient;
    }
}
