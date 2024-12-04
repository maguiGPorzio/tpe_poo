package frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CustomVBoxRight extends VBox {

    private static final int PADDING=5;
    private static final int MIN_WIDTH=100;
    //private static final int BUTTON_MIN_WIDTH=90;
    private static final int SPACING=10;

    private final static String DIVIDE="Dividir";
    private final static String DUPLICATE="Duplicate";
    private final static String ROTATE_D="Girar D";
    private final static String FLIP_H="Voltear H";
    private final static String FLIP_V="Voltear V";
    private final static String TITLE="Acciones:";

    Label title = new Label(TITLE);

    private final ToggleButton rotateButton = new ToggleButton(ROTATE_D);
    private final ToggleButton flipHButton = new ToggleButton(FLIP_H);
    private final ToggleButton flipVButton = new ToggleButton(FLIP_V);
    private final ToggleButton duplicateButton = new ToggleButton(DUPLICATE);
    private final ToggleButton divideButton = new ToggleButton(DIVIDE);



    public CustomVBoxRight(){

        setPadding(new Insets(PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(MIN_WIDTH);
        setAlignment(Pos.CENTER_RIGHT);
        setSpacing(SPACING);

        getChildren().addAll(title, rotateButton,flipHButton,flipVButton,duplicateButton, divideButton);
    }

    public void setFlipVAction(EventHandler<ActionEvent> action){flipVButton.setOnAction(action);}
    public void setFlipHAction(EventHandler<ActionEvent> action){flipHButton.setOnAction(action);}
    public void setRotateAction(EventHandler<ActionEvent> action){rotateButton.setOnAction(action);}
    public void setDuplicateAction(EventHandler<ActionEvent> action){duplicateButton.setOnAction(action);}
    public void setDivideAction(EventHandler<ActionEvent> action){divideButton.setOnAction(action);}

}
