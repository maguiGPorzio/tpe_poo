package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer<F extends Figure> {
    private boolean visible;
    private final List<F> figuresInLayer = new ArrayList<>();

    public Layer(){
        this.visible = true;
    }

    public void addFigure(F figure){
        if(figure != null && !figuresInLayer.contains(figure)) {
            figuresInLayer.add(figure);
        }
    }

    public void removeFigure(F figure){
        figuresInLayer.remove(figure);
    }

    public List<F> getFiguresInLayer(){
        return figuresInLayer;
    }

    public void showLayer(){
        this.visible = true;
    }

    public void hideLayer(){
        this.visible = false;
    }

    public boolean isVisible(){
        return visible;
    }

    public void moveToFront(F figure){
        removeFigure(figure);
        figuresInLayer.addLast(figure);
    }

    public void moveToBack(F figure){
        removeFigure(figure);
        figuresInLayer.addFirst(figure);
    }

    @SafeVarargs
    public final void addFiguresInMiddle(F figure, F... newFigures){
        if(figure != null && figuresInLayer.contains(figure)){
            int idx = figuresInLayer.indexOf(figure);
            figuresInLayer.remove(figure);
            figuresInLayer.add(idx, figure);
            figuresInLayer.addAll(idx + 1, Arrays.asList(newFigures));
        }
    }
}
