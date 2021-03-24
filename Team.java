//Author: Powell

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;

public class Team extends Application  {
    public static int teamDesignator;
    private static Stage stage;
    public static boolean selected = false;
    public static boolean teamWindowShown = false;
    private static Scene teamScene;
    public static int TDnum = 0;
    public static int playerOneWins = 0;
    public static int playerTwoWins = 0;
    public static boolean finalWin = false;
    public static int winERLocal = 0;
    public static int winERLocal2 = 0;
    public static int winERLocal3 = 0;
    public static int winERLocal4 = 0;
    public static int winERLocal5 = 0;
    public static int winERLocal6 = 0;
    public static int winTFLocal = 0;
    public static int winTFLocal2 = 0;
    public static int winTFLocal3 = 0;
    public static int winTFLocal4 = 0;
    public static int winTFLocal5 = 0;
    public static int winTFLocal6 = 0;

    public static boolean gameRestarted = false;
    public static int k = 1;
    private static Scene gameMenuScene;
    public static String game;
    public static Integer gameNumber;

    public void start(Stage primaryStage) {
        run();
    }

    public static void pause() {
        long start = new Date().getTime();
        while (new Date().getTime() - start < 1000L) {
        }
    }

    // INITIAL GAME SELECTION //
    public static void run() {
        if (gameRestarted == false) {
            Stage gameNumberWindow = new Stage();
            gameNumberWindow.setTitle("Select Number of Games");
            gameNumberWindow.setWidth(330);
            Label text = new Label("How many games to play?");

            ToggleGroup group = new ToggleGroup();

            RadioButton One = new RadioButton("One Game");
            One.setToggleGroup(group);

            RadioButton Two = new RadioButton("Two Games");
            Two.setToggleGroup(group);

            RadioButton Three = new RadioButton("Three Games");
            Three.setToggleGroup(group);

            RadioButton Four = new RadioButton("Four Games");
            Four.setToggleGroup(group);

            RadioButton Five = new RadioButton("Five Games");
            Five.setToggleGroup(group);

            RadioButton Six = new RadioButton("Six Games");
            Six.setToggleGroup(group);

            Button select = new Button("Select");
            select.setOnAction((event) -> {
                if (One.isSelected()) {
                    gameNumber = 1;
                    gameNumberWindow.close();
                } else if (Two.isSelected()) {
                    gameNumber = 2;
                    gameNumberWindow.close();
                } else if (Three.isSelected()) {
                    gameNumber = 3;
                    gameNumberWindow.close();
                } else if (Four.isSelected()) {
                    gameNumber = 4;
                    gameNumberWindow.close();
                } else if (Five.isSelected()) {
                    gameNumber = 5;
                    gameNumberWindow.close();
                } else {
                    gameNumber = 6;
                    gameNumberWindow.close();
                }
            });

            VBox mainLayout = new VBox(10);
            mainLayout.getChildren().addAll(text, One, Two, Three, Four, Five, Six, select);
            mainLayout.setAlignment(Pos.CENTER);

            gameMenuScene = new Scene(mainLayout);
            gameNumberWindow.setScene(gameMenuScene);
            gameNumberWindow.showAndWait();
        }
        // END INITIAL GAME SELECTION //
        // INITIAL GAME SELECTION //
        for (int i = 0; i < gameNumber; i++) {
            if (gameRestarted == false) {
                Stage gameMenuWindow = new Stage();
                gameMenuWindow.setTitle("Available Games");
                gameMenuWindow.setWidth(330);
                Label text = new Label("Choose a game to play");

                ToggleGroup group = new ToggleGroup();

                RadioButton TAF = new RadioButton("Toads and Frogs");
                TAF.setToggleGroup(group);

                RadioButton TD = new RadioButton("Toppling Dominoes");
                TD.setToggleGroup(group);

                RadioButton ER = new RadioButton("Elephants and Rhinos");
                ER.setToggleGroup(group);

                RadioButton C = new RadioButton("Chomp");
                C.setToggleGroup(group);

                RadioButton CC = new RadioButton("Colored Chomp");
                CC.setToggleGroup(group);

                RadioButton M = new RadioButton("Memory");
                M.setToggleGroup(group);

                Button select = new Button("Select");
                select.setOnAction((event) -> {
                    if (TAF.isSelected()) {
                        game = "TAF";
                        gameMenuWindow.close();
                    } else if (TD.isSelected()) {
                        game = "TD";
                        gameMenuWindow.close();
                    } else if (ER.isSelected()) {
                        game = "ER";
                        gameMenuWindow.close();
                    } else if (C.isSelected()) {
                        game = "C";
                        gameMenuWindow.close();
                    } else if (CC.isSelected()) {
                        game = "CC";
                        gameMenuWindow.close();
                    } else {
                        game = "M";
                        gameMenuWindow.close();
                    }
                });

                VBox mainLayout = new VBox(10);
                mainLayout.getChildren().addAll(text, TAF, TD, ER, C, CC, M, select);
                mainLayout.setAlignment(Pos.CENTER);

                gameMenuScene = new Scene(mainLayout);
                gameMenuWindow.setScene(gameMenuScene);
                gameMenuWindow.showAndWait();

                if (game == "TAF") {
                    System.out.println("Chosen game TAF");
                    ToadsAndFrogs TF = new ToadsAndFrogs();
                    TF.start(ToadsAndFrogs.toadStage);
                } else if (game == "TD") {
                    System.out.println("Chosen game TD");
                    if (k == 1){
                        TopplingDominoes TD2 = new TopplingDominoes();
                        TD2.start(TopplingDominoes.dominoStage);
                    } else if (k == 2) {
                        TopplingDominoes TD3 = new TopplingDominoes();
                        TD3.start(TopplingDominoes.dominoStage2);
                    } else if (k == 3) {
                        TopplingDominoes TD4 = new TopplingDominoes();
                        TD4.start(TopplingDominoes.dominoStage3);
                    } else if (k == 4) {
                        TopplingDominoes TD5 = new TopplingDominoes();
                        TD5.start(TopplingDominoes.dominoStage4);
                    } else if (k == 5) {
                        TopplingDominoes TD6 = new TopplingDominoes();
                        TD6.start(TopplingDominoes.dominoStage5);
                    } else if (k == 6) {
                        TopplingDominoes TD7 = new TopplingDominoes();
                        TD7.start(TopplingDominoes.dominoStage6);
                    }
                } else if (game == "ER") {
                    System.out.println("Chosen game ER");
                    if (k == 1) {
                        ElephantsAndRhinos ER2 = new ElephantsAndRhinos();
                        ER2.start(ElephantsAndRhinos.elephantStage);
                    } else if (k == 2) {
                        System.out.println("Second elephant and rhinos called");
                        ElephantsAndRhinos ER3 = new ElephantsAndRhinos();
                        ER3.start(ElephantsAndRhinos.elephantStage2);
                    } else if (k == 3) {
                        ElephantsAndRhinos ER4 = new ElephantsAndRhinos();
                        ER4.start(ElephantsAndRhinos.elephantStage3);
                    } else if (k == 4) {
                        ElephantsAndRhinos ER5 = new ElephantsAndRhinos();
                        ER5.start(ElephantsAndRhinos.elephantStage4);
                    } else if (k == 5) {
                        ElephantsAndRhinos ER6 = new ElephantsAndRhinos();
                        ER6.start(ElephantsAndRhinos.elephantStage5);
                    } else if (k == 6) {
                        ElephantsAndRhinos ER7 = new ElephantsAndRhinos();
                        ER7.start(ElephantsAndRhinos.elephantStage6);
                    }
                } else if (game == "C") {
                    System.out.println("Chosen game C");
                    if (k == 1) {
                        Chomp C2 = new Chomp();
                        C2.start(Chomp.chompStage);
                    } else if (k == 2) {
                        Chomp C3 = new Chomp();
                        C3.start(Chomp.chompStage2);
                    } else if (k == 3) {
                        Chomp C4 = new Chomp();
                        C4.start(Chomp.chompStage3);
                    } else if (k == 4) {
                        Chomp C5 = new Chomp();
                        C5.start(Chomp.chompStage4);
                    } else if (k == 5) {
                        Chomp C6 = new Chomp();
                        C6.start(Chomp.chompStage5);
                    } else if (k == 6) {
                        Chomp C7 = new Chomp();
                        C7.start(Chomp.chompStage6);
                    }
                } else if (game == "CC") {
                    System.out.println("Chosen game CC");
                    if (k == 1) {
                        ColorChomp CC2 = new ColorChomp();
                        CC2.start(ColorChomp.colorChompStage);
                    } else if (k == 2) {
                        ColorChomp CC3 = new ColorChomp();
                        CC3.start(ColorChomp.colorChompStage2);
                    } else if (k == 3) {
                        ColorChomp CC4 = new ColorChomp();
                        CC4.start(ColorChomp.colorChompStage3);
                    } else if (k == 4) {
                        ColorChomp CC5 = new ColorChomp();
                        CC5.start(ColorChomp.colorChompStage4);
                    } else if (k == 5) {
                        ColorChomp CC6 = new ColorChomp();
                        CC6.start(ColorChomp.colorChompStage5);
                    } else if (k == 6) {
                        ColorChomp CC7 = new ColorChomp();
                        CC7.start(ColorChomp.colorChompStage6);
                    }
                } else if (game == "M") {
                    System.out.println("Chosen game M");
                    if (k == 1) {
                        Memory M2 = new Memory();
                        M2.start(Memory.fruitStage);
                    } else if (k == 2) {
                        Memory M3 = new Memory();
                        M3.start(Memory.fruitStage2);
                    } else if (k == 3) {
                        Memory M4 = new Memory();
                        M4.start(Memory.fruitStage3);
                    } else if (k == 4) {
                        Memory M5 = new Memory();
                        M5.start(Memory.fruitStage4);
                    } else if (k == 5) {
                        Memory M6 = new Memory();
                        M6.start(Memory.fruitStage5);
                    } else if (k == 6) {
                        Memory M7 = new Memory();
                        M7.start(Memory.fruitStage6);
                    }
                }
                k++;
            }
        }
    }
    // END INITIAL GAME SELECTION //

