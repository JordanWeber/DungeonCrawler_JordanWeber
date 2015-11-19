/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weber_3_interactivefiction;
import java.util.Scanner;
/**
 *
 * @author JordanWeber
 */
public class Player {
    static Scanner sc = new Scanner(System.in);
    public static String name = "";
    public static String playerClass = "";
    
    //Player Stats
    public static int healthPoints = 100;
    public static String weapon = "none";
    public static int weaponDamage = 0;
    //Food restores 10 hp
    public static int foodCount = 0;
    //Potions restore 25 hp
    public static int potionCount = 0;
    //End Player Stats
    
    public static String setPlayerClass(){
        System.out.print("Choose a class: Archer, Warrior, or Mage\n>> ");
        String classChoice = sc.nextLine().toLowerCase();
        switch(classChoice) {
            case "warrior":
                weapon = "Long Sword";
                weaponDamage = 10;
                foodCount = 10;
                potionCount = 5;
                break;
            case "mage":
                
                break;
            case "archer":
                
                break;
            default:
                System.out.println("Not a class.");
                setPlayerClass();
                break;
        }
        return classChoice;
    }
    
    public static void displayStats() {
        System.out.println("/------------------------------\\");
        System.out.println("Health: "+healthPoints);
        System.out.println("----------Inventory---------");
        System.out.println("Weapon: "+weapon+"\n--> Damage: "+weaponDamage);
        System.out.println("Food: "+foodCount);
        System.out.println("Potions: "+potionCount);
        System.out.println("\\------------------------------/");
    }
}
