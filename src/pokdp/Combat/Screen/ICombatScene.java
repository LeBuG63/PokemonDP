package pokdp.Combat.Screen;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pokdp.Entity.Pokemon.Pokemon;

public abstract class ICombatScene {
    private Scene scene;

    private GridPane gridPane = new GridPane();

    private IUIPokemonStat  statPlayer;
    private IUIPokemonStat  statEnemy;

    private ImageView imageView = new ImageView(new Image("file:assets/combat/combat_template1.png"));

    ICombatScene(Pokemon player, Pokemon enemy) {
        statPlayer = new UIPokemonStatSimple(player);
        statEnemy = new UIPokemonStatSimple(enemy);

        gridPane.add(statPlayer, 0, 0);
        gridPane.add(statEnemy, 0, 1);

        scene = new Scene(gridPane);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:assets/combat/combat_template1.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        gridPane.setBackground(new Background(backgroundImage));

        //this.getChildren().add(statPlayer);
        //this.getChildren().add(statEnemy);
    }

    public Scene getScene() {
        return scene;
    }
}
