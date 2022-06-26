package View;

import Model.*;
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
        primaryStage.setTitle("Big Test");
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

//        IModel model = new MyModel();
//        ViewModel viewModel = new ViewModel(model);
//        View view = fxmlLoader.getController();
//        view.setViewModel(viewModel);
//        viewModel.addObserver(view);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
