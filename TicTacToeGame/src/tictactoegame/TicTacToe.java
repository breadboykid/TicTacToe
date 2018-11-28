/*
 *Copyright 2018 Zi Feng Pan
 */
package tictactoegame;

/**
 * @author breadboykid
 **/

public class TicTacToe {

    protected char[] board;
    protected char compTurn, userTurn, currentTurn, winner;

    public TicTacToe(char user, char comp){
        this.userTurn = user;
        this.compTurn = comp;
        this.currentTurn = userTurn;
        this.winner = '-';
        this.board = setBoard();
    }

    //creates a new board with '-' filled in
    public static char[] setBoard(){
        char[] newBoard = new char[9];
        for(int i = 0; i < 9; i++){
            newBoard[i] = '-';
        }
        return newBoard;
    }

    //Printing board methods
    public static void printNewBoard(){
        System.out.println();

        for(int i = 0; i < 9; i++){
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("--------------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }

    public void printCurBoard(){
        System.out.println();

        for(int i = 0; i < 9; i++){
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("--------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

    //Marker to change turns
    public boolean playTurn(int position){
        boolean playValid = !isSpotTaken(position) && isWithinRange(position);
        if(playValid){
            board[position-1] = currentTurn;
            currentTurn = (currentTurn == userTurn) ? compTurn:userTurn;
                    }
        return playValid;
    }

    //checks if spot is taken
    public boolean isSpotTaken(int n){
        return board[n-1] != '-';
    }

    //checks if within range
    public boolean isWithinRange(int n){
        return n > 0 && n <= board.length;
    }



    //checks if any 3 in row
    public boolean isThereAWinner(){
        boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';

        if(diagonalsAndMiddles){
            this.winner = board[4];
        }else if(topAndFirst){
            this.winner = board[0];
        }else if(bottomAndThird){
            this.winner = board[8];
        }
        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }

    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }

    public boolean middleRow(){
        return  board[3] == board[4] && board[4] == board[5];
    }

    public boolean bottomRow(){
        return board[6] == board[7] && board[7] == board[8];
    }

    public boolean leftDi(){
        return board[0] == board[4] && board[4] == board[8];
    }

    public boolean rightDi(){
        return board[6] == board[4] && board[4] == board[2];
    }

    public boolean firstCol(){
        return board[0] == board[3] && board[3] == board[6];
    }

    public boolean secondCol(){
        return board[1] == board[4] && board[4] == board[7];
    }

    public boolean thirdCol(){
        return board[2] == board[5] && board[5] == board[8];
    }

    public boolean isTheBoardFilled(){
        for(int i = 0; i < board.length; i++){
            if(board[i] == '-'){
                return  false;
            }
        }
        return true;
    }

    //checks if game is over
    public String gameOver(){
        boolean winStatus = isThereAWinner();
        if(winStatus){
            return "We have a winner!\n" + this.winner + "'s wins!";
        }else if(isTheBoardFilled()){
            return "Draw! No more turns available";
        }else{
            return "Not Over";
        }
    }
}




