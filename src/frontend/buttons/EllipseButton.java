package frontend.buttons;

import frontend.drawable.Format;
import backend.model.Figure;
import backend.model.Point;
import frontend.ShadowType;
import frontend.drawable.DrawableEllipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseButton extends FigureButton{
    public EllipseButton(String action){
        super(action);
    }

    public Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean bevel, Color color1, Color color2, GraphicsContext gc){
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new DrawableEllipse(centerPoint, sMayorAxis, sMinorAxis, new Format(bevel, shadow, color1, color2), gc);
    }
}
