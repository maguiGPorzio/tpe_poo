package backend;

import backend.model.Figure;
import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final int INITIAL_LAYERS = 3;
    private int currentLayer = 0;
    private final List<List<Figure>> layers = new ArrayList<>();

    public CanvasState() {
        // Inicializar las capas iniciales
        for (int i = 0; i < INITIAL_LAYERS; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void addFigure(Figure figure) {
        layers.get(currentLayer).add(figure);
    }

    public void deleteFigure(Figure figure) {
        layers.get(currentLayer).remove(figure);
    }

    public Iterable<Figure> figures() {
        List<Figure> toReturn = new ArrayList<>();
        for(List<Figure> layer : layers){
            toReturn.addAll(layer);
        }
        return toReturn;
    }

    public void addLayer(){
        layers.add(new ArrayList<>());
    }

    public void removeLayer(){
        if (layers.size() > INITIAL_LAYERS) {
            layers.remove(currentLayer);
            currentLayer--;
        } else {
            System.out.println("No se pueden eliminar las capas iniciales."); //aca deberiamos ver tema errores
        }
    }

    // Cambiar a una capa específica
    public void changeLayer(int layer) {
        if (layer > 0 && layer <= layers.size()) {
            currentLayer = layer - 1; // Cambiar a la capa indicada
        } else {
            System.out.println("Error: Capa inválida."); //aca deberiamos ver tema errores
        }
    }

    // Obtener figuras de una capa específica
    public List<Figure> getFiguresFromLayer(int layer) {
        if (layer > 0 && layer <= layers.size()) {
            return layers.get(layer - 1);
        }
        return new ArrayList<>();
    }

    // Obtener figuras de la capa actual
    public List<Figure> getFiguresFromCurrentLayer() {
        return layers.get(currentLayer);
    }
}
