package ViewModel;

import Model.IModel;
import View.IView;
import View.MyViewController;

public class MyViewModel {

    private IModel model;
    public MyViewModel(IModel model) {
        this.model = model;
    }

    public void generateMaze(int rows, int cols){

    }

    public void loadMaze(){

    }

    public int[][] getMazeArray(){

        return null;
    }

    public void addObserver(MyViewController viewController) {
    }
}
