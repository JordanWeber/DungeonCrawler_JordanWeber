/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10x10;

import java.util.Random;

/**
 *
 * @author JordanWeber
 */
public class Enemy {
    public static Random rand = new Random();
    public boolean isAlive;
    public int x, y;
    private static int enemiesDestroyed = 0;
    
    
    public Enemy() {
        this.x = rand.nextInt(39) + 1;
        this.y = rand.nextInt(39) + 1;
        this.isAlive = true;
        
    }
    
    public void destroyEnemy() {
        this.isAlive = false;
        this.x = 100;
        this.y = 100;
        enemiesDestroyed++;
        System.out.println("check: "+enemiesDestroyed);
        
        if(enemiesDestroyed >= 3) {
            BombBlaster.win(1);
        }
    }
}
