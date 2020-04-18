package pa1.exceptions;

import pa1.Player;

public class DeficitException extends Exception {
    private final int deficit;
    private final int deficitTimeSpan;
    private final String playerName;

    /**
     * constructor
     *
     * @param player the player who enters into deficit status
     */
    public DeficitException(Player player) {
        // TODO
        //  1. record deficit amount of the player in deficit of this exception object.
        //     the deficit amount could be retrieved using player.getMoney()
        //  2. record the play's name in playerName of this exception object.
        //  3. record the deficit duration of the player in deficitTimeSpan of
        //     this exception object. You can use the getDeficitTimeSpan() method of the player
        //     to get this information
    }

    /**
     * getMessage
     *
     * @return the formatted message string
     */
    @Override
    public String getMessage() {
        // TODO
        //  1. Format the string like follows
        //     "Player Kay has been in deficit for 2 turns, current deficit is -100,
        //     keeping in deficit status will lead to bankruptcy"
        //  2. you can use the String.format() function and provide:
        //     "Player %s has been in deficit for %d turns, current deficit is %d, " +
        //                        "keeping in deficit status will lead to bankruptcy"
        //  you may find slide 22 of the note set https://course.cse.ust.hk/comp3021/notes/3-strings-full.pdf helpful
    }
}
