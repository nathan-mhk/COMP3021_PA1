package pa1;

import java.io.IOException;
import java.util.Scanner;

import pa1.departments.Department;
import pa1.doctors.Doctor;
import pa1.exceptions.DeficitException;

public class GameEngine {
    private int turns;
    private Scanner userInputScanner = new Scanner(System.in);
    private GameData gameData = new GameData();

    /**
     * Test if game is over.
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        // TODO
        //  The game ends when either of following
        //  condition meets:
        //  1. number of turns > 20;
        //  2. any of the players gets bankrupted.
        //     you can get all the players of the game by calling gameData.getPlayers()
        //     you can get the total number of players using gameData.getPlayers().size()
        //     and to get the i-th player you can use gameData.getPlayers().get(i)
        //     to know whether the i-th player has gone bankrupted, you can do
        //     gameData.getPlayers().get(i).hasBankrupted()
        if (this.turns > 20) {
            return true;
        } else {
            for (int i = 0; i < this.gameData.getPlayers().size(); ++i) {
                if (this.gameData.getPlayers().get(i).hasBankrupted()) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Get the winner for the game.
     *
     * @return the winning player in the game
     */
    public Player getWinner() {
        // TODO
        //  1. When the number of turns is larger than 20, which
        //     means the game ends by reaching maximum number of turns,
        //     find the player with highest curedPatientCount.
        //     you can get all the players of the game by calling gameData.getPlayers()
        //     you can get the total number of players using gameData.getPlayers().size()
        //     and to get the i-th player you can use gameData.getPlayers().get(i)
        //     to get the cured patient number of the i-th player, you can do:
        //     gameData.getPlayers().get(i).getCuredPatientCount()
        //  2. Otherwise, either one or both of the players are
        //     bankrupted. Find the player which is not bankrupted as
        //     the winner (return null if both players get bankrupted)
        int playerIndex = 0;

        if (this.turns > 20) {
            int highestCuredCount = 0;
            for (int i = 0; i < this.gameData.getPlayers().size(); ++i) {
                int currentCuredCount = this.gameData.getPlayers().get(i).getCuredPatientCount();
                if (currentCuredCount > highestCuredCount) {
                    highestCuredCount = currentCuredCount;
                    playerIndex = i;
                }
            }
            return this.gameData.getPlayers().get(playerIndex);
        } else {
            Player firstPlayer = this.gameData.getPlayers().get(0);
            Player secondPlayer = this.gameData.getPlayers().get(1);
            boolean firstBR = firstPlayer.hasBankrupted();
            boolean secondBR = secondPlayer.hasBankrupted();

            if (firstBR && secondBR) {
                return null;
            } else {
                return (firstBR ? secondPlayer : firstPlayer);
            }
        }

    }

    public void processPlayersTurn() {
        turns++;
        for (Player player : gameData.getPlayers()) {

            if (!player.hasBankrupted()) {
                System.out.printf("\n\t--- Season %d | %s's turn ---\n\n", turns, player.getName());
                printStatistics();
                player.processAtStartOfTurn();
            } else {
                break;
            }

            while(!player.allDoctorOccupied()) {
                printPlayerInfo(player);
                Doctor doctor = selectDoctor(player);
                if (doctor == null) break;
                selectAndPerformAction(player, doctor);
                doctor.endTurn();
                printPlayerInfo(player);
            }

            handlePlayerPostProcessing(player);

            printPlayerInfo(player);

            confirmAtEndOfTurn();
        }
    }

    /**
     * Handle the post processing for the player
     *
     * @param player
     */
    private void handlePlayerPostProcessing(Player player) {
        // TODO
        //  1. Handle the post processing for the player by calling
        //  the player's processAtEndOfTurn() method.
        //  2. Note that this method will throw DeficitException,
        //  you need to carefully handle the exception.
        //  3. print out the exception message, appending "!!! Warning: "
        //  before the exception message, also add a newline to the end.
        try {
            player.processAtEndOfTurn();
        } catch (DeficitException e) {
            // FIXME
//            e.printStackTrace();
            System.out.println("!!! Warning: ");
        }
    }

