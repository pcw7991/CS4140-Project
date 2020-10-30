import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class BoardSize{
    public static int size;

    public static int boardSize(){
        //int size = 0;
        Stage boardSizeWindow = new Stage();
        boardSizeWindow.setTitle("BoardSize");
        boardSizeWindow.setWidth(250);
        Label text = new Label("Select the Board Size");

        ToggleGroup group = new ToggleGroup();

        RadioButton ten = new RadioButton("10");
        ten.setToggleGroup(group);

        RadioButton twelve = new RadioButton("12");
        twelve.setToggleGroup(group);

        RadioButton fifteen = new RadioButton("15");
        fifteen.setToggleGroup(group);

        Button submit = new Button("Submit");
        submit.setOnAction((event) -> {
            if(ten.isSelected()){
                size = 10;
                boardSizeWindow.close();

            }
            else if(twelve.isSelected()){
                size = 12;
                boardSizeWindow.close();
            }
            else{
                size = 15;
                boardSizeWindow.close();
            }

        });


        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(text, ten, twelve, fifteen, submit);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout);
        boardSizeWindow.setScene(scene);
        boardSizeWindow.showAndWait();
        return size;

    }

}