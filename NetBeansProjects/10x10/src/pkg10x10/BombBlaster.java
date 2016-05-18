package pkg10x10;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkg10x10;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author JordanWeber
 */
public class BombBlaster {

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static int[] coordinate = new int[2];
    public static ArrayList<Enemy> enemies = new ArrayList();
    public static Enemy enemy1, enemy2, enemy3;
    public static int[][] trapPos = new int[10][2];
    public static int[][] bombs = new int[3][2];
    public static int bomb = 0;
    public static String[][] matrix = new String[42][42];
    public static boolean[] bombTick = new boolean[3];
    public static int[] bombTickTimer = new int[3];
    public static boolean[] bombExplode = new boolean[3];
    public static boolean[] bombCleared = new boolean[3];
    public static int[][] retrievableBombs = new int[10][2];
    public static int bombsOnField = 0;
    public static int[] treasure = new int[2];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startScreen();
    }

    public static void start() {
        enemy1 = new Enemy();
        enemy2 = new Enemy();
        enemy3 = new Enemy();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        generateMatrix();
        generateTreasure();
        setPlayer(19, 19);
        drawEnemy();
        generateTraps();
        printMatrix();
        while (true) {
            String input = userInput(0);
            if (input.equals("5") && bomb < 3) {
                bombTick[bomb] = true;
                setBomb();
                bomb++;
            } else if (input.equals("5") && bomb >= 3) {
                System.out.println("No More Bombs!");
                moveAt(input);
                if (bomb > 0) {
                    if (bomb == 1) {
                        bombTickTimer[0]++;
                    }
                    if (bomb == 2) {
                        bombTickTimer[0]++;
                        bombTickTimer[1]++;
                    }
                    if (bomb == 3) {
                        bombTickTimer[0]++;
                        bombTickTimer[1]++;
                        bombTickTimer[2]++;
                    }
                }
            } else {
                moveAt(input);
                if (bomb > 0) {
                    if (bomb == 1) {
                        bombTickTimer[0]++;
                    }
                    if (bomb == 2) {
                        bombTickTimer[0]++;
                        bombTickTimer[1]++;
                    }
                    if (bomb == 3) {
                        bombTickTimer[0]++;
                        bombTickTimer[1]++;
                        bombTickTimer[2]++;
                    }
                }
            }
            clearBombs();
            moveEnemy();
            drawEnemy();
            checkEnemyCollide();
            checkBombExplode();
            checkPlayerTrapCollide();
            generateBombs();
            checkPickUpBombs();
            checkTreasureCollide();
            printMatrix();
        }
    }

    public static void startScreen() {
        System.out.println("---------------Enemy Destroyer-----------------------");
        System.out.println("Type A Direction On The Keypad To Move\nOr\n(5) To Drop A Bomb At Your Position");
        System.out.println("Beware Of Traps! (O), Touch One And You'll Die!");
        System.out.println("Press Any Key + Enter To Begin");
        System.out.println("-----------------------------------------------------");
        Scanner startSC = new Scanner(System.in);
        String blankInput = startSC.next();
        if (blankInput.equals("")) {
            start();
        } else {
            start();
        }
    }

    public static void generateMatrix() {
        for (int i = 0; i < 41; i++) {
            if (i == 0 || i == 40) {
                for (int a = 0; a < 41; a++) {
                    if (a < 40) {
                        matrix[i][a] = "--";
                    } else {
                        matrix[i][a] = "-";
                    }
                }
            } else {
                for (int a = 0; a < 41; a++) {
                    if (a == 0 || a == 40) {
                        matrix[i][a] = "|";
                    } else {
                        matrix[i][a] = " ";
                    }
                }
            }
        }
    }

    public static void printMatrix() {
        for (int i = 0; i < 41; i++) {
            if (i == 0 || i == 40) {
                for (int a = 0; a < 41; a++) {
                    if (a < 40) {
                        System.out.print(matrix[i][a]);
                    } else {
                        System.out.println(matrix[i][a]);
                    }
                }
            } else {
                for (int a = 0; a < 41; a++) {
                    if (a < 40) {
                        System.out.print(matrix[i][a] + " ");
                    } else {
                        System.out.println(matrix[i][a] + " ");
                    }
                }
            }
        }
    }
