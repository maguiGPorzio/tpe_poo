package frontend.buttons;

import frontend.formatted.FormattedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import backend.model.Point;
import frontend.formatted.Format;

public abstract class FigureButton extends ToggleButton {
    public FigureButton(String action){
        super(action);
    }

    public abstract FormattedFigure generate(Point startPoint, Point endPoint, Format format, GraphicsContext gc, int layer);
}
