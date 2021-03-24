//Authors: Powell

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ElephantsAndRhinos extends Application {

    public static Stage elephantStage = new Stage();
    public static Stage elephantStage2 = new Stage();
    public static Stage elephantStage3 = new Stage();
    public static Stage elephantStage4 = new Stage();
    public static Stage elephantStage5 = new Stage();
    public static Stage elephantStage6 = new Stage();
    public static int gameSelection = 2;
    private static String teamName;
    private static String[] BoxesOfTheBoard;
    private static String[] BoxesOfTheBoard2;
    private static String[] BoxesOfTheBoard3;
    private static String[] BoxesOfTheBoard4;
    private static String[] BoxesOfTheBoard5;
    private static String[] BoxesOfTheBoard6;
    private boolean gameOver = false;
    public static String game;
    private String gameTitle = "Mammal Battle!";
    private int teamDesignator;
    private int winLocal = 0;
    private Scene menuWindow;
    private int localK = 0;
    private boolean rhinoWinFlag = false;
    private boolean rhinoWinFlag2 = false;
    private boolean rhinoWinFlag3 = false;
    private boolean rhinoWinFlag4 = false;
    private boolean rhinoWinFlag5 = false;
    private boolean rhinoWinFlag6 = false;
    private boolean elephantWinFlag = false;
    private boolean elephantWinFlag2 = false;
    private boolean elephantWinFlag3 = false;
    private boolean elephantWinFlag4 = false;
    private boolean elephantWinFlag5 = false;
    private boolean elephantWinFlag6 = false;
    public static boolean elephantNoMoves = false;
    private static boolean elephantNoM = false;
    public static boolean elephantNoM2 = false;
    public static boolean elephantNoM3 = false;
    public static boolean elephantNoM4 = false;
    public static boolean elephantNoM5 = false;
    public static boolean elephantNoM6 = false;
    private static boolean rhinoNoM = false;
    public static boolean rhinoNoM2 = false;
    public static boolean rhinoNoM3 = false;
    public static boolean rhinoNoM4 = false;
    public static boolean rhinoNoM5 = false;
    public static boolean rhinoNoM6 = false;
    public static boolean erStarted = false;
    public static boolean erStarted2 = false;
    public static boolean erStarted3 = false;
    public static boolean erStarted4 = false;
    public static boolean erStarted5 = false;
    public static boolean erStarted6 = false;
    public static boolean rhinoNoMoves = false;
    public boolean gameLoaded = false;
    private int sizeOfBoard = 10;
    private File saveFileER = new File("saveGameER.txt");

    private Image elephantImg = new Image("https://i.imgur.com/OUW83Yg.png");

    private Image rhinoImg = new Image("https://i.imgur.com/6rgxE7k.png");

    private Image whiteImg = new Image("https://i.imgur.com/lP0lAFe.png");


    public void start(Stage primaryStage) {
        System.out.println("Elephants and Rhinos start method has been called");
        gameSelection = 2;

        if (Team.k == 1) {
            erStarted = true;
            System.out.println("erStarted = true");
            elephantStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            erStarted2 = true;
            System.out.println("erStarted2 = true");
            elephantStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            System.out.println("erStarted3 = true");
            erStarted3 = true;
            elephantStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            System.out.println("erStarted4 = true");
            erStarted4 = true;
            elephantStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            System.out.println("erStarted5 = true");
            erStarted5 = true;
            elephantStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            System.out.println("erStarted6 = true");
            erStarted6 = true;
            elephantStage6 = primaryStage;
            localK = 6;
        }


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
            elephantStage.hide();
        });

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> {
            game = "TAF";
            gameSelection = 0;
            System.out.println("Chosen game TAF");
            this.elephantStage.hide();
            ToadsAndFrogs TF = new ToadsAndFrogs();
            TF.start(ToadsAndFrogs.toadStage);
        });

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> {
            game = "CC";
            gameSelection = 4;
            System.out.println("Chosen game CC");
            elephantStage.hide();
            ColorChomp CC2 = new ColorChomp();
            CC2.start(ColorChomp.colorChompStage);
        });

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> {
            game = "C";
            gameSelection = 3;
            System.out.println("Chosen game C");
            elephantStage.hide();
            Chomp C = new Chomp();
            C.start(Chomp.chompStage);
        });

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> {
            game = "M";
            gameSelection = 5;
            System.out.println("Chosen game M");
            this.elephantStage.hide();
            Memory M = new Memory();
            M.start(Memory.fruitStage);
        });
        // END SWITCH GAME MENU //


        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton, elephantsButton, chompButton, colorChompButton, memoryButton);
        menuWindow = new Scene(menuLayout, 200, 300);


        boxesOfTheBoard(this.sizeOfBoard);

        // Loads the previously saved game
        loadGame();


        primaryStage.setTitle(gameTitle);
        if (localK == 1){
            gameBoard(this.elephantStage);
        } else if (localK == 2){
            gameBoard(this.elephantStage2);
        } else if (localK == 3){
            gameBoard(this.elephantStage3);
        } else if (localK == 4){
            gameBoard(this.elephantStage4);
        } else if (localK == 5){
            gameBoard(this.elephantStage5);
        } else if (localK == 6){
            gameBoard(this.elephantStage6);
        }

        teamDesignator = erFirstPlayer();
    }

    // This method creates the BoxesOfTheBoard[] according to the size of game board
    // provided by the user
    public void boxesOfTheBoard(int size) {
        if (gameLoaded == false) {
            if (localK == 1){
                BoxesOfTheBoard = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            } else if (localK == 2){
                BoxesOfTheBoard2 = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            } else if (localK == 3){
                BoxesOfTheBoard3 = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            } else if (localK == 4){
                BoxesOfTheBoard4 = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            } else if (localK == 5){
                BoxesOfTheBoard5 = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            } else if (localK == 6){
                BoxesOfTheBoard6 = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
            }
            //BoxesOfTheBoard = new String[]{"Elephant", "    ", "Rhino", "Elephant", "Elephant", "    ", "    ", "Rhino", "    ", "Rhino"};
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
                try (Scanner s = new Scanner(new File("saveGameER.txt")).useDelimiter(",\\s*")) {
                    while (s.hasNext()) {
                        for (int i = 0; i < sizeOfBoard; i++) {
                            read = s.next();
                            if (read.equals("Elephant") || read.equals("Rhino"))
                                BoxesOfTheBoard[i] = read;
                            else
                                BoxesOfTheBoard[i] = "    ";
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

    // This method sets the height and width of the stage according to the size of
    // game board.
    public void stageDimensions(Stage stage) {
        stage.setHeight(330);
        stage.setWidth(1030);
    } // End stageDimensions


    public int erFirstPlayer() {
        Team team = new Team();
        if (team.selected != true) {
            team.FirstPlayer();
        }
        return team.teamDesignator;
    }

    private void rhinosWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Team Has Won");
            alert.setHeaderText("Player One Wins Elephants And Rhinos!");
            alert.showAndWait();
            System.out.println("Player One Wins Elephants And Rhinos");
        } else {
            //winLocal++;
            System.out.println("Player One has Won Elephants and Rhinos");
            if (localK == 1){
                if (elephantNoM == true){
                    elephantNoMoves = true;
                }
                if (rhinoNoM == true){
                    rhinoNoMoves = true;
                }
            }
            Team.finalWinCheck();
        }
    }

    private void elephantsWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Team Has Won");
            alert.setHeaderText("Player Two Wins Elephants And Rhinos!");
            alert.showAndWait();
            System.out.println("Player Two Wins Elephants And Rhinos");
        } else {
            //winLocal++;
            System.out.println("Player Two has Won Elephants and Rhinos");
            if (localK == 1) {
                if (elephantNoM == true) {
                    elephantNoMoves = true;
                }
                if (rhinoNoM == true) {
                    rhinoNoMoves = true;
                }
            }
            Team.finalWinCheck();
        }
    }

    public void gameBoard(Stage stage) {
        if (localK == 1) {
            this.elephantStage = stage;
        } else if (localK == 2){
            this.elephantStage2 = stage;
        } else if (localK == 3){
            this.elephantStage3 = stage;
        } else if (localK == 4){
            this.elephantStage4 = stage;
        } else if (localK == 5){
            this.elephantStage5 = stage;
        } else if (localK == 6){
            this.elephantStage6 = stage;
        }
            HBox hbox = new HBox(10);

            for (int boxPlacememt = 0; boxPlacememt < sizeOfBoard; boxPlacememt++) {
                if (localK == 1){
                    teamName = BoxesOfTheBoard[boxPlacememt];
                } else if (localK == 2){
                    teamName = BoxesOfTheBoard2[boxPlacememt];
                } else if (localK == 3){
                    teamName = BoxesOfTheBoard3[boxPlacememt];
                } else if (localK == 4){
                    teamName = BoxesOfTheBoard4[boxPlacememt];
                } else if (localK == 5){
                    teamName = BoxesOfTheBoard5[boxPlacememt];
                } else if (localK == 6){
                    teamName = BoxesOfTheBoard6[boxPlacememt];
                }
                //String teamName = BoxesOfTheBoard[boxPlacememt];
                Button pane = new Button();

                if (teamName.equals("Elephant")) {
                    ImageView elephantImgView = new ImageView(elephantImg);
                    pane.setGraphic(elephantImgView);
                } else if (teamName.equals("Rhino")) {
                    ImageView rhinoImgView = new ImageView(rhinoImg);
                    pane.setGraphic(rhinoImgView);
                } else {
                    ImageView whiteImgView = new ImageView(whiteImg);
                    pane.setGraphic(whiteImgView);
                }

                hbox.getChildren().add(pane);
                if (teamName.equals("Elephant")) {
                    elephantMove(pane, boxPlacememt);
                } else if (teamName.equals("Rhino")) {
                    rhinoMove(pane, boxPlacememt);
                }
            }

            // This method saves the game by storing the BoxesOfTheBoard[] into a file
            Button saveAndQuit = new Button("Save & Quit");
            saveAndQuit.setOnAction((event) -> {
                try {
                    File saveFileER = new File("saveGameER.txt");
                    FileWriter newSave = new FileWriter("saveGameER.txt");
                    for (int i = 0; i < 10; i++) {
                        newSave.write(BoxesOfTheBoard[i] + ",\n");
                    }
                    newSave.close();
                } catch (IOException e) {
                    System.out.println("An error occured.");
                    e.printStackTrace();
                }
                stage.close();
            });

            // BACK TO GAME MENU //
            Button backButton2 = new Button("Back to Game Menu");
            backButton2.setOnAction(event -> {
                stage.setScene(menuWindow);
                stage.setTitle("Game Selection!");
            });
            // END BACK TO GAME MENU //

            VBox vbox = new VBox(10);
            vbox.getChildren().add(hbox);
            vbox.getChildren().add(new Label("Player One = Rhinos || Player Two = Elephants"));
            vbox.getChildren().add(new Label("If you can't move, then you lose the game."));
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(backButton2, saveAndQuit);

            ScrollPane boardSize = new ScrollPane(vbox);
            stage.setScene(new Scene(boardSize, 1015, 290));
            stage.show();

            // This condenses buttons when window width is too small; looks better
            boardSize.setFitToWidth(true);
      //  }
    }


    public void teamSwitch(Stage stage) {
        if (localK == 1){
            gameBoard(this.elephantStage);
        } else if (localK == 2){
            gameBoard(this.elephantStage2);
        } else if (localK == 3){
            gameBoard(this.elephantStage3);
        } else if (localK == 4){
            gameBoard(this.elephantStage4);
        } else if (localK == 5){
            gameBoard(this.elephantStage5);
        } else if (localK == 6){
            gameBoard(this.elephantStage6);
        }

        Team.teamDesignator = 1 - Team.teamDesignator;
        if (gameOver == false) {
            Team.teamBox();
        }
        if (gameOver == true && Team.gameNumber > 1 && Team.finalWin == false){
            Team.teamBox();
        }
    }

    private void checkWin() {
        for (int checkWin = 0; checkWin < (sizeOfBoard - 1); checkWin++) {
            if (localK == 1){
                if (BoxesOfTheBoard[checkWin] == "Elephant" && BoxesOfTheBoard[checkWin + 1] == "    ") {
                    this.rhinoWinFlag = false;
                    this.elephantNoM = false;
                    break;
                } else {
                    this.rhinoWinFlag = true;
                    this.elephantNoM = true;
                }
            }
            if (localK == 2){
                    if (BoxesOfTheBoard2[checkWin] == "Elephant" && BoxesOfTheBoard2[checkWin + 1] == "    ") {
                        this.rhinoWinFlag2 = false;
                        this.elephantNoM2 = false;
                        break;
                    } else {
                        this.rhinoWinFlag2 = true;
                        this.elephantNoM2 = true;
                    }
            }
            if (localK == 3){
                    if (BoxesOfTheBoard3[checkWin] == "Elephant" && BoxesOfTheBoard3[checkWin + 1] == "    ") {
                        this.rhinoWinFlag3 = false;
                        this.elephantNoM3 = false;
                        break;
                    } else {
                        this.rhinoWinFlag3 = true;
                        this.elephantNoM3 = true;
                    }
            }
            if (localK == 4){
                if (BoxesOfTheBoard4[checkWin] == "Elephant" && BoxesOfTheBoard4[checkWin + 1] == "    ") {
                    this.rhinoWinFlag4 = false;
                    this.elephantNoM4 = false;
                    break;
                } else {
                    this.rhinoWinFlag4 = true;
                    this.elephantNoM4 = true;
                }
            }
            if (localK == 5){
                if (BoxesOfTheBoard5[checkWin] == "Elephant" && BoxesOfTheBoard5[checkWin + 1] == "    ") {
                    this.rhinoWinFlag5 = false;
                    this.elephantNoM5 = false;
                    break;
                } else {
                    this.rhinoWinFlag5 = true;
                    this.elephantNoM5 = true;
                }
            }
            if (localK == 6) {
                if (BoxesOfTheBoard6[checkWin] == "Elephant" && BoxesOfTheBoard6[checkWin + 1] == "    ") {
                    this.rhinoWinFlag6 = false;
                    this.elephantNoM6 = false;
                    break;
                } else {
                    this.rhinoWinFlag6 = true;
                    this.elephantNoM6 = true;
                }
            }
        }

        if (localK == 1) {
            if (this.rhinoWinFlag == true && this.elephantNoM == true) {
                rhinosWin();
            }
        } else if (localK == 2){
            if (this.rhinoWinFlag2 == true && this.elephantNoM2 == true) {
                rhinosWin();
            }
        } else if (localK == 3){
            if (this.rhinoWinFlag3 == true && this.elephantNoM3 == true) {
                rhinosWin();
            }
        } else if (localK == 4){
            if (this.rhinoWinFlag4 == true && this.elephantNoM4 == true) {
                rhinosWin();
            }
        } else if (localK == 5){
            if (this.rhinoWinFlag5 == true && this.elephantNoM5 == true) {
                rhinosWin();
            }
        } else if (localK == 6){
            if (this.rhinoWinFlag6 == true && this.elephantNoM6 == true) {
                rhinosWin();
            }
        }

        ////////////////////////////////////////////////////

        for (int checkWin = (sizeOfBoard - 1); checkWin > 0; checkWin--) {
            if (localK == 1) {
                if (BoxesOfTheBoard[checkWin] == "Rhino" && BoxesOfTheBoard[checkWin - 1] == "    ") {
                    this.elephantWinFlag = false;
                    this.rhinoNoM = false;
                    break;
                } else {
                    this.elephantWinFlag = true;
                    this.rhinoNoM = true;
                }
            }
            if (localK == 2) {
                if (BoxesOfTheBoard2[checkWin] == "Rhino" && BoxesOfTheBoard2[checkWin - 1] == "    ") {
                    this.elephantWinFlag2 = false;
                    this.rhinoNoM2 = false;
                    break;
                } else {
                    this.elephantWinFlag2 = true;
                    this.rhinoNoM2 = true;
                }
            }
            if (localK == 3) {
                if (BoxesOfTheBoard3[checkWin] == "Rhino" && BoxesOfTheBoard3[checkWin - 1] == "    ") {
                    this.elephantWinFlag3 = false;
                    this.rhinoNoM3 = false;
                    break;
                } else {
                    this.elephantWinFlag3 = true;
                    this.rhinoNoM3 = true;
                }
            }
            if (localK == 4) {
                if (BoxesOfTheBoard4[checkWin] == "Rhino" && BoxesOfTheBoard4[checkWin - 1] == "    ") {
                    this.elephantWinFlag4 = false;
                    this.rhinoNoM4 = false;
                    break;
                } else {
                    this.elephantWinFlag4 = true;
                    this.rhinoNoM4 = true;
                }
            }
            if (localK == 5) {
                if (BoxesOfTheBoard5[checkWin] == "Rhino" && BoxesOfTheBoard5[checkWin - 1] == "    ") {
                    this.elephantWinFlag5 = false;
                    this.rhinoNoM5 = false;
                    break;
                } else {
                    this.elephantWinFlag5 = true;
                    this.rhinoNoM5 = true;
                }
            }
            if (localK == 6) {
                if (BoxesOfTheBoard6[checkWin] == "Rhino" && BoxesOfTheBoard6[checkWin - 1] == "    ") {
                    this.elephantWinFlag6 = false;
                    this.rhinoNoM6 = false;
                    break;
                } else {
                    this.elephantWinFlag6 = true;
                    this.rhinoNoM6 = true;
                }
            }
        }

        if (localK == 1) {
            if (this.elephantWinFlag == true && this.rhinoNoM == true) {
                elephantsWin();
            }
        } else if (localK == 2){
            if (this.elephantWinFlag2 == true && this.rhinoNoM2 == true) {
                elephantsWin();
            }
        } else if (localK == 3){
            if (this.elephantWinFlag3 == true && this.rhinoNoM3 == true) {
                elephantsWin();
            }
        } else if (localK == 4){
            if (this.elephantWinFlag4 == true && this.rhinoNoM4 == true) {
                elephantsWin();
            }
        } else if (localK == 5){
            if (this.elephantWinFlag5 == true && this.rhinoNoM5 == true) {
                elephantsWin();
            }
        } else if (localK == 6){
            if (this.elephantWinFlag6 == true && this.rhinoNoM6 == true) {
                elephantsWin();
            }
        }
    }
    

    public void elephantMove(Button elephantButton, int elephantSpace) {
        elephantButton.setOnAction((event) -> {
            System.out.println("Elephant selected!");
            if (Team.teamDesignator == 0) {
                return;
            }
            if (localK == 1) {
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard[elephantSpace] = "    ";
                    BoxesOfTheBoard[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage);
                }
            } else if (localK == 2){
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard2[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard2[elephantSpace] = "    ";
                    BoxesOfTheBoard2[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage2);
                }
            } else if (localK == 3) {
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard3[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard3[elephantSpace] = "    ";
                    BoxesOfTheBoard3[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage3);
                }
            } else if (localK == 4) {
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard4[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard4[elephantSpace] = "    ";
                    BoxesOfTheBoard4[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage4);
                }
            } else if (localK == 5) {
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard5[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard5[elephantSpace] = "    ";
                    BoxesOfTheBoard5[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage5);
                }
            } else if (localK == 6) {
                if (elephantSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard6[elephantSpace + 1] == "    ") {
                    BoxesOfTheBoard6[elephantSpace] = "    ";
                    BoxesOfTheBoard6[elephantSpace + 1] = "Elephant";
                    checkWin();
                    teamSwitch(elephantStage6);
                }
            }
        });
    }


    public void rhinoMove(Button rhinoButton, int rhinoSpace) {
        rhinoButton.setOnAction((event) -> {
            System.out.println("Rhino selected!");
            if (Team.teamDesignator == 1) {
                return;
            }
            if (localK == 1) {
                if (rhinoSpace > 0 && BoxesOfTheBoard[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard[rhinoSpace] = "    ";
                    BoxesOfTheBoard[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage);
                }
            } else if (localK == 2){
                if (rhinoSpace > 0 && BoxesOfTheBoard2[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard2[rhinoSpace] = "    ";
                    BoxesOfTheBoard2[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage2);
                }
            } else if (localK == 3){
                if (rhinoSpace > 0 && BoxesOfTheBoard3[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard3[rhinoSpace] = "    ";
                    BoxesOfTheBoard3[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage3);
                }
            } else if (localK == 4){
                if (rhinoSpace > 0 && BoxesOfTheBoard4[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard4[rhinoSpace] = "    ";
                    BoxesOfTheBoard4[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage4);
                }
            } else if (localK == 5){
                if (rhinoSpace > 0 && BoxesOfTheBoard5[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard5[rhinoSpace] = "    ";
                    BoxesOfTheBoard5[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage5);
                }
            } else if (localK == 6){
                if (rhinoSpace > 0 && BoxesOfTheBoard6[rhinoSpace - 1] == "    ") {
                    BoxesOfTheBoard6[rhinoSpace] = "    ";
                    BoxesOfTheBoard6[rhinoSpace - 1] = "Rhino";
                    checkWin();
                    teamSwitch(elephantStage6);
                }
            }
        });
    }

} //end of ElephantsAndRhinos.java
