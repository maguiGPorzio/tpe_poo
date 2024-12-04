package frontend.drawable;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawableRectangle extends Rectangle implements DrawableFigureRectangle{

    public DrawableRectangle(Point topLeft, Point bottomRight, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(topLeft, bottomRight, shadow, gradient, bevel, color1, color2);
    }

    public void draw(GraphicsContext gc, boolean isSelected){
        drawRectangle(this.hasShade(), this.hasGradient(), this.hasBevel(), gc, this.color1, this.color2, isSelected, topLeft, bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }
}
