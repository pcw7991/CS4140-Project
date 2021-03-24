// Authors: Powell

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TopplingDominoes extends Application {
    public static Stage dominoStage = new Stage();
    public static Stage dominoStage2 = new Stage();
    public static Stage dominoStage3 = new Stage();
    public static Stage dominoStage4 = new Stage();
    public static Stage dominoStage5 = new Stage();
    public static Stage dominoStage6 = new Stage();
    private int teamDesignator;
    public boolean gameRestarted = false;
    public boolean gameLoaded = false;
    private boolean gameOver = false;
    private boolean dominoChosen = false;
    private int k = 1;
    public int gameSelection = -1;
    private Stage stage;
    public String[] DominoButtons;
    private String direction;
    private Scene menuWindow, gameMenuScene, teamWindow, teamScene;
    public static String game;
    private int localK = 0;
    public static Integer gameNumber;
    private File saveFileTD = new File("saveGameTD.txt");

    private Image redDomino = new Image("https://i.imgur.com/h9C9hNz.png");
    private Image blueDomino = new Image("https://i.imgur.com/Tm9TkqB.png");
    private Image downDomino = new Image("https://i.imgur.com/5CXqZNH.png");


    public static Stage getStage() {
        return dominoStage;
    }

    //kicks off the game
    public void start(Stage primaryStage) {
            // sets our window we created as the primaryStage (the first window we see)
        if (Team.k == 1) {
            dominoStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            dominoStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            dominoStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            dominoStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            dominoStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            dominoStage6 = primaryStage;
            localK = 6;
        }
            //dominoStage = primaryStage;
            gameSelection = 1;


        // SWITCH GAME MENU //
        Label chooseLabel = new Label("Choose Your Game");

        //button to go to Domino Game
        Button dominoButton = new Button("Toppling Dominoes");
        dominoButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> {
            game = "TAF";
            gameSelection = 0;
            System.out.println("Chosen game TAF");
            this.dominoStage.hide();
            ToadsAndFrogs TF = new ToadsAndFrogs();
            TF.start(ToadsAndFrogs.toadStage);
        });

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> {
            game = "CC";
            gameSelection = 4;
            System.out.println("Chosen game CC");
            dominoStage.hide();
            ColorChomp CC2 = new ColorChomp();
            CC2.start(ColorChomp.colorChompStage);
        });

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> {
            game = "C";
            gameSelection = 3;
            System.out.println("Chosen game C");
            dominoStage.hide();
            Chomp C = new Chomp();
            C.start(Chomp.chompStage);
        });

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> {
            game = "ER";
            gameSelection = 2;
            System.out.println("Chosen game ER");
            this.dominoStage.hide();
            ElephantsAndRhinos ER = new ElephantsAndRhinos();
            ER.start(ElephantsAndRhinos.elephantStage);
        });

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> {
            game = "M";
            gameSelection = 5;
            System.out.println("Chosen game M");
            this.dominoStage.hide();
            Memory M2 = new Memory();
            M2.start(Memory.fruitStage);
        });

        // END SWITCH GAME MENU //


        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton, elephantsButton, chompButton, colorChompButton, memoryButton);
        menuWindow = new Scene(menuLayout, 200, 300);


        // String of arrays to hold domino button text
        this.DominoButtons = new String[29];
        for (int space = 0; space < 29; ) {
            DominoButtons[space] = "    ";
            space = space + 1;
        }
        //top row
        DominoButtons[0] = "red";
        DominoButtons[1] = "red";
        DominoButtons[2] = "blue";
        DominoButtons[3] = "red";
        DominoButtons[4] = "blue";
        DominoButtons[5] = "blue";
        DominoButtons[6] = "red";
        DominoButtons[7] = "blue";
        DominoButtons[8] = "blue";
        //middle row
        DominoButtons[9] = "red";
        DominoButtons[10] = "blue";
        DominoButtons[11] = "blue";
        DominoButtons[12] = "red";
        DominoButtons[13] = "red";
        DominoButtons[14] = "blue";
        DominoButtons[15] = "blue";
        DominoButtons[16] = "red";
        DominoButtons[17] = "blue";
        //bottom row
        DominoButtons[18] = "blue";
        DominoButtons[19] = "red";
        DominoButtons[20] = "blue";
        DominoButtons[21] = "red";
        DominoButtons[22] = "blue";
        DominoButtons[23] = "red";
        DominoButtons[24] = "blue";
        DominoButtons[25] = "red";
        DominoButtons[26] = "red";


        //tdFirstPlayer() only shows up if TD is the selected game and it is not reloaded
        if (Team.game == "TD") {
            loadGameTD();
            teamDesignator = tdFirstPlayer();
        }

        if (localK == 1){
            gameBoard(this.dominoStage);
        } else if (localK == 2){
            gameBoard(this.dominoStage2);
        } else if (localK == 3){
            gameBoard(this.dominoStage3);
        } else if (localK == 4){
            gameBoard(this.dominoStage4);
        } else if (localK == 5){
            gameBoard(this.dominoStage5);
        } else if (localK == 6){
            gameBoard(this.dominoStage6);
        }
        //gameBoard(this.dominoStage);

    } //end start

    // Prompts the user to load the previous game or not and sets the game board.
    public void loadGameTD() {
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
                try (Scanner s = new Scanner(new File("saveGameTD.txt")).useDelimiter(",\\s*")) {
                    while (s.hasNext()) {
                        for (int i = 0; i < 27; i++) {
                            read = s.next();
                            this.DominoButtons[i] = read;
                        }
                    }
                } catch (FileNotFoundException e) {
                }
                loadGameWindow.close();
            }
            if (no.isSelected()) {
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

    //First player method for TD
    public int tdFirstPlayer(){
        Team team = new Team();
        if (team.selected != true) {
            team.FirstPlayer();
        }
        return team.teamDesignator;
    } // end tdFirstPlayer

    //Switches teams per turn
    public void teamSwitch(Stage stage) {
        if (localK == 1){
            gameBoard(this.dominoStage);
        } else if (localK == 2){
            gameBoard(this.dominoStage2);
        } else if (localK == 3){
            gameBoard(this.dominoStage3);
        } else if (localK == 4){
            gameBoard(this.dominoStage4);
        } else if (localK == 5){
            gameBoard(this.dominoStage5);
        } else if (localK == 6){
            gameBoard(this.dominoStage6);
        }
        Team.teamDesignator = 1 - Team.teamDesignator;
        if (gameOver == false) {
            Team.teamBox();
        }
        if (gameOver == true && Team.gameNumber > 1 && Team.finalWin == false){
            Team.teamBox();
        }
    } // end teamSwitch

    //Set ups the gameboard with buttons
    public void gameBoard(Stage stage) {
        stage.setTitle("Toppling Dominoes Game");
        if (localK == 1) {
            this.dominoStage = stage;
        } else if (localK == 2){
            this.dominoStage2 = stage;
        } else if (localK == 3){
            this.dominoStage3 = stage;
        } else if (localK == 4){
            this.dominoStage4 = stage;
        } else if (localK == 5){
            this.dominoStage5 = stage;
        } else if (localK == 6){
            this.dominoStage6 = stage;
        }

            HBox hboxTopRow = new HBox(10);
            HBox hboxCenterRow = new HBox(10);
            HBox hboxBottomRow = new HBox(10);

            // This method saves the game by storing the BoxesOfTheBoard[] into a file
            Button saveAndQuitTD = new Button("Save & Quit");
            saveAndQuitTD.setOnAction((event) -> {
                try {
                    File saveFileTD = new File("saveGameTD.txt");
                    FileWriter newSave = new FileWriter("saveGameTD.txt");
                    for (int i = 0; i < 27; i++) {
                        newSave.write(DominoButtons[i] + ",\n");
                    }
                    newSave.close();

                } catch (IOException e) {
                    System.out.println("An error occured.");
                    e.printStackTrace();
                }

                stage.close();
            });

            // Trying to make three layers of buttons using BorderPanes
            BorderPane border1 = new BorderPane();
            border1.setTop(hboxTopRow);
            border1.setCenter(hboxCenterRow);
            border1.setBottom(hboxBottomRow);
            stage.setScene(new Scene(border1, 750, 235));

            Button backButton2 = new Button("Back to Game Menu");
            backButton2.setOnAction(event -> {
                dominoStage.setScene(menuWindow);
                dominoStage.setTitle("Game Selection!");
            });
            Button restart = new Button("Restart");
            restart.setOnAction(event -> {
                TopplingDominoes tdRestart = new TopplingDominoes();
                tdRestart.gameRestarted = true;
                tdRestart.start(TopplingDominoes.dominoStage);
            });

            stage.show();

            // Generates top row of buttons
            for (int buttonCreation = 0; buttonCreation < 9; buttonCreation++) {
                String teamName = DominoButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxTopRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("red")) {
                    ImageView redDominoView = new ImageView(redDomino);
                    generatedButtons.setGraphic(redDominoView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueDominoView = new ImageView(blueDomino);
                    generatedButtons.setGraphic(blueDominoView);
                    blueMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView blueDominoView = new ImageView(downDomino);
                    generatedButtons.setGraphic(blueDominoView);
                }
            }

            hboxTopRow.getChildren().addAll(backButton2, restart, saveAndQuitTD);


            // Generates middle row of buttons
            for (int buttonCreation = 9; buttonCreation < 18; buttonCreation++) {
                String teamName = DominoButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxCenterRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("red")) {
                    ImageView redDominoView = new ImageView(redDomino);
                    generatedButtons.setGraphic(redDominoView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueDominoView = new ImageView(blueDomino);
                    generatedButtons.setGraphic(blueDominoView);
                    blueMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView blueDominoView = new ImageView(downDomino);
                    generatedButtons.setGraphic(blueDominoView);
                }
            }

            // Generates bottom row of buttons
            for (int buttonCreation = 18; buttonCreation < 27; buttonCreation++) {
                String teamName = DominoButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxBottomRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("red")) {
                    ImageView redDominoView = new ImageView(redDomino);
                    generatedButtons.setGraphic(redDominoView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueDominoView = new ImageView(blueDomino);
                    generatedButtons.setGraphic(blueDominoView);
                    blueMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView blueDominoView = new ImageView(downDomino);
                    generatedButtons.setGraphic(blueDominoView);
                }
            }
            hboxBottomRow.getChildren().add(new Label("Player One = Red || Player Two = Blue"));

        }
    //} //end gameBoard

    //code for a red move
    private void redMove(Button redButton, int redDomino) {
        redButton.setOnAction((event) -> {
            System.out.println("red selected!");

            if (Team.teamDesignator == 1) {
                return;
            } else {
                // This method asks the user to choose the direction to move
                direction();
            }
            //Top row red move
            if (redDomino >= 0 && redDomino < 9) {
                if (this.direction == "Left") {
                    for (int i = 0; i < (redDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 8; i > (redDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                }

                //middle row red move
            } else if (redDomino >= 9 && redDomino < 18) {
                if (this.direction == "Left") {
                    for (int i = 9; i < (redDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 17; i > (redDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                }

                //bottom row red move
            } else if (redDomino >= 18 && redDomino < 27) {
                if (this.direction == "Left") {
                    for (int i = 18; i < (redDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 27; i > (redDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkRedWin();
                    teamSwitch(dominoStage);
                }
            }
        });
    } //end red move

    //code for blue move
    private void blueMove(Button blueButton, int blueDomino) {
        blueButton.setOnAction((event) -> {
            System.out.println("blue selected!");

            if (Team.teamDesignator < 1) {
                return;
            } else {
                // This method asks the user to choose the direction to move
                direction();
            }
            //Top row blue move
            if (blueDomino >= 0 && blueDomino < 9) {
                if (this.direction == "Left") {
                    for (int i = 0; i < (blueDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 8; i > (blueDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                }

                //middle row blue move
            } else if (blueDomino >= 9 && blueDomino < 18) {
                if (this.direction == "Left") {
                    for (int i = 9; i < (blueDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 17; i > (blueDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                }

                //bottom row blue move
            } else if (blueDomino >= 18 && blueDomino < 27) {
                if (this.direction == "Left") {
                    for (int i = 18; i < (blueDomino + 1); i++) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                } else if (this.direction == "Right") {
                    for (int i = 27; i > (blueDomino - 1); i--) {
                        this.DominoButtons[i] = "down";
                    }
                    checkBlueWin();
                    teamSwitch(dominoStage);
                }
            }
        });
    } //end blue move

    //Allows players to choose a direction to topple
    public String direction() {
        Stage directionWindow = new Stage();
        directionWindow.setTitle("Direction");
        directionWindow.setWidth(150);
        Label text = new Label("Choose the direction");
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Left");
        choiceBox.getItems().add("Right");
        Button select = new Button("Select");
        select.setOnAction((event) -> {
            directionWindow.close();
        });

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(text, choiceBox, select);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout);
        directionWindow.setScene(scene);
        directionWindow.showAndWait();
        return direction = (String) choiceBox.getValue();
    } //end direction


    // Checks if all buttons are in DOWN state, and if so, alertbox
    // pops up alerting user of RED WINS
    private void checkRedWin() {
        int counter = 0;
        for (int i = 0; i < 27; i++) {
            if (this.DominoButtons[i] == "down") {
                counter++;
                if (counter == 27) {
                    gameOver = true;
                    playerWin();
                    /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Player One Wins Toppling Dominoes!");
                    alert.setHeaderText("Player One WINS Toppling Dominoes!");
                    alert.showAndWait();*/
                }
            }
        }
    } //end checkRedWin

    // Checks if all buttons are in DOWN state, and if so, alertbox
    // pops up alerting user of BLUE WINS
    private void checkBlueWin() {
        int counter = 0;
        for (int i = 0; i < 27; i++) {
            if (this.DominoButtons[i] == "down") {
                counter++;
                if (counter == 27) {
                    gameOver = true;
                    playerWin();
                    /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Player Two Wins Toppling Dominoes!");
                    alert.setHeaderText("Player Two WINS Toppling Dominoes!");
                    alert.showAndWait();*/
                }
            }
        }
    } //end checkBlueWin

    private void playerWin(){
        if (Team.gameNumber == 1) {
            if (gameOver == true && Team.teamDesignator == 1) {
                System.out.println("Player Two Wins!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Two Wins!");
                alert.setHeaderText("Player Two Wins Toppling Dominoes!");
                alert.showAndWait();
            } else if (gameOver == true) {
                System.out.println("Player One Wins Toppling Dominoes!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player One Wins Toppling Dominoes!");
                alert.setHeaderText("Player One Wins Toppling Dominoes!");
                alert.showAndWait();
            }
        } else {
            if (gameOver == true && Team.teamDesignator == 1) {
                System.out.println("Player Two has Won Toppling Dominoes!");
                Team.playerTwoWins++;
                Team.finalWinCheck();
            } else if (gameOver == true) {
                System.out.println("Player One has Won Toppling Dominoes!");
                Team.playerOneWins++;
                Team.finalWinCheck();
            }
        }
    }


    // Used in button creation method
    public Button getButton() {
        return new Button();
    }
} //end of TopplingDominoes.java
