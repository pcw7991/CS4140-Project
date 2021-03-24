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

//Start of State Pattern design - Sets the title on the game board to show which turn it is
interface TurnState {
    public void turn();
}

class TurnStateContext {
    private TurnState currentState;

    public TurnStateContext() {
        currentState = new ToadNotification();
    }

    public void setState(TurnState state) {
        currentState = state;
    }

    public void turn() {
        currentState.turn();
    }
}

class ToadNotification implements TurnState {
    @Override
    public void turn() {
        ToadsAndFrogs.toadStage.setTitle("Amphibian Battle! Player Two's Turn.");
    }
}

class FrogNotification implements TurnState {
    @Override
    public void turn() {
        ToadsAndFrogs.toadStage.setTitle("Amphibian Battle! Player One's Turn.");
    }

} //End State Pattern Design

public class ToadsAndFrogs extends Application {

    public static Stage toadStage = new Stage();
    public static Stage toadStage2 = new Stage();
    public static Stage toadStage3 = new Stage();
    public static Stage toadStage4 = new Stage();
    public static Stage toadStage5 = new Stage();
    public static Stage toadStage6 = new Stage();
    public static int gameSelection = 0;
    private static String[] BoxesOfTheBoard;
    private static String teamName;
    private static String[] BoxesOfTheBoard2;
    private static String[] BoxesOfTheBoard3;
    private static String[] BoxesOfTheBoard4;
    private static String[] BoxesOfTheBoard5;
    private static String[] BoxesOfTheBoard6;
    public static String game;
    private String gameTitle = "Amphibian Battle!";
    private int teamDesignator;
    private Scene menuWindow;
    private int winLocal = 0;
    private boolean frogWinFlag = false;
    private boolean frogWinFlag2 = false;
    private boolean frogWinFlag3 = false;
    private boolean frogWinFlag4 = false;
    private boolean frogWinFlag5 = false;
    private boolean frogWinFlag6 = false;
    private boolean toadWinFlag = false;
    private boolean toadWinFlag2 = false;
    private boolean toadWinFlag3 = false;
    private boolean toadWinFlag4 = false;
    private boolean toadWinFlag5 = false;
    private boolean toadWinFlag6 = false;
    public static boolean tfStarted = false;
    public static boolean tfStarted2 = false;
    public static boolean tfStarted3 = false;
    public static boolean tfStarted4 = false;
    public static boolean tfStarted5 = false;
    public static boolean tfStarted6 = false;
    public static boolean toadNoMoves = false;
    public static boolean toadNoM2 = false;
    public static boolean toadNoM3 = false;
    public static boolean toadNoM4 = false;
    public static boolean toadNoM5 = false;
    public static boolean toadNoM6 = false;
    public static boolean frogNoMoves = false;
    public static boolean frogNoM2 = false;
    public static boolean frogNoM3 = false;
    public static boolean frogNoM4 = false;
    public static boolean frogNoM5 = false;
    public static boolean frogNoM6 = false;
    private int localK = 0;
    public boolean gameLoaded = false;
    private boolean gameOver = false;
    private int sizeOfBoard = BoardSize.boardSize();
    private File saveFile10 = new File("saveGame10.txt");
    private File saveFile12 = new File("saveGame12.txt");
    private File saveFile15 = new File("saveGame15.txt");

    private Image toadImg = new Image("https://i.imgur.com/8v9Wtit.png");

    private Image frogImg = new Image("https://i.imgur.com/de44o56.png");

    private Image whiteImg = new Image("https://i.imgur.com/lP0lAFe.png");


