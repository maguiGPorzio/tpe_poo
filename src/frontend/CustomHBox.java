package frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.stream.Stream;

public class CustomHBox extends HBox {

    private static final int PADDING=5;
    private static final int MIN_WIDTH=100;
    private static final int BUTTON_MIN_WIDTH=90;
    private static final int SPACING=10;

    private final static String BRING_TO_FRONT="Traer al frente";
    private final static String MOVE_TO_BACK="Enviar al fondo";
    private final static String SHOW="Mostrar";
    private final static String HIDE="Ocultar";
    private final static String ADD_LAYER="Agregar capa";
    private final static String ERASE_LAYER="Eliminar una capa";
    private final static String LAYERS_SUBTITLE="Capas";

    Label layers_subtitle = new Label(LAYERS_SUBTITLE);

    private final ToggleButton moveToFrontButton = new ToggleButton(BRING_TO_FRONT);
    private final ToggleButton moveToBack = new ToggleButton(MOVE_TO_BACK);
    private ChoiceBox layers = new ChoiceBox(FXCollections.observableArrayList("Capa 1", "Capa 2", "Capa 3"));
    private final RadioButton showButton = new RadioButton(SHOW);
    private final RadioButton hideButton = new RadioButton(HIDE);
    private final ToggleButton addLayerButton = new ToggleButton(ADD_LAYER);
    private final ToggleButton removeLayerButton = new ToggleButton(ERASE_LAYER);

    public CustomHBox(){
        ToggleButton[] toolsArrLeft = {moveToFrontButton, moveToBack};
        ToggleButton[] toolsArrRight = {addLayerButton, removeLayerButton};
        ToggleButton[] toolsArr = {moveToFrontButton, moveToBack, addLayerButton, removeLayerButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(BUTTON_MIN_WIDTH);
            tool.setCursor(Cursor.HAND);
            tool.setToggleGroup(tools);
        }

        setPadding(new Insets(PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(MIN_WIDTH);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(SPACING);

        getChildren().addAll(toolsArrLeft);
        getChildren().addAll(layers_subtitle, layers, showButton, hideButton);
        getChildren().addAll(toolsArrRight);
    }

    public void setMoveToFrontAction(EventHandler<ActionEvent> action){moveToFrontButton.setOnAction(action);}
    public void setMoveToBackAction(EventHandler<ActionEvent> action){moveToBack.setOnAction(action);}
    public void setLayerAction(EventHandler<ActionEvent> action){layers.setOnAction(action);} //esta no se como funciona
    public void setShowAction(EventHandler<ActionEvent> action){showButton.setOnAction(action);}
    public void setHideAction(EventHandler<ActionEvent> action){hideButton.setOnAction(action);}
    public void setAddLayerAction(EventHandler<ActionEvent> action){addLayerButton.setOnAction(action);}
    public void setRemoveLayerAction(EventHandler<ActionEvent> action){removeLayerButton.setOnAction(action);}

    public int getCurrentLayer(){
        return layers.getSelectionModel().getSelectedIndex() + 1;
    }
}
