package frontend.formatted;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Pair;
import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class FormattedCircle extends Circle implements FormattedFigureOval {
    private final GraphicsContext gc;
    private final Format format;

    public FormattedCircle(Point centerPoint, double radius, Format format, GraphicsContext gc, int layer){
        super(centerPoint, radius, layer);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, getRadius()*2, getRadius()*2);
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
    public Figure duplicate(){
        return new FormattedCircle(duplicatePoint(), getRadius(), format.duplicate(), gc, layer);
    }

    @Override
    public Pair<Figure> divide(){
        Pair<Point> p = dividePoints();
        FormattedCircle f1 = new FormattedCircle(p.getLeft(), getRadius()/2, format.duplicate(), gc, layer);
        FormattedCircle f2 = new FormattedCircle(p.getRight(), getRadius()/2, format.duplicate(), gc, layer);
        return new Pair<>(f1, f2);
    }

}
