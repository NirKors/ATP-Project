package View;

import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

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
    private boolean lockkeys = true;

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

    public Pane displayerPane;
    public void drawMaze(int row, int col) {
        viewModel.generateMaze(row, col);
        Maze temp = viewModel.getMaze();

        maze = new int[temp.getRowNum()][temp.getColNum()];
        for (int i = 0; i < temp.getRowNum(); i++) {
            for (int j = 0; j < temp.getColNum(); j++) {
                maze[i][j] = temp.getVal(i, j);
            }
        }
        playTheme();
        Position pos = temp.getStartPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 2;
        pos = temp.getGoalPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 3;

        displayer.setHeight(displayerPane.getHeight());
        displayer.setWidth(displayerPane.getWidth());

        displayer.drawMaze(maze);
        lockkeys = false;
    }

    public void redraw(){
        displayer.setHeight(displayerPane.getHeight());
        displayer.setWidth(displayerPane.getWidth());
        displayer.draw();
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
        System.exit(0);
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


    int numOfSteps = 0;
    public void keyPressed(String keyEvent) {
        if (lockkeys || maze == null)
            return;

        boolean success;
        switch (keyEvent) {
            case "NUMPAD2" -> success = viewModel.movePlayer("DOWN");
            case "NUMPAD4" -> success = viewModel.movePlayer("LEFT");
            case "NUMPAD6" -> success = viewModel.movePlayer("RIGHT");
            case "NUMPAD8" -> success = viewModel.movePlayer("UP");
            case "NUMPAD1" -> success = viewModel.movePlayer("DOWNLEFT");
            case "NUMPAD3" -> success = viewModel.movePlayer("DOWNRIGHT");
            case "NUMPAD7" -> success = viewModel.movePlayer("UPLEFT");
            case "NUMPAD9" -> success = viewModel.movePlayer("UPRIGHT");
            default -> {return;}
        }
        if (success){
            numOfSteps++;
            Pair<Integer, Integer> pair = viewModel.getPlayerLocation();
            if (displayer.movePlayer(pair.getKey(), pair.getValue())){
                //reached goal
                playEnding();
                showScore();
                //TODO: now what
            }
        }
        else {
            playStuck();
        }
    }

    private MediaPlayer music;
    private MediaPlayer fx;

    private void showScore(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maze Solved");
        alert.setHeaderText("You won! You managed to reach the BFG9000!");
        alert.setContentText("It only took you " + numOfSteps + " steps until you reached the ending!\nCan you do better?");
        alert.showAndWait();
        lockkeys = true;
    }

    private void playStuck() {
        if (fx != null)
            fx.stop();

        String path = "resources/Music/hitwall.mp3";
        Media media = new Media(new File(path).toURI().toString());
        fx = new MediaPlayer(media);
        fx.setVolume(1);
        fx.setAutoPlay(true);
        fx.play();
    }

    private void playEnding() {
        if (fx != null)
            fx.stop();

        String path = "resources/Music/goalreached.mp3";
        Media media = new Media(new File(path).toURI().toString());
        fx = new MediaPlayer(media);
        fx.setVolume(1);
        fx.setAutoPlay(true);
        fx.play();


        if (music != null)
            music.stop();
        path = "resources/Music/Intermission.mp3";
        media = new Media(new File(path).toURI().toString());
        music = new MediaPlayer(media);
        music.setVolume(0.3);
        music.setAutoPlay(true);

        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.play();
    }

    public void playTheme(){
        if (music != null)
            music.stop();
        String path = "resources/Music/At_Dooms_Gate.mp3";
        Media media = new Media(new File(path).toURI().toString());
        music = new MediaPlayer(media);
        music.setVolume(0.3);
        music.setAutoPlay(true);

        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.play();

    }

    public void solveButton(ActionEvent actionEvent) {
        if (maze == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Solve");
            alert.setHeaderText(null);
            alert.setContentText("There's no maze yet.\nCreate a maze with File->New, or load your maze with File->Load.");
            alert.showAndWait();
            return;
        }

        Pair<Integer,Integer> player = displayer.getPlayerPos();
        Pair<Integer,Integer>[] solution = viewModel.getSolution(player);
        displayer.drawSolution(solution);
    }
}
