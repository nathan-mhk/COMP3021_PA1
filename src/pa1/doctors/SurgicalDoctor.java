package pa1.doctors;

import pa1.Player;
import pa1.patients.Patient;

import java.util.Random;

public class SurgicalDoctor extends Doctor {
    /**
     * constructor
     *
     * @param name
     */
    public SurgicalDoctor(String name) {
        // TODO: 1. initialize the name attribute of this SurgicalDoctor object using super class constructor
        //       2. make the specialty of the doctor to be "Surgical"
        //       3. set the salary of the surgical doctor to 500
        super(name);
        this.specialty = "Surgical";
        this.salary = 500;
    }

    /**
     * getTrainingCost
     *
     * @return training cost
     */
    @Override
    public int getTrainingCost() {
        // TODO
        //  the training cost of Surgical doctor is 2 * super.getTrainingCost()
        return (2 * super.getTrainingCost());
    }

    /**
     * recruitDoctor
     *
     * @param name
     */
    @Override
    public void recruitDoctor(Player player, String name) {
        // TODO
        //  1. SurgicalDoctor can only recruit surgical doctors.
        //  2. After creating Doctor instance, call player's addNewlyRecruitedDoctor()
        //  to add the new doctor to the doctor list.
        //  3. Finally, transfer the newly recruited doctor to the same department as
        //  the recruiter (if the recruiter has been affiliated an any of the department).
        //  The basic assumption is a doctor recruit new doctor for his/her own department.
        Doctor dr = new SurgicalDoctor(name);
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
        // TODO
        //  1. SurgicalDoctor can cure surgical patients with 100% chance. When the patient
        //     is in other categories, the chance will drop to 80% (i.e., with 0.2 probability
        //     the doctor may fail to cure the patient).
        //  2. You may have to use java.util.random to model the probability. Refer to slides 39-40 of the note set
        //      below for the details of using the random object:
        //      https://course.cse.ust.hk/comp3021/notes/2-classes-objects-full.pdf
        if (this.specialty.equals(patient.getCategory())) {
            return true;
        } else {
            Random rnd = new Random();
            return (rnd.nextInt(100) > 20);
        }
    }
}
