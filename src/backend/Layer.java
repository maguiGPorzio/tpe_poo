package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class Layer<Color> {
    private boolean visible;
    private final List<Figure<Color>> figuresInLayer = new ArrayList<>();

    public Layer(){
        this.visible = true;
    }

    public void addFigure(Figure<Color> figure){
        figuresInLayer.add(figure);
    }

    public void removeFigure(Figure<Color> figure){
        figuresInLayer.remove(figure);
    }

    public List<Figure<Color>> getFiguresInLayer(){
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

    public void moveToFront(Figure<Color> figure){
        removeFigure(figure);
        figuresInLayer.addLast(figure);
    }

    public void moveToBack(Figure<Color> figure){
        removeFigure(figure);
        figuresInLayer.addFirst(figure);
    }
}
