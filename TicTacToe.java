import java.util.Scanner;

public class TicTacToe {

    private static final Scanner input = new Scanner(System.in);
    private static Character[][] board = new Character[3][3];
    private static Character player1Mark;
    private static Character player2Mark;
    private static int[] kullanilanlar = new int[9];
    private static int nextIndex = 0;
    private static int selection;
    private static boolean shouldBreak = false;

    public static void boardInputExample() {
        int[][] thisBoard = new int[3][3];
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                thisBoard[i][j] = (i * 3) + j + 1;
            }
        }
        for (int[] obj : thisBoard) {
            for (int obj2 : obj) {
                System.out.print(obj2 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void boardReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        for (int i = 0; i < 9; i++) {
            kullanilanlar[i] = 0;
        }
        nextIndex = 0;
    }

    public static void printCurrentBoard()
    {
        for (Character[] obj : board) {
            for (Character obj2 : obj) {
                System.out.print(obj2 + " ");
            }
            System.out.println();
        }
    }

    public static void rulesPrinter() {
        System.out.println("X-O-X \nAlta alta, üst üste veya çapraz olarak üçleyen kazanır."
                + "\nX her zaman önce başlar. \nSeçimlerinizi aşağıdaki tabloya göre yapınız: ");
        boardInputExample();
        boardReset();
    }
    
    public static void markSetter() {
        do {
            System.out.println("Oyuncu 1: X mi O mu?");
            char mark = getInput().next().charAt(0);
            setPlayer1Mark(Character.toUpperCase(mark));
        } while (getPlayer1Mark() != 'X' && getPlayer1Mark() != 'O');
        System.out.print("\033[H\033[2J");
        if (getPlayer1Mark() == 'X') {
            System.out.println("Oyuncu 1 X olarak belirlendi. \nOyuncu 2 O olarak belirlendi.\n");
            setPlayer2Mark('O');
        } else {
            System.out.println("Oyuncu 1 O olarak belirlendi. \nOyuncu 2 X olarak belirlendi.\n");
            setPlayer2Mark('X');
        }
    }

    public static int rangeCheck(int high, int low, int num) {
        if (low <= num && num < high) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void getSelection() {
        System.out.println("\nLütfen 1-9 arasında bir sayı girin: ");
        while (!getInput().hasNextInt()) {
            System.out.println("\nYanlış giriş yaptınız. 1-9 arasında bir sayı giriniz: ");
            getInput().next();
        }
        int selectionPH = getInput().nextInt();
        if (rangeCheck(10, 1, selectionPH) == 0) {
            getSelection();
        } else {
            selection = selectionPH;
        }
    }

    public static void playerSelection(int x) {
        char ch;
        if (x == 0) {
            ch = 'X';
        } else {
            ch = 'O';
        }

        getSelection();
        for (int element : kullanilanlar) {
            while (element == selection) {
                System.out.println("\nSeçiminizi boş bir alana yapınız: ");
                getSelection();

            }
        }
        pushNextIndex(selection);
        char mark = ch;
        switch (selection) {

            case 1:
                board[0][0] = mark;
                break;
            case 2:
                board[0][1] = mark;
                break;
            case 3:
                board[0][2] = mark;
                break;
            case 4:
                board[1][0] = mark;
                break;
            case 5:
                board[1][1] = mark;
                break;
            case 6:
                board[1][2] = mark;
                break;
            case 7:
                board[2][0] = mark;
                break;
            case 8:
                board[2][1] = mark;
                break;
            case 9:
                board[2][2] = mark;
                break;
        }
    }

    public static void pushNextIndex(int element) {
        kullanilanlar[nextIndex] = element;
        nextIndex++;
    }

    public static int checkRowForWin() {
        int a = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][2] && board[i][1] == board[i][2] && board[i][2] != '-') {
                a = 1;
                break;
            }
        }
        return a;
    }

    public static int checkColForWin() {
        int a = 0;
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[2][i] && board[1][i] == board[2][i] && board[2][i] != '-') {
                a = 1;
                break;
            }
        }
        return a;
    }

    public static int checkDiagForWin() {
        if (board[0][0] == board[2][2] && board[1][1] == board[2][2] && board[2][2] != '-'
                || board[0][2] == board[2][0] && board[1][1] == board[2][0] && board[2][0] != '-') {
            return 1;
        }
        return 0;
    }

    public static int checkForWin() {
        if (checkColForWin() == 1 || checkDiagForWin() == 1 || checkRowForWin() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void gameStart() {
        rulesPrinter();
        markSetter();
        int x = 0;
        while (!shouldBreak) {
            printCurrentBoard();
            playerSelection(x % 2);
            System.out.print("\033[H\033[2J");
            if (checkForWin() == 1) {
                printCurrentBoard();
                switch (x % 2) {
                    case 0:
                        if (getPlayer1Mark() == 'X') {
                            System.out.println("\nOYUNCU 1 KAZANDI!");

                        } else {
                            System.out.println("\nOYUNCU 2 KAZANDI!");

                        }
                        shouldBreak = true;
                        break;

                    case 1:
                        if (getPlayer1Mark() == 'X') {
                            System.out.println("\nOYUNCU 2 KAZANDI!");
                        } else {
                            System.out.println("\nOYUNCU 1 KAZANDI!");
                        }
                        shouldBreak = true;
                        break;
                }
            }
            if ((checkZeroes() == 0) && checkForWin() == 0) {
                System.out.println("BERABERE!");
                break;
            }
            x++;
        }
    }

    public static int checkZeroes() {
        for (int i = 0; i < 9; i++) {
            if (kullanilanlar[i] == 0) {
                return 1;
            }
        }
        return 0;
    }

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        TicTacToe.board = board;
    }

    public static char getPlayer1Mark() {
        return player1Mark;
    }

    public static void setPlayer1Mark(char player1Mark) {
        TicTacToe.player1Mark = player1Mark;
    }

    public static char getPlayer2Mark() {
        return player2Mark;
    }

    public static void setPlayer2Mark(char player2Mark) {
        TicTacToe.player2Mark = player2Mark;
    }

    public static Scanner getInput() {
        return input;
    }

    public static void main(String[] args) {
        char chooser = 'e';
        while (chooser == 'E' || chooser == 'e') {
            gameStart();
            System.out.println();
            System.out.println("Devam etmek istiyor musun? E/e veya H/h");
            chooser = getInput().next().charAt(0);
            boardReset();
            System.out.print("\033[H\033[2J");
            shouldBreak = false;
        }
    }

}