package pa1.departments;

import pa1.patients.Patient;
import pa1.doctors.Doctor;

import java.util.ArrayList;
import java.util.List;

public abstract class Department {
    protected List<Doctor> doctorList = new ArrayList<>();
    protected List<Patient> patientList = new ArrayList<>();
    protected int waitingPatientCount = 0; // number of patients waiting to be accepted/admitted by this department in the current season
    protected int bedCapacity;            // bed capacity of the department
    protected int upgradeCost;            // cost for player upgrade action on the department
    protected int curedPatientCount = 0; //  cumulative cured patient count up to the current season for the department
    protected int deadPatientCount = 0;  // cumulative dead patient count up to the current season for the department
    protected int fee;
    protected String name;


    Department() {
        this.bedCapacity = 10;
        this.upgradeCost = 1000;
    }

    public String getName() {
        return name;
    }

    public int getFee() {
        return fee;
    }

    public int getCuredPatientCount() {
        return curedPatientCount;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public int getWaitingPatientCount() {
        return waitingPatientCount;
    }

    public void addWaitingPatientCount(int queueLength){waitingPatientCount=waitingPatientCount+queueLength;}//AL added

    /**
     * Upgrade the department by doubling the bed capacity
     */
    public void upgrade() {
        // TODO
        //  double the bedCapacity variable
        //
        this.bedCapacity *= 2;
    }

    /**
     * Let doctors in the department start treatment (see patients).
     * This function will be called automatically at the end of each
     * player's turn.
     *
     * @return the number of newly cured patients during the treatment
     *
     */
    public int startTreatment() {


        int oldCuredPatientCount = curedPatientCount; //provided

        // TODO
        //  This method will *get the doctor objects one-by-one* from the *doctorList* (ArrayList)
        //  and then assign patients from the patientList (ArrayList) one-by-one to the current doctor object,
        //  until the current doctor object has served 10 patients.
        //  1. we Can use the size() method of the doctorList (ArrayList) to check the total
        //     number of doctors in the doctorList (ArrayList)
        //  2. to get the i-th doctors from the doctorList, we can use the
        //     get(i) method of the doctorList object (where i goes from 0 to doctorList.size()-1)
        //  3. the doctor object will then treat the first patient object in the patientList (ArrayList)
        //     to get the first patient object from the patientList ArrayList, one can use patientList.get(0)
        //     to treat a patient we use the seePatient() method of the doctor object and pass the method
        //     the patient object (i.e. patientList.get(0)).
        //  4. even if doctor.isOccupied() is true (meaning that he/she can not carry out the administrative
        //     tasks assigned by for the player), the doctor will still treat patients.
        //  5. Each doctor can at treat a maximum of 10 patients from the patientList (through seePatient() method).
        //     When a doctor has treated 10 patients (through seePatient() method), we will pick another doctor
        //     from the doctorList to treat the remaining patients in the patientList. After we have picked all the
        //     doctors from the doctorList to run seePatient(), either the patientList is cleared
        //     (patientList.size()==0), or patientList still contains untreated patients (patientList.size()>0)
        //  6. When a doctor has cured a patient, seePatient() will return true. Otherwise seePatient() will return
        //     false. This returned value is random, for the details please refer to the seePatient() method of the
        //     FeverDoctor, MEdicalDoctor, MinisterDoctor and SurgicalDoctor classes
        //  7. if seePatient() returns true for a patient, we call the checkoutCuredPatient() method and pass it
        //     the cured patient object. This checkoutCuredPatient() would remove the patient object
        //     from the patientList and increment the curedPatientCount variable by 1.
        //  8. The number of newly cured patients will be returned by this method, and it is the difference
        //      between the oldCuredPatientCount below and the latest curedPatientCount at the end of this method after
        //      treating the patients in the patientList by all the doctors in the doctorList. This latest
        //      curedPatientCount will be accumulated by the checkoutCuredPatient() method calls. You do not need to
        //      touch this curedPatientCount value, as it will be updated by the checkoutCuredPatient() method.

        int ptCount = 0;
        int drNum = 0;
        while ((this.patientList.size() != 0) && (drNum < this.doctorList.size())) {
            Patient pt = this.patientList.get(0);
            Doctor dr = this.doctorList.get(drNum);

            if (dr.seePatient(pt)) {
                // The method will remove the patient from the patientList
                // and increment curedPatientCount
                this.checkoutCuredPatient(pt);
            }
            if ((++ptCount) == 10) {
                ptCount = 0;
                ++drNum;
            }
        }
        return curedPatientCount - oldCuredPatientCount; //provided
    }

    /**
     * Assign a doctor to the department, this function will be called
     * when the player transfer a doctor to a department.
     */
    public void doctorAssigned(Doctor doctor) {
        // TODO
        //  1. we add the doctor object passed to this method to the doctorList
        //     if and only if the doctor is not already in the  doctorList.
        //  2. doctorList is an arrayList, to check whether a doctor object
        //     is already in the doctorList you can use doctorList.contains(), and pass it the doctor object
        //  4. we can use the add() method of the doctorList ArrayList to add the doctor object to the doctorList
        if (!this.doctorList.contains(doctor)) {
            this.doctorList.add(doctor);
        }
    }

    /**
     * Make a doctor leave the department, this function will be called
     * when the player transfer a doctor to a department
     */
    public void doctorLeft(Doctor doctor) {
        // TODO
        //  1. when the player transfers a doctor from one department to another department,
        //     we will run this method
        //  2. we can remove the doctor from doctorList only if the list
        //     contains the doctor. You can use doctorList.contains(), and pass it the doctor object to check
        //  3. if the doctor object is in the doctorList we can use the remove() method, refer to PA description for detailed usage
        this.doctorList.remove(doctor);
    }

    /**
     * Checkout the cured patient
     */
    public void checkoutCuredPatient(Patient patient) {
        // remove the patient from the patientList
        patientList.remove(patient);
        //increase the curedPatientCount for the department
        curedPatientCount++;
    }

    /**
     * Checkout the dead patient
     */
    public void checkoutDeadPatient(Patient patient) {
        // remove the patient from the patientList
        patientList.remove(patient);
        //  increase the deadPatientCount for the department
        deadPatientCount++;
    }

    /**
     * At the end of turn, if there are patients dead, they will be removed from the patientList (ArrayList), there
     * could be beds become empty because of this. The number of empty beds is max(0, bedCapacity - patientList.size())
     * If this empty bed number is positive, Fill the empty space by the waitingList, update the length of waiting list
     * accordingly (i.e. "update waitingPatientCount").
     * updateWaitingListAtEndOfTurn()
     *
     * @return the number of dead patients at the end of this current season
     */
    public int updateWaitingListAtEndOfTurn () {


        int oldDeathCount = deadPatientCount; //provided
        List<Patient> deadPatients = new ArrayList<>(); //provided

        // TODO
        //  1. For each of the patients, check if the he/she is dead at the end of this turn. We can check the
        //    patients by applying the For-each loop on the patientList (ArrayList)
        //     (i.e. for (Patient p: patientList){} )
        //  2. If a patient is dead, (i.e. patient.isDeadAtEndofTurn()==true), we add this patient object to the
        //     deadPatients (ArrayList) using deadPatients.add(patient).
        //  3. if patient.isDeadAtEndofTurn() returns false, call the stayForAnotherSeason() of the patient object
        //     to increment the "timeSpan" of this patient object, indicating he/she has stayed for another season
        //     in the hospital.
        //  4. for each patient object in the deadPatients ArrayList, call the checkoutDeadPatient() method to remove
        //     this dead patient object from the patientList (ArrayList). This checkoutDeadPatient method will increment
        //     the "deadPatientCount" each time a dead patient is removed from the patientList. This "deadPatientCount"
        //     is the accumulated dead patient number up to the current season.
        //  5. Otherwise if the patient.isDeadAtEndofTurn()==false, we call the stayForAnotherSeason() to increment
        //     the number of seasons this patient has stayed in this hospital department.
        //  6. Now we check to see the number of waiting patients that could be admitted into the department using
        //    acceptPatients(waitingPatientCount), the returned value from this method gives the number of newly
        //    admitted patient by the department in this season, we need to deduct "waitingPatientCount" by that newly
        //    admitted patient number to reflect that patients are admitted by the department.
        //  5. The dead patient count of this season is deadPatientCount - oldDeathCount, which should be the return
        //     value of the method

        for (Patient pt: this.patientList) {
            if (pt.isDeadAtEndOfTurn()) {
                deadPatients.add(pt);
            } else {
                pt.stayForAnotherSeason();
            }
        }

        for (Patient pt: deadPatients) {
            // The method will remove the patient from the patientList
            // and increment deadPatientCount
            this.checkoutDeadPatient(pt);
        }
        this.waitingPatientCount -= this.acceptPatients(this.waitingPatientCount);
        return deadPatientCount - oldDeathCount; // return the dead count of this season.
    }

    /**
     * clearWaitingList
     */
    public void clearWaitingList () {
        //  Clear the waiting list by setting the waitingPatientCount to 0
        this.waitingPatientCount = 0;
    }

    /**
     * Generate patients from the "potentialCustomers" parameter
     * for current department in the current season.
     * By default, the patients is generated as 0.04 times the potential
     * customers (remember to convert to integer).
     *
     * For FeverDepartment, the number of naturally generated patients
     * is lower than other departments. So you have to override this
     * method.
     *
     * @param potentialCustomers number of potential customers
     *
     * @return the number of patients generated
     */
    public int generatePatients(int potentialCustomers) {
        //  0.04 times potential customers, convert the value to int
        return (int) (potentialCustomers * 0.04);
    }

    /**
     * calculate the death compensation the department needs to pay upon each patient death.
     * For each death, the compensation is 5 times the "fee" variable of the department.
     *
     * @return the compensation
     */
    public int getDeathCompensation() {
        // TODO
        //  1. return the compensation paid by the department for a dead patient,
        //     which is 5 times of the "fee" variable of the department object.
        return (5 * this.fee);
    }

    @Override
    public String toString() {
        return String.format("Department: %s | doctors: %d | patients: %d | waiting list: %d | cured: %d | dead: %d | capacity: %d",
                name, doctorList.size(), patientList.size(), waitingPatientCount, curedPatientCount, deadPatientCount, bedCapacity);
    }

    /**
     * Given potential customers, accept new patients and add the patients
     * to the patientList
     *
     * 1. If the bed capacity allows, accept all the patients.
     * 2. Otherwise, the number of accepted patients should equal
     * to the available bed space.
     *
     * @param potentialCustomers number of potential customers
     *
     * @return the number of newly accepted patients
     */
    public abstract int acceptPatients(int potentialCustomers);
}
