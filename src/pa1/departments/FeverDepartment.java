package pa1.departments;

import pa1.patients.FeverPatient;
import pa1.patients.Patient;

public class FeverDepartment extends Department {
    /**
     * constructor
     */
    public FeverDepartment() {
        // TODO
        //  1. Calls the superclass' constructor
        //  2. set the name of the department to "Fever"
        //  3. set the "fee" variable to 100, this fee will be used in many fee calculations,
        //     including the calculation of penalty the department needs to pay for each dead patient
        super();
        name = "Fever";
        fee = 100;

    }

    /**
     * At the end of turn, if there are patients dead, there will be empty beds.
     * Fill the empty space by the waitingList, update the length of waiting list
     * accordingly.
     *
     * For fever department, you have to override this method, because
     * fever disease is infectious. so the waitingPatientCount will double at the
     * beginning of each season.
     *
     * @return the number of dead patients found during the update
     */
    @Override
    public int updateWaitingListAtEndOfTurn() {
        // TODO
        //  1. double the waitingPatientCount (fever disease is infectious. so the waitingPatientCount will double at the
        //     beginning of each season.)
        //  2. calls the updateWaitingListAtEndOfTurn() method in the super class to update the waiting list
        waitingPatientCount *= 2;
        return super.updateWaitingListAtEndOfTurn();
    }

    /**
     * For FeverDepartment, the number of naturally generated patients per season
     * is lower than other departments, namely 0.02 times potential customers.
     *
     * @return the number of patients generated
     */
    @Override
    public int generatePatients(int potentialCustomers) {
        // TODO
        //  1. return value should be 0.02 times potential customers
        //  2. remember to convert this number to int before returning
        return ((int) (potentialCustomers * 0.02));
    }

    /**
     * request to admit/accept "count" number of patients, letting each one of them having a bed
     * there are "patientCount" number of waiting patients admitted into the department
     *
     *
     * @param count number of patients are requesting to get admitted/accepted
     *
     * @return the number of actually accepted patients
     *
     *
     */
    @Override
    public int acceptPatients(int count) { //request to admit/accept count number of patients
        int bedCount = Math.max(0, bedCapacity - patientList.size()); // newly empty bed count because of died patients and also the cured patient
        int patientCount = Math.min(bedCount, count); // the admitted/accepted patient by the department cannot be more than the number of empty beds
        for (int i=0; i<patientCount; i++) { //patientCount is the number of patients in the waiting list that could be admitted/accepted by the department in this season
                                             // (note: there are waitingPatientCount number of ppl in the waiting list)
            Patient patient = new FeverPatient(); //create the FeverPatient accordingly, once the patient object is created, he/she is accepted/admitted to the department
            patientList.add(patient);             // if patient object is not yet created, he is just a number, together with other unaccepted/un-admitted patients, they constitute the waitingPatientCount int
                                                  // i.e. if waitingPatientCount  of the department is 10, that means these 10 patients are numbers, they are not yet patient objects.
                                                  // They will become patient objects only if they are added the the patientList, patientList is the list of patients admitted/accepted by the department
        }

        return patientCount; // return the admitted/accepted patient number among all the waiting patients
    }
}