    private void printPlayerInfo(Player player) {
        System.out.print("\n\n");
        System.out.println(player);
        System.out.print("\n");
        for (Department dept: player.getDepartments()) {
            System.out.println(dept);
        }
        System.out.print("\n");
    }

    private int getSelection(int min, int max, String name) {
        while (true) {
            try {
                System.out.printf("\nSelect %s (%d-%d):\n", name, min, max);
                int selection = userInputScanner.nextInt();
                userInputScanner.nextLine();
                if ((selection < min || selection > max) && selection != 0)
                    throw new IndexOutOfBoundsException();
                return selection;

            } catch (Exception e) {
                userInputScanner.nextLine();
                System.out.print("Invalid option, choose again");
            }
        }
    }

    private void confirmAtEndOfTurn() {
        System.out.println("Your turn is ended, press enter to continue:");
        userInputScanner.nextLine();
    }

    private void selectAndPerformAction(Player player, Doctor doctor) {
        System.out.println("SELECT DOCTOR ACTION");
        System.out.println("\t[ 1]\tGo training");
        System.out.println("\t[ 2]\tRecruit doctor");
        System.out.println("\t[ 3]\tUpgrade department");
        System.out.println("\t[ 4]\tTransfer doctor to department");
        System.out.println("\t[ 5]\tRaise fund");

        int command = getSelection(1, 5, "action");
        processPlayerCommand(command, player, doctor);
    }

    private Doctor selectDoctor(Player player) {
        System.out.println("DOCTOR SELECTION");
        for (int i = 0; i < player.getDoctors().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getDoctors().get(i));

        Doctor doctor = null;
        while (true) {
            int selection = getSelection(1, player.getDoctors().size(), "doctor (0 to skip turn)");
            if (selection == 0) break;
            doctor = player.getDoctors().get(selection - 1);
            if (doctor.isOccupied()) {
                System.out.println("Selected doctor already performed a task");
            } else {
                break;
            }
        }

        return doctor;
    }

    private Department selectDepartment(Player player) {
        System.out.println("DEPARTMENT SELECTION");
        for (int i = 0; i < player.getDepartments().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getDepartments().get(i));

        int selection = getSelection(1, player.getDepartments().size(), "department (0 to skip turn)");
        if (selection == 0) return null;
        return player.getDepartments().get(selection - 1);
    }

    private void processPlayerCommand(int command, Player player, Doctor doctor) {
        Department dept;
        switch (command) {
            case 1:
                doctor.goTraining(player);
                System.out.printf("After training:\n%s\n", doctor);
                break;
            case 2:
                System.out.println("Input new doctor's name:");
                String name = userInputScanner.next();
                doctor.recruitDoctor(player, name);
                break;
            case 3:
                dept = selectDepartment(player);
                if (dept != null) doctor.upgradeDepartment(player, dept);
                break;
            case 4:
                dept = selectDepartment(player);
                if (dept != null) doctor.transferToDepartment(dept);
                break;
            case 5:
                doctor.raiseFund();
                break;
            default:
                System.out.printf("\n player %s enter command %d", player.getName(), command);
                break;
        }
    }

    private void printStatistics() {
        System.out.printf("*** Game statistics\n", turns);
        for (Player player: gameData.getPlayers()) {
            System.out.printf("*** %s\n", player);
        }
        System.out.println();
    }

    public static void main(String args[]) throws IOException {
        GameEngine game = new GameEngine();
        game.gameData.loadGameData("players.txt");

        while (!game.isGameOver()) {
            game.processPlayersTurn();
        }

        Player winner = game.getWinner();
        if (winner == null) {
            System.out.printf("All the players get bankrupted!");
        } else {
            System.out.printf("Player %s wins the game", winner.getName());
        }
    }
}
