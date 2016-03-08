package slickexample;

import java.time.zone.ZoneOffsetTransitionRule;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;


class blocked {

    public static boolean[][] blocked;
    public static boolean[][] getblocked() {
        return blocked;
    }

};

public class DungeonCrawler extends BasicGameState {
    
    public Player player;;
    public Orb orb1, orb2, orb3, orb4, orb5, orb6, orb7, orb8, orb9, orb10;
    public Enemy enemy1, enemy2, enemy3, enemy4, enemy5;
    public Item1 healthpotion, healthpotion1;
    public Item2 speedpotion, speedpotion1;
    public ItemWin antidote;
    public Ninja Weber, Morse, Giavana;
    public Coin coin, coin1;
    public Orb[] orbs = new Orb[10];
    public ArrayList<Item1> stuff = new ArrayList();
    public ArrayList<Item2> stuff1 = new ArrayList();
    public ArrayList<ItemWin> stuffwin = new ArrayList();   
    public ArrayList<Ninja> ninjas = new ArrayList();
    public ArrayList<Coin> coins = new ArrayList();
    public ArrayList<Enemy> enemies = new ArrayList();
    
    private boolean[][] hostiles;
    private static TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;

    private static final int SIZE = 64;

	// screen width and height won't change
    private static final int SCREEN_WIDTH = 1000;

    private static final int SCREEN_HEIGHT = 750;

    public DungeonCrawler(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);
		// *******************

		// Scenerey Stuff
		// ****************
        grassMap = new TiledMap("res/d6.tmx");
        

		// Ongoing checks are useful
        System.out.println("Tile map is this wide: " + grassMap.getWidth());

        camera = new Camera(gc, grassMap);
        
        player = new Player();
        
        orb1 = new Orb((int) player.x, (int) player.y);
        orb2 = new Orb((int) player.x, (int) player.y);
        orb3 = new Orb((int) player.x, (int) player.y);
        orb4 = new Orb((int) player.x, (int) player.y);
        orb5 = new Orb((int) player.x, (int) player.y);
        orb6 = new Orb((int) player.x, (int) player.y);
        orb7 = new Orb((int) player.x, (int) player.y);
        orb8 = new Orb((int) player.x, (int) player.y);
        orb9 = new Orb((int) player.x, (int) player.y);
        orb10 = new Orb((int) player.x, (int) player.y);
        

        orbs[0] = orb1;
        orbs[1] = orb2;
        orbs[2] = orb3;
        orbs[3] = orb4;
        orbs[4] = orb5;
        orbs[5] = orb6;
        orbs[6] = orb7;
        orbs[7] = orb8;
        orbs[8] = orb9;
        orbs[9] = orb10;
        
        
        enemy1 = new Enemy(500, 200);
        enemy2 = new Enemy(550, 200);
        enemy3 = new Enemy(600, 200);
        enemy4 = new Enemy(650, 200);
        enemy5 = new Enemy(700, 200);

        
		// *********************************************************************************
		// Player stuff --- these things should probably be chunked into methods
        // and classes
		// *********************************************************************************
        

		// *****************************************************************
		// Obstacles etc.
		// build a collision map based on tile properties in the TileD map
        blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		// System.out.println("Map height:" + grassMap.getHeight());
		// System.out.println("Map width:" + grassMap.getWidth());
		// There can be more than 1 layer. You'll check whatever layer has the
        // obstacles.
		// You could also use this for planning traps, etc.
		// System.out.println("Number of tile layers: "
        // +grassMap.getLayerCount());
        System.out.println("The grassmap is " + grassMap.getWidth() + "by "
                + grassMap.getHeight());

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

				// int tileID = grassMap.getTileId(xAxis, yAxis, 0);
				// Why was this changed?
				// It's a Different Layer.
				// You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");

                    blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }

        System.out.println("Array length" + blocked.blocked[0].length);

		// A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        //Draw Health Potions
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item1 i = new Item1(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }
        
        //Draw Speed Potions
