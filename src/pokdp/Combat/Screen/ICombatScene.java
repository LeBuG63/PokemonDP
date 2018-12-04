package pokdp.Combat.Screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pokdp.Entity.Pokemon.Pokemon;

public interface ICombatScene {
    static void launch(Stage primaryStage, String pokemonPlayer, String pokemonEnnemy) {};
}
