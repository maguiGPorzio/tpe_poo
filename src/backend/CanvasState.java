package backend;

import backend.model.Figure;
import backend.model.Pair;

import java.util.*;

public class CanvasState {

    private final int INITIAL_LAYERS = 3;
    private int currentLayer = 0;
    private final SortedMap<Integer, Layer> layers = new TreeMap<>();

    public CanvasState() {
        // Inicializar las capas iniciales
        for (int i = 0; i < INITIAL_LAYERS; i++) {
            layers.put(i, new Layer());
        }
    }

    public void addFigure(Figure figure) {
        layers.get(currentLayer).addFigure(figure);
    }

    public void deleteFigure(Figure figure) {
        layers.get(currentLayer).removeFigure(figure);
    }

    public void addLayer(){
        layers.put(layers.lastKey()+1, new Layer());
    }

    public void removeLayer(){
        if (currentLayer > INITIAL_LAYERS) {
            layers.remove(currentLayer);
            // nos posicionamos en la capa existente que le sigue.
            int previousLayer=1;
            for(int l=1 ; l<currentLayer ; l++){
                if(layers.containsKey(l)){
                    previousLayer = l;
                }
            }
            currentLayer = previousLayer;
        } else {
            System.out.println("No se pueden eliminar las capas iniciales."); //aca deberiamos ver tema errores
        }
    }

    // Cambiar a una capa específica
    public void changeLayer(int layer) {
        if (layers.containsKey(layer)) {
            currentLayer = layer; // Cambiar a la capa indicada
        } else {
            System.out.println("Error: Capa inválida."); //aca deberiamos ver tema errores
        }
    }

    public Format copyFormat(Figure figure){
        return figure.getFormat(); //Esto hay que cambiarlo pero me da paja en este momento
    }

    public void rotate(Figure figure){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(figure)){
            figure.rotate();
        }
    }

    public void flipV(Figure figure){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(figure)){
            figure.flipV();
        }
    }

    public void flipH(Figure figure){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(figure)){
            figure.flipH();
        }
    }

    public void duplicate(Figure figure){
        Figure newFigure = figure.duplicate();
        addFigure(figure);
    }

    public void divide(Figure figure){
        Pair<Figure> figurePair = figure.divide();
        Figure figure1 = figurePair.getLeft();
        Figure figure2 = figurePair.getRight();
        addFigure(figure1);
        addFigure(figure2);
    }

    public void showLayer(){
        layers.get(currentLayer).showLayer();
    }


    public void hideLayer(){
        layers.get(currentLayer).hideLayer();
    }

    public void moveToFront(Figure figure){
        List<Figure> l = figuresInLayer(currentLayer);
        if(l.contains(figure)){
            layers.get(currentLayer).moveToFront(figure);
        }
    }

    public void moveToBack(Figure figure){
        List<Figure> l = figuresInLayer(currentLayer);
        // se mueve la figura únicamente si ésta pertenece a la capa seleccionada
        if(l.contains(figure)){
            layers.get(currentLayer).moveToBack(figure);
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

    public Iterable<Figure> getCurrentFigures(){
        return figuresInLayer(currentLayer);
    }

    public Iterable<Figure> figures() {
        return getFigures(layers.keySet());
    }

    public Iterable<Figure> visibleFigures(){
        return getFigures(getVisibleLayers());
    }


}
