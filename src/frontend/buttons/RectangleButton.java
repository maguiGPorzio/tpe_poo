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

    public FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc){
        return new DrawableRectangle(startPoint, endPoint, format, gc);
    }
}
