package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.ShadowType;
import frontend.drawable.DrawableRectangle;
import javafx.scene.paint.Color;

public class RectangleButton extends FigureButton{
    public RectangleButton(String action){
        super(action);
    }

    public Figure generate(Point endPoint, Point startPoint, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        return new DrawableRectangle(startPoint, endPoint, shadow, gradient, bevel, color1, color2);
    }
}
