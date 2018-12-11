package pokdp.Combat.Screen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Utils.Constantes;

public class CombatSceneSimple implements ICombatScene {
    private static Scene scene;

    private GridPane gridPane = new GridPane();

    private AUIPokemonStat statPlayer;
    private AUIPokemonStat statEnemy;

    public CombatSceneSimple(Pokemon player, Pokemon enemy, double width, double height) {
        Button buttonAttack = new Button("Attaque");
        Button buttonDefense = new Button("Defense");

        buttonAttack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                enemy.setPV(enemy.getPV() - 10);
            }
        });

        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(width);

        statPlayer = new UIPokemonStatSimple(player);
        statEnemy = new UIPokemonStatSimple(enemy);

        gridPane.add(statPlayer, 0, 0);
        gridPane.add(statEnemy, 0, 1);
        gridPane.add(buttonAttack, 0, 2);
        gridPane.add(buttonDefense, 0, 3);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:assets/combat/combat_template1.png", 1920, 1080, true, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    //    gridPane.setBackground(new Background(backgroundImage));

        scene = new Scene(gridPane, 1920, 1080, Color.WHITE);
    }


    public static void launch(Stage primaryStage, String pokemonPlayer, String pokemonEnnemy) {
        ICombatScene combatScene = new CombatSceneSimple(Constantes.pokemonHashMap.get(pokemonPlayer), Constantes.pokemonHashMap.get(pokemonEnnemy), 1920, 1080);

        primaryStage.setScene(scene);
    }
}