//        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
//            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
//                int xBlock = (int) xAxis;
//                int yBlock = (int) yAxis;
//                if (!blocked.blocked[xBlock][yBlock]) {
//                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
//                        Item2 h = new Item2(xAxis * SIZE, yAxis * SIZE);
//                        //	stuff.add(i);
//                        stuff1.add(h);
//                        hostiles[xAxis][yAxis] = true;
//                    }
//                }
//            }
//        }

        healthpotion = new Item1(300, 200);
//        healthpotion1 = new Item1(450, 400);
        stuff.add(healthpotion);
//        stuff.add(healthpotion1);
        
//        Morse = new Ninja(100,75);
//        Giavana = new Ninja(100, 100);
//        Weber = new Ninja(100, 125);
//        ninjas.add(Morse);
//        ninjas.add(Giavana);
//        ninjas.add(Weber);
        
//        coin = new Coin(100, 150);
//        coin1 = new Coin(100, 250);
//        coins.add(coin);
//        coins.add(coin1);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        enemies.add(enemy5);
        

        speedpotion = new Item2(600, 200);
//        speedpotion1 = new Item2(450, 100);
        stuff1.add(speedpotion);
//        stuff1.add(speedpotion1);

        antidote = new ItemWin(3000, 3000);
        stuffwin.add(antidote);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) player.x, (int) player.y);

        camera.drawMap();

        camera.translateGraphics();

		// it helps to add status reports to see what's going on
		// but it gets old quickly
