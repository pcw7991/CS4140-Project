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

public class Memory extends Application {
    public static Stage fruitStage = new Stage();
    public static Stage fruitStage2 = new Stage();
    public static Stage fruitStage3 = new Stage();
    public static Stage fruitStage4 = new Stage();
    public static Stage fruitStage5 = new Stage();
    public static Stage fruitStage6 = new Stage();
    public boolean gameRestarted = false;
    public boolean gameLoaded = false;
    private boolean gameOver = false;
    private int teamDesignator;
    private boolean bananaMatched = false;
    private boolean cherryMatched = false;
    private boolean grapeMatched = false;
    private boolean pineappleMatched = false;
    private boolean melonMatched = false;
    private boolean appleMatched = false;
    private boolean oneWinFlag = true;
    private boolean twoWinFlag = true;
    private int localK = 0;
    public int gameSelection = 5;
    private int moveCount = 0;
    public static String game;
    private Stage stage;
    public String[] fruitButtons;
    private Scene menuWindow;
    private final File saveFileM = new File("saveGameM.txt");
    private final Image apple = new Image("https://i.imgur.com/pB7nnJB.png");
    private final Image banana = new Image("https://i.imgur.com/oZEs52w.png");
    private final Image cherry = new Image("https://i.imgur.com/rLFsOyr.png");
    private final Image grape = new Image("https://i.imgur.com/xjEoPQN.png");
    private final Image pineapple = new Image("https://i.imgur.com/5ncVF5l.png");
    private final Image melon = new Image("https://i.imgur.com/uljsk1t.png");
    private final Image hidden = new Image("https://i.imgur.com/H0XLvlx.png");


    /* public static void main(String[] args) {

    }*/

