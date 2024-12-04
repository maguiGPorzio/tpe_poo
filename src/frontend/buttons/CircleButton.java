package frontend.buttons;

import backend.Format;
import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableCircle;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String action){
        super(action);
    }

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableCircle(startPoint, circleRadius, new Format(gradient, bevel, shadow, color1, color2));
    }
}
