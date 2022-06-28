package View;

import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyViewController implements IView {
    //TODO:

    private int[][] maze;

    public Displayer displayer;

    private static MyViewModel viewModel;

    @FXML
    private RadioButton randomMaze, myMazeGen, bfsChoice, dfsChoice, bestChoice;
    @FXML
    private CheckBox soundCheckBox;
    @FXML
    private TextField threadPoolTextField;
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
    public void propertiesButton(javafx.event.ActionEvent actionEvent){
        displayPropertiesSelection();
    }

    private void displayPropertiesSelection() {
        Parent root;
        Stage stage;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Properties.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Properties Menu");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            // TODO: add to logger.
            e.printStackTrace();
            return;
        }
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

    //Properties:
    public void mazeCreatePropertyChoice(javafx.event.ActionEvent event){
        if(randomMaze.isSelected()){
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm","SimpleMazeGenerator");
        }
        if(myMazeGen.isSelected()){
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm","MyMazeGenerator");
        }
    }

    public void solvePropertyChoice(javafx.event.ActionEvent event){
        if(bfsChoice.isSelected()){
            Configurations.getProp().setProperty("mazeSearchingAlgorithm","BreadthFirstSearch");
        }
        if(dfsChoice.isSelected()){
            Configurations.getProp().setProperty("mazeSearchingAlgorithm","DepthFirstSearch");
        }
        if(bestChoice.isSelected()){
            Configurations.getProp().setProperty("mazeSearchingAlgorithm","BestFirstSearch");
        }

    }

    public void threadPoolButton(javafx.event.ActionEvent actionEvent) {
        if(threadPoolTextField.getText().matches("-?\\d+")) {
            Configurations.getProp().setProperty("threadPoolSize", threadPoolTextField.getText());
        }
    }


    public void soundBox(javafx.event.ActionEvent event){
        if(soundCheckBox.isSelected()){
            System.out.println("sounds ON");
        } else {
            System.out.println("sounds OFF");
        }
    }


}
