package ViewModel;

import Model.IModel;
import View.IView;
import View.MyViewController;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import javafx.util.Pair;

public class MyViewModel {

    private IModel model;

    public MyViewModel(IModel model) {
        this.model = model;
    }

    public void generateMaze(int rows, int cols) {
        model.generateRandomMaze(rows, cols);
    }


    public Maze getMaze() {
        return model.getMaze();
    }

    public boolean movePlayer(String direction) {
        try {
            switch (direction) {
                case "UP" -> model.updateCharacterLocation(1);
                case "DOWN" -> model.updateCharacterLocation(2);
                case "LEFT" -> model.updateCharacterLocation(3);
                case "RIGHT" -> model.updateCharacterLocation(4);
                case "DOWNLEFT" -> model.updateCharacterLocation(5);
                case "DOWNRIGHT" -> model.updateCharacterLocation(6);
                case "UPLEFT" -> model.updateCharacterLocation(7);
                case "UPRIGHT" -> model.updateCharacterLocation(8);
            }
        } catch (UnsupportedOperationException e) { return false;}
        return true;
    }

    public Pair<Integer, Integer> getPlayerLocation() {
        Position pos = model.getPlayer();

        return new Pair<Integer, Integer>(pos.getRowIndex(), pos.getColumnIndex());
    }
}
