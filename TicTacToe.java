import java.util.Scanner;
import java.lang.*;

public class TicTacToe {

    private static Scanner input = new Scanner(System.in);
    private static Character[][] board = new Character[3][3];
    private static Character player1Mark;
    private static Character player2Mark;
    private static int[] kullanilanlar = new int[9];

    public static void boardInputExample()
    {
        int[][] thisBoard = new int[3][3];
        System.out.println();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                thisBoard[i][j] = (i*3) + j + 1;
            }
        }
        for (int[] obj: thisBoard)
        {
            for (int obj2: obj)
            {
                System.out.print(obj2 + " ");
            }
            System.out.println();
        }
    }

    public static void rulesPrinter()
    {
        System.out.println("X-O-X \nAlta alta, üst üste veya çapraz olarak üçleyen kazanır.\nX her zaman önce başlar. \nSeçimlerinizi aşağıdaki tabloya göre yapınız: ");
        boardInputExample();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = '-';
            }
        }
    }

    public static void markSetter()
    {
        System.out.println("Oyuncu 1: X mi O mu?");
        do {
            char mark = getInput().next().charAt(0);
            setPlayer1Mark(Character.toUpperCase(mark));
        } while (getPlayer1Mark() != 'X' && getPlayer1Mark() != 'O');
        if (getPlayer1Mark() == 'X')
        {
            System.out.println("Oyuncu 1 X olarak belirlendi.");
            System.out.println("Oyuncu 2 O olarak belirlendi.");
            setPlayer2Mark('O');}
        else {
            System.out.println("Oyuncu 1 O olarak belirlendi.");
            System.out.println("Oyuncu 2 X olarak belirlendi.\n");
            setPlayer2Mark('X');}
    }

    public static void playerSelection(int x)
    {
        Character ch;
        if (x == 0)
        {
            ch = 'X';
        }
        else {
            ch = 'O';}

        System.out.println("Seçiminizi yapın: ");
        int selection = getInput().nextInt();
        for (int element: kullanilanlar)
        {
                while(element == selection)
                {
                    System.out.println("Seçiminizi boş bir alana yapınız: ");
                    selection = getInput().nextInt();

            }
        }
        char mark = ch;
        switch (selection)
        {

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

    public static int checkRowForWin()
    {
        int a = 0;
        for (int i=0; i < 3; i++)
        {
            if (board[i][0] == board[i][2] && board [i][1] == board[i][2] && board[i][2] != '-')
            {
                a = 1;
                break;
            }
        }
        return a;
    }

    public static int checkColForWin()
    {
        int a = 0;
        for (int i=0; i < 3; i++)
        {
            if (board[0][i] == board[2][i] && board [1][i] == board[2][i] && board[2][i] != '-')
            {
                a = 1;
                break;
            }
        }
        return a;
    }

    public static int checkDiagForWin()
    {
        int a = 0;
        if (board[0][0] == board[2][2] && board [1][1] == board[2][2] && board[2][2] != '-'
        || board[0][2] == board[2][0] && board [1][1] == board[2][0] && board[2][0] != '-')
        {a = 1;}
        return a;
    }

    public static int checkForWin()
    {
        if (checkColForWin()==1 || checkDiagForWin()==1 || checkRowForWin()==1)
        {
            return 1;
        } else {return 0;}
    }

    public static void gameStart()
    {
        rulesPrinter();
        System.out.println();
        markSetter();
        int x = 0;
        while (true){
            int check = 0;
            playerSelection(x % 2);
            System.out.print("\033[H\033[2J");
            for (Character[] obj: board)
            {
                for (Character obj2: obj)
                {
                    System.out.print(obj2 + " ");
                }
                System.out.println();
            }
            if (checkForWin() == 1)
            {
                if ((x%2)==0)
                {
                    if (getPlayer1Mark() == 'X')
                    {
                        System.out.println("OYUNCU 1 KAZANDI!");
                    } else {
                        System.out.println("OYUNCU 2 KAZANDI!");
                    } break;
                } else
                {if (getPlayer1Mark() == 'X')
                {
                    System.out.println("OYUNCU 2 KAZANDI!");
                } else {
                    System.out.println("OYUNCU 1 KAZANDI!");
                } break;}
            }
            x++;
        }
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
        gameStart();
    }

}
