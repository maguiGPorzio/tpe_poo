package frontend.drawable;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse extends Ellipse implements DrawableFigureOval {

    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(centerPoint, sMayorAxis, sMinorAxis, shadow, gradient, bevel, color1, color2);
    }

    public void draw(GraphicsContext gc, boolean isSelected){
        drawOval(this.hasShade(), this.hasGradient(), this.hasBevel(), gc, this.color1, this.color2, isSelected, centerPoint, sMayorAxis/2, sMinorAxis/2);
    }

    public boolean belongs(Point eventPoint){
        return belongsInOval(eventPoint, centerPoint,sMayorAxis,sMinorAxis) <= 0.30;
    }

}
