package View;

import IO.SimpleCompressorOutputStream;
import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import View.Log4J;

import static View.Log4J.LOG;

public class MyViewController implements IView {
    private int[][] maze;
    public Displayer displayer;
    private static MyViewModel viewModel;

    @FXML
    private RadioButton randomMaze, myMazeGen, emptyMaze, bfsChoice, dfsChoice, bestChoice;
    @FXML
    private CheckBox soundCheckBox;
    @FXML
    private TextField threadPoolTextField;

    private enum MazeState {NOTRUNNING, RUNNING, SOLVED};
    private MazeState currentState = MazeState.NOTRUNNING;
    private int numOfSteps;
    private MediaPlayer music = null;
    private MediaPlayer fx = null;

    public Stage propertiesStage = null;
    private boolean mute = false;
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
            LOG.error("Unable to display difficulty selection.", e);
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
        LOG.info("Created a new " + temp.getRowNum() + "x" + temp.getColNum() +" maze.");
        Position pos = temp.getStartPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 2;
        pos = temp.getGoalPosition();
        maze[pos.getRowIndex()][pos.getColumnIndex()] = 3;
        zoom(displayerPane);
        displayer.setHeight(displayerPane.getHeight());
        displayer.setWidth(displayerPane.getWidth());

        displayer.drawMaze(maze);
        currentState = MazeState.RUNNING;
        numOfSteps = 0;
    }


    public void sizeListener(MyViewModel viewModel, Stage stage){
        this.viewModel = viewModel;
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            redraw();
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            redraw();
        });
    }

    public void redraw(){
        if(displayer.getPlayerPos()!=null)
        {
            displayer.setHeight(displayerPane.getHeight());
            displayer.setWidth(displayerPane.getWidth());
            displayer.draw();
        }

    }


    public void zoom( Pane pane) {
        pane.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        if( event.isControlDown()) {
                            double zoomFactor = 1.05;
                            double deltaY = event.getDeltaY();

                            if (deltaY < 0) {
                                zoomFactor = 0.95;
                            }
                            pane.setScaleX(pane.getScaleX() * zoomFactor);
                            pane.setScaleY(pane.getScaleY() * zoomFactor);
                            event.consume();
                            LOG.debug("Zoom level changed.");
                        }
                    }
                });

    }

    //TODO: disable selecting a window like File->Save when a different window is open like File->New


    public void saveButton(javafx.event.ActionEvent actionEvent) {
        //TODO: add usage of viewModel.save(String fileName). If method returns false: error
        String userResult = getUserFileName("Save Maze");
        if (viewModel.save(userResult)){
            successAlert("File saved successfully.");
            LOG.info(String.format("User saved a maze to file \"%n\"",userResult));
        }
        else{
            warningAlert("Unable to save the maze.");
            LOG.warn("User failed to save a maze.");
        }

    }
    public void loadButton(javafx.event.ActionEvent actionEvent) {
        String userResult = getUserFileName("Load Maze");
        if (viewModel.load(userResult)) {
            successAlert("File loaded successfully.");
            LOG.info(String.format("User loaded maze \"%n\"",userResult));
        }
        else {
            warningAlert("Unable to load the maze.");
            LOG.warn("User failed to load a maze.");

        }
    }
    private String getUserFileName(String title){
        TextInputDialog td = new TextInputDialog("Enter the file name...");
        td.setTitle(title);
        td.setHeaderText("File name:");
        td.showAndWait();
        return td.getResult();
    }

    private void successAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void warningAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    //Options menu:
    public void propertiesButton(javafx.event.ActionEvent actionEvent) {
        displayPropertiesSelection();
    }

    private void displayPropertiesSelection() {
        Parent root;
        Stage stage;
        try {
            if(propertiesStage==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Properties.fxml"));
                root = fxmlLoader.load();
                propertiesStage = new Stage();
                propertiesStage.setTitle("Properties Menu");
                propertiesStage.setScene(new Scene(root));
                propertiesStage.show();
            }
            else{
                propertiesStage.show();
            }
            propertiesStage.setOnCloseRequest(e -> propertiesStage.hide());
        } catch (IOException e) {
            LOG.error("Unable to display properties.", e);
            return;
        }
    }

    public void exitButton(javafx.event.ActionEvent actionEvent) {
        LOG.info("Program terminated by user");
        viewModel.stopServers();
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
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm", "\"SimpleMazeGenerator\"");
            LOG.info("SimpleMazeGenerator selected.");
        }
        if (myMazeGen.isSelected()) {
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm", "\"MyMazeGenerator\"");
        }
        if (emptyMaze.isSelected()){
            Configurations.getProp().setProperty("mazeGeneratingAlgorithm", "\"EmptyMazeGenerator\"");
            LOG.info("MyMazeGenerator selected.");
        }
        System.out.println("Current generator is: " + Configurations.getProp().getProperty("mazeGeneratingAlgorithm"));
    }

    public void solvePropertyChoice(javafx.event.ActionEvent event) {
        if (bfsChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "\"BreadthFirstSearch\"");
            LOG.info("BreadthFirstSearch selected.");
        }
        if (dfsChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "\"DepthFirstSearch\"");
            LOG.info("DepthFirstSearch selected.");
        }
        if (bestChoice.isSelected()) {
            Configurations.getProp().setProperty("mazeSearchingAlgorithm", "\"BestFirstSearch\"");
            LOG.info("BestFirstSearch selected.");
        }
        System.out.println("Current searcher is: " + Configurations.getProp().getProperty("mazeSearchingAlgorithm"));

    }

    public void threadPoolButton(javafx.event.ActionEvent actionEvent) {
        if (threadPoolTextField.getText().matches("-?\\d+")) {
            if(Integer.parseInt(threadPoolTextField.getText())>1) {
                Configurations.getProp().setProperty("threadPoolSize", threadPoolTextField.getText());
                LOG.info("Threadpool size changed.");
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong number of threads");
        alert.setHeaderText(null);
        alert.setContentText("Please input numbers above 1 in the threads text field.");
        alert.showAndWait();

        System.out.println("Current threadpool size is: " + Configurations.getProp().getProperty("threadPoolSize"));
    }


    public void soundBox(javafx.event.ActionEvent event) {
        mute = soundCheckBox.isSelected();
        LOG.info("Mute: " + mute);

        if (mute)
            stopsounds();
    }

    private void stopsounds() {
        if (music != null)
            music.setMute(true);
        if (fx != null)
            fx.setMute(true);
    }



    public void keyPressed(String keyEvent) {
        if (currentState != MazeState.RUNNING)
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
                currentState = MazeState.SOLVED;
            }
        }
        else {
            playStuck();
        }
    }



    private void showScore(){
        LOG.info(String.format("User solved maze. Highscore: %s.", numOfSteps));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maze Solved");
        alert.setHeaderText("You won! You managed to reach the BFG9000!");
        alert.setContentText("It only took you " + numOfSteps + " steps until you reached the ending!\nCan you do better?");
        alert.showAndWait();
    }

    private void playStuck() {
        if (fx != null)
            fx.stop();

        String path = "resources/Music/hitwall.mp3";
        Media media = new Media(new File(path).toURI().toString());
        fx = new MediaPlayer(media);
        fx.setVolume(1);
        fx.setAutoPlay(true);
        fx.setMute(mute);
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
        music.setMute(mute);
        music.play();
    }

    public void playTheme(){
        if (currentState == MazeState.RUNNING)
            return;
        if (music != null)
            music.stop();
        String path = "resources/Music/At_Dooms_Gate.mp3";
        Media media = new Media(new File(path).toURI().toString());
        music = new MediaPlayer(media);
        music.setVolume((0.3));
        music.setAutoPlay(true);

        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.setMute(mute);
        music.play();

    }

    public void solveButton(ActionEvent actionEvent) {
        LOG.info("User requested solution.");
        if (currentState == MazeState.NOTRUNNING)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Solve");
            alert.setHeaderText(null);
            alert.setContentText("There's no maze yet.\nCreate a maze with File->New, or load your maze with File->Load.");
            alert.showAndWait();
            return;
        }

        if (currentState == MazeState.SOLVED)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Solve");
            alert.setHeaderText(null);
            alert.setContentText("You already solved the maze! We can't help you against hell itself.");
            alert.showAndWait();
            return;
        }

        Pair<Integer,Integer> player = displayer.getPlayerPos();
        Pair<Integer,Integer>[] solution = viewModel.getSolution(player);
        displayer.drawSolution(solution);
    }
}
