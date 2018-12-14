package pokdp.Combat.Screen;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Utils.Constantes;
import pokdp.World.Screen.WorldScreen;

import java.util.ArrayList;
import java.util.List;

public class CombatSceneSimple implements ICombatScene {
    private static Scene scene;

    private GridPane gridPane = new GridPane();

    private AUIPokemonStat statPlayer;
    private AUIPokemonStat statEnemy;

    private List<Button> buttonList = new ArrayList<>();

    public CombatSceneSimple(Stage primaryStage, Pokemon player, Pokemon enemy, double width, double height) {
        Button buttonDefense = new Button("Defense");

        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(width);

        statPlayer = new UIPokemonStatSimple(player, enemy);
        statEnemy = new UIPokemonStatSimple(enemy, player);

        gridPane.add(statPlayer, 0, 0);
        gridPane.add(statEnemy, 0, 1);
        gridPane.add(buttonDefense, 0, 3);

        int i = 4;

        for(Attack attack : player.getAttacks()) {
            Button button = new Button(attack.getName());

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    int damage = attack.calculateDamage(player, enemy);
                    enemy.setPV(enemy.getPV() - damage);

                    if(enemy.getPV() <= 0) {
                        WorldScreen.load(primaryStage);
                    }
                }
            });

            gridPane.add(button, i++, 0);
            buttonList.add(button);
        }

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:assets/combat/combat_template1.png", 1920, 1080, true, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    //    gridPane.setBackground(new Background(backgroundImage));

        scene = new Scene(gridPane, 1920, 1080, Color.WHITE);
    }


    public static void launch(Stage primaryStage, String pokemonPlayer, String pokemonEnemy) {
        Pokemon player = Constantes.pokemonHashMap.get(pokemonPlayer);
        Pokemon enemy = Constantes.pokemonHashMap.get(pokemonEnemy);

        ICombatScene combatScene = new CombatSceneSimple(primaryStage, player, enemy, 1920, 1080);

        primaryStage.setScene(scene);
    }
}