    public void start(Stage primaryStage) {
        if (Team.k == 1) {
            tfStarted = true;
            System.out.println("erStarted = true");
            toadStage = primaryStage;
            localK = 1;
        } else if (Team.k == 2){
            tfStarted2 = true;
            System.out.println("erStarted2 = true");
            toadStage2 = primaryStage;
            localK = 2;
        } else if (Team.k == 3){
            System.out.println("erStarted3 = true");
            tfStarted3 = true;
            toadStage3 = primaryStage;
            localK = 3;
        } else if (Team.k == 4){
            System.out.println("erStarted4 = true");
            tfStarted4 = true;
            toadStage4 = primaryStage;
            localK = 4;
        } else if (Team.k == 5){
            System.out.println("erStarted5 = true");
            tfStarted5 = true;
            toadStage5 = primaryStage;
            localK = 5;
        } else if (Team.k == 6){
            System.out.println("erStarted6 = true");
            tfStarted6 = true;
            toadStage6 = primaryStage;
            localK = 6;
        }

        gameSelection = 0;


        // SWITCH GAME MENU //
        Label chooseLabel = new Label("Choose Your Game");

        Button dominoButton = new Button("Toppling Dominoes");
        dominoButton.setOnAction(event -> {
            System.out.println("Chosen game TD");
            TopplingDominoes TD = new TopplingDominoes();
            TD.gameRestarted = true;
            TD.game = "TD";
            TD.start(TopplingDominoes.dominoStage);
            toadStage.hide();
        });

        // button to go to toads and frogs game
        Button toadsButton = new Button("Toads and Frogs");
        toadsButton.setOnAction(event -> gameBoard(primaryStage));

        // button to go to ColorChomp game
        Button colorChompButton = new Button("Colored Chomp");
        colorChompButton.setOnAction(event -> {
            game = "CC";
            gameSelection = 4;
            System.out.println("Chosen game CC");
            toadStage.hide();
            ColorChomp CC2 = new ColorChomp();
            CC2.start(ColorChomp.colorChompStage);
        });

        // button to go to Chomp game
        Button chompButton = new Button("Chomp");
        chompButton.setOnAction(event -> {
            game = "C";
            gameSelection = 3;
            System.out.println("Chosen game C");
            toadStage.hide();
            Chomp C = new Chomp();
            C.start(Chomp.chompStage);
        });

        //button to go to Elephants and Rhinos
        Button elephantsButton = new Button("Elephants and Rhinos");
        elephantsButton.setOnAction(event -> {
            game = "ER";
            gameSelection = 2;
            System.out.println("Chosen game ER");
            this.toadStage.hide();
            ElephantsAndRhinos ER = new ElephantsAndRhinos();
            ER.start(ElephantsAndRhinos.elephantStage);
        });

        // button to go to Memory game
        Button memoryButton = new Button("Memory");
        memoryButton.setOnAction(event -> {
            game = "M";
            gameSelection = 5;
            System.out.println("Chosen game M");
            this.toadStage.hide();
            Memory M = new Memory();
            M.start(Memory.fruitStage);
        });

        // END SWITCH GAME MENU //


        // game menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(chooseLabel, dominoButton, toadsButton, elephantsButton, chompButton, colorChompButton, memoryButton);
        menuWindow = new Scene(menuLayout, 200, 300);


        toadStage = primaryStage;

        boxesOfTheBoard(sizeOfBoard);

        // Loads the previously saved game
        loadGame();


        primaryStage.setTitle(this.gameTitle);
        if (localK == 1){
            gameBoard(this.toadStage);
        } else if (localK == 2){
            gameBoard(this.toadStage2);
        } else if (localK == 3){
            gameBoard(this.toadStage3);
        } else if (localK == 4){
            gameBoard(this.toadStage4);
        } else if (localK == 5){
            gameBoard(this.toadStage5);
        } else if (localK == 6){
            gameBoard(this.toadStage6);
        }

        teamDesignator = tfFirstPlayer();
    }