    //kicks off the game
    public void start(Stage primaryStage) {
        // sets our window we created as the primaryStage (the first window we see)
        if (Team.k == 1) {
            fruitStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            fruitStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            fruitStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            fruitStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            fruitStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            fruitStage6 = primaryStage;
            localK = 6;
        }
        gameSelection = 5;
        game = "M";

        // SWITCH GAME MENU //
        Label chooseLabel = new Label("Choose Your Game");

        //button to go to Domino Game
        Button dominoButton = new Button("Toppling Dominoes");
        dominoButton.setOnAction(event -> {
            System.out.println("Chosen game TD");
            TopplingDominoes TD = new TopplingDominoes();
            TD.gameRestarted = true;
            TopplingDominoes.game = "TD";
            TD.start(TopplingDominoes.dominoStage);
            fruitStage.hide();
        });

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> {
            game = "TAF";
            gameSelection = 0;
            System.out.println("Chosen game TAF");
            fruitStage.hide();
            ToadsAndFrogs TF = new ToadsAndFrogs();
            TF.start(ToadsAndFrogs.toadStage);
        });

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> {
            game = "CC";
            gameSelection = 4;
            System.out.println("Chosen game CC");
            fruitStage.hide();
            ColorChomp CC2 = new ColorChomp();
            CC2.start(ColorChomp.colorChompStage);
        });

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> {
            game = "C";
            gameSelection = 3;
            System.out.println("Chosen game C");
            fruitStage.hide();
            Chomp C = new Chomp();
            C.start(Chomp.chompStage);
        });

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> {
            game = "ER";
            gameSelection = 2;
            System.out.println("Chosen game ER");
            fruitStage.hide();
            ElephantsAndRhinos ER = new ElephantsAndRhinos();
            ER.start(ElephantsAndRhinos.elephantStage);
        });
        // END SWITCH GAME MENU //


        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton, elephantsButton, chompButton, colorChompButton, memoryButton);
        menuWindow = new Scene(menuLayout, 200, 300);


        // String of arrays to hold chomp button text
        this.fruitButtons = new String[12];
        for (int space = 0; space < 12; ) {
            fruitButtons[space] = "    ";
            space = space + 1;
        }
        //top row
        this.fruitButtons[0] = "hidden";
        this.fruitButtons[1] = "hidden";
        this.fruitButtons[2] = "hidden";
        this.fruitButtons[3] = "hidden";
        //middle row
        this.fruitButtons[4] = "hidden";
        this.fruitButtons[5] = "hidden";
        this.fruitButtons[6] = "hidden";
        this.fruitButtons[7] = "hidden";
        //bottom row
        this.fruitButtons[8] = "hidden";
        this.fruitButtons[9] = "hidden";
        this.fruitButtons[10] = "hidden";
        this.fruitButtons[11] = "hidden";


        //mFirstPlayer() only shows up if C is the selected game and it is not reloaded
        if (game.equals("M")) {
            loadGameM();
            teamDesignator = mFirstPlayer();
        }

        if (localK == 1){
            gameBoard(this.fruitStage);
        } else if (localK == 2){
            gameBoard(this.fruitStage2);
        } else if (localK == 3){
            gameBoard(this.fruitStage3);
        } else if (localK == 4){
            gameBoard(this.fruitStage4);
        } else if (localK == 5){
            gameBoard(this.fruitStage5);
        } else if (localK == 6){
            gameBoard(this.fruitStage6);
        }


    } //end start

    // Prompts the user to load the previous game or not and sets the game board.
    public void loadGameM() {
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
                try (Scanner s = new Scanner(new File("saveGameM.txt")).useDelimiter(",\\s*")) {
                    while (s.hasNext()) {
                        for (int i = 0; i < 12; i++) {
                            read = s.next();
                            this.fruitButtons[i] = read;
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

    //Updates board
    public void boardUpdate(Stage stage) {
        if (localK == 1){
            gameBoard(this.fruitStage);
        } else if (localK == 2){
            gameBoard(this.fruitStage2);
        } else if (localK == 3){
            gameBoard(this.fruitStage3);
        } else if (localK == 4){
            gameBoard(this.fruitStage4);
        } else if (localK == 5){
            gameBoard(this.fruitStage5);
        } else if (localK == 6){
            gameBoard(this.fruitStage6);
        }
    } // end boardUpdate

    public int mFirstPlayer(){
        Team team = new Team();
        if (team.selected != true) {
            team.FirstPlayer();
        }
        return team.teamDesignator;
    }// end tdFirstPlayer

    //Switches teams per turn
    public void teamSwitch(Stage stage) {
        if (localK == 1){
            gameBoard(this.fruitStage);
        } else if (localK == 2){
            gameBoard(this.fruitStage2);
        } else if (localK == 3){
            gameBoard(this.fruitStage3);
        } else if (localK == 4){
            gameBoard(this.fruitStage4);
        } else if (localK == 5){
            gameBoard(this.fruitStage5);
        } else if (localK == 6){
            gameBoard(this.fruitStage6);
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
        if (game.equals("M")) {
            String[] teams = {"Player One", "Player Two"};
            stage.setTitle("Memory Game: It's the " + teams[Team.teamDesignator] + " team's Turn.");
            if (localK == 1) {
                this.fruitStage = stage;
            } else if (localK == 2){
                this.fruitStage2 = stage;
            } else if (localK == 3){
                this.fruitStage3 = stage;
            } else if (localK == 4){
                this.fruitStage4 = stage;
            } else if (localK == 5){
                this.fruitStage5 = stage;
            } else if (localK == 6){
                this.fruitStage6 = stage;
            }


            HBox hboxTopRow = new HBox(10);
            HBox hboxCenterRow = new HBox(10);
            HBox hboxBottomRow = new HBox(10);

            // This method saves the game by storing the BoxesOfTheBoard[] into a file
            Button saveAndQuitM = new Button("Save & Quit");
            saveAndQuitM.setOnAction((event) -> {
                try {
                    File saveFileM = new File("saveGameM.txt");
                    FileWriter newSave = new FileWriter("saveGameM.txt");
                    for (int i = 0; i < 12; i++) {
                        newSave.write(fruitButtons[i] + ",\n");
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
            stage.setScene(new Scene(border1, 700, 250));

            Button backButton2 = new Button("Back to Game Menu");
            backButton2.setOnAction(event -> {
                fruitStage.setScene(menuWindow);
                fruitStage.setTitle("Game Selection!");
            });
            Button restart = new Button("Restart");
            restart.setOnAction(event -> {
                Memory mRestart = new Memory();
                mRestart.gameRestarted = true;
                mRestart.start(fruitStage);
            });


            stage.show();

            // Generates top row of buttons
            for (int buttonCreation = 0; buttonCreation < 4; buttonCreation++) {
                String teamName = fruitButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxTopRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("banana")) {
                    ImageView bananaView = new ImageView(banana);
                    generatedButtons.setGraphic(bananaView);
                } else if (teamName.equals("cherry")) {
                    ImageView cherryView = new ImageView(cherry);
                    generatedButtons.setGraphic(cherryView);
                } else if (teamName.equals("grape")) {
                    ImageView grapeView = new ImageView(grape);
                    generatedButtons.setGraphic(grapeView);
                } else if (teamName.equals("hidden")) {
                    ImageView hiddenView = new ImageView(hidden);
                    generatedButtons.setGraphic(hiddenView);
                    Move(generatedButtons, buttonCreation);
                }
            }
            hboxTopRow.getChildren().addAll(backButton2, restart, saveAndQuitM);


            // Generates middle row of buttons
            for (int buttonCreation = 4; buttonCreation < 8; buttonCreation++) {
                String teamName = fruitButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxCenterRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("pineapple")) {
                    ImageView pineappleView = new ImageView(pineapple);
                    generatedButtons.setGraphic(pineappleView);
                } else if (teamName.equals("apple")) {
                    ImageView appleView = new ImageView(apple);
                    generatedButtons.setGraphic(appleView);
                } else if (teamName.equals("cherry")) {
                    ImageView cherryView = new ImageView(cherry);
                    generatedButtons.setGraphic(cherryView);
                } else if (teamName.equals("melon")) {
                    ImageView melonView = new ImageView(melon);
                    generatedButtons.setGraphic(melonView);
                } else if (teamName.equals("hidden")) {
                    ImageView hiddenView = new ImageView(hidden);
                    generatedButtons.setGraphic(hiddenView);
                    Move(generatedButtons, buttonCreation);
                }
            }

            // Generates bottom row of buttons
            for (int buttonCreation = 8; buttonCreation < 12; buttonCreation++) {
                String teamName = fruitButtons[buttonCreation];
                Button generatedButtons = this.getButton();
                hboxBottomRow.getChildren().addAll(generatedButtons);
                if (teamName.equals("grape")) {
                    ImageView grapeView = new ImageView(grape);
                    generatedButtons.setGraphic(grapeView);
                } else if (teamName.equals("apple")) {
                    ImageView appleView = new ImageView(apple);
                    generatedButtons.setGraphic(appleView);
                } else if (teamName.equals("pineapple")) {
                    ImageView pineappleView = new ImageView(pineapple);
                    generatedButtons.setGraphic(pineappleView);
                } else if (teamName.equals("melon")) {
                    ImageView melonView = new ImageView(melon);
                    generatedButtons.setGraphic(melonView);
                } else if (teamName.equals("hidden")) {
                    ImageView hiddenView = new ImageView(hidden);
                    generatedButtons.setGraphic(hiddenView);
                    Move(generatedButtons, buttonCreation);
                }
            }
        }
    } //end gameBoard


    //code for a move
    private void Move(Button button, int fruit) {
        button.setOnAction((event) -> {
            moveCount++;
            //System.out.println(moveCount);
            //Banana matching v
            if (fruit == 0) {
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[0] = "banana";
                boardUpdate(fruitStage);
                if (this.fruitButtons[3].equals("banana")) {
                    bananaMatched = true;
                }
                if (bananaMatched){
                    this.fruitButtons[0] = "banana";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !bananaMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Cherry matching v
            } else if (fruit == 1) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[1] = "cherry";
                boardUpdate(fruitStage);
                if (this.fruitButtons[6].equals("cherry")) {
                    cherryMatched = true;
                }
                if (cherryMatched){
                    this.fruitButtons[1] = "cherry";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !cherryMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Grape matching v
            } else if (fruit == 2) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[2] = "grape";
                boardUpdate(fruitStage);
                if (this.fruitButtons[8].equals("grape")) {
                    grapeMatched = true;
                }
                if (grapeMatched){
                    this.fruitButtons[2] = "grape";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !grapeMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Banana matching v
            } else if (fruit == 3) {
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[3] = "banana";
                boardUpdate(fruitStage);
                if (this.fruitButtons[0].equals("banana")) {
                    bananaMatched = true;
                }
                if (bananaMatched){
                    this.fruitButtons[3] = "banana";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !bananaMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Pineapple matching v
            } else if (fruit == 4) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[4] = "pineapple";
                boardUpdate(fruitStage);
                if (this.fruitButtons[10].equals("pineapple")) {
                    pineappleMatched = true;
                }
                if (pineappleMatched){
                    this.fruitButtons[4] = "pineapple";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !pineappleMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Apple matching v
            } else if (fruit == 5) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[5] = "apple";
                boardUpdate(fruitStage);
                if (this.fruitButtons[9].equals("apple")) {
                    appleMatched = true;
                }
                if (appleMatched){
                    this.fruitButtons[5] = "apple";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !appleMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Cherry matching v
            } else if (fruit == 6) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[6] = "cherry";
                boardUpdate(fruitStage);
                if (this.fruitButtons[1].equals("cherry")) {
                    cherryMatched = true;
                }
                if (cherryMatched){
                    this.fruitButtons[6] = "cherry";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !cherryMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Melon matching v
            } else if (fruit == 7) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                this.fruitButtons[7] = "melon";
                boardUpdate(fruitStage);
                if (this.fruitButtons[11].equals("melon")) {
                    melonMatched = true;
                }
                if (melonMatched){
                    this.fruitButtons[7] = "melon";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !melonMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Grape matching v
            } else if (fruit == 8) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[8] = "grape";
                boardUpdate(fruitStage);
                if (this.fruitButtons[2].equals("grape")) {
                    grapeMatched = true;
                }
                if (grapeMatched){
                    this.fruitButtons[8] = "grape";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !grapeMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                // Apple matching v
            } else if (fruit == 9) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[9] = "apple";
                boardUpdate(fruitStage);
                if (this.fruitButtons[5].equals("apple")) {
                    appleMatched = true;
                }
                if (appleMatched){
                    this.fruitButtons[9] = "apple";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !appleMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Pineapple matching v
            } else if (fruit == 10) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                if (!melonMatched){
                    this.fruitButtons[7] = "hidden";
                    this.fruitButtons[11] = "hidden";
                }
                this.fruitButtons[10] = "pineapple";
                boardUpdate(fruitStage);
                if (this.fruitButtons[4].equals("pineapple")) {
                    pineappleMatched = true;
                }
                if (pineappleMatched){
                    this.fruitButtons[10] = "pineapple";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !pineappleMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
                //Melon matching v
            } else if (fruit == 11) {
                if (!bananaMatched){
                    this.fruitButtons[0] = "hidden";
                    this.fruitButtons[3] = "hidden";
                }
                if (!cherryMatched){
                    this.fruitButtons[1] = "hidden";
                    this.fruitButtons[6] = "hidden";
                }
                if (!grapeMatched){
                    this.fruitButtons[2] = "hidden";
                    this.fruitButtons[8] = "hidden";
                }
                if (!pineappleMatched){
                    this.fruitButtons[4] = "hidden";
                    this.fruitButtons[10] = "hidden";
                }
                if (!appleMatched){
                    this.fruitButtons[5] = "hidden";
                    this.fruitButtons[9] = "hidden";
                }
                this.fruitButtons[11] = "melon";
                boardUpdate(fruitStage);
                if (this.fruitButtons[7].equals("melon")) {
                    melonMatched = true;
                }
                if (melonMatched){
                    this.fruitButtons[11] = "melon";
                    boardUpdate(fruitStage);
                }
                if (moveCount%2 == 0 && !melonMatched){
                    moveCount = -1;
                    teamSwitch(fruitStage);
                }
                twoWinFlag = true;
                oneWinFlag = true;
            }
            for (int i = 0; i < 12; i++){
                if (this.fruitButtons[i].equals("hidden")){
                    oneWinFlag = false;
                    twoWinFlag = false;
                }
            }
            if (Team.teamDesignator == 0 && oneWinFlag == true){
                oneWin();
            } else if (Team.teamDesignator == 1 && oneWinFlag == true){
                twoWin();
            }
        });
    } //end move

    private void oneWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over - Player One Wins Memory!");
            alert.setHeaderText("Game Over - Player One Wins Memory!");
            alert.showAndWait();
            System.out.println("Game Over - Player One Wins Memory!");
        } else {
            System.out.println("Player One has Won Memory");
            Team.playerOneWins++;
            Team.finalWinCheck();
            if (Team.finalWin == false) {
                //teamSwitch(fruitStage);
            }
        }
    }

    private void twoWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over - Player Two Wins Memory!");
            alert.setHeaderText("Game Over - Player Two Wins Memory!");
            alert.showAndWait();
            System.out.println("Game Over - Player Two Wins Memory!");
        } else {
            System.out.println("Player Two has Won Memory");
            Team.playerTwoWins++;
            Team.finalWinCheck();
            if (Team.finalWin == false) {
                //teamSwitch(fruitStage);
            }
        }
    }

    // Used in button creation method
    public Button getButton() {
        return new Button();
    }
} //end of Memory.java
