package View;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

public class DifficultyController implements IView{

    //TODO: should close after valid selection.
    private int row, col;

    public TextField rowsText;
    public TextField colsText;

    private MyViewController myviewc;
    private Stage stage;

    public void tooYoungDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("tooYoungDifficulty");
        row = 5;
        col = 5;
        sendback(row, col);

    }

    public void hurtMeDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("hurtMeDifficulty");
        row = 10;
        col = 10;
        sendback(row, col);
    }

    public void nightmareDifficulty(javafx.event.ActionEvent actionEvent) {
        System.out.println("nightmareDifficulty");
        row = 20;
        col = 20;
        sendback(row, col);
    }

    public void choiceDifficulty(javafx.event.ActionEvent actionEvent) { // TODO: Lock button when text boxes are empty.
        System.out.println("choiceDifficulty");
        row = Integer.parseInt(rowsText.getText());
        col = Integer.parseInt(colsText.getText());
        sendback(row, col);

        //TODO: catch error or disable button
    }

    public void sendback(int row, int col){
        stage.close();
        myviewc.drawMaze(row, col);

    }

    public void setParent(MyViewController myViewController, Stage stage) {
        myviewc = myViewController;
        this.stage = stage;
    }
}
