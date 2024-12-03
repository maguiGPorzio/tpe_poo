package frontend.drawable;

import frontend.buttons.CircleButton;
import frontend.buttons.EllipseButton;
import frontend.buttons.RectangleButton;
import frontend.buttons.SquareButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomVBoxLeft extends VBox {

    private static final int PADDING=5;
    private static final int MIN_WIDTH=100;
    private static final int BUTTON_MIN_WIDTH=90;

    private final static String SELECT="seleccion";
    private final static String RECTANGLE="Rectangulo";
    private final static String CIRCLE="circulo";
    private final static String ELLIPSE="Elipse";
    private final static String SQUARE="Cuadrado";
    private final static String ERASE="Rectangulo";
    private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
    private static final Color DEFAULT_SECOND_COLOR = Color.ORANGE;

    private final ToggleButton selectionButton = new ToggleButton(SELECT);
    private final ToggleButton rectangleButton = new RectangleButton(RECTANGLE);
    private final ToggleButton circleButton = new CircleButton(CIRCLE);
    private final ToggleButton squareButton = new SquareButton(SQUARE);
    private final ToggleButton ellipseButton = new EllipseButton(ELLIPSE);
    private final ToggleButton deleteButton = new ToggleButton(ERASE);

    private final ChoiceBox shadowType = new ChoiceBox(FXCollections.observableArrayList("Simple Shadow", "Color Shadow", "Simple Inverted", "Color Inverted"));

    private final CheckBox bevel = new CheckBox("Biselado");

    private final ToggleButton copyFormatButton = new ToggleButton("Copiar Fmt.");

    // Selector de color de relleno
    ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR); //componente visual que permite a los usuarios seleccionar un color
    ColorPicker secondFillColorPicker = new ColorPicker(DEFAULT_SECOND_COLOR);

    public CustomVBoxLeft(){
        ToggleButton[] toolsArr = {selectionButton, rectangleButton, squareButton, circleButton, ellipseButton, deleteButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(BUTTON_MIN_WIDTH);
            tool.setCursor(Cursor.HAND);
            tool.setToggleGroup(tools);
        }

        setPadding(new Insets(PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(MIN_WIDTH);
        setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(toolsArr);

    }
}
