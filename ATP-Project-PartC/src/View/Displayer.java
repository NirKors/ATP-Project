package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.scene.canvas.Canvas;

public class Displayer extends Canvas {

    private int[][] maze;
    private Pair<Integer, Integer> player;

    public void drawMaze(int[][] maze){
        this.maze = maze;
        draw();
    }

    private void draw() {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        int row = maze.length;
        int col = maze[0].length;
        double cellHeight = canvasHeight / row;
        double cellWidth = canvasWidth / col;

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
        graphicsContext.setFill(Color.RED);
    }
}
