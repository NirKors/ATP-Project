package View;

import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

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

    public void setViewModel(MyViewModel viewModel, Scene scene) {
        this.viewModel = viewModel;
        scene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            keyPressed(codeString);
        });
    }


    //File menu:
    public void newButton(javafx.event.ActionEvent actionEvent) {
        displayDifficultySelection();
    }

    private void displayDifficultySelection() {
        Parent root;
        Stage stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DifficultyMenu.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Select Difficulty");
            stage.setScene(new Scene(root));
            stage.show();

            DifficultyController dcontrol = fxmlLoader.getController();
            dcontrol.setParent(this, stage);
        } catch (IOException e) {
            // TODO: add to logger.
            e.printStackTrace();
            return;
        }
    }

    public void drawMaze(int row, int col) {
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

        displayer.setHeight(300);
        displayer.setWidth(300);
        displayer.drawMaze(maze);

    }




    public void loadButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("load");
    }

    public void saveButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("save");
    }

    //Options menu:
    public void propertiesButton(javafx.event.ActionEvent actionEvent) {
        displayPropertiesSelection();
    }

    private void displayPropertiesSelection() {
        Parent root;
        Stage stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Properties.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Properties Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            // TODO: add to logger.
            e.printStackTrace();
            return;
        }
    }

    public void exitButton(javafx.event.ActionEvent actionEvent) {
        Platform.exit();
        //TODO: wtf is a safe exit, and do we need to close the server streams as well?
    }

    public void helpButton(javafx.event.ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("Hell's forces are loose on Mars!\nUse the numpad keys to move around and find the BFG9000!\nLook for its green plasma glow.");
        alert.showAndWait();
    }

    public void aboutButton(javafx.event.ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Hell's forces were let loose by\nCyberdemon: Daniel Zaslavsky\nSpider Mastermind: Nir Korsunsky");
        alert.showAndWait();
    }

    //Properties:
    public void mazeCreatePropertyChoice(javafx.event.ActionEvent event) {
        if (randomMaze.isSelected()) {
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm", "SimpleMazeGenerator");
        }
        if (myMazeGen.isSelected()) {
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        }
        System.out.println("Current generator is: " + Configurations.getProp().getProperty("mazeGeneratingAlgorithm"));
    }

    public void solvePropertyChoice(javafx.event.ActionEvent event) {
        if (bfsChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "BreadthFirstSearch");
        }
        if (dfsChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "DepthFirstSearch");
        }
        if (bestChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
        }
        System.out.println("Current searcher is: " + Configurations.getProp().getProperty("mazeSearchingAlgorithm"));

    }

    public void threadPoolButton(javafx.event.ActionEvent actionEvent) {
        if (threadPoolTextField.getText().matches("-?\\d+")) {
            Configurations.getProp().setProperty("threadPoolSize", threadPoolTextField.getText());
        }

        System.out.println("Current threadpool size is: " + Configurations.getProp().getProperty("threadPoolSize"));
    }


    public void soundBox(javafx.event.ActionEvent event) {
        if (soundCheckBox.isSelected()) {
            System.out.println("sounds ON");
        } else {
            System.out.println("sounds OFF");
        }
    }


    public void keyPressed(String keyEvent) {
        System.out.println("keyPressed: " + keyEvent);
        boolean success;
        switch (keyEvent) {
            case "NUMPAD2" -> success = viewModel.movePlayer("DOWN");
            case "NUMPAD4" -> success = viewModel.movePlayer("LEFT");
            case "NUMPAD6" -> success = viewModel.movePlayer("RIGHT");
            case "NUMPAD8" -> success = viewModel.movePlayer("UP");
            case "NUMPAD1" -> {
                success = viewModel.movePlayer("DOWN");
                keyPressed("LEFT");
            }
            case "NUMPAD3" -> {
                success = viewModel.movePlayer("RIGHT");
                keyPressed("DOWN");
            }
            case "NUMPAD7" -> {
                success = viewModel.movePlayer("LEFT");
                keyPressed("UP");
            }
            case "NUMPAD9" -> {
                success = viewModel.movePlayer("UP");
                keyPressed("RIGHT");
            }
            default -> {return;}
        }
        if (!success){
            // TODO: play player sound
        }
    }
}
