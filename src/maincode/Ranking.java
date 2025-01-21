package maincode;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Ranking {
    private RankingHandler rankingHandler;

    public Ranking() {
        rankingHandler = new RankingHandler();
    }

    public void start(Stage stage) {
        Label logoTitulo = new Label();
        logoTitulo.getStyleClass().add("titulo");
        logoTitulo.setAlignment(Pos.TOP_CENTER);

        VBox layout = new VBox(10);
        List<Player> pvpRanking = rankingHandler.getRanking("rankingpvp.txt");
        List<Player> pvcRanking = rankingHandler.getRanking("rankingpvc.txt");

        HBox ambosRanking = new HBox(100);
        VBox rankingPvP = new VBox(30);
        rankingPvP.getStyleClass().add("rankingPVP");
        Label pvpLabel = new Label("Dos Jugadores:");
        rankingPvP.getChildren().add(pvpLabel);

        if (pvpRanking.isEmpty()) {
            rankingPvP.getChildren().add(new Label("Sin partidas jugadas"));
        } else {
            for (Player player : pvpRanking) {
                rankingPvP.getChildren().add(new Label(player.getName() + " - Winrate: " + String.format("%.2f", player.getWinrate() * 100) + "%"));
            }
        }

        VBox rankingPvC = new VBox(30);
        rankingPvC.getStyleClass().add("rankingPVC");
        Label pvcLabel = new Label("Un Jugador:");
        rankingPvC.getChildren().add(pvcLabel);

        if (pvcRanking.isEmpty()) {
            rankingPvC.getChildren().add(new Label("Sin partidas jugadas"));
        } else {
            for (Player player : pvcRanking) {
                rankingPvC.getChildren().add(new Label(player.getName() + " - Winrate: " + String.format("%.2f", player.getWinrate() * 100) + "%"));
            }
        }

        ambosRanking.getChildren().addAll(rankingPvC, rankingPvP);
        ambosRanking.setAlignment(Pos.CENTER);

        Button backButton = new Button("Volver al menú principal");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        });

        layout.getChildren().addAll(logoTitulo, ambosRanking, backButton);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setStyle("-fx-padding: 20 20 100 20;");


        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("styleRanking.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}
