import java.util.Scanner;

public class TicTacToe5x5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                board[i][j] = '.';

        System.out.println("=== 5x5 井字遊戲 ===");
        printBoard(board);

        char currentPlayer = 'X';
        while (true) {
            int row, col;
            while (true) {
                try {
                    row = sc.nextInt();
                    col = sc.nextInt();
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("請輸入兩個整數 (0~4)：");
                    continue;
                }

                if (row < 0 || row >= 5 || col < 0 || col >= 5) {
                    System.out.println("座標超出範圍，請重新輸入！");
                } else if (board[row][col] != '.') {
                    System.out.println("該位置已被佔用，請重新輸入！");
                } else {
                    break;
                }
            }

            board[row][col] = currentPlayer;
            System.out.println("玩家 " + currentPlayer + " 在位置 (" + row + ", " + col + ") 放置棋子");
            printBoard(board);

            if (checkWin(board, currentPlayer)) {
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                break;
            }

            if (isDraw(board)) {
                System.out.println("平手！");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    static void printBoard(char[][] board) {
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
                if (j < 4) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean checkWin(char[][] b, char p) {
        for (int i = 0; i < 5; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p && b[i][3] == p && b[i][4] == p)
                return true;
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p && b[3][i] == p && b[4][i] == p)
                return true;
        }
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p && b[3][3] == p && b[4][4] == p)
            return true;
        if (b[0][4] == p && b[1][3] == p && b[2][2] == p && b[3][1] == p && b[4][0] == p)
            return true;

        return false;
    }

    static boolean isDraw(char[][] board) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (board[i][j] == '.')
                    return false;
        return true;
    }
}