    // This method creates the BoxesOfTheBoard[] according to the size of game board
    // provided by the user
    public  void boxesOfTheBoard(int size) {
        if (gameLoaded == false) {
            if (localK == 1) {
                if (size == 10) {
                    BoxesOfTheBoard = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            } else if (localK == 2) {
                if (size == 10) {
                    BoxesOfTheBoard2 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard2 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard2 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            } else if (localK == 3) {
                if (size == 10) {
                    BoxesOfTheBoard3 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard3 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard3 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            } else if (localK == 4) {
                if (size == 10) {
                    BoxesOfTheBoard4 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard4 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard4 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            } else if (localK == 5) {
                if (size == 10) {
                    BoxesOfTheBoard5 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard5 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard5 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            } else if (localK == 6) {
                if (size == 10) {
                    BoxesOfTheBoard6 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "    ",
                            "Frog"};
                } else if (size == 12) {
                    BoxesOfTheBoard6 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "Frog", "    ", "Frog",
                            "Toad", "    ", "Frog"};
                } else {
                    BoxesOfTheBoard6 = new String[]{"Toad", "    ", "Frog", "Toad", "Toad", "    ", "    ", "Frog", "Toad",
                            "    ", "Toad", "Frog", "    ", "Frog", "Frog"};
                }
            }
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
            stage.setHeight(330);
            stage.setWidth(1030);
        } else if (sizeOfBoard == 12) {
            stage.setHeight(330);
            stage.setWidth(1240);
        } else {
            stage.setHeight(330);
            stage.setWidth(1530);
        }
    } // End stageDimensions


    public int tfFirstPlayer(){
        Team team = new Team();
        if (team.selected != true) {
            team.FirstPlayer();
        }
        return team.teamDesignator;
    }

    private void toadsWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Team Has Won");
            alert.setHeaderText("Player Two Wins Toads And Frogs!");
            alert.showAndWait();
            System.out.println("Player Two Wins Toads And Frogs!");
        } else {
            System.out.println("Player Two Wins Toads And Frogs");
            Team.finalWinCheck();
        }
    }
    private void frogsWin() {
        gameOver = true;
        if (Team.gameNumber == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Team Has Won");
            alert.setHeaderText("Player One Wins Toads And Frogs!");
            alert.showAndWait();
            System.out.println("Player One Wins Toads And Frogs");
        } else {
            System.out.println("Player One has Won Toads And Frogs");
            Team.finalWinCheck();
        }
    }

    public void gameBoard(Stage stage) {
        if (gameSelection == 0) {
            if (localK == 1) {
                this.toadStage = stage;
            } else if (localK == 2){
                this.toadStage2 = stage;
            } else if (localK == 3){
                this.toadStage3 = stage;
            } else if (localK == 4){
                this.toadStage4 = stage;
            } else if (localK == 5){
                this.toadStage5 = stage;
            } else if (localK == 6){
                this.toadStage6 = stage;
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
               // String teamName = BoxesOfTheBoard[boxPlacememt];
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
                            newSave.write(BoxesOfTheBoard[i] + ",\n");
                        }

                        newSave.close();
                    } else if (sizeOfBoard == 12) {
                        File saveFile12 = new File("saveGame12.txt");
                        FileWriter newSave = new FileWriter("saveGame12.txt");
                        for (int i = 0; i < sizeOfBoard; i++) {
                            newSave.write(BoxesOfTheBoard[i] + ",\n");
                        }

                        newSave.close();
                    } else if (sizeOfBoard == 15) {
                        File saveFile15 = new File("saveGame15.txt");
                        FileWriter newSave = new FileWriter("saveGame15.txt");
                        for (int i = 0; i < sizeOfBoard; i++) {
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


            // BACK TO GAME MENU //
            Button backButton2 = new Button("Back to Game Menu");
            backButton2.setOnAction(event -> {
                stage.setScene(menuWindow);
                stage.setTitle("Game Selection!");
            });
            // END BACK TO GAME MENU //

            VBox vbox = new VBox(10);
            vbox.getChildren().add(hbox);
            vbox.getChildren().add(new Label("Player One = Frogs || Player Two = Toads"));
            vbox.getChildren().add(new Label("If you can't move, then you lose the game."));
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(backButton2, saveAndQuit);

            ScrollPane boardSize = new ScrollPane(vbox);
            if (sizeOfBoard == 10) {
                stage.setScene(new Scene(boardSize, 1015, 270));
            } else if (sizeOfBoard == 12) {
                stage.setScene(new Scene(boardSize, 1210, 270));
            } else {
                stage.setScene(new Scene(boardSize, 1510, 270));
            }

            stage.show();

            // This condenses buttons when window width is too small; looks better
            boardSize.setFitToWidth(true);
        }
    }


    public void teamSwitch(Stage stage) {
        if (localK == 1){
            gameBoard(this.toadStage);
        } else if (localK == 2){
            gameBoard(this.toadStage2);
        } else if (localK == 3){
            gameBoard(this.toadStage3);
        } else if (localK == 4){
            gameBoard(this.toadStage4);
        } else if (localK == 5){
            gameBoard(this.toadStage5);
        } else if (localK == 6){
            gameBoard(this.toadStage6);
        }
        //this.gameBoard(stage);
        Team.teamDesignator = 1 - Team.teamDesignator;
        if (gameOver == false) {
            Team.teamBox();
        }
        if (gameOver == true && Team.gameNumber > 1 && Team.finalWin == false){
            Team.teamBox();
        }
    }


    private void checkWin() {
        for (int i = (sizeOfBoard - 1); i > 0; i--) {
            if (localK == 1) {
                if (BoxesOfTheBoard[i] == "Frog" && BoxesOfTheBoard[i - 1] == "    " && i != 0) {
                    toadWinFlag = false;
                    frogNoMoves = false;
                    break;
                } else if (i < BoxesOfTheBoard.length - 2 && i > 1 && BoxesOfTheBoard[i] == "Frog" && BoxesOfTheBoard[i - 1] == "Toad" && BoxesOfTheBoard[i - 2] == "    ") {
                    toadWinFlag = false;
                    frogNoMoves = false;
                    break;
                } else {
                    toadWinFlag = true;
                    frogNoMoves = true;
                }
            }
            if (localK == 2) {
                if (BoxesOfTheBoard2[i] == "Frog" && BoxesOfTheBoard2[i - 1] == "    " && i != 0) {
                    toadWinFlag2 = false;
                    frogNoM2 = false;
                    break;
                } else if (i < BoxesOfTheBoard2.length - 2 && i > 1 && BoxesOfTheBoard2[i] == "Frog" && BoxesOfTheBoard2[i - 1] == "Toad" && BoxesOfTheBoard2[i - 2] == "    ") {
                    toadWinFlag2 = false;
                    frogNoM2 = false;
                    break;
                } else {
                    toadWinFlag2 = true;
                    frogNoM2 = true;
                }
            }
            if (localK == 3) {
                if (BoxesOfTheBoard3[i] == "Frog" && BoxesOfTheBoard3[i - 1] == "    " && i != 0) {
                    toadWinFlag3 = false;
                    frogNoM3 = false;
                    break;
                } else if (i < BoxesOfTheBoard3.length - 2 && i > 1 && BoxesOfTheBoard3[i] == "Frog" && BoxesOfTheBoard3[i - 1] == "Toad" && BoxesOfTheBoard3[i - 2] == "    ") {
                    toadWinFlag3 = false;
                    frogNoM3 = false;
                    break;
                } else {
                    toadWinFlag3 = true;
                    frogNoM3 = true;
                }
            }
            if (localK == 4) {
                if (BoxesOfTheBoard4[i] == "Frog" && BoxesOfTheBoard4[i - 1] == "    " && i != 0) {
                    toadWinFlag4 = false;
                    frogNoM4 = false;
                    break;
                } else if (i < BoxesOfTheBoard4.length - 2 && i > 1 && BoxesOfTheBoard4[i] == "Frog" && BoxesOfTheBoard4[i - 1] == "Toad" && BoxesOfTheBoard4[i - 2] == "    ") {
                    toadWinFlag4 = false;
                    frogNoM4 = false;
                    break;
                } else {
                    toadWinFlag4 = true;
                    frogNoM4 = true;
                }
            }
            if (localK == 5) {
                if (BoxesOfTheBoard5[i] == "Frog" && BoxesOfTheBoard5[i - 1] == "    " && i != 0) {
                    toadWinFlag5 = false;
                    frogNoM5 = false;
                    break;
                } else if (i < BoxesOfTheBoard5.length - 2 && i > 1 && BoxesOfTheBoard5[i] == "Frog" && BoxesOfTheBoard5[i - 1] == "Toad" && BoxesOfTheBoard5[i - 2] == "    ") {
                    toadWinFlag5 = false;
                    frogNoM5 = false;
                    break;
                } else {
                    toadWinFlag5 = true;
                    frogNoM5 = true;
                }
            }
            if (localK == 6) {
                if (BoxesOfTheBoard6[i] == "Frog" && BoxesOfTheBoard6[i - 1] == "    " && i != 0) {
                    toadWinFlag6 = false;
                    frogNoM6 = false;
                    break;
                } else if (i < BoxesOfTheBoard6.length - 2 && i > 1 && BoxesOfTheBoard6[i] == "Frog" && BoxesOfTheBoard6[i - 1] == "Toad" && BoxesOfTheBoard6[i - 2] == "    ") {
                    toadWinFlag6 = false;
                    frogNoM6 = false;
                    break;
                } else {
                    toadWinFlag6 = true;
                    frogNoM6 = true;
                }
            }
        }
        if (localK == 1) {
            if (toadWinFlag == true && frogNoMoves == true) {
                toadsWin();
            }
        } else if (localK == 2){
            if (toadWinFlag2 == true && frogNoM2 == true) {
                toadsWin();
            }
        } else if (localK == 3){
            if (toadWinFlag3 == true && frogNoM3 == true) {
                toadsWin();
            }
        } else if (localK == 4){
            if (toadWinFlag4 == true && frogNoM4 == true) {
                toadsWin();
            }
        } else if (localK == 5){
            if (toadWinFlag5 == true && frogNoM5 == true) {
                toadsWin();
            }
        } else if (localK == 6){
            if (toadWinFlag6 == true && frogNoM6 == true) {
                toadsWin();
            }
        }

        ////////////////////
        for (int i = 0; i < (sizeOfBoard - 1); i++) {
            if (localK == 1) {
                if (BoxesOfTheBoard[i] == "Toad" && BoxesOfTheBoard[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag = false;
                    toadNoMoves = false;
                    break;
                } else if (BoxesOfTheBoard[i] == "Toad" && BoxesOfTheBoard[i + 1] == "Frog" && BoxesOfTheBoard[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag = false;
                    toadNoMoves = false;
                    break;
                } else {
                    frogWinFlag = true;
                    toadNoMoves = true;
                }
            }
            if (localK == 2){
                if (BoxesOfTheBoard2[i] == "Toad" && BoxesOfTheBoard2[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag2 = false;
                    toadNoM2 = false;
                    break;
                } else if (BoxesOfTheBoard2[i] == "Toad" && BoxesOfTheBoard2[i + 1] == "Frog" && BoxesOfTheBoard2[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag2 = false;
                    toadNoM2 = false;
                    break;
                } else {
                    frogWinFlag2 = true;
                    toadNoM2 = true;
                }
            }
            if (localK == 3){
                if (BoxesOfTheBoard3[i] == "Toad" && BoxesOfTheBoard3[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag3 = false;
                    toadNoM3 = false;
                    break;
                } else if (BoxesOfTheBoard3[i] == "Toad" && BoxesOfTheBoard3[i + 1] == "Frog" && BoxesOfTheBoard3[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag3 = false;
                    toadNoM3 = false;
                    break;
                } else {
                    frogWinFlag3 = true;
                    toadNoM3 = true;
                }
            }
            if (localK == 4){
                if (BoxesOfTheBoard4[i] == "Toad" && BoxesOfTheBoard4[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag4 = false;
                    toadNoM4 = false;
                    break;
                } else if (BoxesOfTheBoard4[i] == "Toad" && BoxesOfTheBoard4[i + 1] == "Frog" && BoxesOfTheBoard4[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag4 = false;
                    toadNoM4 = false;
                    break;
                } else {
                    frogWinFlag4 = true;
                    toadNoM4 = true;
                }
            }
            if (localK == 5){
                if (BoxesOfTheBoard5[i] == "Toad" && BoxesOfTheBoard5[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag5 = false;
                    toadNoM5 = false;
                    break;
                } else if (BoxesOfTheBoard5[i] == "Toad" && BoxesOfTheBoard5[i + 1] == "Frog" && BoxesOfTheBoard5[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag5 = false;
                    toadNoM5 = false;
                    break;
                } else {
                    frogWinFlag5 = true;
                    toadNoM5 = true;
                }
            }
            if (localK == 6){
                if (BoxesOfTheBoard6[i] == "Toad" && BoxesOfTheBoard6[i + 1] == "    " && i != (sizeOfBoard - 1)) {
                    frogWinFlag6 = false;
                    toadNoM6 = false;
                    break;
                } else if (BoxesOfTheBoard6[i] == "Toad" && BoxesOfTheBoard6[i + 1] == "Frog" && BoxesOfTheBoard6[i + 2] == "    " && i != (sizeOfBoard - 1) && i != (sizeOfBoard - 2)) {
                    frogWinFlag6 = false;
                    toadNoM6 = false;
                    break;
                } else {
                    frogWinFlag6 = true;
                    toadNoM6 = true;
                }
            }
        }
        if (localK == 1) {
            if (frogWinFlag == true && toadNoMoves == true) {
                frogsWin();
            }
        } else if (localK == 2){
            if (frogWinFlag2 == true && toadNoM2 == true) {
                frogsWin();
            }
        } else if (localK == 3){
            if (frogWinFlag3 == true && toadNoM3 == true) {
                frogsWin();
            }
        } else if (localK == 4){
            if (frogWinFlag4 == true && toadNoM4 == true) {
                frogsWin();
            }
        } else if (localK == 5){
            if (frogWinFlag5 == true && toadNoM5 == true) {
                frogsWin();
            }
        } else if (localK == 6){
            if (frogWinFlag6 == true && toadNoM6 == true) {
                frogsWin();
            }
        }
    }

    public void toadMove(Button toadButton, int toadSpace) {
        toadButton.setOnAction((event) -> {
            System.out.println("Toad selected!");
            if (Team.teamDesignator == 0) {
                return;
            }
            TurnStateContext stateContext = new TurnStateContext();
            stateContext.setState(new FrogNotification());
            stateContext.turn();
            if (toadSpace <= (sizeOfBoard - 1) && BoxesOfTheBoard[toadSpace + 1] == "    ") {
                BoxesOfTheBoard[toadSpace] = "    ";
                BoxesOfTheBoard[toadSpace + 1] = "Toad";
                checkWin();
                teamSwitch(toadStage);
            } else if (toadSpace <= (sizeOfBoard  - 2) && BoxesOfTheBoard[toadSpace + 1] == "Frog" && BoxesOfTheBoard[toadSpace + 2] == "    ") {
                BoxesOfTheBoard[toadSpace] = "    ";
                BoxesOfTheBoard[toadSpace + 2] = "Toad";
                checkWin();
                teamSwitch(toadStage);
            }
        });
    }

    public void frogMove(Button frogButton, int frogSpace) {
        frogButton.setOnAction((event) -> {
            System.out.println("Frog selected!");
            if (Team.teamDesignator == 1) {
                return;
            }
            TurnStateContext stateContext = new TurnStateContext();
            stateContext.setState(new ToadNotification());
            stateContext.turn();
            if (frogSpace > 0 && BoxesOfTheBoard[frogSpace - 1] == "    ") {
                BoxesOfTheBoard[frogSpace] = "    ";
                BoxesOfTheBoard[frogSpace - 1] = "Frog";
                checkWin();
                teamSwitch(toadStage);
            } else if (frogSpace > 1 && BoxesOfTheBoard[frogSpace-2] == "    " && BoxesOfTheBoard[frogSpace - 1] == "Toad") {
                BoxesOfTheBoard[frogSpace] = "    ";
                BoxesOfTheBoard[frogSpace - 2] = "Frog";
                checkWin();
                teamSwitch(toadStage);
            }
        });
    }

} //end of ToadsAndFrogs.java
