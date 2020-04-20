package pa1.doctors;

import pa1.Player;
import pa1.patients.Patient;

import java.util.Random;

public class MinisterDoctor extends Doctor {
    /**
     * Constructor
     *
     * @param name
     */
    public MinisterDoctor (String name) {
        // TODO: 1. initialize the name attribute of this MinisterDoctor object using super class constructor
        //       2. make the specialty of the doctor to be "Minister"
        //       3. set the salary of the fever doctor to 500
        super(name);
        this.specialty = "Minister";
        this.salary = 500;
    }

    /**
     * recruitDoctor
     *
     * @param name
     */
    @Override
    public void recruitDoctor(Player player, String name) {
        // TODO 1. Since MinisterDoctor can hire a FeverDoctor, a MedicalDoctor, and SurgicalDoctor and also a MinisterDoctor all with 25% of chance
        //         so you may want to use a Random object to generate a random int from 0-3, and then for each value of the random int, you let this
        //         minister doctor to hire a different kind of doctor (i.e. Doctor dr=new FeverDoctor(name) if that random int is 0, and then
        //         Doctor dr=new MedicalDoctor(name) when the random int is 1, and so on...)
        //         Refer to slides 39-40 of the note set below for the details of using the random object:
        //         https://course.cse.ust.hk/comp3021/notes/2-classes-objects-full.pdf
        //      2. add this new doctor object to the player, using addNewlyRecruitDoctor() method
        //      3. once this doctor is hired, he will be occupied, set the boolean variable "occupied" accordingly
        Random rnd = new Random();
        Doctor dr;
        switch (rnd.nextInt(4)) {
            case 0: {
                dr = new MinisterDoctor(name);
                break;
            }
            case 1: {
                dr = new FeverDoctor(name);
                break;
            }
            case 2: {
                dr = new MedicalDoctor(name);
                break;
            }
            default: {
                dr = new SurgicalDoctor(name);
                break;
            }
        }
        player.addNewlyRecruitedDoctor(dr);
        this.occupied = true;
    }

    /**
     * Minister doctor is better at raising fund than other types of doctor.
     *
     * @return 1000 times (1 + 0.1 times the skillLevel)
     */
    @Override
    public int raiseFund() {
        // TODO
        //  the amount the minister doctor can raise is 1000 times (1 + 0.1 times the skillLevel), remember to return an int
        return ((int) (1000 * (1 + 0.1 * this.specialSkillLevel)));
    }

    /**
     * The minister doctor is good at getting discount when upgrading the hospital.
     *
     * @return 1.0 - 0.1 times the specialSkillLevel
     */
    @Override
    public double getUpgradeDiscountRate() {
        // TODO
        //  the discount rate  is 1.0 - 0.1 times the specialSkillLevel
        return (1.0 - 0.1 * this.specialSkillLevel);
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
        //  1. MinisterDoctor is specialized in administration. So for any type of patient,
        //     a minister doctor only has 50% chance to cure them (i.e., with 0.5 probability
        //     the doctor may fail to cure the patient).
        //  2. You may have to use java.util.random to model the probability.
        Random rnd = new Random();
        return (rnd.nextInt(100) > 50);
    }
}
