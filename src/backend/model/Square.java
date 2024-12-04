package backend.model;

import frontend.ShadowType;
import javafx.scene.paint.Color;

public abstract class Square extends Rectangle {

    public Square(Point topLeft, double size, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size), shadow, gradient, bevel, color1, color2);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
