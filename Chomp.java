// Authors: Powell

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chomp extends Application {
    public static Stage chompStage = new Stage();
    public static Stage chompStage2 = new Stage();
    public static Stage chompStage3 = new Stage();
    public static Stage chompStage4 = new Stage();
    public static Stage chompStage5 = new Stage();
    public static Stage chompStage6 = new Stage();
    public boolean gameRestarted = false;
    public boolean gameLoaded = false;
    private boolean gameOver = false;
    private boolean checkHalf = false;
    private int teamDesignator;
    public int gameSelection = 3;
    public static String game;
    private Stage stage;
    public String[] ChompButtons;
    private Scene menuWindow;
    private int localK = 0;
    private File saveFileC = new File("saveGameC.txt");

    private Image Chomp = new Image("https://i.imgur.com/RjtksGk.png");
    private Image downChomp = new Image("https://i.imgur.com/H0XLvlx.png");
    private Image poisonChomp = new Image("https://i.imgur.com/WNSuOcx.png");

    //kicks off the game
    public void start(Stage primaryStage) {
        // sets our window we created as the primaryStage (the first window we see)
        if (Team.k == 1) {
            chompStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            chompStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            chompStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            chompStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            chompStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            chompStage6 = primaryStage;
            localK = 6;
        }

        gameSelection = 3;
        game = "C";

        // SWITCH GAME MENU //
        Label chooseLabel = new Label("Choose Your Game");

        //button to go to Domino Game
        Button dominoButton = new Button("Toppling Dominoes");
        dominoButton.setOnAction(event -> {
            System.out.println("Chosen game TD");
            TopplingDominoes TD = new TopplingDominoes();
            TD.gameRestarted = true;
            TD.game = "TD";
            TD.start(TopplingDominoes.dominoStage);
            chompStage.hide();
        });

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> {
            game = "TAF";
            gameSelection = 0;
            System.out.println("Chosen game TAF");
            this.chompStage.hide();
            ToadsAndFrogs TF = new ToadsAndFrogs();
            TF.start(ToadsAndFrogs.toadStage);
        });

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> {
            game = "CC";
            gameSelection = 4;
            System.out.println("Chosen game CC");
            chompStage.hide();
            ColorChomp CC2 = new ColorChomp();
            CC2.start(ColorChomp.colorChompStage);
        });

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> gameBoard(primaryStage));

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> {
            game = "ER";
            gameSelection = 2;
            System.out.println("Chosen game ER");
            this.chompStage.hide();
            ElephantsAndRhinos ER = new ElephantsAndRhinos();
            ER.start(ElephantsAndRhinos.elephantStage);
        });

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> {
            game = "M";
            gameSelection = 5;
            System.out.println("Chosen game M");
            this.chompStage.hide();
            Memory M = new Memory();
            M.start(Memory.fruitStage);
        });
        // END SWITCH GAME MENU //

        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton, elephantsButton, chompButton, colorChompButton, memoryButton);
        menuWindow = new Scene(menuLayout, 200, 300);

        // String of arrays to hold chomp button text
        this.ChompButtons = new String[29];
        for (int space = 0; space < 29; ) {
            this.ChompButtons[space] = "    ";
            space = space + 1;
        }
        //top row
        this.ChompButtons[0] = "chomp";
        this.ChompButtons[1] = "chomp";
        this.ChompButtons[2] = "chomp";
        this.ChompButtons[3] = "chomp";
        this.ChompButtons[4] = "chomp";
        this.ChompButtons[5] = "chomp";
        this.ChompButtons[6] = "chomp";
        this.ChompButtons[7] = "chomp";
        this.ChompButtons[8] = "chomp";
        //middle row
        this.ChompButtons[9] = "chomp";
        this.ChompButtons[10] = "chomp";
        this.ChompButtons[11] = "chomp";
        this.ChompButtons[12] = "chomp";
        this.ChompButtons[13] = "chomp";
        this.ChompButtons[14] = "chomp";
        this.ChompButtons[15] = "chomp";
        this.ChompButtons[16] = "chomp";
        this.ChompButtons[17] = "chomp";
        //bottom row
        this.ChompButtons[18] = "poison";
        this.ChompButtons[19] = "chomp";
        this.ChompButtons[20] = "chomp";
        this.ChompButtons[21] = "chomp";
        this.ChompButtons[22] = "chomp";
        this.ChompButtons[23] = "chomp";
        this.ChompButtons[24] = "chomp";
        this.ChompButtons[25] = "chomp";
        this.ChompButtons[26] = "chomp";


        //cFirstPlayer() only shows up if C is the selected game and it is not reloaded
        if (game == "C") {
            loadGameC();
            teamDesignator = cFirstPlayer();
        }
        if (localK == 1){
            gameBoard(this.chompStage);
        } else if (localK == 2){
            gameBoard(this.chompStage2);
        } else if (localK == 3){
            gameBoard(this.chompStage3);
        } else if (localK == 4){
            gameBoard(this.chompStage4);
        } else if (localK == 5){
            gameBoard(this.chompStage5);
        } else if (localK == 6){
            gameBoard(this.chompStage6);
        }


    } //end start

    // Prompts the user to load the previous game or not and sets the game board.
    public void loadGameC() {
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
                try (Scanner s = new Scanner(new File("saveGameC.txt")).useDelimiter(",\\s*")) {
                    while (s.hasNext()) {
                        for (int i = 0; i < 27; i++) {
                            read = s.next();
                            this.ChompButtons[i] = read;
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


    //First player method for C
    public int cFirstPlayer(){
        Team team = new Team();
        if (team.selected != true) {
            team.FirstPlayer();
        }
        return team.teamDesignator;
    } // end cFirstPlayer

    //Switches teams per turn
    public void teamSwitch(Stage stage) {
        if (localK == 1){
            gameBoard(this.chompStage);
        } else if (localK == 2){
            gameBoard(this.chompStage2);
        } else if (localK == 3){
            gameBoard(this.chompStage3);
        } else if (localK == 4){
            gameBoard(this.chompStage4);
        } else if (localK == 5){
            gameBoard(this.chompStage5);
        } else if (localK == 6){
            gameBoard(this.chompStage6);
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
        if (game == "C") {
            stage.setTitle("Chomp Game");
            if (localK == 1) {
                this.chompStage = stage;
            } else if (localK == 2){
                this.chompStage2 = stage;
            } else if (localK == 3){
                this.chompStage3 = stage;
            } else if (localK == 4){
                this.chompStage4 = stage;
            } else if (localK == 5){
                this.chompStage5 = stage;
            } else if (localK == 6){
                this.chompStage6 = stage;
            }

            //this.chompStage = stage;

            HBox hboxTopRow = new HBox(10);
            HBox hboxCenterRow = new HBox(10);
            HBox hboxBottomRow = new HBox(10);

            // This method saves the game by storing the BoxesOfTheBoard[] into a file
            Button saveAndQuitC = new Button("Save & Quit");
            saveAndQuitC.setOnAction((event) -> {
                try {
                    File saveFileC = new File("saveGameC.txt");
                    FileWriter newSave = new FileWriter("saveGameC.txt");
                    for (int i = 0; i < 27; i++) {
                        newSave.write(ChompButtons[i] + ",\n");
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
            stage.setScene(new Scene(border1, 1200, 250));

            Button backButton2 = new Button("Back to Game Menu");
            backButton2.setOnAction(event -> {
                chompStage.setScene(menuWindow);
                chompStage.setTitle("Game Selection!");
            });
            Button restart = new Button("Restart");
            restart.setOnAction(event -> {
                Chomp cRestart = new Chomp();
                cRestart.gameRestarted = true;
                cRestart.start(Chomp.this.chompStage);
            });


            stage.show();

            // Generates top row of buttons
            for (int buttonCreation = 0; buttonCreation < 9; buttonCreation++) {
                String teamName = ChompButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxTopRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("chomp")) {
                    ImageView chompView = new ImageView(Chomp);
                    generatedButtons.setGraphic(chompView);
                    Move(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView downChompView = new ImageView(downChomp);
                    generatedButtons.setGraphic(downChompView);
                }
            }
            hboxTopRow.getChildren().addAll(backButton2, restart, saveAndQuitC);


            // Generates middle row of buttons
            for (int buttonCreation = 9; buttonCreation < 18; buttonCreation++) {
                String teamName = ChompButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxCenterRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("chomp")) {
                    ImageView chompView = new ImageView(Chomp);
                    generatedButtons.setGraphic(chompView);
                    Move(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView downChompView = new ImageView(downChomp);
                    generatedButtons.setGraphic(downChompView);
                }
            }

            // Generates bottom row of buttons
            for (int buttonCreation = 18; buttonCreation < 27; buttonCreation++) {
                String teamName = ChompButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxBottomRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("chomp")) {
                    ImageView chompView = new ImageView(Chomp);
                    generatedButtons.setGraphic(chompView);
                    Move(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView downChompView = new ImageView(downChomp);
                    generatedButtons.setGraphic(downChompView);
                } else if (teamName.equals("poison")) {
                    ImageView poisonChompView = new ImageView(poisonChomp);
                    generatedButtons.setGraphic(poisonChompView);
                    poisonMove(generatedButtons, buttonCreation);
                }
            }
        }
    } //end gameBoard

    private void poisonMove(Button poisonButton, int poisonChomp) {
        poisonButton.setOnAction((event) -> {
            if (Team.teamDesignator < 1) {
                System.out.println("Player Two Wins Chomp!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Two Wins Chomp!");
                alert.setHeaderText("Player One eats the poison cookie - Player Two Wins Chomp!");
                alert.showAndWait();
            } else {
                System.out.println("Player One Wins!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player One Wins!");
                alert.setHeaderText("Player Two eats the poison cookie - Player One Wins Chomp!");
                alert.showAndWait();
            }
            gameOver = true;
        });
    }

    //code for a move
    private void Move(Button Button, int Chomp) {
        Button.setOnAction((event) -> {
            System.out.println("Chomp selected!");
            //Top row red move
            if (Chomp >= 0 && Chomp < 9) {
                for (int i = 8; i > (Chomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(chompStage);
                //middle row red move
            } else if (Chomp >= 9 && Chomp < 18) {
                for (int i = 17; i > (Chomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (Chomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(chompStage);
                //boardUpdate(chompStage);
                //bottom row red move
            } else if (Chomp > 18 && Chomp < 27) {
                for (int i = 27; i > (Chomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 17; i > (Chomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (Chomp - 19); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(chompStage);
            }
            if (Chomp == 18){
                System.out.println("You Lose");
            }
        });
    } //end move

    private void checkWin(){
        if (this.ChompButtons[19] == "down") {
            if (this.ChompButtons[9] == "down") {
                gameOver = true;
                playerWin();
            }
        }
    }

    private void playerWin(){
        if (Team.gameNumber == 1) {
            if (gameOver == true && Team.teamDesignator == 1) {
                System.out.println("Player Two Wins!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Two Wins!");
                alert.setHeaderText("Player Two Wins Chomp!");
                alert.showAndWait();
            } else if (gameOver == true) {
                System.out.println("Player One Wins Chomp!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player One Wins Chomp!");
                alert.setHeaderText("Player One Wins Chomp!");
                alert.showAndWait();
            }
        } else {
            if (gameOver == true && Team.teamDesignator == 1) {
                System.out.println("Player Two has Won Chomp");
                Team.playerTwoWins++;
                Team.finalWinCheck();
            } else if (gameOver == true) {
                System.out.println("Player One has Won Chomp");
                Team.playerOneWins++;
                Team.finalWinCheck();
            }
        }
    }

    // Used in button creation method
    public Button getButton() {
        return new Button();
    }
} //end of Chomp.java
