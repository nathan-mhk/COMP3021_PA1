package pa1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameData {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * loadGameData from players.txt
     */
    public void loadGameData(String filename) throws IOException {
        //  the following reads the players.txt file
        //  1. The first line includes the number of players
        //  2. Then there is an empty line
        //  3. Then the next line includes the detailed information of  a player
        //     including player name, player hospitalName and initial
        //     amount of money (all these fields are separated by one space each).
        //  4. then another empty line
        //  5. then another line of player information
        //  6. and so on so forth

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            int numPlayers = Integer.parseInt(in.readLine()); // number of players read
            in.readLine(); // read the empty line

            players = new ArrayList<>(); // create the ArrayList for holding all the player objects
            for (int i = 0; i < numPlayers; i++) {
                //TODO
                //  in this for loop, do the following:
                //  1. read a line for player information
                //  2. split the line (using space as delimiter) to get the playerName, hospitalName, initMoneyAmount
                //     of the player.
                // 3. construct the player object using the playerName, hospitalName, initMoneyAmount you read from the
                //    file
                // 4. add the player to the players (ArrayList) using the add() method
                // 5. read an empty line without doing anything

            } //end of for loop
        }
    }
}
