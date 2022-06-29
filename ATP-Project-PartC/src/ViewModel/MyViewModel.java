package ViewModel;

import Model.IModel;
import View.IView;
import View.MyViewController;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

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
            }
        } catch (UnsupportedOperationException e) { return false;}
        return true;
    }
}
