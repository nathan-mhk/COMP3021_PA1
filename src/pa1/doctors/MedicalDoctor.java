package pa1.doctors;

import pa1.Player;
import pa1.patients.Patient;

import java.util.Random;

public class MedicalDoctor extends Doctor {
    /**
     * constructor
     *
     * @param name
     */
    public MedicalDoctor (String name) {
        // TODO: 1. initialize the name attribute of this MedicalDoctor object using super class constructor
        //       2. make the specialty of the doctor to be "Medical"
        //       3. set the salary of the Medical doctor to 200
        super(name);
        this.specialty = "Medical";
        this.salary = 200;
    }

    /**
     * recruitDoctor
     *
     * @param name
     */
    @Override
    public void recruitDoctor(Player player, String name) {
        // TODO
        //  1. MedicalDoctor can only recruit medical doctors.
        //  2. After creating Doctor instance, call player's addNewlyRecruitedDoctor()
        //  to add the new doctor to the doctor list.
        //  3. Finally, transfer the newly recruited doctor to the same department as
        //  the recruiter (if the recruiter has been affiliated an any of the department).
        //  The basic assumption is a doctor recruit new doctor for his/her own department.

    }

    /**
     * seePatient
     *
     * @param patient
     *
     * @return true if the patient is cured, false otherwise.
     */
    @Override
    public boolean seePatient(Patient patient) {
        // TODO 1. check the category of the patient using patient.getCategory() see if the returned string is the same as the specialty of the doctor
        //          if the category of the patient matches with the specialty of the doctor, return true to indicate the patient is cured
        //      2. if the category of the patient does not match with the speciality of the doctor, create a random object, generate a number between 0 and 99,
        //          the patient is cured only when the random number is bigger than 50 (0.5 probability). Refer to slides 39-40 of the note set below for the
        //          details of using the random object:
        //          https://course.cse.ust.hk/comp3021/notes/2-classes-objects-full.pdf

    }
}
