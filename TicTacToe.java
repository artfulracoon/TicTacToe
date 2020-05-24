import java.util.Scanner;

public class TicTacToe {

    private static final Scanner input = new Scanner(System.in);
    private static Character[][] board = new Character[3][3];
    private static Character player1Mark, player2Mark;
    private static int[] kullanilanlar = new int[9];
    private static int nextIndex = 0;
    private static int selection;
    private static boolean shouldBreak = false;
    private static int chooser = 1;
    private static int oyuncu1Win = 0;
    private static int oyuncu2Win = 0;
    private static String oyuncu1Ad, oyuncu2Ad;
    private static char charPH;

    public static void boardInputExample() {
        int[][] thisBoard = new int[3][3];
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                thisBoard[i][j] = (i * 3) + j + 1;
            }
        }
        for (int i = 2; i > -1; i--) {
            for (int j = 0; j < 3; j++) {
                System.out.print(thisBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nNumpad Düzeni\n");
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

    public static void printCurrentBoard() {
        for (Character[] obj : board) {
            for (Character obj2 : obj) {
                System.out.print(obj2 + " ");
            }
            System.out.println();
        }
    }

    public static void rulesPrinter() {
        System.out.println("X-O-X \n\nAlt alta, üst üste veya çapraz olarak kendi harfini üçleyen kazanır."
                + "\nX her zaman önce başlar. \nSeçimlerinizi aşağıdaki tabloya göre yapınız: ");
        boardInputExample();
        boardReset();
    }

    public static void markSetter() {
        do {
            System.out.println("\n" + oyuncu1Ad + ": X mi O mu?");
            char mark = getInput().next().charAt(0);
            setPlayer1Mark(Character.toUpperCase(mark));
        } while (getPlayer1Mark() != 'X' && getPlayer1Mark() != 'O');
        System.out.print("\033[H\033[2J");
        if (getPlayer1Mark() == 'X') {
            System.out.println("Oyuncu 1 " + oyuncu1Ad + " X olarak belirlendi." + "\nOyuncu 2 " + oyuncu2Ad
                    + " O olarak belirlendi.\n");
            setPlayer2Mark('O');
        } else {
            System.out.println("Oyuncu 1 " + oyuncu1Ad + " O olarak belirlendi." + "\nOyuncu 2 " + oyuncu2Ad
                    + " X olarak belirlendi.\n");
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
        switch (x) {
            case 0:
                charPH = 'X';
                if (getPlayer1Mark() == 'X') {
                    System.out.println("\n" + oyuncu1Ad);
                } else {
                    System.out.println(oyuncu2Ad);
                }
                break;
            case 1:
                charPH = 'O';
                System.out.println();
                if (getPlayer2Mark() == 'X') {
                    System.out.println("\n" + oyuncu1Ad);
                } else {
                    System.out.println(oyuncu2Ad);
                }
                break;
        }

        getSelection();
        for (int element : kullanilanlar) {
            while (element == selection) {
                System.out.println("\nLütfen seçiminizi boş bir alana yapınız: ");
                getSelection();

            }
        }
        pushNextIndex(selection);
        switch (selection) {

            case 1:
                board[2][0] = charPH;
                break;
            case 2:
                board[2][1] = charPH;
                break;
            case 3:
                board[2][2] = charPH;
                break;
            case 4:
                board[1][0] = charPH;
                break;
            case 5:
                board[1][1] = charPH;
                break;
            case 6:
                board[1][2] = charPH;
                break;
            case 7:
                board[0][0] = charPH;
                break;
            case 8:
                board[0][1] = charPH;
                break;
            case 9:
                board[0][2] = charPH;
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
                            System.out.println("\n" + oyuncu1Ad + " KAZANDI!");
                            oyuncu1Win++;
                        } else {
                            System.out.println("\n" + oyuncu2Ad + " KAZANDI!");
                            oyuncu2Win++;
                        }
                        shouldBreak = true;
                        break;

                    case 1:
                        if (getPlayer1Mark() == 'X') {
                            System.out.println("\n" + oyuncu1Ad + " KAZANDI!");
                            oyuncu2Win++;
                        } else {
                            System.out.println("\n" + oyuncu2Ad + " KAZANDI!");
                            oyuncu1Win++;
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

    public static void wannaContinue() {
        while (!getInput().hasNextInt()) {
            System.out.println("Lütfen geçerli bir sayı giriniz!");
            getInput().next();
        }
        int chooserPH = getInput().nextInt();
        if (chooserPH == 1 || chooserPH == 2) {
            chooser = chooserPH;
            boardReset();
            System.out.print("\033[H\033[2J");
            shouldBreak = false;
        } else {
            System.out.println("Lütfen geçerli bir sayı giriniz!");
            wannaContinue();
        }
    }

    private static void adSetter() {
        System.out.println("Oyuncu 1 isminizi giriniz: (Default: Oyuncu 1)");
        oyuncu1Ad = getInput().nextLine();
        if (oyuncu1Ad.isEmpty()) {
            oyuncu1Ad = "Oyuncu 1";
        }
        System.out.println("Oyuncu 2 isminizi giriniz: (Default: Oyuncu 2)");
        oyuncu2Ad = getInput().nextLine();
        if (oyuncu2Ad.isEmpty()) {
            oyuncu2Ad = "Oyuncu 2";
        }
    }

    public static void main(String[] args) {
        rulesPrinter();
        adSetter();
        while (chooser == 1) {
            gameStart();
            System.out.println("\n" + oyuncu1Ad + ": " + oyuncu1Win + "\n" + oyuncu2Ad + ": " + oyuncu2Win);
            System.out.println("\nDevam etmek istiyor musun? \n1. Evet \n2. Hayır\n");
            wannaContinue();

        }
    }

}