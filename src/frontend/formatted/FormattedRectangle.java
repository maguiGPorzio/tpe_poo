package frontend.formatted;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import backend.model.Pair;

public class FormattedRectangle extends Rectangle implements FormattedFigureRectangle {

    private final GraphicsContext gc;
    private final Format format;

    public FormattedRectangle(Point topLeft, Point bottomRight, Format format, GraphicsContext gc, int layer){
        super(topLeft, bottomRight, layer);
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
        FormattedRectangle f1 = new FormattedRectangle(p.getLeft().getLeft(), p.getLeft().getRight(), format.duplicate(), gc, layer);
        FormattedRectangle f2 = new FormattedRectangle(p.getRight().getLeft(), p.getRight().getRight(), format.duplicate(), gc, layer);
        return new Pair<>(f1,f2);
    }

    @Override
    public Figure duplicate(){
        Pair<Point> p = duplicatePoint();
        return new FormattedRectangle(p.getLeft(), p.getRight(), format.duplicate(), gc, layer);
    }


}
