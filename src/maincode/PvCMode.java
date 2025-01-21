package maincode;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;


public class PvCMode {
    private Player player;
    private Player computer;
    private RankingHandler rankingHandler;

    public PvCMode() {
        rankingHandler = new RankingHandler();
    }

    public void start(Stage stage) {
        // Solicitar el nombre del jugador
        TextField playerNameField = new TextField("");

        playerNameField.getStyleClass().add("textfield");
        playerNameField.setPromptText("Player Name");
        Button startButton = new Button("Empezar Juego");
        startButton.getStyleClass().add("start-button");
        VBox layout = new VBox(100, new Label("Introduce tu nombre:"), playerNameField, startButton);
        Scene scene = new Scene(layout);
        layout.setAlignment(Pos.CENTER);
        scene.getStylesheets().add(getClass().getResource("stylePvC.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);


        startButton.setOnAction(e -> {
            String playerName = playerNameField.getText();
            startGame(stage, playerName);
        });
    }

    private void startGame(Stage stage, String playerName) {
        player = new Player(playerName);
        computer = new Player("Máquina");
        player.resetHealth();
        computer.resetHealth();

        //MITAD DE PANTALLA DE JUGADOR
        VBox textoSuperior = new VBox(20);

        Label nombrePlayer = new Label(player.getName());

        Image vida100 = new Image(getClass().getResourceAsStream("images/Vidas/barra100.png"));
        ImageView vidaPlayerView = new ImageView(vida100);

        textoSuperior.getChildren().addAll(nombrePlayer, vidaPlayerView);
        textoSuperior.setAlignment(Pos.TOP_LEFT);

        HBox botones = new HBox(60);

        Button rockButton = new Button("Piedra");
        Button paperButton = new Button("Papel");
        Button scissorsButton = new Button("Tijeras");

        rockButton.getStyleClass().add("rock-button");
        paperButton.getStyleClass().add("paper-button");
        scissorsButton.getStyleClass().add("scissors-button");

        botones.getChildren().addAll(rockButton, paperButton, scissorsButton);
        botones.setAlignment(Pos.BOTTOM_LEFT);
        botones.getStyleClass().add("botones");

        Image gifImage = new Image(getClass().getResourceAsStream("images/Manos/gifi.gif"));
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitWidth(380);
        gifView.setFitHeight(380);
        gifView.setTranslateX(200);

        VBox layoutJugador = new VBox(110, textoSuperior, gifView,  botones);
        layoutJugador.setAlignment(Pos.CENTER_LEFT);
        layoutJugador.getStyleClass().add("layout-jugador");


        //MITAD DE PANTALLA DE MAQUINA
        VBox infoMaquina = new VBox(20);
        Label nombreMaquina = new Label("Máquina");

        Image vidaBot = new Image(getClass().getResourceAsStream("images/Vidas/barra100.png"));
        ImageView vidaBotView = new ImageView(vidaBot);

        infoMaquina.getChildren().addAll(nombreMaquina, vidaBotView);
        infoMaquina.setAlignment(Pos.TOP_RIGHT);

        Image gifImageBot = new Image(getClass().getResourceAsStream("images/Manos/gifd.gif"));
        ImageView gifViewBot = new ImageView(gifImageBot);
        gifViewBot.setFitWidth(380);
        gifViewBot.setFitHeight(380);
        gifViewBot.setTranslateX(-200);

        VBox layoutMaquina = new VBox(100, infoMaquina, gifViewBot);
        layoutMaquina.setAlignment(Pos.TOP_RIGHT);
        layoutMaquina.setStyle("-fx-padding: 80 20 20 20;");


        HBox fullLayout = new HBox(600);
        fullLayout.getChildren().addAll(layoutJugador, layoutMaquina);
        fullLayout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(fullLayout);
        scene.getStylesheets().add(getClass().getResource("stylePvC.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);


        rockButton.setOnAction(e -> playRound(stage, 1, vidaPlayerView, vidaBotView, nombrePlayer));
        paperButton.setOnAction(e -> playRound(stage, 2, vidaPlayerView, vidaBotView, nombrePlayer));
        scissorsButton.setOnAction(e -> playRound(stage, 3, vidaPlayerView, vidaBotView, nombrePlayer));
    }

    private void playRound(Stage stage, int choice1, ImageView vidaView, ImageView vidaBotView, Label resultLabel) {
        // La máquina elige al azar
        int choice2 = (int) (Math.random() * 3) + 1;

        String result = determineRoundWinner(choice1, choice2);

        if (player.getHealth() > 0 && computer.getHealth() > 0) {
            resultLabel.setText(result);
            if (player.getHealth() == 3) {
                vidaView.setImage(vidaView.getImage());
            } else if (player.getHealth() == 2) {
                Image vida60 = new Image(getClass().getResourceAsStream("images/Vidas/barra100-60.gif"));
                vidaView.setImage(vida60);
            } else if (player.getHealth() == 1) {
                Image vida30 = new Image(getClass().getResourceAsStream("images/Vidas/barra60-30.gif"));
                vidaView.setImage(vida30);
            }
            if (computer.getHealth() == 3) {
                vidaBotView.setImage(vidaBotView.getImage());
            } else if (computer.getHealth() == 2) {
                Image vida60Bot = new Image(getClass().getResourceAsStream("images/Vidas/barra100-60_d.gif"));
                vidaBotView.setImage(vida60Bot);
            } else if (computer.getHealth() == 1) {
                Image vida30Bot = new Image(getClass().getResourceAsStream("images/Vidas/barra60-30_d.gif"));
                vidaBotView.setImage(vida30Bot);
            }
        }

        if (player.getHealth() == 0 || computer.getHealth() == 0) {
            if (player.getHealth() == 0) {
                resultLabel.setText("Máquina ha ganado la partida.");
                computer.increaseNVictories();
                player.increaseNDefeats();
            } else {
                resultLabel.setText(player.getName() + " ha ganado la partida.");
                player.increaseNVictories();
                computer.increaseNDefeats();
            }
            Image gifImage = new Image(getClass().getResourceAsStream("images/Vidas/ko-street-fighter.gif"));

            ImageView gifView = new ImageView(gifImage);
            gifView.setFitWidth(700);
            gifView.setFitHeight(500);

            // Guardar los resultados en el archivo de texto
            rankingHandler.savePvCPlayer(player);

            // Opción para volver al menú
            Button backButton = new Button("Volver al menú principal");
            backButton.getStyleClass().add("back-button");
            backButton.setOnAction(e -> {
                MainMenu mainMenu = new MainMenu();
                mainMenu.start(stage);
            });

            VBox layout = new VBox(10,gifView,resultLabel, backButton);
            layout.setAlignment(Pos.CENTER);
            Scene endScene = new Scene(layout);
            endScene.getStylesheets().add(getClass().getResource("stylePvC.css").toExternalForm());
            stage.setScene(endScene);
            stage.show();
            stage.setMaximized(true);
        }
    }

    private String determineRoundWinner(int choice1, int choice2) {
        if (choice1 == choice2) {
            return "Empate";
        } else if ((choice1 == 1 && choice2 == 3) || (choice1 == 2 && choice1 == 1) || (choice1 == 3 && choice2 == 2)) {
            computer.reduceHealth();
            return player.getName() + " gana esta ronda";
        } else {
            player.reduceHealth();
            return "Máquina gana esta ronda";
        }
    }
}
