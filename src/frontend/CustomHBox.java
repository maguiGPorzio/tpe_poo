package frontend;

import backend.CanvasState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class CustomHBox extends HBox {

    private static final int PADDING=5;
    private static final int MIN_WIDTH=100;
    private static final int BUTTON_MIN_WIDTH=90;
    private static final int SPACING=10;
    private static final int MIN_LAYERS=3;
    private static final int FIRST_LAYER=1;

    private final static String BRING_TO_FRONT="Traer al frente";
    private final static String MOVE_TO_BACK="Enviar al fondo";
    private final static String SHOW="Mostrar";
    private final static String HIDE="Ocultar";
    private final static String ADD_LAYER="Agregar capa";
    private final static String ERASE_LAYER="Eliminar una capa";
    private final static String LAYERS_SUBTITLE="Capas";
    private int currentLayer=FIRST_LAYER;
    private int lastLayer=MIN_LAYERS;

    Label layers_subtitle = new Label(LAYERS_SUBTITLE);

    private final ToggleButton moveToFrontButton = new ToggleButton(BRING_TO_FRONT);
    private final ToggleButton moveToBack = new ToggleButton(MOVE_TO_BACK);
    ObservableList<String> layersList = FXCollections.observableArrayList("Capa 1", "Capa 2", "Capa 3");
    private final ChoiceBox<String> layers = new ChoiceBox<>(layersList);
    private final RadioButton showButton = new RadioButton(SHOW);
    private final RadioButton hideButton = new RadioButton(HIDE);
    private final ToggleButton addLayerButton = new ToggleButton(ADD_LAYER);
    private final ToggleButton removeLayerButton = new ToggleButton(ERASE_LAYER);

    public CustomHBox(){
        ToggleButton[] toolsArrMid={showButton, hideButton};
        ToggleGroup toolsMid = new ToggleGroup();
        for (ToggleButton tool : toolsArrMid){
            tool.setMinWidth(BUTTON_MIN_WIDTH);
            tool.setCursor(Cursor.HAND);
            tool.setToggleGroup(toolsMid);
        }
        showButton.setSelected(true);
        ToggleButton[] toolsArrLeft = {moveToFrontButton, moveToBack};
        ToggleButton[] toolsArrRight = {addLayerButton, removeLayerButton};
        ToggleButton[] toolsArr = {moveToFrontButton, moveToBack, addLayerButton, removeLayerButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(BUTTON_MIN_WIDTH);
            tool.setCursor(Cursor.HAND);
            tool.setToggleGroup(tools);
        }

        layers.setValue(layersList.getFirst());

        setPadding(new Insets(PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(MIN_WIDTH);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(SPACING);

        getChildren().addAll(toolsArrLeft);
        getChildren().addAll(layers_subtitle, layers, showButton, hideButton);
        getChildren().addAll(toolsArrRight);
    }

    //hide, show, moveToBack, moveToFront buttons
    public void setMoveToFrontAction(EventHandler<ActionEvent> action){moveToFrontButton.setOnAction(action);}
    public void setMoveToBackAction(EventHandler<ActionEvent> action){moveToBack.setOnAction(action);}
    public void setShowAction(EventHandler<ActionEvent> action){
        showButton.setOnAction(action);
    }
    public void setHideAction(EventHandler<ActionEvent> action){
        hideButton.setOnAction(action);
    }

    //Layers buttons
    public int getCurrentLayer(){
        int i = getLayerFromString(layers.getSelectionModel().getSelectedItem());
        return i;
    }

    public void setChangeLayerAction(EventHandler<ActionEvent> action){
        currentLayer = getCurrentLayer();
        layers.setOnAction(action);
    }

    public void setRemoveLayerAction(EventHandler<ActionEvent> action){
        removeLayerButton.setOnAction(event -> {
            if (currentLayer > MIN_LAYERS) {
                currentLayer=getCurrentLayer();
                System.out.println("La current layer en el front antes de borrar es: Capa %d".formatted(currentLayer));
                String layerToRemove = "Capa %d".formatted(currentLayer);
                int layerBelowNumber=getLayerBelow();//esto tiene que ir aca si o si, antes de sacar la layer de la lista
                layersList.removeIf( s -> s.equals(layerToRemove));
                layers.getItems().removeIf(s -> s.equals(layerToRemove));
                currentLayer = Math.max(MIN_LAYERS, layerBelowNumber);
                layers.setValue(layersList.get(findCurrentIndex()));
                lastLayer = getLayerFromString(layersList.getLast());
                System.out.println("After removal");
                System.out.println(layersList);
            }
            action.handle(event);
        });
    }

    public void setAddLayerAction(EventHandler<ActionEvent> action){
        addLayerButton.setOnAction(event -> {
            currentLayer=getCurrentLayer();
            String newLayer = "Capa %d".formatted(++lastLayer);
            layersList.add(newLayer);
            currentLayer = lastLayer;
            layers.setValue(layersList.getLast());
            action.handle(event);
            System.out.println("After adding");
            System.out.println(layersList);
        });
    }

    public void setLayerVisibility(boolean visibility){
        showButton.setSelected(visibility);
        hideButton.setSelected(!visibility);
    }

    //funciones privadas auxiliares

    private int findCurrentIndex(){
        return layersList.indexOf("Capa %d".formatted(currentLayer));
    }

    private int getLayerBelowIndex(String layer){
        return layersList.indexOf(layer)-1;
    }

    private int getLayerBelow(){
        return getLayerFromString(layersList.get(getLayerBelowIndex("Capa %d".formatted(currentLayer))));
    }

    private int getLayerFromString(String s){
        String[] fragments = s.split(" ");
        return Integer.parseInt(fragments[fragments.length-1]);
    }
}
