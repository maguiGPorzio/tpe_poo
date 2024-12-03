package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;
import frontend.drawable.DrawableSquare;
import javafx.scene.paint.Color;

public class SquareButton extends FigureButton{
    public SquareButton(String action){
        super(action);
    }

    public Figure generate(Point endPoint, Point startPoint, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableSquare(startPoint, size, shadow, gradient, bevel, color1, color2);
    }
}
