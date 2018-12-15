package pokdp.Combat.Screen;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pokdp.Attack.Attack;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Type.Style;
import pokdp.Utils.Button.ButtonStyle;
import pokdp.Utils.Constantes;
import pokdp.World.Screen.WorldScreen;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatSceneSimple implements ICombatScene {
    private static Scene scene;

    private Label actionEnemy = new Label();
    private Label actionPlayer = new Label();
    private GridPane gridPane = new GridPane();

    private AUIPokemonStat statPlayer;
    private AUIPokemonStat statEnemy;

    private List<Button> buttonList = new ArrayList<>();

    private boolean deadOccuredOnce = false;

    public CombatSceneSimple(Stage primaryStage, Player player, Pokemon pokPlayer, Pokemon enemy, double width, double height) {
        Button buttonDefense = new Button("Defense");

        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(width);

        statPlayer = new UIPokemonStatSimple(pokPlayer, enemy);
        statEnemy = new UIPokemonStatSimple(enemy, pokPlayer);

        gridPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

        ColumnConstraints colFirstPok = new ColumnConstraints();
        ColumnConstraints colSectPok = new ColumnConstraints();
        ColumnConstraints colAction = new ColumnConstraints();

        colFirstPok.setPercentWidth(40);
        colSectPok.setPercentWidth(40);
        colAction.setPercentWidth(20);

        gridPane.getColumnConstraints().add(colFirstPok);
        gridPane.getColumnConstraints().add(colAction);
        gridPane.getColumnConstraints().add(colSectPok);

        gridPane.add(statPlayer, 0, 2);
        gridPane.add(buttonDefense, 0, 4);
        gridPane.add(actionEnemy, 2,1);
        gridPane.add(actionPlayer, 0,1);
        gridPane.add(statEnemy, 2, 0);

        buttonDefense.setOnAction(actionEvent -> {
            pokPlayer.setDefense(true);
            actionPlayer.setText(pokPlayer.getName() + " se défend!");
        });

        int i = 0;

        GridPane actionPane = new GridPane(); // et pas action man

        gridPane.add(actionPane, 0, 3);

        for(Attack attack : pokPlayer.getAttacks()) {
            Button button = new Button(attack.getName());

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(!deadOccuredOnce) {
                        int damage = attack.calculateDamage(pokPlayer, enemy);

                        if(enemy.isDefending()) {
                            actionPlayer.setText(attack.getName() + " a loupé!");
                            enemy.setDefense(false);
                            return;
                        }

                        actionPlayer.setText(pokPlayer.getName() + " attaque avec " + attack.getName());
                        playTranslationAnimation(statEnemy.getPokemonImageView(), 10f, 10, 100);
                        enemy.setPV(enemy.getPV() - damage);

                        if (enemy.getPV() <= 0) {
                            processDeathAnim(attack, pokPlayer, enemy, statEnemy.getPokemonImageView());
                            addContinue(primaryStage, player, pokPlayer, enemy);
                        }

                        enemyAttack(pokPlayer, enemy);
                    }
                }
            });

            actionPane.add(button, i++, 0);
            buttonList.add(button);
        }

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:assets/combat/combat_template1.png", 1920, 1080, true, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    //    gridPane.setBackground(new Background(backgroundImage));

        scene = new Scene(gridPane, 1920, 1080, Color.WHITE);

        scene.getStylesheets().add("file:assets/styles/combat.css");
    }

    private void enemyAttack(Pokemon player, Pokemon enemy) {
        Random random = new Random();

        boolean def = random.nextBoolean();

        enemy.setDefense(def);

        if(def) {
            actionEnemy.setText(enemy.getName() + " se défend!");
            return;
        }

        int iAttack = random.nextInt(enemy.getAttacks().size());
        Attack attack = enemy.getAttacks().get(iAttack);

        if(player.isDefending()) {
            actionEnemy.setText(attack.getName() + " a loupé!");
            player.setDefense(false);
            return;
        }
        playTranslationAnimation(statPlayer.getPokemonImageView(), 10f, 10, 100);

        int damage = attack.calculateDamage(enemy, player);

        player.setPV(player.getPV() - damage);

        actionEnemy.setText(enemy.getName() + " attaque avec " + attack.getName());

        if(player.getPV() <= 0) {
            processDeathAnim(attack, enemy, player, statPlayer.getPokemonImageView());
            addDeath();
        }
    }

    public void addDeath() {
        throw new NotImplementedException();
    }

    public void addContinue(Stage primaryStage, Player player, Pokemon attacker, Pokemon victim) {
        Button continueButton = new Button("continuer...");

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                victim.setPV(victim.getPVMax());

                attacker.levelUp();
                attacker.setPV(attacker.getPVMax());

                if(!player.alreayHavePokemon(victim.getName())) {
                    player.addPokemon(victim);
                }
                WorldScreen.load(primaryStage);
            }
        });

        gridPane.add(continueButton, 1, 1);
    }

    public void processDeathAnim(Attack attack, Pokemon attacker, Pokemon victim, ImageView imageView) {
        int damage = attack.calculateDamage(attacker, victim);
        victim.setPV(victim.getPV() - damage);

        if (victim.getPV() <= 0) {
            deadOccuredOnce = true;

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), imageView);

            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);

            fadeTransition.play();

            fadeTransition.getOnFinished();

        }
    }

    public void playTranslationAnimation(Node node, float offsetX, int cycleCount, int duration) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(duration), node);
        tt.setByX(offsetX);
        tt.setCycleCount(cycleCount);
        tt.setAutoReverse(true);

        tt.play();
    }

    public static void launch(Stage primaryStage, Player player, Pokemon pokemonPlayer, Pokemon pokemonEnemy) {
        ICombatScene combatScene = new CombatSceneSimple(primaryStage, player, pokemonPlayer, pokemonEnemy, 1920, 1080);

        primaryStage.setScene(scene);
    }
}
