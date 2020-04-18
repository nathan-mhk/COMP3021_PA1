package pa1.patients;

import java.util.Random;

public abstract class Patient {
    protected String category;
    protected int timeSpan = 0;
    protected int maxAllowedTimeSpan = 3;
    protected double deathRate;

    /**
     * return the category of the patient
     *
     * @return the category of the patient
     */
    public String getCategory () {
        return category;
    }

    /**
     *
     * The patient has not been treated, so he/she
     * have to wait for another turn to see the doctor.
     *
     * Here we increase the timespan by 1 indicating the patient
     * has waited for another season
     */
    public void stayForAnotherSeason() {

        timeSpan++;
    }

    /**
     * Check if the patient is dead at the end of the turn
     *
     * There are two occasions that a patient might die
     * 1. if the maxAllowedTimeSpan is reached, the patient will
     * definitely die.
     *
     * 2. Otherwise, the patient die with a probability equals
     * to the deathRate.
     *
     * Use java.util.Random to model the probability behavior.
     *
     * @return if the patient is dead
     *
     */
    public boolean isDeadAtEndOfTurn () {
        // TODO
        //   1. There is the maxAllowedTimeSpan for the patient to stay in the hospital.
        //      if the patient stay in the hospital longer than that max duration, he/she is dead, return true
        //      You can check this using the timeSpan variable of the patient object
        //   2. if the patient has not stayed longer than the maxAllowedTimeSpan, the patient will die with a
        //      probability equals to the deathRate. Use java.util.Random to model the probability behavior.

    }
}
