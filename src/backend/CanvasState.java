package backend;

import backend.model.Figure;
import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final int INITIAL_LAYERS = 3;
    private int currentLayer = 0; //chequear
    private final List<List<Figure>> layers = new ArrayList<>();

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
        if(currentLayer >= INITIAL_LAYERS){
            layers.remove(currentLayer);
        }
    }

    public void changeLayer(int layer){
        // (?) verificamos que la layer sea válida?
        currentLayer = layer-1;
    }
}
