package pa1.doctors;

import pa1.Player;
import pa1.patients.Patient;

import java.util.Random;

public class FeverDoctor extends Doctor {
    /**
     * Constructor
     *
     * @param name
     */
    public FeverDoctor(String name) {
        // TODO 1. create a new FeverDoctor object, pass the name to the constructor to initialize the name of this newly hired FeverDoctor
        //      2. Put the specialty of the doctor to be "Fever"
        //      3. put the salary of the doctor to be 100
        super(name);
        this.specialty = "Fever";
        this.salary = 100;
    }

    /**
     * recruitDoctor
     *
     * @param name
     */
    @Override
    public void recruitDoctor(Player player, String name) {
        // TODO
        //  1. FeverDoctor can only recruit fever doctors. So you need to create a brand new FeverDoctor object using
        //     the FeverDoctor() constructor, pass the constructor the name.
        //  2. After creating Doctor instance, call player's addNewlyRecruitedDoctor() method
        //     to add the new doctor to the doctorList (ArrayList).
        //  3. Finally, transfer the newly recruited doctor to the same department as
        //    the recruiting doctor (if the recruiter has been affiliated with an department).
        //    The basic assumption is a doctor recruit new doctor for his/her own department.
        //    You can check the affiliation of the recruiting doctor using this.affiliation
        //    to add the new doctor object created, you could call the transferToDepartment() method and
        //    pass it with the affiliation object (type Department) of the recruiting doctor
        Doctor dr = new FeverDoctor(name);
        player.addNewlyRecruitedDoctor(dr);
        dr.transferToDepartment(this.affiliation);
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
        // TODO 1. FeverDoctor can cure fever patients with 100% chance. Check the category of the patient using
        //          patient.getCategory() see if the returned string is the same as the specialty of the doctor
        //          if the category of the patient matches with the specialty of the doctor, return true to indicate the patient is cured
        //      2. if the category of the patient does not match with the speciality of the doctor, create a random object, generate a number between 0 and 99,
        //          the patient is cured only when the random number is bigger than 50 (0.5 probability). Refer to slides 39-40 of the note set below for the
        //          details of using the random object:
        //          https://course.cse.ust.hk/comp3021/notes/2-classes-objects-full.pdf
        if (this.specialty.equals(patient.getCategory())) {
            return true;
        } else {
            Random rnd = new Random();
            return (rnd.nextInt(100) > 50);
        }
    }
}
