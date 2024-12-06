package frontend.buttons;

import frontend.drawable.Format;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleButton extends FigureButton{
    public RectangleButton(String action){
        super(action);
    }

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean bevel, Color color1, Color color2, GraphicsContext gc){
        return new DrawableRectangle(startPoint, endPoint, new Format(bevel, shadow, color1, color2), gc);
    }
}
