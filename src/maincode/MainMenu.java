package maincode;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Piedra, Papel o Tijeras - Estilo Street Fighter");
        double width = primaryStage.getWidth();  // Guardar el ancho actual
        double height = primaryStage.getHeight();  // Guardar la altura actual

        Label label = new Label();
        VBox logoTitulo = new VBox();
        logoTitulo.getChildren().addAll(label);
        logoTitulo.setAlignment(Pos.TOP_CENTER);
        logoTitulo.setStyle("-fx-padding: 0 0 100 0;");

        Button pvpButton = new Button("Modo 2 Jugadores");
        Button pvcButton = new Button("Modo 1 Jugador");
        Button rankingButton = new Button("Ver Ranking");

        pvpButton.getStyleClass().add("pvp-button");
        pvcButton.getStyleClass().add("pvc-button");
        rankingButton.getStyleClass().add("ranking-button");

        pvpButton.setOnAction(e -> {
            PvPMode pvpMode = new PvPMode();
            pvpMode.start(primaryStage);
        });

        pvcButton.setOnAction(e -> {
            PvCMode pvcMode = new PvCMode();
            pvcMode.start(primaryStage);
        });

        rankingButton.setOnAction(e -> {
            Ranking ranking = new Ranking();
            ranking.start(primaryStage);
        });
        VBox layout = new VBox(20); // 20 es el espaciado entre los botones
        layout.getChildren().addAll(logoTitulo, pvcButton, pvpButton, rankingButton);
        layout.setAlignment(Pos.BOTTOM_CENTER); // Alinear al centro horizontalmente
        layout.setStyle("-fx-padding: 20 20 100 20;");
        layout.getStyleClass().add("vbox");


        Scene scene = new Scene(layout, width, height);
        scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);  // Restaurar el ancho
        primaryStage.setHeight(height);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
