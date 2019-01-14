package pokdp.Scene.Custom.Combat;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Combat.ACombatManager;
import pokdp.Combat.Attack.Attack;
import pokdp.Combat.ECombatRules;
import pokdp.Combat.SimpleCombatManager;
import pokdp.Entity.ArtificialIntelligence.AArtificialIntelligenceManager;
import pokdp.Entity.ArtificialIntelligence.EAiType;
import pokdp.Entity.ArtificialIntelligence.EEntityHurted;
import pokdp.Entity.ArtificialIntelligence.SimpleAI;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperSceneCombat;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.ColConstraintManager;
import pokdp.Utils.ConstraintManager.ConstraintManager;
import pokdp.Utils.ConstraintManager.RowConstraintManager;
import pokdp.Utils.Transition.Transition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CombatScene extends WrapperSceneCombat {
    private Label actionEnemy;
    private Label actionPlayer;
    private GridPane gridPane = new GridPane();
    private GridPane actionPane = new GridPane(); // et pas action man

    private Button buttonDefense = new Button("Defense");

    private AUIPokemonStat statPlayer;
    private AUIPokemonStat statEnemy;

    private List<Button> buttonList = new ArrayList<>();

    private AArtificialIntelligenceManager iaManager = new SimpleAI();
    private ACombatManager combatManager = new SimpleCombatManager();


    private boolean deadOccuredOnce = false;
    private boolean nextTurnCanBePlayed = true;
    private int actualTurn = 0; // 0 = joueur    1 = ennemi

    @Override
    public void load(double width, double height) {
        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(width);

        buttonDefense.setStyle(Constantes.DEFAULT_BUTTON);

        ConstraintManager colConstraintsManager = new ColConstraintManager(new int[]{5, 40, 20, 40});
        ConstraintManager rowConstraintsManager = new RowConstraintManager(new int[]{80, 20});

        colConstraintsManager.addPercentToPane(gridPane);
        rowConstraintsManager.addPercentToPane(gridPane);

        gridPane.setStyle(
                "-fx-background-image: url(" +
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/2fb2821a-1406-4a1d-9b04-6668f278e944/d83jrvo-9c5af987-f243-4528-8455-5ca87551aa58.jpg" +
                        "); " +
                        "-fx-background-size: cover;"
        );

        gridPane.setAlignment(Pos.BOTTOM_LEFT);

        setScene(new Scene(gridPane, width, height, Color.WHITE));

        //getScene().getStylesheets().add("file:assets/styles/combat.css");
    }

    /**
     * met a jour le combat
     * @param player        le joueur
     * @param pokPlayer     le pokemon du joueur
     * @param enemy         le pokemon ennemi
     */
    @Override
    public void setAttributes(Player player, Pokemon pokPlayer, Pokemon enemy) {
        initialize(player, pokPlayer, enemy);
    }

    /**
     * initialise le combat
     * @param player        le joueur
     * @param pokPlayer     le pokemon du joueur
     * @param enemy         le pokemon ennemi
     */
    private void initialize(Player player, Pokemon pokPlayer, Pokemon enemy) {
        gridPane.getChildren().clear();
        actionPane.getChildren().clear();
        buttonList.clear();

        deadOccuredOnce = false;

        statPlayer = new UIPokemonStatSimple(pokPlayer, enemy);
        statEnemy = new UIPokemonStatSimple(enemy, pokPlayer);

        actionPlayer = new Label();
        actionEnemy = new Label();

        gridPane.add(statPlayer, 1, 3);
        gridPane.add(statEnemy, 3, 1);

        gridPane.add(actionEnemy, 3,2);
        gridPane.add(actionPlayer, 1,2);
        gridPane.add(actionPane, 1, 4);
        gridPane.add(buttonDefense, 1, 5);

        int i = 0;

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 15);

            actionEnemy.setFont(font);
            actionPlayer.setFont(font);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        buttonDefense.setOnAction(actionEvent -> {
            pokPlayer.setDefense(true);
            actionPlayer.setText(pokPlayer.getName() + " se défend!");
            nextTurnCanBePlayed = true;
            enemyAttack(player,pokPlayer, enemy);
        });

        for(Attack attack : pokPlayer.getAttacks()) {
            Button button = new Button(attack.getName());

            button.setStyle(Constantes.DEFAULT_BUTTON);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(!deadOccuredOnce && nextTurnCanBePlayed ) {
                        int damage = attack.calculateDamage(pokPlayer, enemy);

                        if(Constantes.CHEAT_ON) {
                            processDeathAnim(attack, pokPlayer, enemy, statEnemy.getPokemonImageView());
                        }

                        if (enemy.isDefending()) {
                            actionPlayer.setText(attack.getName() + " a loupé!");
                            enemy.setDefense(false);
                            return;
                        }

                        enemy.setPV(enemy.getPV() - damage);
                        actionPlayer.setText(pokPlayer.getName() + " attaque avec " + attack.getName());

                        TranslateTransition tt = Transition.translate(statEnemy.getPokemonImageView(), 10f, 10, 100);

                        nextTurnCanBePlayed = false;

                        tt.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                nextTurnCanBePlayed = true;
                                if(!deadOccuredOnce)
                                    enemyAttack(player,pokPlayer, enemy);
                            }
                        });

                        ECombatRules rule = combatManager.checkRules(pokPlayer, enemy);

                        if (rule == ECombatRules.ENEMY_DEAD) {
                            processDeathAnim(attack, pokPlayer, enemy, statEnemy.getPokemonImageView());
                            addContinue(player, pokPlayer, enemy);
                        }
                    }
                }
            });

            actionPane.add(button, 1 + i++, 0);
            buttonList.add(button);
        }
    }

    /**
     * gere l attaque de l ennemi
     * @param player    le pokemon du joueur
     * @param enemy     le pokemon ennemi
     */
    private void enemyAttack(Player player,Pokemon pokPlayer, Pokemon enemy) {
        EEntityHurted entityHurted = iaManager.performAction(enemy, pokPlayer, EAiType.NORMAL);
        Attack attack = iaManager.getSelectedAttack();

        switch (entityHurted) {
            case POKEMON_ATTACKER_DEF:
                actionEnemy.setText(enemy.getName() + " se défend!");
                break;
            case POKEMON_VICTIM_MISSED:
                actionEnemy.setText(attack.getName() + " a loupé!");
                break;
            case POKEMON_VICTIM: {
                actionEnemy.setText(enemy.getName() + " attaque avec " + attack.getName());
                TranslateTransition tt = Transition.translate(statPlayer.getPokemonImageView(), 10f, 10, 100);

                nextTurnCanBePlayed = false;

                tt.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        nextTurnCanBePlayed = true;
                    }
                });
                }
                break;
        }

        ECombatRules rule = combatManager.checkRules(pokPlayer, enemy);

        if (rule == ECombatRules.PLAYER_DEAD) {
            processDeathAnim(attack, pokPlayer, enemy, statPlayer.getPokemonImageView());
            addDeath(player,enemy,pokPlayer);
            return;
        }
    }

    /**
     * gere la mort du joueur
     */
    private void addDeath(Player player, Pokemon attacker, Pokemon victim) {
        Button continueButton = new Button("Continuer");
        Button fleeButton = new Button("Fuir");

        continueButton.setStyle(Constantes.DEFAULT_BUTTON);
        fleeButton.setStyle(Constantes.DEFAULT_BUTTON);

        fleeButton.setOnAction(actionEvent -> {
            /*
            Afficher l'écran de défaite
            Relancer l'overworld
            */
            SceneManager.setSceneDefeat(Constantes.DEFEATSCENE_NAME,player);
        });

        continueButton.setOnAction(actionEvent -> {
            /*
                Lancer le menu de choix de Pokemon, choisir puis revenir sur le combat
             */
        });
        gridPane.add(continueButton,1,1);
        gridPane.add(fleeButton,2,1);
    }

    /**
     * gere la victoire du joueur
     * @param player        le joueur
     * @param attacker      le pokemon gagnant
     * @param victim        le pokemon perdant
     */
    private void addContinue(Player player, Pokemon attacker, Pokemon victim) {
        Button continueButton = new Button("continuer...");

        continueButton.setStyle(Constantes.DEFAULT_BUTTON);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                victim.setPV(victim.getPVMax());

                attacker.levelUp();
                attacker.setPV(attacker.getPVMax());

                if (!player.alreayHavePokemon(victim.getName())) {
                    player.addPokemon(new Pokemon(victim));
                    SceneManager.setSceneVictory(Constantes.VICTORYSCENE_NAME, victim);
                } else {
                    SceneManager.setScene(Constantes.WORLDSCENE_NAME);
                }
            }
        });

        gridPane.add(continueButton, 1, 1);
    }

    /**
     * gere l animation de mort d un pokemon
     * @param attack        l attaque
     * @param attacker      le pokemon attaquant
     * @param victim        le pokemon victime
     * @param imageView     l imageview du sprite du pokemon
     */
    private void processDeathAnim(Attack attack, Pokemon attacker, Pokemon victim, ImageView imageView) {
        victim.setPV(0);
        deadOccuredOnce = true;

        Transition.fade(imageView, 3000, 1.0, 0.0);
    }
}
