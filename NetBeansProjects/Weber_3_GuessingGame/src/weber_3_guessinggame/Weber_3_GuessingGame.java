
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weber_3_guessinggame;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author JordanWeber
 */
public class Weber_3_GuessingGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int tries = 15;
        int count = 0;
        System.out.print("What is your name?\n>> ");
        String name = sc.nextLine();
        int randomNum = rand.nextInt(1000);
        while(true){
        System.out.print("(Guesses left: "+(tries-count)+") Guess a number between 0 and 10, "+name+"\n>> ");
        int guess = sc.nextInt();
        count++;
        if(guess == randomNum) {
            System.out.println("You got it right!");
            break;
        } else {
            if(guess > randomNum) {
                if(tries == count){
                    break;
                }else{
                System.out.println("Too high! Guess again.");
                }
            } else {
                if(tries == count){
                    break;
                }else{
                System.out.println("Too low! Guess again.");
                }
            }
        }
        }
   
    }
    
}