//    public static   int[] askUser() {
//        int[] coordinates = new int[2];
//        
//        System.out.print("Choose X Coordinate\n>> ");
//        coordinates[0] = sc.nextInt() - 1;
//        System.out.print("Choose Y Coordinate\n>> ");
//        coordinates[1] = sc.nextInt() - 1;
//        
//        return coordinates;
//        
//    }

    public static String userInput(int a) {
        String input = "";
        if (a == 1) {
            System.out.print("You hit a wall! Chose another direction\n>> ");
        } else {
            System.out.print("Which direction do you want to move?\n>> ");
        }
        input = sc.nextLine().toLowerCase();
        while (!input.equals("8") && !input.equals("2") && !input.equals("6") && !input.equals("4") && !input.equals("9")
                && !input.equals("7") && !input.equals("3") && !input.equals("1") && !input.equals("5")) {
            System.out.print("Invalid direction, choose another\n>> ");
            input = sc.nextLine();
        }

        return input;
    }

    public static void setPlayer(int a, int b) {
        coordinate[0] = a;
        coordinate[1] = b;
        matrix[coordinate[0]][coordinate[1]] = "@";
    }

    public static void setO(int a, int b) {
        matrix[a][b] = " ";
    }

    public static void moveAt(String input) {
        int[] start = new int[2];
        start[0] = coordinate[0];
        start[1] = coordinate[1];
        switch (input) {
            case "8":
                if (coordinate[0] == 1) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] -= 1;
                }
                break;
            case "2":
                if (coordinate[0] == 39) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] += 1;
                }
                break;
            case "6":
                if (coordinate[1] == 39) {
                    moveAt(userInput(1));
                } else {
                    coordinate[1] += 1;
                }
                break;
            case "4":
                if (coordinate[1] == 1) {
                    moveAt(userInput(1));
                } else {
                    coordinate[1] -= 1;
                }
                break;
            case "9":
                if (coordinate[1] == 39 || coordinate[0] == 1) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] -= 1;
                    coordinate[1] += 1;
                }
                break;
            case "7":
                if (coordinate[1] == 1 || coordinate[0] == 1) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] -= 1;
                    coordinate[1] -= 1;
                }
                break;
            case "3":
                if (coordinate[1] == 39 || coordinate[0] == 39) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] += 1;
                    coordinate[1] += 1;
                }
                break;
            case "1":
                if (coordinate[1] == 1 || coordinate[0] == 39) {
                    moveAt(userInput(1));
                } else {
                    coordinate[0] += 1;
                    coordinate[1] -= 1;
                }
                break;

        }
        setPlayer(coordinate[0], coordinate[1]);
        setO(start[0], start[1]);
    }

    public static void drawEnemy() {
        for (Enemy e : enemies) {
            if (e.isAlive) {
                matrix[e.x][e.y] = "X";
            }
        }
    }

    public static void moveEnemy() {
        for (Enemy e : enemies) {
            int chance = rand.nextInt(100);

            if (chance < 25) {
                //enemy does not move
            } else if (e.isAlive) {
                int[] start = new int[2];
                start[0] = e.x;
                start[1] = e.y;
                int xDiff = e.x - coordinate[0];
                int yDiff = e.y - coordinate[1];

                if (xDiff < 0 && yDiff < 0) {
                    e.x++;
                    e.y++;
                } else if (xDiff > 0 && yDiff < 0) {
                    e.x--;
                    e.y++;
                } else if (xDiff < 0 && yDiff > 0) {
                    e.x++;
                    e.y--;
                } else if (xDiff > 0 && yDiff > 0) {
                    e.x--;
                    e.y--;
                } else if (xDiff < 0 && yDiff == 0) {
                    e.x++;
                } else if (xDiff > 0 && yDiff == 0) {
                    e.x--;
                } else if (xDiff == 0 && yDiff < 0) {
                    e.y++;
                } else if (xDiff == 0 && yDiff > 0) {
                    e.y--;
                }

                matrix[start[0]][start[1]] = " ";
            }
        }
    }

    public static void setBomb() {
        bombs[bomb][0] = coordinate[0];
        bombs[bomb][1] = coordinate[1];
        String input = userInput(0);
        moveAt(input);
        drawBomb(bombs[bomb][0], bombs[bomb][1]);
//        bomb++;
    }

    public static void drawBomb(int a, int b) {
        matrix[a][b] = "*";
    }

    public static void checkBombExplode() {
        for (int i = 0; i < 3; i++) {
            if (bombTickTimer[i] >= 3 && !bombCleared[i]) {
//                System.out.println("Bomb"+i+" Exploded");
                bombExplode(i);
            }
        }
    }

    public static void bombExplode(int a) {
//        int a = b - 1;
        String[][] bombPlace = new String[3][3];
        bombPlace[0][0] = "/";
        bombPlace[0][1] = "|";
        bombPlace[0][2] = "\\";
        bombPlace[1][0] = "=";
        bombPlace[1][1] = "+";
        bombPlace[1][2] = "=";
        bombPlace[2][0] = "\\";
        bombPlace[2][1] = "|";
        bombPlace[2][2] = "/";

        matrix[bombs[a][0] - 1][bombs[a][1] + 1] = bombPlace[0][0];
        matrix[bombs[a][0] - 1][bombs[a][1]] = bombPlace[0][1];
        matrix[bombs[a][0] + 1][bombs[a][1] + 1] = bombPlace[0][2];
        matrix[bombs[a][0]][bombs[a][1] - 1] = bombPlace[1][0];
        matrix[bombs[a][0]][bombs[a][1]] = bombPlace[1][1];
        matrix[bombs[a][0]][bombs[a][1] + 1] = bombPlace[1][2];
        matrix[bombs[a][0] - 1][bombs[a][1] - 1] = bombPlace[2][0];
        matrix[bombs[a][0] + 1][bombs[a][1]] = bombPlace[2][1];
        matrix[bombs[a][0] + 1][bombs[a][1] - 1] = bombPlace[2][2];

        bombExplode[a] = true;

        checkBombCollide();

    }

    public static void checkBombCollide() {
        for (int i = 0; i < 3; i++) {
            for (Enemy e : enemies) {
                if (Math.abs(e.x - bombs[i][0]) < 2 && Math.abs(e.y - bombs[i][1]) < 2 && bombExplode[i]) {
                    e.destroyEnemy();
                }
            }
            if (Math.abs(bombs[i][0] - coordinate[0]) < 2 && Math.abs(bombs[i][1] - coordinate[1]) < 2) {
                printMatrix();
                lose(3);
            }
        }
    }

    public static void checkEnemyCollide() {
        for (Enemy e : enemies) {
            if (e.x - coordinate[0] == 0 && e.y - coordinate[1] == 0) {
                lose(1);
            }
        }
    }

    public static void destroyAllEnemies() {
        String[][] explosion = new String[3][3];
        explosion[0][0] = "/";
        explosion[0][1] = "|";
        explosion[0][2] = "\\";
        explosion[1][0] = "=";
        explosion[1][1] = "+";
        explosion[1][2] = "=";
        explosion[2][0] = "\\";
        explosion[2][1] = "|";
        explosion[2][2] = "/";

        for (Enemy e : enemies) {
            if (e.isAlive) {
                matrix[e.x - 1][e.y + 1] = explosion[0][0];
                matrix[e.x - 1][e.y] = explosion[0][1];
                matrix[e.x + 1][e.y + 1] = explosion[0][2];
                matrix[e.x][e.y - 1] = explosion[1][0];
                matrix[e.x][e.y] = explosion[1][1];
                matrix[e.x][e.y + 1] = explosion[1][2];
                matrix[e.x - 1][e.y - 1] = explosion[2][0];
                matrix[e.x + 1][e.y] = explosion[2][1];
                matrix[e.x + 1][e.y - 1] = explosion[2][2];
            }
        }
    }

    public static void generateTraps() {
        for (int i = 0; i < 10; i++) {
            trapPos[i][0] = rand.nextInt(39) + 1;
            trapPos[i][1] = rand.nextInt(39) + 1;

            drawTraps(trapPos[i][0], trapPos[i][1]);
        }
    }

    public static void drawTraps(int a, int b) {
        matrix[a][b] = "O";
    }

    public static void clearBombs() {
        for (int i = 0; i < 3; i++) {
            if (bombExplode[i]) {
                bombCleared[i] = true;
                matrix[bombs[i][0] - 1][bombs[i][1] + 1] = " ";
                matrix[bombs[i][0] - 1][bombs[i][1]] = " ";
                matrix[bombs[i][0] + 1][bombs[i][1] + 1] = " ";
                matrix[bombs[i][0]][bombs[i][1] - 1] = " ";
                matrix[bombs[i][0]][bombs[i][1]] = " ";
                matrix[bombs[i][0]][bombs[i][1] + 1] = " ";
                matrix[bombs[i][0] - 1][bombs[i][1] - 1] = " ";
                matrix[bombs[i][0] + 1][bombs[i][1]] = " ";
                matrix[bombs[i][0] + 1][bombs[i][1] - 1] = " ";
            }
        }
    }

    public static void checkPlayerTrapCollide() {
        String[][] bombPlace = new String[3][3];
        bombPlace[0][0] = "/";
        bombPlace[0][1] = "|";
        bombPlace[0][2] = "\\";
        bombPlace[1][0] = "=";
        bombPlace[1][1] = "+";
        bombPlace[1][2] = "=";
        bombPlace[2][0] = "\\";
        bombPlace[2][1] = "|";
        bombPlace[2][2] = "/";

        for (int i = 0; i < 10; i++) {
            if (coordinate[0] - trapPos[i][0] == 0 && coordinate[1] - trapPos[i][1] == 0) {
                matrix[coordinate[0] - 1][coordinate[1] + 1] = bombPlace[0][0];
                matrix[coordinate[0] - 1][coordinate[1]] = bombPlace[0][1];
                matrix[coordinate[0] + 1][coordinate[1] + 1] = bombPlace[0][2];
                matrix[coordinate[0]][coordinate[1] - 1] = bombPlace[1][0];
                matrix[coordinate[0]][coordinate[1]] = bombPlace[1][1];
                matrix[coordinate[0]][coordinate[1] + 1] = bombPlace[1][2];
                matrix[coordinate[0] - 1][coordinate[1] - 1] = bombPlace[2][0];
                matrix[coordinate[0] + 1][coordinate[1]] = bombPlace[2][1];
                matrix[coordinate[0] + 1][coordinate[1] - 1] = bombPlace[2][2];
                printMatrix();
                lose(2);
            }
        }
    }

    public static void generateBombs() {
//        int chance = rand.nextInt(100);
//        if(chance < 10 && bomb > 0) {
//            bombsOnField++;
//            retrievableBombs[bombsOnField-1][0] = rand.nextInt(39)+1;
//            retrievableBombs[bombsOnField-1][1] = rand.nextInt(39)+1;
//            
//            matrix[retrievableBombs[bombsOnField-1][0]][retrievableBombs[bombsOnField-1][1]] = "B";
//        }
    }

    public static void checkPickUpBombs() {
//        for(int i = 0; i < bombsOnField; i++) {
//            if(retrievableBombs[i][0] - coordinate[0] == 0 && retrievableBombs[i][1] - coordinate[1] == 0) {
//                bomb--;
//                bombsOnField--;
//            }
//        }
    }

    public static void generateTreasure() {
        treasure[0] = rand.nextInt(39) + 1;
        treasure[1] = rand.nextInt(39) + 1;

        matrix[treasure[0]][treasure[1]] = "T";
    }

    public static void checkTreasureCollide() {
        if (treasure[0] - coordinate[0] == 0 && treasure[1] - coordinate[1] == 0) {
            destroyAllEnemies();
            printMatrix();
            win(2);
        }
    }

    public static void lose(int a) {
        //int a: 1 = collide with enemy, 2 = collide with trap
        if (a == 1) {
            System.out.println("You Hit An Enemy! You Died!");
        } else if (a == 2) {
            System.out.println("You Hit A Trap! You Died!");
        } else if (a == 3) {
            System.out.println("You Hit Your Own Bomb! You Died!");
        }
        System.exit(0);
    }

    public static void win(int a) {
        checkBombExplode();
        printMatrix();
        //1 = all enemies killed, 2 = objective collected
        if (a == 1) {
            System.out.println("You Destroyed All Of The Enemies! You Won!");
        } else if (a == 2) {
            System.out.println("You Collected The Treasure! You Won!");
        }
        System.exit(0);
    }
}
