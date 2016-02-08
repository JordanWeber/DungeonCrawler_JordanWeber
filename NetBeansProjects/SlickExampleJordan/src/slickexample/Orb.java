/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickexample;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;



/**
 *
 * @author JordanWeber
 */
public class Orb {
    private int width, height, dmg, hitboxX, hitboxY, hitBoxWidth, hitBoxHeight;
    private float x, y;
    private boolean isVisible = false;
    Image currentImage;
    Image orbImage;
    Shape hitbox;
    
    public Orb(float a, float b) throws SlickException{
        this.orbImage = new Image("res/orbs/Ninja_1.png");
        this.x = a;
        this.y = b;
        this.isVisible = false;
        this.hitbox = new Rectangle(a, b, 32, 32);
        this.currentImage = orbImage;
        this.dmg = 10;
        
        /*
        Getters and setters are a common concept in Java.
        A design guideline in Java, and ovject oriented
        programming in general, is to encapsulate.isolate
        values as smuch as possible.
        Getters - are methods used to query the value of
        instance variables.
        this.getLocationX();
        Setters  - methods that set values for instance
        variables.
        */
        
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHitboxX() {
        return hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public Image getOrbImage() {
        return orbImage;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setHitboxX(int hitboxX) {
        this.hitboxX = hitboxX;
    }

    public void setHitboxY(int hitboxY) {
        this.hitboxY = hitboxY;
    }

    public void setHitBoxWidth(int hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }

    public void setHitBoxHeight(int hitBoxHeight) {
        this.hitBoxHeight = hitBoxHeight;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public void setOrbImage(Image orbImage) {
        this.orbImage = orbImage;
    }

    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }
    
}
