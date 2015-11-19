package weber_3_interactivefiction;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author JordanWeber
 */
public class Weber_3_InteractiveFiction {
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static Player player = new Player();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        start();
    }
    public static void run() {
        String answer = "";
        player.playerClass = player.setPlayerClass();
        System.out.println("Player class is: "+Player.playerClass);
        
        //Player plays or does not play tutorial
        System.out.print("Would you like to play the tutorial?");
        answer = sc.nextLine().toLowerCase();
        if(answer.contains("y")){
            tutorial();
            answer = "";
        } else {
            answer = "";
        }
        //End tutorial prompt
        player.displayStats();
        
    }
    public static void start() {
        System.out.print("What is your name?\n>> ");
        Player.name = sc.nextLine();
        System.out.print("Would you like to play a game, "+Player.name+"?\n>> ");
        String answer = sc.nextLine();
        if(answer.contains("y")) {
            run();
        }
    }
    public static void tutorial(){
        System.out.println("Tutorial goes here.");
    }
}
