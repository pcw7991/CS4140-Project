// Authors: Powell

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.embed.swing.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.util.Scanner;
import javafx.stage.Modality;


public class ToadsAndFrogs extends Application {

    private String gameTitle = "Amphibian Battle!";
    private int teamDesignator;
    private Stage lightedStage;
    private String[] BoxesOfTheBoard;
    private boolean frogWinFlag = false;
    private boolean toadWinFlag = false;

    Image toadImg = new Image("https://i.imgur.com/8v9Wtit.png");

    Image frogImg = new Image("https://i.imgur.com/de44o56.png");

    Image whiteImg = new Image("https://i.imgur.com/lP0lAFe.png");



    public static void main(String[] args) {
        launch(args);
    }

    public int firstPlayer(){
        Stage firstPlayer = new Stage();
        firstPlayer.setX(850);
        firstPlayer.setY(100);
        firstPlayer.initModality(Modality.APPLICATION_MODAL);
        firstPlayer.setTitle("First Move");
        firstPlayer.setMinWidth(250);

        Label l = new Label();
        l.setText("Choose your First Player");


        Button toadButton = new Button("Toad");
        toadButton.setOnAction((event) -> {
            teamDesignator = 0;
            firstPlayer.close();
        });

        Button frogButton = new Button("Frog");
        frogButton.setOnAction((event) -> {
            teamDesignator = 1;
            firstPlayer.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(l, toadButton, frogButton);
        layout.setAlignment(Pos.CENTER);

        Scene playerSelect = new Scene(layout);
        firstPlayer.setScene(playerSelect);
        firstPlayer.show();

        return teamDesignator;
    }

    private void frogsWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Team Has Won");
        alert.setHeaderText("Frogs Win!");
        alert.showAndWait();
    }
    private void toadsWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Team Has Won");
        alert.setHeaderText("Toads Win!");
        alert.showAndWait();
    }

    public void start(Stage primaryStage) {

            teamDesignator = firstPlayer();
            primaryStage.setHeight(200);
            primaryStage.setWidth(1015);

            lightedStage = primaryStage;

            this.BoxesOfTheBoard = new String[10];

            for (int space = 0; space < 10; ) {
                BoxesOfTheBoard[space] = "    ";
                space = space + 1;
            }

            this.BoxesOfTheBoard[0] = "Toad";
            BoxesOfTheBoard[3] = "Toad";
            this.BoxesOfTheBoard[4] = "Toad";
            BoxesOfTheBoard[2] = "Frog";
            this.BoxesOfTheBoard[7] = "Frog";
            BoxesOfTheBoard[9] = "Frog";

            primaryStage.setTitle(this.gameTitle);
            gameBoard(this.lightedStage);
    }


    public void gameBoard(Stage stage) {
            String[] teams = {
                    "Toad", "Frog"
            };
            lightedStage.setTitle("Amphibian Battle!  " + teams[teamDesignator] + "'s Turn.");
            this.lightedStage = stage;

            HBox hbox = new HBox(10);

            for (int boxPlacememt = 0; boxPlacememt < 10; boxPlacememt++) {
                String teamName = BoxesOfTheBoard[boxPlacememt];
                Button pane = new Button();

                if (teamName.equals("Toad")) {
                    ImageView toadImgView = new ImageView(toadImg);
                    pane.setGraphic(toadImgView);
                } else if (teamName.equals("Frog")) {
                    ImageView frogImgView = new ImageView(frogImg);
                    pane.setGraphic(frogImgView);
                } else {
                    ImageView whiteImgView = new ImageView(whiteImg);
                    pane.setGraphic(whiteImgView);
                }

                hbox.getChildren().add(pane);
                if (teamName.equals("Toad")) {
                    toadMove(pane, boxPlacememt);
                } else if (teamName.equals("Frog")) {
                    frogMove(pane, boxPlacememt);
                }
            }


            VBox vbox = new VBox(10);
            vbox.getChildren().add(hbox);
            vbox.getChildren().add(new Label("If you can't move, then you lose the game."));

            ScrollPane boardSize = new ScrollPane(vbox);
            stage.setScene(new Scene(boardSize, 1015, 200));
            stage.show();

            // This condenses buttons when window width is too small; looks better
            boardSize.setFitToWidth(true);
    }


    public void teamSwitch(Stage stage) {
        this.teamDesignator = 1 - this.teamDesignator;
        this.gameBoard(stage);
    }



    public void toadMove(Button toadButton, int toadSpace) {
        toadButton.setOnAction( (event) -> {
            System.out.println("Toad selected!");
            if (this.teamDesignator == 1) {
                return;
            }
            if (toadSpace <= 8 && this.BoxesOfTheBoard[toadSpace + 1] == "    ") {
                this.BoxesOfTheBoard[toadSpace] = "    ";
                this.BoxesOfTheBoard[toadSpace + 1] = "Toad";
                teamSwitch(lightedStage);
            } else if (toadSpace <= 7 && this.BoxesOfTheBoard[toadSpace + 1] == "Frog" && this.BoxesOfTheBoard[toadSpace + 2] == "    ") {
                this.BoxesOfTheBoard[toadSpace] = "    ";
                this.BoxesOfTheBoard[toadSpace + 2] = "Toad";
                teamSwitch(lightedStage);
            } else {
                for (int checkWin = 0; checkWin < 9;) {
                    if (this.BoxesOfTheBoard[checkWin] == "Toad" && this.BoxesOfTheBoard[checkWin + 1] == "    ") {
                        frogWinFlag = false;
                        break;
                    } else if (this.BoxesOfTheBoard[checkWin + 1] == "Frog" && this.BoxesOfTheBoard[checkWin + 2] == "    ") {
                        frogWinFlag = false;
                    } else {
                        frogWinFlag = true;
                    }
                    checkWin = checkWin + 1;
                }

            }
            if (frogWinFlag == true){
                frogsWin();
            }
        });
    }



    public void frogMove(Button frogButton, int frogSpace) {
        frogButton.setOnAction((event) -> {
            System.out.println("Frog selected!");
            if (this.teamDesignator == 0) {
                return;
            }
            if (frogSpace > 0 && this.BoxesOfTheBoard[frogSpace-1] == "    ") {
                this.BoxesOfTheBoard[frogSpace] = "    ";
                this.BoxesOfTheBoard[frogSpace-1] = "Frog";
                teamSwitch(lightedStage);
            } else if (frogSpace > 1 && this.BoxesOfTheBoard[frogSpace-2] == "    " && this.BoxesOfTheBoard[frogSpace-1] == "Toad") {
                this.BoxesOfTheBoard[frogSpace] = "    ";
                this.BoxesOfTheBoard[frogSpace-2] = "Frog";
                teamSwitch(this.lightedStage);
            } else {
                for (int checkWin = 9; checkWin > 0;){
                    if (this.BoxesOfTheBoard[checkWin] == "Frog" && this.BoxesOfTheBoard[checkWin - 1] == "    ") {
                        toadWinFlag = false;
                        break;
                    } else if (this.BoxesOfTheBoard[checkWin - 1] == "Toad" && this.BoxesOfTheBoard[checkWin - 2] == "    ") {
                        toadWinFlag = false;
                    } else {
                        toadWinFlag = true;
                    }
                    checkWin = checkWin - 1;
                }

            }
            if (toadWinFlag == true){
                toadsWin();
            }
        });
    }



} //end of ToadsAndFrogs.java
