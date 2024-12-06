package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private boolean visible;
    private final List<Figure> figuresInLayer = new ArrayList<>();

    public Layer(){
        this.visible = true;
    }

    public void addFigure(Figure figure){
        figuresInLayer.add(figure);
    }

    public void removeFigure(Figure figure){
        figuresInLayer.remove(figure);
    }

    public List<Figure> getFiguresInLayer(){
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

    public void moveToFront(Figure figure){
        removeFigure(figure);
        figuresInLayer.addLast(figure);
    }

    public void moveToBack(Figure figure){
        removeFigure(figure);
        figuresInLayer.addFirst(figure);
    }
}
