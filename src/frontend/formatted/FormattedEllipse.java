package frontend.formatted;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Pair;
import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class FormattedEllipse extends Ellipse implements FormattedFigureOval {
    private final Format format;
    private final GraphicsContext gc;

    public FormattedEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, Format format, GraphicsContext gc, int layer){
        super(centerPoint, sMayorAxis, sMinorAxis, layer);
        this.gc = gc;
        this.format = format;
    }

    public void draw(boolean isSelected){
        drawOval(format, gc, isSelected, centerPoint, sMayorAxis, sMinorAxis);
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
        return new FormattedEllipse(duplicatePoint(), getsMayorAxis(), getsMinorAxis(), format.duplicate(), gc, layer);
    }

    @Override
    public Pair<Figure> divide(){
        Pair<Point> p = dividePoints();
        FormattedEllipse f1 = new FormattedEllipse(p.getLeft(), getsMayorAxis()/2, getsMinorAxis()/2, format.duplicate(), gc, layer);
        FormattedEllipse f2 = new FormattedEllipse(p.getRight(), getsMayorAxis()/2, getsMinorAxis()/2, format.duplicate(), gc, layer);
        return new Pair<>(f1, f2);
    }
}
