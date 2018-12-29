package pokdp.Combat.Screen;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pokdp.Attack.Attack;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperSceneCombat;
import pokdp.Utils.Constantes;
import pokdp.World.Screen.WorldScene;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatSceneSimple extends WrapperSceneCombat {
    private Label actionEnemy;
    private Label actionPlayer;
    private GridPane gridPane = new GridPane();
    private GridPane actionPane = new GridPane(); // et pas action man

    private Button buttonDefense = new Button("Defense");

    private AUIPokemonStat statPlayer;
    private AUIPokemonStat statEnemy;

    private List<Button> buttonList = new ArrayList<>();

    private boolean deadOccuredOnce = false;

    @Override
    public void load(double width, double height) {
        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(width);

        buttonDefense. setStyle(Constantes.DEFAULT_BUTTON);

        ColumnConstraints colFirstPok = new ColumnConstraints();
        ColumnConstraints colSectPok = new ColumnConstraints();
        ColumnConstraints colAction = new ColumnConstraints();

        colFirstPok.setPercentWidth(40);
        colSectPok.setPercentWidth(40);
        colAction.setPercentWidth(20);

        gridPane.getColumnConstraints().add(colFirstPok);
        gridPane.getColumnConstraints().add(colAction);
        gridPane.getColumnConstraints().add(colSectPok);

        gridPane.setStyle(
                "-fx-background-image: url(" +
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/2fb2821a-1406-4a1d-9b04-6668f278e944/d83jrvo-9c5af987-f243-4528-8455-5ca87551aa58.jpg" +
                        "); " +
                        "-fx-background-size: cover;"
        );

        gridPane.setAlignment(Pos.BOTTOM_CENTER);

        setScene(new Scene(gridPane, width, height, Color.WHITE));

        //getScene().getStylesheets().add("file:assets/styles/combat.css");
    }

    @Override
    public void setAttributes(Player player, Pokemon pokPlayer, Pokemon enemy) {
        buttonDefense.setOnAction(actionEvent -> {
            pokPlayer.setDefense(true);
            actionPlayer.setText(pokPlayer.getName() + " se défend!");
        });


        initialize(player, pokPlayer, enemy);
    }

    private void initialize(Player player, Pokemon pokPlayer, Pokemon enemy) {
        gridPane.getChildren().clear();
        actionPane.getChildren().clear();
        buttonList.clear();

        deadOccuredOnce = false;

        statPlayer = new UIPokemonStatSimple(pokPlayer, enemy);
        statEnemy = new UIPokemonStatSimple(enemy, pokPlayer);

        actionPlayer = new Label();
        actionEnemy = new Label();

        gridPane.add(statPlayer, 0, 2);
        gridPane.add(statEnemy, 2, 0);

        gridPane.add(actionEnemy, 2,1);
        gridPane.add(actionPlayer, 0,1);
        gridPane.add(actionPane, 0, 3);
        gridPane.add(buttonDefense, 0, 4);

        int i = 0;

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 15);

            actionEnemy.setFont(font);
            actionPlayer.setFont(font);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        for(Attack attack : pokPlayer.getAttacks()) {
            Button button = new Button(attack.getName());

            button.setStyle(Constantes.DEFAULT_BUTTON);

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
                            addContinue(player, pokPlayer, enemy);
                        }

                        enemyAttack(pokPlayer, enemy);
                    }
                }
            });

            actionPane.add(button, i++, 0);
            buttonList.add(button);
        }
        //BackgroundImage backgroundImage = new BackgroundImage(new Image("file:assets/combat/combat_template1.png", 1920, 1080, true, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    //    gridPane.setBackground(new Background(backgroundImage));

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

    private void addDeath() {
        throw new NotImplementedException();
    }

    private void addContinue(Player player, Pokemon attacker, Pokemon victim) {
        Button continueButton = new Button("continuer...");

        continueButton.setStyle(Constantes.DEFAULT_BUTTON);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                victim.setPV(victim.getPVMax());

                attacker.levelUp();
                attacker.setPV(attacker.getPVMax());

                if(!player.alreayHavePokemon(victim.getName())) {
                    player.addPokemon(new Pokemon(victim));
                }

                //SceneManager.setScene("WorldScene");
                SceneManager.setSceneVictory("VictoryScene", victim);
            }
        });

        gridPane.add(continueButton, 1, 1);
    }

    private void processDeathAnim(Attack attack, Pokemon attacker, Pokemon victim, ImageView imageView) {
        int damage = attack.calculateDamage(attacker, victim);
        victim.setPV(victim.getPV() - damage);

        if (victim.getPV() <= 0) {
            deadOccuredOnce = true;

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), imageView);

            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);

            fadeTransition.play();
        }
    }

    private void playTranslationAnimation(Node node, float offsetX, int cycleCount, int duration) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(duration), node);
        tt.setByX(offsetX);
        tt.setCycleCount(cycleCount);
        tt.setAutoReverse(true);

        tt.play();
    }
}
