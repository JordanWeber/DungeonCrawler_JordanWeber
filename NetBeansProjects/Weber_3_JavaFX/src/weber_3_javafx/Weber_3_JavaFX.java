/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weber_3_javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 *
 * @author JordanWeber
 */
public class Weber_3_JavaFX extends Application {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    private void drawShapes(GraphicsContext gc) {
//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);
//        gc.strokeLine(40,10,10,40);
//        gc.fillOval(10, 60, 30, 30);
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 166, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40}, new double[]{210, 210, 240, 240}, 4);
//        gc.fillPolygon(new double[]{60, 90, 60, 90}, new double[]{210, 210, 240, 240}, 4);
//        gc.fillPolygon(new double[]{110, 140, 110, 140}, new double[]{210, 210, 240, 240}, 4);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeLine(200, 300, 200, 150);
        gc.strokeLine(400, 300, 400, 150);
        gc.fillPolygon(new double[]{180,420,300}, new double[]{150,150,100}, 3);
        gc.strokeLine(200, 300, 400, 300);
        gc.fillPolygon(new double[]{285,285,315,315}, new double[]{300,260,260,300}, 4);
        gc.strokeLine(240, 225, 260, 225);
        gc.strokeLine(240, 200, 260, 200);
        gc.strokeLine(240, 225, 240, 200);
        gc.strokeLine(260, 225, 260, 200);
        gc.strokeLine(250, 225, 250, 200);
        gc.strokeLine(240, 212.5, 260, 212.5);
        gc.setFill(Color.GREY);
        gc.setStroke(Color.GREY);
        gc.fillPolygon(new double[]{0,600,600,0}, new double[]{475,475,375,375}, 4);
        gc.setFill(Color.GREY);
        gc.fillPolygon(new double[]{401,425,425,475,475,401}, new double[]{250,250,375,375,200,200}, 6);
       
    }
}
    
