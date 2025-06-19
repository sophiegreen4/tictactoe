import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // keeping track of positions chosen
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String playAgain;

        do {
            // set up the board in the console using a 2D array and '|' & '+' to create the grid
            char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}};

            // empty the board from previous games
            playerPositions.clear();
            computerPositions.clear();

        // print the board (set up in below method)
        printGameBoard(gameBoard);


            // while loop so the game continues
            while (true) {


                // PLAYER TURN
                System.out.println("Enter your choice (1 - 9): ");
                int pos = input.nextInt();
                // validates input
                if (pos < 0 || pos > 9) {
                    System.out.println("Invalid input, try again:");
                    pos = input.nextInt();
                }
                // prevents overriding already chosen positions
                while (playerPositions.contains(pos) || computerPositions.contains(pos)) {
                    System.out.println("Position already taken! Choose again:");
                    pos = input.nextInt();
                }

                placePiece(gameBoard, pos, "player");
                String result = checkWinner();
                if (result.length() > 0) {
                    System.out.println(result);
                }


                // CPU TURN
                Random rand = new Random();
                int compPos = rand.nextInt(9) + 1;
                while (playerPositions.contains(compPos) || computerPositions.contains(compPos)) {
                    compPos = rand.nextInt(9) + 1;
                }
                placePiece(gameBoard, compPos, "computer");

                printGameBoard(gameBoard);

                result = checkWinner();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }

            }

            // Ask to play again
            System.out.print("Play again? ");
            input.nextLine();
            playAgain = input.nextLine().toLowerCase();


        } while (playAgain.equals("yes") || playAgain.equals("y") || playAgain.equals("yeah") || playAgain.equals("ok"));

        input.close();


    }


    public static void printGameBoard(char [][] gameBoard){
        for(char[] row : gameBoard) {
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // method to allocate symbol
    public static void placePiece(char[][] gameBoard, int pos, String user){
        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("computer")){
            symbol = 'O';
            computerPositions.add(pos);
        }

        // numbers 1-9 = where the X/O will go
        for (int i = 0; i < gameBoard.length; i++) {
            if (pos == 1) {
                gameBoard[0][0] = symbol;
            } else if (pos == 2) {
                gameBoard[0][2] = symbol;
            } else if (pos == 3) {
                gameBoard[0][4] = symbol;
            } else if (pos == 4) {
                gameBoard[2][0] = symbol;
            } else if (pos == 5) {
                gameBoard[2][2] = symbol;
            } else if (pos == 6) {
                gameBoard[2][4] = symbol;
            } else if (pos == 7) {
                gameBoard[4][0] = symbol;
            } else if (pos == 8) {
                gameBoard[4][2] = symbol;
            } else if (pos == 9) {
                gameBoard[4][4] = symbol;
            }
        }


    }
public static String checkWinner(){

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "You win!";
            } else if(computerPositions.containsAll(l)){
                return "You lost!";
            } else if(playerPositions.size() + computerPositions.size() == 9){
                return "Draw!";
            }
        }

        return "";


}


}