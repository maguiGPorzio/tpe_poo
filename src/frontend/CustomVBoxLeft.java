package frontend;

import frontend.buttons.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private static final int SPACING=10;

    private final static String SELECT="seleccion";
    private final static String RECTANGLE="Rectangulo";
    private final static String CIRCLE="circulo";
    private final static String ELLIPSE="Elipse";
    private final static String SQUARE="Cuadrado";
    private final static String ERASE="Borrar";
    private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
    private static final Color DEFAULT_SECOND_COLOR = Color.ORANGE;

    private final ToggleButton selectionButton = new ToggleButton(SELECT);
    private final FigureButton rectangleButton = new RectangleButton(RECTANGLE);
    private final FigureButton circleButton = new CircleButton(CIRCLE);
    private final FigureButton squareButton = new SquareButton(SQUARE);
    private final FigureButton ellipseButton = new EllipseButton(ELLIPSE);
    private final ToggleButton deleteButton = new ToggleButton(ERASE);
    private final ChoiceBox shadowType = new ChoiceBox(FXCollections.observableArrayList("Simple Shadow", "Color Shadow", "Simple Inverted", "Color Inverted"));
    private final CheckBox bevel = new CheckBox("Biselado");
    private final ToggleButton copyFormatButton = new ToggleButton("Copiar Fmt.");

    // Selector de color de relleno
    ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR); //componente visual que permite a los usuarios seleccionar un color
    ColorPicker secondFillColorPicker = new ColorPicker(DEFAULT_SECOND_COLOR);

    ToggleButton[] toolsArr = {selectionButton, rectangleButton, squareButton, circleButton, ellipseButton};
    ToggleGroup tools = new ToggleGroup();

    public CustomVBoxLeft(){

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(BUTTON_MIN_WIDTH);
            tool.setCursor(Cursor.HAND);
            tool.setToggleGroup(tools);
        }

        setPadding(new Insets(PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(MIN_WIDTH);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(SPACING);

        getChildren().addAll(toolsArr);
        getChildren().add(new Separator());
        getChildren().addAll(deleteButton,shadowType, bevel, copyFormatButton, fillColorPicker, secondFillColorPicker);

    }

    public ToggleGroup getFigureButtons(){
        return tools;
    }

    public void setEraseAction(EventHandler<ActionEvent> action){ //EventHandler<ActionEvent> especifica lo que pasa cuando el boton es presionado
        deleteButton.setOnAction(action);
    }
    public void setCopyFormatAction(EventHandler<ActionEvent> action){ //EventHandler<ActionEvent> especifica lo que pasa cuando el boton es presionado
        copyFormatButton.setOnAction(action);
    }

    public boolean isSelectionButtonSelected(){
        return selectionButton.isSelected();
    }

}
