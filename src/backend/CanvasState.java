package backend;

import backend.model.Figure;
import backend.model.Pair;

import java.util.*;

public class CanvasState<F extends Figure> {

    private final int INITIAL_LAYERS = 3;
    private int currentLayer = 1;
    private final SortedMap<Integer, Layer<F>> layers = new TreeMap<>();
    private F selectedFigure;

    public CanvasState() {
        // Inicializar las capas iniciales
        for (int i = 1; i <= INITIAL_LAYERS; i++) {
            layers.put(i, new Layer<>());
        }
    }

    public void setCurrentLayer(int layer){
        if(layers.containsKey(layer)){
            currentLayer = layer;
        }
    }

    public void addFigure(F figure) {
        if(figure != null){
            layers.get(currentLayer).addFigure(figure);
        }
    }

    @SafeVarargs
    private void addFiguresInMiddle(F figure, F... figuresToAdd){
        if(figure != null){
            layers.get(currentLayer).addFiguresInMiddle(figure, figuresToAdd);
        }
    }

    public void deleteFigure() {
        if (selectedFigure != null) {
            layers.get(currentLayer).removeFigure(selectedFigure);
            selectedFigure = null;
        }
    }

    public void addLayer(){
        layers.put(layers.lastKey()+1, new Layer<>());
        changeLayer(layers.lastKey());
    }

    public void removeLayer(){
        if (currentLayer > INITIAL_LAYERS) {
            layers.remove(currentLayer);
            // nos posicionamos en la capa existente que le sigue.
            int previousLayer = INITIAL_LAYERS;
            for(int l=3 ; l<currentLayer ; l++){
                if(layers.containsKey(l)){
                    previousLayer = l;
                }
            } 
            changeLayer(previousLayer);
        }
    }

    // Cambiar a una capa específica
    public void changeLayer(int layer) {
        if (layers.containsKey(layer)) {
            currentLayer = layer; // Cambiar a la capa indicada
            selectedFigure = null;
        }
    }

    public void rotate(){
        List<F> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.rotate();
        }
    }

    public void flipV(){
        List<F> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.flipV();
        }
    }

    public void flipH(){
        List<F> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            selectedFigure.flipH();
        }
    }

    @SuppressWarnings("unchecked")
    public void duplicate() {
        if (selectedFigure != null) {
            F newFigure =  (F) selectedFigure.duplicate();
            addFiguresInMiddle(selectedFigure, newFigure);
            setSelectedFigure(null);
        }
    }

    @SuppressWarnings("unchecked")
    public void divide(){
        if(selectedFigure != null) {
            Pair<F> figurePair = (Pair<F>) selectedFigure.divide();
            F figure1 = figurePair.getLeft();
            F figure2 = figurePair.getRight();
            addFiguresInMiddle(selectedFigure, figure1, figure2);
            deleteFigure();
            setSelectedFigure(null);
        }
    }

    public void showLayer(){
        layers.get(currentLayer).showLayer();
    }


    public void hideLayer(){
        layers.get(currentLayer).hideLayer();
    }

    public void moveToFront(){
        List<F> l = figuresInLayer(currentLayer);
        if(l.contains(selectedFigure)){
            layers.get(currentLayer).moveToFront(selectedFigure);
        }
    }

    public void moveToBack(){
        List<F> l = figuresInLayer(currentLayer);
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

    private Iterable<F> getFigures(Iterable<Integer> givenLayers){
        List<F> toReturn = new ArrayList<>();
        for(int layer : givenLayers){
            toReturn.addAll(layers.get(layer).getFiguresInLayer());
        }
        return toReturn;
    }


    public List<F> figuresInLayer(int layer){
        return layers.getOrDefault(layer, new Layer<>()).getFiguresInLayer();
    }

    public List<F> getCurrentFigures(){
        return figuresInLayer(currentLayer);
    }

    public Iterable<F> figures() {
        return getFigures(layers.keySet());
    }

    public Iterable<F> visibleFigures(){
        return getFigures(getVisibleLayers());
    }

    public void setSelectedFigure(F figure){ this.selectedFigure = figure; }

    public F getSelectedFigure() {
        return selectedFigure;
    }

    public boolean belongsInCurrentLayer(F figure){
        return layers.get(currentLayer).getFiguresInLayer().contains(figure);
    }

    public boolean isCurrentLayerVisible(){
        return layers.get(currentLayer).isVisible();
    }

}
