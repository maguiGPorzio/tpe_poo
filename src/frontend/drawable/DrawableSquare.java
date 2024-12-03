package frontend.drawable;

import backend.model.Point;
import backend.model.Square;
import frontend.Shadow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableSquare extends Square implements  DrawableFigureRectangle{

    public DrawableSquare(Point topLeft, double size, Shadow shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(topLeft, size, shadow, gradient, bevel, color1, color2);
    }
    public void draw(GraphicsContext gc, Color lineColor, boolean isSelected){
        drawRectangle(this.hasShade(), this.hasGradient(), this.hasBevel(), gc, lineColor, this.color1, this.color2, isSelected, topLeft, bottomRight);
    }

    public boolean belongs(Point eventPoint){
        return belongsInRectangle(eventPoint, topLeft, bottomRight);
    }

}
