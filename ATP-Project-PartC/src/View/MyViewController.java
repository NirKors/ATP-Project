package View;

import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyViewController implements IView {


    //TODO:

    private int[][] maze;

    private static Displayer displayer;

    private static MyViewModel viewModel;


    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void display(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Change title");
            stage.setScene(new Scene(root));
            stage.show();
            displayer = new Displayer();
        }
        catch (IOException e){
            // TODO: add to logger.
            System.out.println(e.getMessage());
        }
    }



    //File menu:
    public void newButton(javafx.event.ActionEvent actionEvent){
        displayDifficultySelection();
    }

    private void displayDifficultySelection() {
        Parent root;
        Stage stage;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DifficultyMenu.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Select Difficulty");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            // TODO: add to logger.
            e.printStackTrace();
            return;
        }
    }

    public void drawMaze(int row, int col){
        viewModel.generateMaze(row, col);
        Maze temp = viewModel.getMaze();

        maze = new int[temp.getRowNum()][temp.getColNum()];
        for (int i = 0; i < temp.getRowNum(); i++) {
            for (int j = 0; j < temp.getColNum(); j++) {
                maze[i][j] = temp.getVal(i, j);
            }
        }
        Position pos = temp.getStartPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 2;
        pos = temp.getGoalPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 3;


        displayer.drawMaze(maze);

    }

    //TODO: should close after valid selection.
    public void tooYoungDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("tooYoungDifficulty");
        drawMaze(5, 5);

    }

    public void hurtMeDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("hurtMeDifficulty");

    }

    public void nightmareDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("nightmareDifficulty");

    }

    public void choiceDifficulty(javafx.event.ActionEvent actionEvent) { // TODO: Lock button when text boxes are empty.
        System.out.println("choiceDifficulty");
    }


    public void loadButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("load");
    }

    public void saveButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("save");
    }
    //Options menu:
    public void propertiesButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("properties");
    }

    //TODO: Following buttons don't work.
    public void exitButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("exit");
    }

    public void helpButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("help");
    }

    public void aboutButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("about");
    }
}
