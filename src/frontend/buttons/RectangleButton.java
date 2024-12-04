package frontend.buttons;

import backend.Format;
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

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        return new DrawableRectangle(startPoint, endPoint, new Format(gradient, bevel, shadow, color1, color2));
    }
}
