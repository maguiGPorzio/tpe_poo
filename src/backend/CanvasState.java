package backend;

import backend.model.Figure;
import backend.model.Pair;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.Format;

import java.util.*;

public class CanvasState {

    private final int INITIAL_LAYERS = 3;
    private int currentLayer = 1;
    public final SortedMap<Integer, Layer> layers = new TreeMap<>(); //lo pongo publico para intentar
    private Format copiedFormat;
    private Figure selectedFigure;

    public CanvasState() {
        // Inicializar las capas iniciales
        for (int i = 1; i <= INITIAL_LAYERS; i++) {
            layers.put(i, new Layer());
        }
    }

    public void setCurrentLayer(int layer){
        if(layers.containsKey(layer)){
            currentLayer = layer;
        }
    }

    public void addFigure(Figure figure) {
        if(figure != null){
            layers.get(currentLayer).addFigure(figure);
        }
    }

    public void deleteFigure() {
        if (selectedFigure != null) {
            layers.get(currentLayer).removeFigure(selectedFigure);
            selectedFigure = null;
        }
    }

    public void addLayer(){
        layers.put(layers.lastKey()+1, new Layer());
        changeLayer(layers.lastKey());
    }

    public void removeLayer(){
        if (currentLayer > INITIAL_LAYERS) {
            layers.remove(currentLayer);
            // nos posicionamos en la capa existente que le sigue.
            int previousLayer=INITIAL_LAYERS;
            for(int l=3 ; l<currentLayer ; l++){
                if(layers.containsKey(l)){
                    previousLayer = l;
                }
            } 
            changeLayer(previousLayer);
        } else {
            System.out.println("No se pueden eliminar las capas iniciales."); //aca deberiamos ver tema errores
        }
    }

    // Cambiar a una capa específica
    public void changeLayer(int layer) {
        if (layers.containsKey(layer)) {
            currentLayer = layer; // Cambiar a la capa indicada
            selectedFigure = null;
        } else {
            System.out.println("Error: Capa inválida."); //aca deberiamos ver tema errores
        }
    }

    public void rotate(){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.rotate();
        }
    }

    public void flipV(){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.flipV();
        }
    }

    public void flipH(){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.flipH();
        }
    }

    public void duplicate() {
        if (selectedFigure != null) {
            Figure newFigure = selectedFigure.duplicate();
            addFigure(newFigure);
        }
    }

    public void divide(){
        if(selectedFigure != null) {
            Pair<Figure> figurePair = selectedFigure.divide();
            Figure figure1 = figurePair.getLeft();
            Figure figure2 = figurePair.getRight();
            addFigure(figure1);
            addFigure(figure2);
            deleteFigure();
        }
    }

    public void showLayer(){
        layers.get(currentLayer).showLayer();
    }


    public void hideLayer(){
        layers.get(currentLayer).hideLayer();
    }

    public void moveToFront(){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            layers.get(currentLayer).moveToFront(selectedFigure);
        }
    }

    public void moveToBack(){
        List<Figure> l = figuresInLayer(currentLayer);
        // se mueve la figura únicamente si ésta pertenece a la capa seleccionada
        if(l.contains(selectedFigure)){
            layers.get(currentLayer).moveToBack(selectedFigure);
        }

    }

    private Iterable<Integer> getVisibleLayers(){
        List<Integer> toReturn = new ArrayList<>();
        for(int layer : layers.keySet()){
            if(layers.get(layer).isVisible()){
                toReturn.add(layer);
            }
        }
        return toReturn;
    }

    private Iterable<Figure> getFigures(Iterable<Integer> givenLayers){
        List<Figure> toReturn = new ArrayList<>();
        for(int layer : givenLayers){
            toReturn.addAll(layers.get(layer).getFiguresInLayer());
        }
        return toReturn;
    }


    public List<Figure> figuresInLayer(int layer){
        return layers.getOrDefault(layer, new Layer()).getFiguresInLayer();
    }

    public List<Figure> getCurrentFigures(){
        return figuresInLayer(currentLayer);
    }

    public Iterable<Figure> figures() {
        return getFigures(layers.keySet());
    }

    public Iterable<Figure> visibleFigures(){
        return getFigures(getVisibleLayers());
    }

    public void setSelectedFigure(Figure figure){ this.selectedFigure = figure; }

    public Figure getSelectedFigure() {
        return selectedFigure;
    }

    public boolean belongsInCurrentLayer(Figure figure){
        return layers.get(currentLayer).getFiguresInLayer().contains(figure);
    }

    public boolean isCurrentLayerVisible(){
        return layers.get(currentLayer).isVisible();
    }


}
