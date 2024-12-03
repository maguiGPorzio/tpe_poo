package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse extends Ellipse implements DrawableFigureOval {

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(centerPoint, sMayorAxis, sMinorAxis, shadow, gradient, bevel, color1, color2);
    }

    public void draw(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected){
        drawOval(this.shadow, this.hasGradient(), this.hasBevel(), gc, lineColor, fillColor, isSelected, centerPoint, sMayorAxis/2, sMinorAxis/2);
    }

    public boolean belongs(Point eventPoint){
        return belongsInOval(eventPoint, centerPoint,sMayorAxis,sMinorAxis) <= 0.30;
    }

    @Override
    public Figure duplicate(){
        Point newCenterPoint = new Point(centerPoint.getX() - OFFSET, centerPoint.getY() - OFFSET);
        return new DrawableEllipse(newCenterPoint, sMayorAxis, sMinorAxis, shadow, gradient, bevel, color1, color2);
    }
}
