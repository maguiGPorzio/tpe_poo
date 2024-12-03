package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import frontend.Shadow;
import frontend.drawable.DrawableCircle;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String action){
        super(action);
    }

    public Figure generate(Point endPoint, Point startPoint, Shadow shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableCircle(startPoint, circleRadius, shadow, gradient, bevel, color1, color2);
    }
}