    public static void teamBox() {
        VBox teamBox = new VBox();
        Stage teamWindow = new Stage();
        String[] teams = {"One", "Two"};
        teamWindow.setTitle("------------------- It's Player " + teams[Team.teamDesignator] + "'s Turn. -------------------");
        teamScene = new Scene(teamBox, 500, 30);
        teamWindow.setScene(teamScene);
        teamWindow.show();
        pause();
        teamWindow.close();
    }

    //elephants = team.designator == 1; Rhinos = team.designator == 0; //toads = team.designator == 1; frogs = team.designator == 0;
    public static void checkERAnyMoves() {
        if (winERLocal == 0) {
            if (ElephantsAndRhinos.erStarted == true) {
                if (ElephantsAndRhinos.elephantNoMoves == true && ElephantsAndRhinos.rhinoNoMoves == true) {
                    System.out.println("Inside checkERAnyMoves no moves: " + (playerOneWins + playerTwoWins));
                    playerTwoWins++;
                    winERLocal++;
                } else if (ElephantsAndRhinos.elephantNoMoves == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoMoves == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoMoves == false && ElephantsAndRhinos.elephantNoMoves == false) {
                } else if (ElephantsAndRhinos.elephantNoMoves == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves: " + (playerOneWins + playerTwoWins));
                    winERLocal++;
                } else if (ElephantsAndRhinos.rhinoNoMoves == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves: " + (playerOneWins + playerTwoWins));
                    winERLocal++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves: " + (playerOneWins + playerTwoWins));
                    winERLocal++;
                }
            }
        }
        if (winERLocal2 == 0) {
            if (ElephantsAndRhinos.erStarted2 == true) {
                if (ElephantsAndRhinos.elephantNoM2 == true && ElephantsAndRhinos.rhinoNoM2 == true) {
                    playerTwoWins++;
                    System.out.println("Inside checkERAnyMoves2 no moves: " + (playerOneWins + playerTwoWins));
                    winERLocal2++;
                } else if (ElephantsAndRhinos.elephantNoM2 == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoM2 == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoM2 == false && ElephantsAndRhinos.elephantNoM2 == false) {
                } else if (ElephantsAndRhinos.elephantNoM2 == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves2: " + (playerOneWins + playerTwoWins));
                    winERLocal2++;
                } else if (ElephantsAndRhinos.rhinoNoM2 == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves2: " + (playerOneWins + playerTwoWins));
                    winERLocal2++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves2: " + (playerOneWins + playerTwoWins));
                    winERLocal2++;
                }
            }
        }
        if (winERLocal3 == 0) {
            if (ElephantsAndRhinos.erStarted3 == true) {
                if (ElephantsAndRhinos.elephantNoM3 == true && ElephantsAndRhinos.rhinoNoM3 == true) {
                    playerTwoWins++;
                    System.out.println("Inside checkERAnyMoves3 no moves: " + (playerOneWins + playerTwoWins));
                    winERLocal3++;
                } else if (ElephantsAndRhinos.elephantNoM3 == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoM3 == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoM3 == false && ElephantsAndRhinos.elephantNoM3 == false) {
                } else if (ElephantsAndRhinos.elephantNoM3 == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves3: " + (playerOneWins + playerTwoWins));
                    winERLocal3++;
                } else if (ElephantsAndRhinos.rhinoNoM3 == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves3: " + (playerOneWins + playerTwoWins));
                    winERLocal3++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves3: " + (playerOneWins + playerTwoWins));
                    winERLocal3++;
                }
            }
        }
        if (winERLocal4 == 0) {
            if (ElephantsAndRhinos.erStarted4 == true) {
                if (ElephantsAndRhinos.elephantNoM4 == true && ElephantsAndRhinos.rhinoNoM4 == true) {
                    playerTwoWins++;
                    System.out.println("Inside checkERAnyMoves4 no moves: " + (playerOneWins + playerTwoWins));
                    winERLocal4++;
                } else if (ElephantsAndRhinos.elephantNoM4 == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoM4 == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoM4 == false && ElephantsAndRhinos.elephantNoM4 == false) {
                } else if (ElephantsAndRhinos.elephantNoM4 == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves4: " + (playerOneWins + playerTwoWins));
                    winERLocal4++;
                } else if (ElephantsAndRhinos.rhinoNoM4 == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves4: " + (playerOneWins + playerTwoWins));
                    winERLocal4++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves4: " + (playerOneWins + playerTwoWins));
                    winERLocal4++;
                }
            }
        }
        if (winERLocal5 == 0) {
            if (ElephantsAndRhinos.erStarted5 == true) {
                if (ElephantsAndRhinos.elephantNoM5 == true && ElephantsAndRhinos.rhinoNoM5 == true) {
                    playerTwoWins++;
                    System.out.println("Inside checkERAnyMoves5 no moves: " + (playerOneWins + playerTwoWins));
                    winERLocal5++;
                } else if (ElephantsAndRhinos.elephantNoM5 == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoM5 == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoM5 == false && ElephantsAndRhinos.elephantNoM5 == false) {
                } else if (ElephantsAndRhinos.elephantNoM5 == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves5: " + (playerOneWins + playerTwoWins));
                    winERLocal5++;
                } else if (ElephantsAndRhinos.rhinoNoM5 == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves5: " + (playerOneWins + playerTwoWins));
                    winERLocal5++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves5: " + (playerOneWins + playerTwoWins));
                    winERLocal5++;
                }
            }
        }
        if (winERLocal6 == 0) {
            if (ElephantsAndRhinos.erStarted6 == true) {
                if (ElephantsAndRhinos.elephantNoM6 == true && ElephantsAndRhinos.rhinoNoM6 == true) {
                    playerTwoWins++;
                    System.out.println("Inside checkERAnyMoves6 no moves: " + (playerOneWins + playerTwoWins));
                    winERLocal6++;
                } else if (ElephantsAndRhinos.elephantNoM6 == true && teamDesignator == 1) {
                } else if (ElephantsAndRhinos.rhinoNoM6 == true && teamDesignator == 0) {
                } else if (ElephantsAndRhinos.rhinoNoM6 == false && ElephantsAndRhinos.elephantNoM6 == false) {
                } else if (ElephantsAndRhinos.elephantNoM6 == true && teamDesignator == 0) {
                    playerOneWins++;
                    System.out.println("Inside first inner checkERAnyMoves6: " + (playerOneWins + playerTwoWins));
                    winERLocal6++;
                } else if (ElephantsAndRhinos.rhinoNoMoves == true && teamDesignator == 1) {
                    playerOneWins++;
                    System.out.println("Inside second inner checkERAnyMoves6: " + (playerOneWins + playerTwoWins));
                    winERLocal6++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerOneWins++;
                    System.out.println("Inside checkERAnyMoves6: " + (playerOneWins + playerTwoWins));
                    winERLocal6++;
                }
            }
        }
    }

