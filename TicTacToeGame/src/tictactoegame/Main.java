/*
 *Copyright 2018 Zi Feng Pan
 */
package tictactoegame;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author breadboykid
 **/

public class Main {

    public static void main(String[] args) throws InterruptedException{
        //Gets input
        Scanner sc = new Scanner(System.in);

        boolean play = true;

        while(play){
            //Set up marker and computer
            System.out.println("Welcome to Tic Tac Toe\nPlease pick a character for yourself and for the " +
                    "computer");
            System.out.println();
            System.out.println("Enter your character");
            char playerToken = sc.next().charAt(0);
            System.out.println("Enter opponents character");
            char opponentToken = sc.next().charAt(0);
            TicTacToe game = new TicTacToe(playerToken, opponentToken);
            Computer ai = new Computer();

            //Set up the game
            System.out.println();
            System.out.println("Start Game.\nEnter a number and your token and your token shall be " +
                    "put in its place.\nThe numbers go from 1-9, left to right.");
            TicTacToe.printNewBoard();
            System.out.println();

            //Play
            while(game.gameOver().equals("Not Over")){
                if(game.currentTurn == game.userTurn){
                    //user turn
                    System.out.println("\nYour turn, enter spot for your token");
                    int spot = sc.nextInt();

                    while(!game.playTurn(spot)){
                        System.out.println("Spot " + spot + " invalid. Please try again.");
                        spot = sc.nextInt();
                    }

                    System.out.println("You picked " + spot + "!");
                }else{
                    //Computer
                    System.out.println();
                    System.out.println("Computer's turn");
                    System.out.print("Computer is thinking");

                    //Simulated computer thinking
                    for(int i = 0; i <3; i++){
                        TimeUnit.MILLISECONDS.sleep(700);
                        System.out.print(" .");
                    }

                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);
                    System.out.println("\nAI picked " + aiSpot + "!");
                    TimeUnit.MILLISECONDS.sleep(900);
                }
                System.out.println();
                game.printCurBoard();
            }
            System.out.println(game.gameOver());
            System.out.println();

            //setup new game
            System.out.println("To play again enter 'Y'");

            char response = sc.next().charAt(0);
            play = (response == 'Y');
            System.out.println();
            System.out.println();
        }
    }
}
