import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> computerPos = new ArrayList<>();
    static String PLAYER = "Player";
    static String COMPUTER = "Computer";

    public static void main(String[] args) {
        char[][] board = {
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '}
        };
        printBoard(board);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Поставте хрестик у вільне поле від 1 до 9");
            int playerPosition = scanner.nextInt();
            while (playerPos.contains(playerPosition) || computerPos.contains(playerPosition)) {
                playerPosition = scanner.nextInt();
            }
            place(board, playerPosition, PLAYER);
            StringBuilder res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
            Random random = new Random();
            int computerPosition = random.nextInt(9) + 1;
            while (playerPos.contains(computerPosition) || computerPos.contains(computerPosition)) {
                computerPosition = random.nextInt(9) + 1;
            }
            place(board, computerPosition, COMPUTER);
            printBoard(board);
            res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }

        }


    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void place(char[][] board, int place, String user) {
        char marker = ' ';
        if (user.equals(PLAYER)) {
            marker = 'X';
            playerPos.add(place);
        } else if (user.equals(COMPUTER)) {
            marker = 'O';
            computerPos.add(place);
        }
        switch (place) {
            case 1:
                board[0][0] = marker;
                break;
            case 2:
                board[0][2] = marker;
                break;
            case 3:
                board[0][4] = marker;
                break;
            case 4:
                board[2][0] = marker;
                break;
            case 5:
                board[2][2] = marker;
                break;
            case 6:
                board[2][4] = marker;
                break;
            case 7:
                board[4][0] = marker;
                break;
            case 8:
                board[4][2] = marker;
                break;
            case 9:
                board[4][4] = marker;
                break;
            default:
                break;
        }
    }

    public static StringBuilder checkWinner() {
        StringBuilder stringBuilder = new StringBuilder();
        List topLine = Arrays.asList(1, 2, 3);
        List midLine = Arrays.asList(4, 5, 6);
        List botLine = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List midColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(7, 5, 3);
        List<List> winList = new ArrayList<>();
        winList.add(topLine);
        winList.add(midLine);
        winList.add(botLine);
        winList.add(leftColumn);
        winList.add(midColumn);
        winList.add(rightColumn);
        winList.add(diagonal1);
        winList.add(diagonal2);
        for (List list : winList) {
            if (playerPos.containsAll(list)) {
                stringBuilder = new StringBuilder("Ви вийграли");
            } else if (computerPos.containsAll(list)) {
                stringBuilder = new StringBuilder("Ви програли");
            } else if (playerPos.size() + computerPos.size() == 9) {
                stringBuilder = new StringBuilder("Нічия");
            }
        }
        return stringBuilder;
    }
}
