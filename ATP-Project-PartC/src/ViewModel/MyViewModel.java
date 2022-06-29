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

    public void generateMaze(int rows, int cols){
        model.generateRandomMaze(rows, cols);
    }

    public void loadMaze(){

    }

    public void addObserver(MyViewController viewController) {
    }

    public Maze getMaze() {
        return model.getMaze();
    }
}
