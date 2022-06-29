package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Displayer extends Canvas {

    private int[][] maze;
    int rows, cols;
    private Pair<Integer, Integer> playerPos;
    private Pair<Integer, Integer> goalPos;
    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameFloor = new SimpleStringProperty();
    StringProperty imageFileNameGoal = new SimpleStringProperty();
    StringProperty imageFileNameBackground = new SimpleStringProperty();

    public void drawMaze(int[][] maze){
        this.maze = maze;
        rows = maze.length;
        cols = maze[0].length;
        playerPos = null;
        goalPos = null;
        draw();
    }

    private void draw() {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();

        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
        graphicsContext.setFill(Color.RED);
        drawMaze(graphicsContext, cellHeight, cellWidth, rows, cols);
    }
    private void drawMaze(GraphicsContext graphicsContext, double cellHeight, double cellWidth, int rows, int cols) {
        graphicsContext.setFill(Color.RED);

        Image wallImage = null;
        try{
            wallImage = new Image(new FileInputStream(getImageFileNameWall()));
        } catch (FileNotFoundException e) {
            // TODO: logger
        }
        Image floor = null;
        try{
            floor = new Image(new FileInputStream(getImageFileNameFloor()));
        } catch (FileNotFoundException e) {
            // TODO: logger
        }

        Image player = null;
        try{
            player = new Image(new FileInputStream(getImageFileNamePlayer()));
        } catch (FileNotFoundException e) {
            // TODO: logger
        }

        Image goal = null;
        try{
            goal = new Image(new FileInputStream(getImageFileNameGoal()));
        } catch (FileNotFoundException e) {
            // TODO: logger
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double x = j * cellWidth;
                double y = i * cellHeight;
                switch (maze[i][j]){
                    case 0:
                        if(floor == null)
                            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                        else
                            graphicsContext.drawImage(floor, x, y, cellWidth, cellHeight);
                        break;
                    case 1:
                        if(wallImage == null)
                            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                        else
                            graphicsContext.drawImage(wallImage, x, y, cellWidth, cellHeight);
                        break;
                    case 2:
                        if (playerPos != null){
                            if(floor == null)
                                graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                            else
                                graphicsContext.drawImage(floor, x, y, cellWidth, cellHeight);
                            break;
                        }
                        if(player == null)
                            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                        else
                            graphicsContext.drawImage(player, x, y, cellWidth, cellHeight);
                        break;
                    case 3:
                        goalPos = new Pair<>(i, j);
                        if(goal == null)
                            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                        else
                            graphicsContext.drawImage(goal, x, y, cellWidth, cellHeight);
                        break;

                }
            }
        }
        if (playerPos != null){
            double x = playerPos.getValue() * cellWidth;
            double y = playerPos.getKey() * cellHeight;
            if(player == null)
                graphicsContext.fillRect( x,y, cellWidth, cellHeight);
            else
                graphicsContext.drawImage(player,  x,y, cellWidth, cellHeight);
        }
    }




    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public String imageFileNameWallProperty() {
        return imageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public String imageFileNamePlayerProperty() {
        return imageFileNamePlayer.get();
    }

    public void setImageFileNamePlayer(String imageFileNamePlayer) {
        this.imageFileNamePlayer.set(imageFileNamePlayer);
    }

    public String getImageFileNameFloor() {
        return imageFileNameFloor.get();
    }

    public String imageFileNameFloorProperty() {
        return imageFileNameFloor.get();
    }

    public void setImageFileNameFloor(String imageFileNameFloor) {
        this.imageFileNameFloor.set(imageFileNameFloor);
    }

    public String getImageFileNameGoal() {
        return imageFileNameGoal.get();
    }

    public String imageFileNameGoalProperty() {
        return imageFileNameGoal.get();
    }

    public void setImageFileNameGoal(String imageFileNameGoal) {
        this.imageFileNameGoal.set(imageFileNameGoal);
    }

    public boolean movePlayer(int row, int col) {
        playerPos = new Pair<>(row, col);
        draw();
        if (goalPos != null)
            if (playerPos.equals(goalPos)){
                return true;
            }
        return false;
    }
}
