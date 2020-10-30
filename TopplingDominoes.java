// Authors: Powell

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TopplingDominoes extends Application {

    int teamDesignator = 0;
    Stage stage;
    String[] DominoButtons;
    String direction;
    Scene menuWindow, dominoWindow, toadsWindow, gameMenuScene;
    public static String game;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        // sets our window we created as the primaryStage (the first window we see)
        stage = primaryStage;

        if (gameMenu() == "TAF") {
            System.out.println("Chosen game TAF");
        } else {
            System.out.println("Chosen game TD");
        }

        // simple text
        Label chooseLabel = new Label("Choose Your Game");

        // button to go to falling dominoes game
        Button dominoButton = new Button("Falling Dominoes");
        dominoButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> stage.setScene(toadsWindow));

        // buttons to return to game menu
        Button backButton1 = new Button("Back to Game Menu");
        backButton1.setOnAction(event -> stage.setScene(menuWindow));
        Button backButton2 = new Button("Back to Game Menu");
        backButton2.setOnAction(event -> stage.setScene(menuWindow));

        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton);
        menuWindow = new Scene(menuLayout, 300, 200);

        // choicebox for dominoes game - not working
        // ChoiceBox<String> choiceBox = new ChoiceBox<>();
        // choiceBox.getItems().addAll("left", "right");
        // choiceBox.getSelectionModel().select(0);

        // String of arrays to hold domino button text
        this.DominoButtons = new String[30];
        for (int space = 0; space < 30;) {
            DominoButtons[space] = "    ";
            space = space + 1;
        }

        DominoButtons[0] = "red";
        DominoButtons[1] = "red";
        DominoButtons[2] = "blue";
        DominoButtons[3] = "red";
        DominoButtons[4] = "blue";
        DominoButtons[5] = "blue";
        DominoButtons[6] = "blue";
        DominoButtons[7] = "red";
        DominoButtons[8] = "blue";
        DominoButtons[9] = "blue";
        DominoButtons[10] = "red";
        DominoButtons[11] = "red";
        DominoButtons[12] = "blue";
        DominoButtons[13] = "red";
        DominoButtons[14] = "blue";
        DominoButtons[15] = "blue";
        DominoButtons[16] = "blue";
        DominoButtons[17] = "red";
        DominoButtons[18] = "blue";
        DominoButtons[19] = "blue";
        DominoButtons[20] = "red";
        DominoButtons[21] = "red";
        DominoButtons[22] = "blue";
        DominoButtons[23] = "red";
        DominoButtons[24] = "blue";
        DominoButtons[25] = "blue";
        DominoButtons[26] = "blue";
        DominoButtons[27] = "red";
        DominoButtons[28] = "blue";
        DominoButtons[29] = "blue";

        gameBoard(this.stage);

    }

    public void teamSwitch(Stage stage) {
        this.teamDesignator = 1 - this.teamDesignator;
        this.gameBoard(stage);
    }

    public void gameBoard(Stage stage) {
        String[] teams = { "red", "blue" };
        stage.setTitle("Domino Game : It's the " + teams[teamDesignator] + " team's Turn.");
        this.stage = stage;

        HBox hboxTopRow = new HBox(10);
        HBox hboxCenterRow = new HBox(10);
        HBox hboxBottomRow = new HBox(10);
        // hboxLayout.getChildren();
        // ScrollPane boardSize = new ScrollPane(hboxLayout);

        // Trying to make three layers of buttons using BorderPanes
        BorderPane border1 = new BorderPane();
        border1.setTop(hboxTopRow);
        border1.setCenter(hboxCenterRow);
        border1.setBottom(hboxBottomRow);
        stage.setScene(new Scene(border1, 800, 100));

        Button backButton2 = new Button("Back to Game Menu");
        backButton2.setOnAction(event -> stage.setScene(menuWindow));

        stage.show();

        // Generates domino buttons; taken from Toads and Frogs Game
        for (int buttonCreation = 0; buttonCreation < 10; buttonCreation++) {
            String teamName = DominoButtons[buttonCreation];
            Button generatedButtons = this.getButton();
            generatedButtons.setText(teamName);
            hboxTopRow.getChildren().addAll(generatedButtons);
            // border.setCenter(hboxLayout);
            if (teamName.equals("red")) {
                redMove(generatedButtons, buttonCreation);
            } else if (teamName.equals("blue")) {
                blueMove(generatedButtons, buttonCreation);
            }
        }
        hboxTopRow.getChildren().addAll(backButton2);

        for (int buttonCreation = 11; buttonCreation < 20; buttonCreation++) {
            String teamName = DominoButtons[buttonCreation];
            Button generatedButtons = this.getButton();
            generatedButtons.setText(teamName);
            hboxCenterRow.getChildren().addAll(generatedButtons);
            // border.setCenter(hboxLayout);
            if (teamName.equals("red")) {
                redMove(generatedButtons, buttonCreation);
            } else if (teamName.equals("blue")) {
                blueMove(generatedButtons, buttonCreation);
            }
        }

        for (int buttonCreation = 21; buttonCreation < 30; buttonCreation++) {
            String teamName = DominoButtons[buttonCreation];
            Button generatedButtons = this.getButton();
            generatedButtons.setText(teamName);
            hboxBottomRow.getChildren().addAll(generatedButtons);
            // border.setCenter(hboxLayout);
            if (teamName.equals("red")) {
                redMove(generatedButtons, buttonCreation);
            } else if (teamName.equals("blue")) {
                blueMove(generatedButtons, buttonCreation);
            }
        }
    }

    private void redMove(Button redButton, int redDomino) {

        redButton.setOnAction((event) -> {
            // choiceBox.getValue();
            System.out.println("red selected!");

            // This method asks the user to choose the direction to move
            direction();
            System.out.println(direction); // Just checking if it is working or not

            if (this.teamDesignator == 1) {
                return;
            }
            if (redDomino <= 10) {
                for (int i = 0; i < (10 - redDomino); i++) {
                    this.DominoButtons[redDomino + i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            } else if (redDomino <= 20) {
                for (int i = 10; i < (20 - redDomino); i++) {
                    this.DominoButtons[redDomino + i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            } else if (redDomino <= 30) {
                for (int i = 20; i < (30 - redDomino); i++) {
                    this.DominoButtons[redDomino + i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            }
        });
    }

    // Iterates through buttons to the LEFT of selected BLUE button, changing them
    // to DOWN state
    private void blueMove(Button blueButton, int blueDomino) {
        blueButton.setOnAction((event) -> {
            System.out.println("blue selected!");

            // This method asks the user to choose the direction to move
            direction();
            System.out.println(direction); // Just checking if it is working or not

            if (this.teamDesignator < 1) {
                return;
            }
            if (blueDomino <= 10) {
                for (int i = 0; i <= blueDomino; i++) {
                    this.DominoButtons[blueDomino - i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            } else if (blueDomino > 10 && blueDomino <= 20) {
                for (int i = 10; i <= (blueDomino - 10); i++) {
                    this.DominoButtons[blueDomino - i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            } else if (blueDomino > 20 && blueDomino <= 30) {
                for (int i = 20; i <= (blueDomino - 10); i++) {
                    this.DominoButtons[blueDomino - i] = "Down";
                    teamSwitch(stage);
                }
                checkTie();
                checkredWin();
                checkblueWin();
            }
        });
    }

    public String direction() {
        Stage directionWindow = new Stage();
        directionWindow.setTitle("Direction");
        directionWindow.setWidth(150);
        Label text = new Label("Choose the direction");
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Left");
        choiceBox.getItems().add("Right");

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(text, choiceBox);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout);
        directionWindow.setScene(scene);
        directionWindow.showAndWait();
        return direction = (String) choiceBox.getValue();

    }

    // Checks if all buttons are in DOWN state, and if so, alertbox pops up alerting
    // user of a TIE
    private void checkTie() {
        int counter = 0;
        for (int i = 0; i < 30; i++) {
            if (this.DominoButtons[i] == "Down") {
                counter++;
                if (counter == 30) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nobody Wins!");
                    alert.setHeaderText("It's a TIE!");
                    alert.showAndWait();
                    // start(stage);
                }
            } else {
                System.out.println("NOPE");
            }
        }
    }

    // Checks if all buttons except one RED are in DOWN state, and if so, alertbox
    // pops up alerting user of RED WINS
    private void checkredWin() {
        int counter = 0;
        for (int i = 0; i < 30; i++) {
            if (this.DominoButtons[i] == "Down") {
                counter++;
                if (counter == 29) {
                    int counter2 = 0;
                    for (int x = 0; x < 30; x++) {
                        if (this.DominoButtons[x] == "red") {
                            counter2++;
                            if (counter2 == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("red Wins!");
                                alert.setHeaderText("red WINS!");
                                alert.showAndWait();
                                // start(stage);
                            }
                        }
                    }
                } else {
                    System.out.println("NOPE");
                }
            }
        }
    }

    // Checks if all buttons except one BLUE are in DOWN state, and if so, alertbox
    // pops up alerting user of BLUE WINS
    private void checkblueWin() {
        int counter = 0;
        for (int i = 0; i < 30; i++) {
            if (this.DominoButtons[i] == "Down") {
                counter++;
                if (counter == 29) {
                    int counter2 = 0;
                    for (int x = 0; x < 30; x++) {
                        if (this.DominoButtons[x] == "blue") {
                            counter2++;
                            if (counter2 == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("blue Wins!");
                                alert.setHeaderText("blue WINS!");
                                alert.showAndWait();
                                // start(stage);
                            }
                        }
                    }
                } else {
                    System.out.println("NOPE");
                }
            }
        }
    }

    public String gameMenu() {
        // String game = "";
        Stage gameMenuWindow = new Stage();
        gameMenuWindow.setTitle("Available Games");
        gameMenuWindow.setWidth(250);
        Label text = new Label("Choose a game to play");

        ToggleGroup group = new ToggleGroup();

        RadioButton TAF = new RadioButton("Toads and Frogs");
        TAF.setToggleGroup(group);

        RadioButton TD = new RadioButton("Toppling Dominoes");
        TD.setToggleGroup(group);

        Button select = new Button("Select");
        select.setOnAction((event) -> {
            if (TAF.isSelected()) {
                game = "TAF";
                gameMenuWindow.close();

            } else {
                game = "TD";
                gameMenuWindow.close();
            }

        });

        // Button toadsAndFrogs = new Button("Toads and Frogs");
        // toadsAndFrogs.setOnAction((event) -> {
        // //game = 0;
        // return 0;
        // gameMenuWindow.close();
        // });

        // Button topplingDominoes = new Button("Toppling Dominoes");
        // topplingDominoes.setOnAction((event) -> {
        // //game = 1;
        // return 1;
        // gameMenuWindow.close();
        // });

        VBox mainLayout = new VBox(10);
        // mainLayout.getChildren().addAll(text, toadsAndFrogs, topplingDominoes);
        mainLayout.getChildren().addAll(text, TAF, TD, select);
        mainLayout.setAlignment(Pos.CENTER);

        gameMenuScene = new Scene(mainLayout);
        gameMenuWindow.setScene(gameMenuScene);
        gameMenuWindow.showAndWait();
        return game;
    }

    // Used in button creation method
    public Button getButton() {
        return new Button();
    }

}
