package frontend.buttons;

import backend.Format;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableSquare;
import javafx.scene.paint.Color;

public class SquareButton extends FigureButton{
    public SquareButton(String action){
        super(action);
    }

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean bevel, Color color1, Color color2){
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableSquare(startPoint, size, new Format(bevel, shadow, color1, color2));
    }
}
