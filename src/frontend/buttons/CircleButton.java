package frontend.buttons;

import frontend.drawable.Format;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableCircle;
import frontend.drawable.FormatedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String action){
        super(action);
    }

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        double circleRadius = startPoint.distance(endPoint);
        return new DrawableCircle(startPoint, circleRadius, new Format(bevel, shadow, color1, color2), gc);
    }
}
