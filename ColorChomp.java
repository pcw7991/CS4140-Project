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

public class ColorChomp extends Application {
    public static Stage colorChompStage = new Stage();
    public static Stage colorChompStage2 = new Stage();
    public static Stage colorChompStage3 = new Stage();
    public static Stage colorChompStage4 = new Stage();
    public static Stage colorChompStage5 = new Stage();
    public static Stage colorChompStage6 = new Stage();
    private int teamDesignator;
    public boolean gameRestarted = false;
    public boolean gameLoaded = false;
    private boolean gameOver = false;
    public int gameSelection = 4;
    public static String game;
    private Stage stage;
    public String[] ChompButtons;
    private int localK = 0;
    private String direction;
    private Scene menuWindow, gameMenuScene;
    private File saveFileCC = new File("saveGameCC.txt");

    private Image redChomp = new Image("https://i.imgur.com/0IMQldi.png");
    private Image blueChomp = new Image("https://i.imgur.com/Oixi5qc.png");
    private Image downChomp = new Image("https://i.imgur.com/H0XLvlx.png");
    private Image poisonChomp = new Image("https://i.imgur.com/WNSuOcx.png");
    /* public static void main(String[] args) {

    }*/

    //kicks off the game
    public void start(Stage primaryStage) {
        // sets our window we created as the primaryStage (the first window we see)
        if (Team.k == 1) {
            colorChompStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            colorChompStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            colorChompStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            colorChompStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            colorChompStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            colorChompStage6 = primaryStage;
            localK = 6;
        }

        gameSelection = 4;
        game = "CC";

        // SWITCH GAME MENU //
        Label chooseLabel = new Label("Choose Your Game");

        Button dominoButton = new Button("Toppling Dominoes");
        dominoButton.setOnAction(event -> {
            System.out.println("Chosen game TD");
            TopplingDominoes TD = new TopplingDominoes();
            TD.gameRestarted = true;
            TD.game = "TD";
            TD.start(TopplingDominoes.dominoStage);
            colorChompStage.hide();
        });

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> {
            game = "TAF";
            gameSelection = 0;
            System.out.println("Chosen game TAF");
            this.colorChompStage.hide();
            ToadsAndFrogs TF = new ToadsAndFrogs();
            TF.start(ToadsAndFrogs.toadStage);
        });

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> {
            game = "C";
            gameSelection = 3;
            System.out.println("Chosen game C");
            colorChompStage.hide();
            Chomp C = new Chomp();
            C.start(Chomp.chompStage);
        });

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> {
            game = "ER";
            gameSelection = 2;
            System.out.println("Chosen game ER");
            this.colorChompStage.hide();
            ElephantsAndRhinos ER = new ElephantsAndRhinos();
            ER.start(ElephantsAndRhinos.elephantStage);
        });

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> {
            game = "M";
            gameSelection = 5;
            System.out.println("Chosen game M");
            this.colorChompStage.hide();
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
            ChompButtons[space] = "    ";
            space = space + 1;
        }
        //top row
        ChompButtons[0] = "red";
        ChompButtons[1] = "blue";
        ChompButtons[2] = "red";
        ChompButtons[3] = "blue";
        ChompButtons[4] = "red";
        ChompButtons[5] = "blue";
        ChompButtons[6] = "red";
        ChompButtons[7] = "blue";
        ChompButtons[8] = "red";
        //middle row
        ChompButtons[9] = "blue";
        ChompButtons[10] = "red";
        ChompButtons[11] = "blue";
        ChompButtons[12] = "red";
        ChompButtons[13] = "blue";
        ChompButtons[14] = "red";
        ChompButtons[15] = "blue";
        ChompButtons[16] = "red";
        ChompButtons[17] = "blue";
        //bottom row
        ChompButtons[18] = "poison";
        ChompButtons[19] = "red";
        ChompButtons[20] = "red";
        ChompButtons[21] = "blue";
        ChompButtons[22] = "red";
        ChompButtons[23] = "blue";
        ChompButtons[24] = "red";
        ChompButtons[25] = "blue";
        ChompButtons[26] = "blue";


        //cFirstPlayer() only shows up if C is the selected game and it is not reloaded
        if (game == "CC") {
            loadGameCC();
            teamDesignator = cFirstPlayer();
        }

        if (localK == 1){
            gameBoard(this.colorChompStage);
        } else if (localK == 2){
            gameBoard(this.colorChompStage2);
        } else if (localK == 3){
            gameBoard(this.colorChompStage3);
        } else if (localK == 4){
            gameBoard(this.colorChompStage4);
        } else if (localK == 5){
            gameBoard(this.colorChompStage5);
        } else if (localK == 6){
            gameBoard(this.colorChompStage6);
        }


    } //end start

    // Prompts the user to load the previous game or not and sets the game board.
    public void loadGameCC() {
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
                try (Scanner s = new Scanner(new File("saveGameCC.txt")).useDelimiter(",\\s*")) {
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
            gameBoard(this.colorChompStage);
        } else if (localK == 2){
            gameBoard(this.colorChompStage2);
        } else if (localK == 3){
            gameBoard(this.colorChompStage3);
        } else if (localK == 4){
            gameBoard(this.colorChompStage4);
        } else if (localK == 5){
            gameBoard(this.colorChompStage5);
        } else if (localK == 6){
            gameBoard(this.colorChompStage6);
        }

        Team.teamDesignator = 1 - Team.teamDesignator;
        //Team.moveCount++;
        if (gameOver == false) {
            Team.teamBox();
        }
        if (gameOver == true && Team.gameNumber > 1 && Team.finalWin == false){
            Team.teamBox();
        }
    } // end teamSwitch

    //Set ups the gameboard with buttons
    public void gameBoard(Stage stage) {
        if (game == "CC") {
            //String[] teams = {"red(one)", "blue(two)"};
            //stage.setTitle("Chomp Game : It's the " + teams[Team.teamDesignator] + "'s Turn.");
            if (localK == 1) {
                this.colorChompStage = stage;
            } else if (localK == 2){
                this.colorChompStage2 = stage;
            } else if (localK == 3){
                this.colorChompStage3 = stage;
            } else if (localK == 4){
                this.colorChompStage4 = stage;
            } else if (localK == 5){
                this.colorChompStage5 = stage;
            } else if (localK == 6){
                this.colorChompStage6 = stage;
            }

            HBox hboxTopRow = new HBox(10);
            HBox hboxCenterRow = new HBox(10);
            HBox hboxBottomRow = new HBox(10);

            // This method saves the game by storing the BoxesOfTheBoard[] into a file
            Button saveAndQuitC = new Button("Save & Quit");
            saveAndQuitC.setOnAction((event) -> {
                try {
                    File saveFileCC = new File("saveGameCC.txt");
                    FileWriter newSave = new FileWriter("saveGameCC.txt");
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
                colorChompStage.setScene(menuWindow);
                colorChompStage.setTitle("Game Selection!");
            });
            Button restart = new Button("Restart");
            restart.setOnAction(event -> {
                ColorChomp ccRestart = new ColorChomp();
                ccRestart.gameRestarted = true;
                ccRestart.start(ColorChomp.colorChompStage);
            });

            stage.show();

            // Generates top row of buttons
            for (int buttonCreation = 0; buttonCreation < 9; buttonCreation++) {
                String teamName = ChompButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxTopRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("red")) {
                    ImageView redChompView = new ImageView(redChomp);
                    generatedButtons.setGraphic(redChompView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueChompView = new ImageView(blueChomp);
                    generatedButtons.setGraphic(blueChompView);
                    blueMove(generatedButtons, buttonCreation);
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
                if (teamName.equals("red")) {
                    ImageView redChompView = new ImageView(redChomp);
                    generatedButtons.setGraphic(redChompView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueChompView = new ImageView(blueChomp);
                    generatedButtons.setGraphic(blueChompView);
                    blueMove(generatedButtons, buttonCreation);
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
                if (teamName.equals("red")) {
                    ImageView redChompView = new ImageView(redChomp);
                    generatedButtons.setGraphic(redChompView);
                    redMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("blue")) {
                    ImageView blueChompView = new ImageView(blueChomp);
                    generatedButtons.setGraphic(blueChompView);
                    blueMove(generatedButtons, buttonCreation);
                } else if (teamName.equals("down")) {
                    ImageView downChompView = new ImageView(downChomp);
                    generatedButtons.setGraphic(downChompView);
                } else if (teamName.equals("poison")) {
                    ImageView poisonChompView = new ImageView(poisonChomp);
                    generatedButtons.setGraphic(poisonChompView);
                    poisonMove(generatedButtons, buttonCreation);
                }
            }
            hboxBottomRow.getChildren().add(new Label("Player One = Red || Player Two = Blue"));

        }
    } //end gameBoard

    private void poisonMove(Button poisonButton, int poisonChomp) {
        poisonButton.setOnAction((event) -> {
            if (Team.teamDesignator < 1) {
                System.out.println("Player Two Wins ColorChomp!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Two Wins ColorChomp!");
                alert.setHeaderText("Player One eats the poison cookie - Player Two Wins ColorChomp!");
                alert.showAndWait();
            } else {
                System.out.println("Player One Wins ColorChomp!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player One Wins ColorChomp!");
                alert.setHeaderText("Player Two eats the poison cookie - Player One Wins ColorChomp!");
                alert.showAndWait();
            }
            gameOver = true;
        });
    }

    //code for a red move
    private void redMove(Button redButton, int redChomp) {
        redButton.setOnAction((event) -> {
            System.out.println("red selected!");
            if (Team.teamDesignator == 1) {
                return;
            }
            //Top row red move
            if (redChomp >= 0 && redChomp < 9) {
                for (int i = 8; i > (redChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
                //middle row red move
            } else if (redChomp >= 9 && redChomp < 18) {
                for (int i = 17; i > (redChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (redChomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
                //bottom row red move
            } else if (redChomp > 18 && redChomp < 27) {
                for (int i = 27; i > (redChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 17; i > (redChomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (redChomp - 19); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
            }
            if (redChomp == 18){
                System.out.println("You Lose");
            }
        });
    } //end red move

    //code for blue move
    private void blueMove(Button blueButton, int blueChomp) {
        blueButton.setOnAction((event) -> {
            System.out.println("blue selected!");

            if (Team.teamDesignator < 1) {
                return;
            }
            //Top row red move
            if (blueChomp >= 0 && blueChomp < 9) {
                for (int i = 8; i > (blueChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
                //middle row red move
            } else if (blueChomp >= 9 && blueChomp < 18) {
                for (int i = 17; i > (blueChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (blueChomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
                //bottom row red move
            } else if (blueChomp >= 18 && blueChomp < 27) {
                for (int i = 27; i > (blueChomp - 1); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 17; i > (blueChomp - 10); i--) {
                    this.ChompButtons[i] = "down";
                }
                for (int i = 8; i > (blueChomp - 19); i--) {
                    this.ChompButtons[i] = "down";
                }
                checkWin();
                teamSwitch(colorChompStage);
            }
        });
    } //end blue move

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
                alert.setHeaderText("Player Two Wins ColorChomp!");
                alert.showAndWait();
            } else if (gameOver == true) {
                System.out.println("Player One Wins ColorChomp!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player One Wins ColorChomp!");
                alert.setHeaderText("Player One Wins ColorChomp!");
                alert.showAndWait();
            }
        } else {
            if (gameOver == true && Team.teamDesignator == 1) {
                System.out.println("Player Two has Won ColorChomp");
                Team.playerTwoWins++;
                Team.finalWinCheck();
            } else if (gameOver == true) {
                System.out.println("Player One has Won ColorChomp");
                Team.playerOneWins++;
                Team.finalWinCheck();
            }
        }
    }

    // Used in button creation method
    public Button getButton() {
        return new Button();
    }
} //end of ColorChomp.java
