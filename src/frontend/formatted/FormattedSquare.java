package frontend.formatted;

import backend.model.Figure;
import backend.model.Pair;
import backend.model.Point;
import backend.model.Square;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FormattedSquare extends Square implements FormattedFigureRectangle {
    private final GraphicsContext gc;
    private final Format format;

    public FormattedSquare(Point topLeft, double size, Format format, GraphicsContext gc, int layer){
        super(topLeft, size, layer);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawRectangle(format, gc, isSelected, topLeft, bottomRight);
    }


    public void setShadow(ShadowType shadow){
        format.setShadow(shadow);
    }
    public void setBevel(boolean bevel){
        format.setBevel(bevel);
    }
    public void setColor1(Color color){
        format.setColor1(color);
    }
    public void setColor2(Color color){
        format.setColor2(color);
    }
    public Format getFormat(){
        return format;
    }

    @Override
    public Pair<Figure> divide(){
        Pair<Pair<Point>> p = dividePoints();
        FormattedSquare f1 = new FormattedSquare(p.getLeft().getLeft(), getSize()/2, format.duplicate(), gc, layer);
        FormattedSquare f2 = new FormattedSquare(p.getRight().getLeft(), getSize()/2, format.duplicate(), gc, layer);
        return new Pair<>(f1,f2);
    }

    @Override
    public Figure duplicate(){
        Pair<Point> p = duplicatePoint();
        return new FormattedSquare(p.getLeft(), getSize(), format.duplicate(), gc, layer);
    }
}
