package maincode;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PvPMode {

    private Player player1;
    private Player player2;
    private boolean player1HasChosen = false;
    private boolean player2HasChosen = false;
    private int player1Choice;
    private int player2Choice;
    private String eleccionP1;
    private String eleccionP2;

    public PvPMode() {
        player1 = new Player("");
        player2 = new Player("");
    }

    public void start(Stage stage) {
        // Crear los campos de texto para ingresar los nombres (ahora vacíos)
        TextField player1NameInput = new TextField();
        player1NameInput.setPromptText("Nombre Jugador 1");

        Image imagen = new Image(getClass().getResourceAsStream("images/Iconos/vs.png"));
        // Crear un ImageView y establecer la imagen
        ImageView imageView = new ImageView(imagen);

        // Puedes ajustar el tamaño del ImageView si es necesario
        imageView.setFitWidth(150);
        imageView.setFitHeight(200);// Ancho ajustado a 200 píxeles
        imageView.setPreserveRatio(true);

        TextField player2NameInput = new TextField();
        player2NameInput.setPromptText("Nombre Jugador 2");


        VBox nombresVS = new VBox(20);
        nombresVS.setAlignment(Pos.CENTER);
        nombresVS.getChildren().addAll(player1NameInput, imageView, player2NameInput);

        // Crear botón para empezar el juego
        Button startButton = new Button("Empezar Juego");
        startButton.getStyleClass().add("start-button");

        Label infoLabel = new Label("Escribid vuestros nombres para que empieze el juego.");

        VBox layout = new VBox(100,infoLabel, nombresVS, startButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("stylePvP.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);


        startButton.setOnAction(e -> {
            String player1Name = player1NameInput.getText();
            String player2Name = player2NameInput.getText();

            if (player1Name.isEmpty() || player2Name.isEmpty()) {
                infoLabel.setText("Ambos jugadores deben ingresar sus nombres.");
            } else {
                player1.setName(player1Name);
                player2.setName(player2Name);
                startGame(stage);
            }
        });
    }

    private void startGame(Stage stage) {
        // Crear botones de elección para piedra, papel o tijeras
        HBox layoutPvP = new HBox(350);
        layoutPvP.setAlignment(Pos.CENTER);


        //LAYOUT DE JUGADOR 1 EN IZQUIERDA

        //BARRA DE VIDA
        VBox layoutP1 = new VBox(100);


        Image vidaP1 = new Image(getClass().getResourceAsStream("images/Vidas/barra100.png"));
        ImageView vidaP1View = new ImageView(vidaP1);

        //GIF MANOS
        Image gifImage = new Image(getClass().getResourceAsStream("images/Manos/gifi.gif"));
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitWidth(380);
        gifView.setFitHeight(380);
        gifView.setTranslateX(200);

        Label gameInfoIzquierda = new Label(player1.getName() + " elige:");

        HBox botonesIzquierda = new HBox(40);
        Button piedraButtonIzquierda = new Button("Piedra");
        Button papelButtonIzquierda = new Button("Papel");
        Button tijerasButtonIzquierda = new Button("Tijeras");

        botonesIzquierda.getChildren().addAll(piedraButtonIzquierda, papelButtonIzquierda, tijerasButtonIzquierda);
        //Damos estilos a los botones
        piedraButtonIzquierda.getStyleClass().add("rock-buttonIzquierda");
        papelButtonIzquierda.getStyleClass().add("paper-buttonIzquierda");
        tijerasButtonIzquierda.getStyleClass().add("scissors-buttonIzquierda");

        layoutP1.getChildren().addAll(vidaP1View, gifView, gameInfoIzquierda, botonesIzquierda);
        layoutP1.setAlignment(Pos.CENTER_LEFT);


        //LAYOUT DE JUGADOR 2 EN DERECHA

        Image vidaP2 = new Image(getClass().getResourceAsStream("images/Vidas/barra100.png"));
        ImageView vidaP2View = new ImageView(vidaP2);

        if (player1.getHealth() > 0 && player2.getHealth() > 0) {
            if (player1.getHealth() == 3) {
                vidaP1View.setImage(vidaP1View.getImage());
            } else if (player1.getHealth() == 2) {
                Image vida60 = new Image(getClass().getResourceAsStream("images/Vidas/barra100-60.gif"));
                vidaP1View.setImage(vida60);
            } else if (player1.getHealth() == 1) {
                Image vida30 = new Image(getClass().getResourceAsStream("images/Vidas/barra60-30.gif"));
                vidaP1View.setImage(vida30);
            }
            if (player2.getHealth() == 3) {
                vidaP2View.setImage(vidaP2View.getImage());
            } else if (player2.getHealth() == 2) {
                Image vida60Bot = new Image(getClass().getResourceAsStream("images/Vidas/barra100-60_d.gif"));
                vidaP2View.setImage(vida60Bot);
            } else if (player2.getHealth() == 1) {
                Image vida30Bot = new Image(getClass().getResourceAsStream("images/Vidas/barra60-30_d.gif"));
                vidaP2View.setImage(vida30Bot);
            }
        }

        Label gameInfoDerecha = new Label(player2.getName() + " elige:");

        HBox botonesDerecha = new HBox(40);
        Button piedraButtonDerecha = new Button("Piedra");
        Button papelButtonDerecha = new Button("Papel");
        Button tijerasButtonDerecha = new Button("Tijeras");

        Image gifImageBot = new Image(getClass().getResourceAsStream("images/Manos/gifd.gif"));
        ImageView gifViewBot = new ImageView(gifImageBot);
        gifViewBot.setFitWidth(380);
        gifViewBot.setFitHeight(380);
        gifViewBot.setTranslateX(-200);

        botonesDerecha.getChildren().addAll(tijerasButtonDerecha, papelButtonDerecha, piedraButtonDerecha);

        //Damos estilos a los botones
        piedraButtonDerecha.getStyleClass().add("rock-buttonDerecha");
        papelButtonDerecha.getStyleClass().add("paper-buttonDerecha");
        tijerasButtonDerecha.getStyleClass().add("scissors-buttonDerecha");

        VBox layoutDerecha = new VBox(100, vidaP2View, gifViewBot, gameInfoDerecha, botonesDerecha);
        layoutDerecha.setAlignment(Pos.CENTER_RIGHT);

        //LAYOUT FULL (SUMAR + VOX)
        layoutPvP.getChildren().addAll(layoutP1, layoutDerecha);
        layoutPvP.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layoutPvP);
        scene.getStylesheets().add(getClass().getResource("stylePvP.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);



        // Acción para elegir opción para Jugador 1 y luego para Jugador 2
        piedraButtonIzquierda.setOnAction(e -> handleChoiceIzquierda(stage, 1, gameInfoIzquierda, piedraButtonIzquierda, papelButtonIzquierda, tijerasButtonIzquierda));
        papelButtonIzquierda.setOnAction(e -> handleChoiceIzquierda(stage, 2, gameInfoIzquierda, piedraButtonIzquierda, papelButtonIzquierda, tijerasButtonIzquierda));
        tijerasButtonIzquierda.setOnAction(e -> handleChoiceIzquierda(stage, 3, gameInfoIzquierda, piedraButtonIzquierda, papelButtonIzquierda, tijerasButtonIzquierda));

        // Acción para elegir opción para Jugador 1 y luego para Jugador 2
        piedraButtonDerecha.setOnAction(e -> handleChoiceDerecha(stage, 1, gameInfoDerecha, piedraButtonDerecha, papelButtonDerecha, tijerasButtonDerecha));
        papelButtonDerecha.setOnAction(e -> handleChoiceDerecha(stage, 2, gameInfoDerecha, piedraButtonDerecha, papelButtonDerecha, tijerasButtonDerecha));
        tijerasButtonDerecha.setOnAction(e -> handleChoiceDerecha(stage, 3, gameInfoDerecha, piedraButtonDerecha, papelButtonDerecha, tijerasButtonDerecha));
    }

    private void handleChoiceIzquierda(Stage stage, int choice, Label gameInfoIzquierda, Button piedraButtonDerecha, Button papelButtonDerecha, Button tijerasButtonDerecha) {
        if (!player1HasChosen) {
            player1Choice = choice;

            if (choice == 1) {
                eleccionP1 = "Piedra";
            } else if (choice == 2) {
                eleccionP1 = "Papel";
            } else if (choice == 3) {
                eleccionP1 = "Tijeras";
            }

            player1HasChosen = true;
            gameInfoIzquierda.setText(player1.getName() + " ha elegido.");

            // Verificar si ambos jugadores ya han elegido antes de determinar el ganador
            if (player2HasChosen) {
                determineWinner(stage);  // Solo se llama si ambos jugadores han elegido
            }
        }
    }

    private void handleChoiceDerecha(Stage stage, int choice, Label gameInfoDerecha, Button piedraButtonDerecha, Button papelButtonDerecha, Button tijerasButtonDerecha) {
        if (!player2HasChosen) {
            player2Choice = choice;

            if (choice == 1) {
                eleccionP2 = "Piedra";
            } else if (choice == 2) {
                eleccionP2 = "Papel";
            } else if (choice == 3) {
                eleccionP2 = "Tijeras";
            }

            player2HasChosen = true;
            gameInfoDerecha.setText(player2.getName() + " ha elegido.");

            // Verificar si ambos jugadores ya han elegido antes de determinar el ganador
            if (player1HasChosen) {
                determineWinner(stage);  // Solo se llama si ambos jugadores han elegido
            }
        }
    }

    private void determineWinner(Stage stage) {
        Player winner = null;
        Player loser = null;
        ImageView vidaP1View = new ImageView();
        ImageView vidaP2View = new ImageView();


            if (player1Choice == player2Choice) {
                winner = null; // Empate
            } else if ((player1Choice == 1 && player2Choice == 3) ||
                    (player1Choice == 2 && player2Choice == 1) ||
                    (player1Choice == 3 && player2Choice == 2)) {
                winner = player1;
                loser = player2;
            } else {
                winner = player2;
                loser = player1;
            }

            if (winner != null) {
                loser.reduceHealth(); // El jugador que pierde una ronda pierde una vida

                // Actualizar victorias y derrotas
                winner.increaseNVictories();
                loser.increaseNDefeats();
            }

            // Comprobar si alguno de los jugadores ha perdido todas las vidas
            if (player1.getHealth() == 0 || player2.getHealth() == 0) {
                displayGameOver(stage);
            } else {
                displayRoundResult(stage, winner);
            }
        }


    private void displayGameOver(Stage stage) {
        Label gameOverLabel = new Label();

        Player winner = (player1.getHealth() > 0) ? player1 : player2;
        gameOverLabel.setText("¡" + winner.getName() + " ha ganado el juego!");
        Image gifImage = new Image(getClass().getResourceAsStream("images/Vidas/ko-street-fighter.gif"));
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitWidth(700);
        gifView.setFitHeight(500);

        // Guardar resultados en el ranking
        RankingHandler rankingHandler = new RankingHandler();
        rankingHandler.savePvPPlayer(winner); // Guarda el ganador
        rankingHandler.savePvPPlayer(winner == player1 ? player2 : player1); // Guarda el perdedor

        Button backButton = new Button("Volver al Menú Principal");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> returnToMainMenu(stage));

        VBox layout = new VBox(10, gifView, gameOverLabel, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("stylePvP.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }


    private void displayRoundResult(Stage stage, Player winner) {
        Label resultLabel = new Label();
        if (winner == null) {
            resultLabel.setText("Empate.");
        } else {
            resultLabel.setText(player1.getName() + " ha elegido " + eleccionP1 + " y " + player2.getName() + " ha elegido " + eleccionP2 +
                    "\nGanador de la ronda: " + winner.getName() +
                    "\nVidas restantes: \n" + player1.getName() + ": " + player1.getHealth() + " vidas.\n" +
                    player2.getName() + ": " + player2.getHealth() + " vidas.");
        }

        Button nextRoundButton = new Button("Siguiente Ronda");
        nextRoundButton.getStyleClass().add("next-button");

        Button backButton = new Button("Volver al Menú Principal");
        backButton.getStyleClass().add("back-button");

        nextRoundButton.setOnAction(e -> resetForNextRound(stage));
        backButton.setOnAction(e -> returnToMainMenu(stage));

        VBox layout = new VBox(10, resultLabel, nextRoundButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("stylePvP.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    private void resetForNextRound(Stage stage) {
        player1HasChosen = false;
        player2HasChosen = false;
        startGame(stage); // Reiniciar el juego para la siguiente ronda
    }

    private void returnToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }
}
