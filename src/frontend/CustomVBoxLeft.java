package frontend;

import frontend.drawable.Format;
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

    private final static String TITLE="Formato:";
    private final static String SELECT="Selección";
    private final static String RECTANGLE="Rectángulo";
    private final static String CIRCLE="Círculo";
    private final static String ELLIPSE="Elipse";
    private final static String SQUARE="Cuadrado";
    private final static String ERASE="Borrar";
    private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
    private static final Color DEFAULT_SECOND_COLOR = Color.ORANGE;
    private boolean copiedFormatedMode = false;
    private Format<Color> savedFormat = new Format<>(false, ShadowType.NOSHADOW, DEFAULT_FILL_COLOR, DEFAULT_SECOND_COLOR );

    private final ToggleButton selectionButton = new ToggleButton(SELECT);
    private final FigureButton rectangleButton = new RectangleButton(RECTANGLE);
    private final FigureButton circleButton = new CircleButton(CIRCLE);
    private final FigureButton squareButton = new SquareButton(SQUARE);
    private final FigureButton ellipseButton = new EllipseButton(ELLIPSE);
    private final ToggleButton deleteButton = new ToggleButton(ERASE);
    private final ChoiceBox<String> shadowType = new ChoiceBox<>(FXCollections.observableArrayList(" ","Simple Shadow", "Color Shadow", "Simple Inverted", "Color Inverted"));
    private final CheckBox bevel = new CheckBox("Biselado");
    private final ToggleButton copyFormatButton = new ToggleButton("Copiar Fmt.");

    Label title = new Label(TITLE);

    ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR);
    ColorPicker secondFillColorPicker = new ColorPicker(DEFAULT_SECOND_COLOR);

    ToggleButton[] toolsArr = {selectionButton, rectangleButton, squareButton, circleButton, ellipseButton};
    ToggleGroup tools = new ToggleGroup();

    public CustomVBoxLeft(){

        bevel.setSelected(false);
        shadowType.setValue(shadowType.getItems().getFirst());

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
        deleteButton.setMinWidth(BUTTON_MIN_WIDTH);
        copyFormatButton.setMinWidth(BUTTON_MIN_WIDTH);

        getChildren().addAll(toolsArr);
        getChildren().addAll(deleteButton,title,shadowType, bevel, copyFormatButton, fillColorPicker, secondFillColorPicker);

    }

    public ToggleGroup getFigureButtons(){
        return tools;
    }

    public void setEraseAction(EventHandler<ActionEvent> action){ //EventHandler<ActionEvent> especifica lo que pasa cuando el boton es presionado
        deleteButton.setOnAction(action);
        deselectButton(deleteButton);
    }

    private void deselectButton(ToggleButton button){
        button.setSelected(false);
        button.setFocusTraversable(false);
    }

    public void setCopyFormatAction(EventHandler<ActionEvent> action){ //EventHandler<ActionEvent> especifica lo que pasa cuando el boton es presionado
        copiedFormatedMode = true;
        copyFormatButton.setSelected(true);
        copyFormatButton.setOnAction(action);
    }

    public boolean isSelectionButtonSelected(){
        return selectionButton.isSelected();
    }

    public boolean isBevel(){
        return bevel.isSelected();
    }

    public ShadowType getShadow(){
        return  ShadowType.values()[shadowType.getSelectionModel().getSelectedIndex()];
    }

    public Color getColor1(){
        return fillColorPicker.getValue();
    }

    public Color getColor2(){
        return secondFillColorPicker.getValue();
    }

    public boolean isFigureSelected(){
        return !isSelectionButtonSelected() && tools.getSelectedToggle() != null;
    }


    public void setChangeShadowAction(EventHandler<ActionEvent> action){
        shadowType.setOnAction(action);
    }

    public void setBevelAction(EventHandler<ActionEvent> action){
        bevel.setOnAction(action);
    }

    public void setColor1Action(EventHandler<ActionEvent> action){
        fillColorPicker.setOnAction(action);
    }

    public void setColor2Action(EventHandler<ActionEvent> action){
        secondFillColorPicker.setOnAction(action);
    }

    public Format<Color> getCopiedFormat(){ //al ser llamada, apaga la flag copiedFormatMode
        copiedFormatedMode = false;
        deselectButton(copyFormatButton);
        return savedFormat;
    }

    public void setSavedFormat(Format<Color> format){
        copiedFormatedMode = true;
        savedFormat = format;
    }

    public boolean hasCopiedFormat(){
        return copiedFormatedMode;
    }

    public void setProperties(Format<Color> format){
        shadowType.setValue(shadowType.getItems().toArray()[format.getShadow().ordinal()].toString());
        bevel.setSelected(format.getBevel());
        fillColorPicker.setValue(format.getColor1());
        secondFillColorPicker.setValue(format.getColor2());
    }

}
