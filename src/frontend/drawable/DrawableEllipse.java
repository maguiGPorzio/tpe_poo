package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class DrawableEllipse extends Ellipse implements DrawableFigureOval {

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(centerPoint, sMayorAxis, sMinorAxis, shadow, gradient, bevel, color1, color2);
    }

    private final GraphicsContext gc;

    public void draw(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected){
        drawOval(this.isShade(), this.isGradient(), this.isBevel(), gc, lineColor, fillColor, isSelected, centerPoint, sMayorAxis/2, sMinorAxis/2);
    }

    public boolean belongs(Point eventPoint){
        return belongsInOval(eventPoint, centerPoint,sMayorAxis,sMinorAxis) <= 0.30;
    }
}
