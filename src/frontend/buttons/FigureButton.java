package frontend.buttons;

import backend.model.Figure;
import javafx.scene.control.ToggleButton;
import backend.model.Point;
import javafx.scene.paint.Color;

public abstract class FigureButton extends ToggleButton {
    public FigureButton(String action){
        super(action);
    }

    public abstract Figure generate(Point endPoint, Point startPoint, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2);
}