//		 System.out.println("Current X: " +player.x + " \n Current Y: "+ player.y);
        player.sprite.draw((int) player.x, (int) player.y);

		g.drawString("x: " + (int)player.x + "y: " +(int)player.y , player.x, player.y - 10);
        g.drawString("Health: " + player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10); 

        g.drawString("speed: " + (int) (player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);
        
        g.drawString("Gold Bars Collected: " + player.goldbars, camera.cameraX + 10, camera.cameraY + 40);

	g.draw(player.rect);
//        g.draw(enemy1.rect);
//        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        
        
        // moveenemies();

        for (Item1 i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
				// draw the hitbox
                //g.draw(i.hitbox);

            }
        }

        for (Item2 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
				// draw the hitbox
                //g.draw(h.hitbox);

            }
        }
        for (Ninja a : ninjas) {
            if (a.isvisible) {
                a.currentImage.draw(a.x, a.y);
                
//                g.draw(a.hitbox);
            }
        }
        
        for (Coin a : coins) {
            if (a.isvisible) {
                a.currentImage.draw(a.x, a.y);
                
//                g.draw(a.hitbox);
            }
        }
        for (Enemy a : enemies) {
            if (a.isVisible) {
                a.currentanime.draw(a.Bx, a.By);
                
//                g.draw(a.rect);
            }
        }

        for (ItemWin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
				// draw the hitbox
                //g.draw(w.hitbox);

            }
        }
        if(orb1.isIsVisible()) {
        orb1.currentImage.draw(orb1.getX(), orb1.getY());
//            g.draw(orb1.hitbox);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        counter += delta;

        Input input = gc.getInput();

        float fdelta = delta * player.speed;

        player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

		// System.out.println("Right limit: " + rightlimit);
        float projectedright = player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

		// there are two types of fixes. A kludge and a hack. This is a kludge.
        if (input.isKeyDown(Input.KEY_UP)) {
            player.direction = "up";

            player.sprite = player.up;

            float fdsc = (float) (fdelta - (SIZE * .15));

            if (!(isBlocked(player.x, player.y - fdelta) || isBlocked((float) (player.x + SIZE + 1.5), player.y - fdelta))) {

                player.sprite.update(delta);

				// The lower the delta the slower the sprite will animate.
                player.y -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            player.direction = "down";

            player.sprite = player.down;

            if (!isBlocked(player.x, player.y + SIZE + fdelta)
                    || !isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta)) {

                player.sprite.update(delta);

                player.y += fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            player.direction = "left";

            player.sprite = player.left;

            if (!(isBlocked(player.x - fdelta, player.y) || isBlocked(player.x
                    - fdelta, player.y + SIZE - 1))) {

                player.sprite.update(delta);

                player.x -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.direction = "right";

            player.sprite = player.right;

			// the boolean-kludge-implementation
            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {

                player.sprite.update(delta);

                player.x += fdelta;

            } // else { System.out.println("Right limit reached: " +
            // rightlimit);}

        } else if (input.isKeyPressed(Input.KEY_SPACE)) {
            orbs[player.shotsFired].setX((int) player.x);
            orbs[player.shotsFired].setY((int) player.y);
            orbs[player.shotsFired].hitbox.setX(orbs[player.shotsFired].getX());
            orbs[player.shotsFired].hitbox.setY(orbs[player.shotsFired].getY());
            orbs[player.shotsFired].setIsVisible(!orbs[player.shotsFired].isIsVisible());
            if(!orbs[player.shotsFired].isIsVisible()) {
                orbs[player.shotsFired].setIsVisible(!orbs[player.shotsFired].isIsVisible());
            }
            orbs[player.shotsFired].setDirection(player.direction);
//            player.shotsFired++;
//            }
            
        }
        if(orbs[player.shotsFired].getDirection()=="up") {
            orbs[player.shotsFired].setX(orbs[player.shotsFired].getX());
            orbs[player.shotsFired].setY(orbs[player.shotsFired].getY() - 5);         
        } else if(orbs[player.shotsFired].getDirection()=="down") {
            orbs[player.shotsFired].setX(orbs[player.shotsFired].getX());
            orbs[player.shotsFired].setY(orbs[player.shotsFired].getY() + 5);
        } else if(orbs[player.shotsFired].getDirection()=="left") {
            orbs[player.shotsFired].setX(orbs[player.shotsFired].getX() - 5);
            orbs[player.shotsFired].setY(orbs[player.shotsFired].getY());
        } else if(orbs[player.shotsFired].getDirection()=="right") {
            orbs[player.shotsFired].setX(orbs[player.shotsFired].getX() + 5);
            orbs[player.shotsFired].setY(orbs[player.shotsFired].getY());
        }
        
        orbs[player.shotsFired].hitbox.setX(orbs[player.shotsFired].getX());
        orbs[player.shotsFired].hitbox.setY(orbs[player.shotsFired].getY());
        
        player.rect.setLocation(player.getplayershitboxX(),
                player.getplayershitboxY());

        for (Item1 i : stuff) {

            if (player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {
                    player.goldbars++;
                    i.isvisible = false;
                }

            }
        }
        
        for (Enemy a : enemies) {
            if(orbs[player.shotsFired].hitbox.intersects(a.rect)) {
                if(a.isVisible) {
                    a.isVisible = false;
                }
            }
        }
        
        for (Ninja n : ninjas) {

            if (player.rect.intersects(n.hitbox)) {
                //System.out.println("yay");
                if (n.isvisible) {

                    player.health -= 10000;
                    n.isvisible = false;
                }

            }
        }

        for (Item2 h : stuff1) {

            if (player.rect.intersects(h.hitbox)) {
                //System.out.println("yay");
                if (h.isvisible) {

                    player.speed += .1f;
                    h.isvisible = false;
                }

            }
        }

        for (ItemWin w : stuffwin) {

            if (player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }
        
//        if(orb1.hitbox.intersects(player.rect)) {
//            
//        }

//        player.health -= counter / 1000;
        if (player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (Item2 h : stuff1) {

            h.isvisible = true;
        }

        for (Item1 i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return blocked.blocked[xBlock][yBlock];

		// this could make a better kludge
    }
    

}
