// Authors: Powell

import javafx.application.Application;
import javafx.geometry.*;
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
import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.io.FileNotFoundException;

public class ToadsAndFrogs extends Application {

    private String gameTitle = "Amphibian Battle!";
    private int teamDesignator;
    private Stage lightedStage;
    private static String[] BoxesOfTheBoard;
    private boolean frogWinFlag = false;
    private boolean toadWinFlag = false;
    public boolean gameLoaded = false;
    int sizeOfBoard = BoardSize.boardSize();;
    File saveFile10 = new File("saveGame10.txt");
    File saveFile12 = new File("saveGame12.txt");
    File saveFile15 = new File("saveGame15.txt");

    Image toadImg = new Image("https://i.imgur.com/8v9Wtit.png");

    Image frogImg = new Image("https://i.imgur.com/de44o56.png");

    Image whiteImg = new Image("https://i.imgur.com/lP0lAFe.png");

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        // Sets the height and width of the stage
        stageDimensions(primaryStage);

        lightedStage = primaryStage;

        // Asks the user to select the game board size
        boxesOfTheBoard(sizeOfBoard);

        // Loads the previously saved game
        loadGame();

        primaryStage.setTitle(this.gameTitle);
        gameBoard(this.lightedStage);

        // Prompts the user to select either Toad or Frog to move first
        teamDesignator = firstPlayer();

    }

    // This method creates the BoxesOfTheBoard[] according to the size of game board
    // provided by the user
    public static void boxesOfTheBoard(int size) {

        if (size == 10) {
            BoxesOfTheBoard = new String[] { "Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                    "Frog" };
        } else if (size == 12) {
            BoxesOfTheBoard = new String[] { "Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                    "Toad", "    ", "Frog" };
        } else {
            BoxesOfTheBoard = new String[] { "Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                    "    ", "Toad", "Frog", "    ", "Frog", "Frog" };
        }

    } // End boxesOfTheBoard

    // Prompts the user to load the previous game or not and sets the game board.
    public void loadGame() {
        Stage loadGameWindow = new Stage();
        loadGameWindow.setTitle("Load Game");
        loadGameWindow.setWidth(250);
        Label text = new Label("Would you like to load previous game?");

        ToggleGroup group = new ToggleGroup();

        RadioButton yes = new RadioButton("Yes");
        yes.setToggleGroup(group);

        RadioButton no = new RadioButton("No");
        no.setToggleGroup(group);

        Button submit = new Button("Submit");
        submit.setOnAction((event) -> {
            String read = "";
            if (yes.isSelected()) {
                gameLoaded = true;
                if (sizeOfBoard == 10) {
                    try (Scanner s = new Scanner(new File("saveGame10.txt")).useDelimiter(",\\s*")) {
                        while (s.hasNext()) {

                            for (int i = 0; i < sizeOfBoard; i++) {
                                read = s.next();
                                if (read.equals("Toad") || read.equals("Frog"))
                                    BoxesOfTheBoard[i] = read;
                                else
                                    BoxesOfTheBoard[i] = "    ";
                            }
                        }
                    } catch (FileNotFoundException e) {
                    }
                    loadGameWindow.close();
                } else if (sizeOfBoard == 12) {
                    try (Scanner s = new Scanner(new File("saveGame12.txt")).useDelimiter(",\\s*")) {
                        while (s.hasNext()) {

                            for (int i = 0; i < sizeOfBoard; i++) {
                                read = s.next();
                                if (read.equals("Toad") || read.equals("Frog"))
                                    BoxesOfTheBoard[i] = read;
                                else
                                    BoxesOfTheBoard[i] = "    ";
                            }
                        }
                    } catch (FileNotFoundException e) {
                    }
                    loadGameWindow.close();
                } else if (sizeOfBoard == 15) {
                    try (Scanner s = new Scanner(new File("saveGame15.txt")).useDelimiter(",\\s*")) {
                        while (s.hasNext()) {

                            for (int i = 0; i < sizeOfBoard; i++) {
                                read = s.next();
                                if (read.equals("Toad") || read.equals("Frog"))
                                    BoxesOfTheBoard[i] = read;
                                else
                                    BoxesOfTheBoard[i] = "    ";
                            }
                        }
                    } catch (FileNotFoundException e) {
                    }
                    loadGameWindow.close();
                }
            }
            if (no.isSelected()) {
                // boxesOfTheBoard(sizeOfBoard);
                loadGameWindow.close();
            }
        });

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(text, yes, no, submit);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout);
        loadGameWindow.setScene(scene);
        loadGameWindow.showAndWait();
    } // End loadGame

    // This method sets the height and width of the stage according to the size of
    // game board.
    public void stageDimensions(Stage stage) {
        if (sizeOfBoard == 10) {
            stage.setHeight(250);
            stage.setWidth(1030);
        } else if (sizeOfBoard == 12) {
            stage.setHeight(250);
            stage.setWidth(1240);
        } else {
            stage.setHeight(250);
            stage.setWidth(1530);
        }
    } // End stageDimensions

    // This method prompts the user to choose the first player to start with
    public int firstPlayer() {
        if (gameLoaded == false) {
            Stage firstPlayer = new Stage();
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
        }
        return teamDesignator;
    }

    // This method display alert box when the Frog wins game
    private void frogsWin() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Team Has Won");
        alert.setHeaderText("Frogs Win!");
        alert.showAndWait();
        System.out.println("Frogs Win");
    }

    // This method display alert box when the Toad wins game
    private void toadsWin() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Team Has Won");
        alert.setHeaderText("Toads Win!");
        alert.showAndWait();
        System.out.println("Toad Win");
    }

    public void gameBoard(Stage stage) {
        String[] teams = { "Toad", "Frog" };
        lightedStage.setTitle("Amphibian Battle!  " + teams[teamDesignator] + "'s Turn.");
        this.lightedStage = stage;

        HBox hbox = new HBox(10);

        for (int boxPlacememt = 0; boxPlacememt < sizeOfBoard; boxPlacememt++) {
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

        // This method saves the game by storing the BoxesOfTheBoard[] into a file
        Button saveAndQuit = new Button("Save & Quit");
        saveAndQuit.setOnAction((event) -> {
            try {
                if (sizeOfBoard == 10) {
                    File saveFile10 = new File("saveGame10.txt");
                    FileWriter newSave = new FileWriter("saveGame10.txt");
                    for (int i = 0; i < sizeOfBoard; i++) {
                        // System.out.println(BoxesOfTheBoard[i]);
                        newSave.write(BoxesOfTheBoard[i] + ",\n");
                    }

                    newSave.close();
                } else if (sizeOfBoard == 12) {
                    File saveFile12 = new File("saveGame12.txt");
                    FileWriter newSave = new FileWriter("saveGame12.txt");
                    for (int i = 0; i < sizeOfBoard; i++) {
                        // System.out.println(BoxesOfTheBoard[i]);
                        newSave.write(BoxesOfTheBoard[i] + ",\n");
                    }

                    newSave.close();
                } else if (sizeOfBoard == 15) {
                    File saveFile15 = new File("saveGame15.txt");
                    FileWriter newSave = new FileWriter("saveGame15.txt");
                    for (int i = 0; i < sizeOfBoard; i++) {
                        // System.out.println(BoxesOfTheBoard[i]);
                        newSave.write(BoxesOfTheBoard[i] + ",\n");
                    }

                    newSave.close();
                }
            } catch (IOException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }

            stage.close();
        });

        VBox vbox = new VBox(20);
        Label info = new Label("If you can't move, then you lose the game.");
        vbox.getChildren().addAll(hbox, info, saveAndQuit);
        vbox.setAlignment(Pos.CENTER);

        ScrollPane boardSize = new ScrollPane(vbox);

        stage.setScene(new Scene(boardSize));
        stage.show();

    }

    // This method switches the team player after each move
    public void teamSwitch(Stage stage) {
        this.teamDesignator = 1 - this.teamDesignator;
        this.gameBoard(stage);
    }

    public void toadMove(Button toadButton, int toadSpace) {
        toadButton.setOnAction((event) -> {
            System.out.println("Toad selected!");
            if (this.teamDesignator == 1) {
                return;
            }
            if (toadSpace <= (sizeOfBoard - 1) && this.BoxesOfTheBoard[toadSpace + 1] == "    ") {
                this.BoxesOfTheBoard[toadSpace] = "    ";
                this.BoxesOfTheBoard[toadSpace + 1] = "Toad";
                teamSwitch(lightedStage);
            } else if (toadSpace <= (sizeOfBoard - 2) && this.BoxesOfTheBoard[toadSpace + 1] == "Frog"
                    && this.BoxesOfTheBoard[toadSpace + 2] == "    ") {
                this.BoxesOfTheBoard[toadSpace] = "    ";
                this.BoxesOfTheBoard[toadSpace + 2] = "Toad";
                teamSwitch(lightedStage);
            } else {
                for (int checkWin = 0; checkWin < sizeOfBoard - 1;) {
                    if (this.BoxesOfTheBoard[checkWin] == "Toad" && this.BoxesOfTheBoard[checkWin + 1] == "    ") {
                        frogWinFlag = false;
                        break;
                    } else if (this.BoxesOfTheBoard[checkWin + 1] == "Frog"
                            && this.BoxesOfTheBoard[checkWin + 2] == "    ") {
                        frogWinFlag = false;
                    } else {
                        frogWinFlag = true;
                    }
                    checkWin = checkWin + 1;
                }

            }
            if (frogWinFlag == true) {
                frogsWin();
            }
        });
    } // end Toadmove

    public void frogMove(Button frogButton, int frogSpace) {
        frogButton.setOnAction((event) -> {
            System.out.println("Frog selected!");
            if (this.teamDesignator == 0) {
                return;
            }
            if (frogSpace > 0 && this.BoxesOfTheBoard[frogSpace - 1] == "    ") {
                this.BoxesOfTheBoard[frogSpace] = "    ";
                this.BoxesOfTheBoard[frogSpace - 1] = "Frog";
                teamSwitch(lightedStage);
            } else if (frogSpace > 1 && this.BoxesOfTheBoard[frogSpace - 2] == "    "
                    && this.BoxesOfTheBoard[frogSpace - 1] == "Toad") {
                this.BoxesOfTheBoard[frogSpace] = "    ";
                this.BoxesOfTheBoard[frogSpace - 2] = "Frog";
                teamSwitch(this.lightedStage);
            } else {
                for (int checkWin = (sizeOfBoard - 1); checkWin > 0;) {
                    if (this.BoxesOfTheBoard[checkWin] == "Frog" && this.BoxesOfTheBoard[checkWin - 1] == "    ") {
                        toadWinFlag = false;
                        break;
                    } else if (this.BoxesOfTheBoard[checkWin - 1] == "Toad"
                            && this.BoxesOfTheBoard[checkWin - 2] == "    ") {
                        toadWinFlag = false;
                    } else {
                        toadWinFlag = true;
                    }
                    checkWin = checkWin - 1;
                }

            }
            if (toadWinFlag == true) {
                toadsWin();
            }
        });
    } // End frogMove

} // end of ToadsAndFrogs.java
