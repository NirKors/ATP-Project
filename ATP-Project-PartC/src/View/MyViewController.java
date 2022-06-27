package View;

import ViewModel.MyViewModel;

import java.awt.event.ActionEvent;

public class MyViewController implements IView {

    //TODO:

    private int[][] maze;
    private Displayer displayer;
    private MyViewModel vModel;

    public void setViewModel(MyViewModel viewModel) {
        vModel = viewModel;
    }




    //File menu:
    public void newButton(javafx.event.ActionEvent actionEvent) {

    }

    public void loadButton(javafx.event.ActionEvent actionEvent) {

    }

    public void saveButton(javafx.event.ActionEvent actionEvent) {

    }
    //Options menu:
    public void propertiesButton(javafx.event.ActionEvent actionEvent) {

    }

    public void exitButton(javafx.event.ActionEvent actionEvent) {

    }

    public void helpButton(javafx.event.ActionEvent actionEvent) {

    }

    public void aboutButton(javafx.event.ActionEvent actionEvent) {

    }
}
