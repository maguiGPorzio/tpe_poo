package frontend.buttons;

import backend.Format;
import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableCircle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String action){
        super(action);
    }

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean bevel, Color color1, Color color2, GraphicsContext gc){
        double circleRadius = startPoint.distance(endPoint);
        return new DrawableCircle(startPoint, circleRadius, new Format(bevel, shadow, color1, color2), gc);
    }
}