    public static void checkTFAnyMoves(){
        if (winTFLocal == 0) {
            if (ToadsAndFrogs.tfStarted == true) {
                if (ToadsAndFrogs.frogNoMoves == true && ToadsAndFrogs.toadNoMoves == true) {
                    playerOneWins++;
                    System.out.println("Inside checkTFAnyMoves NoMoves == true: " + (playerOneWins + playerTwoWins));
                    winTFLocal++;
                } else if (ToadsAndFrogs.toadNoMoves == true && teamDesignator == 1) {
                } else if (ToadsAndFrogs.frogNoMoves == true && teamDesignator == 0) {
                } else if (ToadsAndFrogs.toadNoMoves == false && ToadsAndFrogs.frogNoMoves == false) {
                } else if (ToadsAndFrogs.toadNoMoves == true && teamDesignator == 0) {
                    playerTwoWins++;
                    System.out.println("Inside inner checkTFAnyMoves: " + (playerOneWins + playerTwoWins));
                    winTFLocal++;
                } else if (ToadsAndFrogs.frogNoMoves == true && teamDesignator == 1) {
                    playerTwoWins++;
                    System.out.println("Inside inner checkTFAnyMoves: " + (playerOneWins + playerTwoWins));
                    winTFLocal++;
                } else if ((playerOneWins + playerTwoWins) + 1 == gameNumber) {
                    playerTwoWins++;
                    System.out.println("Inside checkTFAnyMoves: " + (playerOneWins + playerTwoWins));
                    winTFLocal++;
                }
            }
        }
    }

