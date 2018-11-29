import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static char myLetter = 'R';

    private static char[][] board = new char[6][7];

    private static Scanner in = new Scanner(System.in);

    private static boolean win = false;

    private static boolean tie = false;




    public static void main(String[] args) {


        initializeBoard();

        System.out.println(getCurrentBoard());

        try (
                ServerSocket serverSocket =
                        new ServerSocket(1234);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                myLetter = 'Y';
                addPiece(Integer.parseInt(inputLine));

                System.out.println(getCurrentBoard());
                if(win){
                    System.out.println("YOUR OPPONENT HAS WON");
                    break;
                }
                if(tie){
                    System.out.println("THE GAME IS A TIE");
                    break;
                }
                myLetter = 'R';
                System.out.println("Type a column number(0-6) to place your piece: ");
                int col = in.nextInt();

                addPiece(col);
                out.println(col);

                System.out.println(getCurrentBoard());
                if(win) {
                    System.out.println("YOU WIN!!");
                    break;
                }
                if(tie){
                    System.out.println("THE GAME IS A TIE");
                    break;
                }



            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + 1234 + " or listening for a connection");
            System.out.println(e.getMessage());
        }



    }

    private static void initializeBoard() {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

                board[r][c] = ' ';

            }
        }
    }

    private static void addPiece(int col) {

        if (col != -1) {

            for(int r = board.length-1; r >= 0; r--) {

                if (board[r][col] == ' ') {

                    board[r][col] = myLetter;
                    if (checkAll(r, col)){
                        win = true;
                    }
                    else if(checkFullBoard()){
                        tie = true;
                    }
                    return;


                }

            }

        }


    }

    private static boolean checkVertical(int r, int c) {

        int lowR;
        int count=0;

        if (r<5&&board[r][c]==board[r+1][c]) {

            lowR = r+1;
            while(board[lowR][c]==board[r][c]&&lowR!=board.length-1) {

                if (board[r][c]==board[lowR+1][c])
                    lowR++;
                else break;
            }
        }

        else lowR = r;

        while(board[lowR][c] == board[r][c]) {
            lowR--;
            count++;

            if (lowR < 0) break;
        }
        if(count>=4) {
            return true;
        }
        return false;
    }

    private static boolean checkHorizontal(int r, int c) {

        int lowC;
        int count = 0;

        if (c<6&&board[r][c]==board[r][c+1]) {
            lowC = c+1;

            while(board[r][lowC]==board[r][c]&&lowC!=6) {
                lowC++;
            }

        }
        else lowC = c;

        while(board[r][lowC]==board[r][c]) {

            lowC--;
            count++;

            if (lowC < 0) break;

        }

        if (count >= 4) {

            // handle winning
            return true;
        }
        return false;

    }

    private static boolean checkDiagonalNeg(int r, int c) {

        int lowC, lowR, count = 0;

        if (r<5&&c<6&&board[r][c]==board[r+1][c+1]) {

            lowC = c+1;
            lowR = r+1;

            while(board[lowR][lowC]==board[r][c]&&lowC!=6&&lowR!=board.length-1) {
                lowC++;
                lowR++;
            }

        } else {
            lowC = c;
            lowR = r;
        }

        while(board[lowR][lowC]==board[r][c]) {

            lowC--;
            lowR--;
            count++;

            if (lowC < 0 || lowR < 0) break;

        }

        if (count >= 4) {
            return true;
         }
        return false;
    }

    private static boolean checkDiagonalPos(int r, int c) {

        int lowC, lowR, count = 0;

        if (r>0&&r<5&&c<6&&board[r][c]==board[r+1][c+1]) {

            lowC = c+1;
            lowR = r-1;

            while(board[lowR][lowC]==board[r][c]&&lowC!=6&&lowR!=board.length-1) {
                lowC++;
                lowR--;
            }

        } else {
            lowC = c;
            lowR = r;
        }

        while(board[lowR][lowC]==board[r][c]) {

            lowC--;
            lowR++;
            count++;

            if (lowC < 0 || lowR >= board.length) break;

        }

        if (count >= 4) {
            return true;

        }
        return false;
    }

    private static boolean checkFullBoard() {

        int count = 0;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

                if (board[r][c] != ' ') count++;

            }
        }

        if (count == 42) {

            return true;

        }
        return false;
    }

    private static boolean checkAll(int r, int c) {

        if(checkHorizontal(r,c)||
        checkVertical(r,c)||
        checkDiagonalNeg(r,c)||
        checkDiagonalPos(r,c))
            return true;
        return false;


    }

    private static String getCurrentBoard() {

        String output = "";

        for (int r = 0; r < board.length; r++) {
            output += " | ";
            for (int c = 0; c < board[0].length; c++) {
                output += board[r][c] + " | ";
            }
            output += "\n";
        }

        return output;

    }
}
