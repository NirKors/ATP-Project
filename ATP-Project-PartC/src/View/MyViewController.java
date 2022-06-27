package View;

import ViewModel.MyViewModel;

public class MyViewController implements IView {

    //TODO: Link all buttons
    private int[][] maze;
    private Displayer displayer;
    private MyViewModel vModel;

    public void newButton(javafx.event.ActionEvent actionEvent) {

    }


    public void setViewModel(MyViewModel viewModel) {
        vModel = viewModel;
    }
}
