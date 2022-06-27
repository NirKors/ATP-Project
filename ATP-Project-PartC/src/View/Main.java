package View;

import Model.*;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Big Test"); //TODO: change title
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();



        IModel model = new MyModel();
        model.connectGenerator(5400, 1000, new ServerStrategyGenerateMaze());
        model.connectSolver(5401, 1000, new ServerStrategySolveSearchProblem());

        MyViewModel viewModel = new MyViewModel(model);


        MyViewController viewController = fxmlLoader.getController();
        viewModel.addObserver(viewController);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