    public static void finalWinCheck(){
        if (ElephantsAndRhinos.erStarted == true) {
            checkERAnyMoves();
        }
        if (ElephantsAndRhinos.erStarted2 == true) {
            checkERAnyMoves();
        }
        if (ElephantsAndRhinos.erStarted3 == true) {
            checkERAnyMoves();
        }
        if (ElephantsAndRhinos.erStarted4 == true) {
            checkERAnyMoves();
        }
        if (ElephantsAndRhinos.erStarted5 == true) {
            checkERAnyMoves();
        }
        if (ElephantsAndRhinos.erStarted6 == true) {
            checkERAnyMoves();
        }
        if (ToadsAndFrogs.tfStarted == true) {
            checkTFAnyMoves();
        }
            System.out.println("inside final win check: " + (playerOneWins + playerTwoWins));
            if (finalWin == false) {
                if (playerOneWins + playerTwoWins >= gameNumber) {
                    if (Team.teamDesignator == 0) {
                        finalWin = true;
                        System.out.println("Player One Wins!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Player One Wins!");
                        alert.setHeaderText("Player One Wins!");
                        alert.showAndWait();
                        System.out.println("PLAYER ONE WINS ALL GAMES");
                    } else {
                        finalWin = true;
                        System.out.println("Player Two Wins!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Player Two Wins!");
                        alert.setHeaderText("Player Two Wins!");
                        alert.showAndWait();
                        System.out.println("PLAYER TWO WINS ALL GAMES");
                    }
                }
            }
    }

    //First player method
    public static void FirstPlayer(){
        //teamBox(stage);
        Stage FirstPlayer = new Stage();
        FirstPlayer.setX(850);
        FirstPlayer.setY(250);
        FirstPlayer.initModality(Modality.APPLICATION_MODAL);
        FirstPlayer.setTitle("First Move");
        FirstPlayer.setMinWidth(250);

        Label l = new Label();
        l.setText("Choose your First Player");

        //RED = ONE, BLUE = TWO
        Button redButton = new Button("Player One");
        redButton.setOnAction((event) -> {
            teamDesignator = 0;
            FirstPlayer.close();
        });

        Button blueButton = new Button("Player Two");
        blueButton.setOnAction((event) -> {
            teamDesignator = 1;
            FirstPlayer.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(l, redButton, blueButton);
        layout.setAlignment(Pos.CENTER);

        Scene playerSelect = new Scene(layout);
        FirstPlayer.setScene(playerSelect);
        FirstPlayer.showAndWait();
        selected = true;
    } // end FirstPlayer
}
