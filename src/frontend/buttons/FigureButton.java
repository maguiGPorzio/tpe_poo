package frontend.buttons;

import backend.model.Figure;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import backend.model.Point;
import javafx.scene.paint.Color;

public abstract class FigureButton extends ToggleButton {
    public FigureButton(String action){
        super(action);
    }

    public abstract Figure generate(Point startPoint, Point endPoint, ShadowType shadow, boolean bevel, Color color1, Color color2, GraphicsContext gc);
}
