package backend.model;

import frontend.Shadow;
import javafx.scene.paint.Color;

public abstract class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius, Shadow shadow, boolean gradient, boolean bevel, Color color1, Color color2) {
        super(centerPoint, radius * 2, radius * 2, shadow, gradient, bevel, color1, color2);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getsMayorAxis() / 2);
    }

    public double getRadius() {
        return getsMayorAxis() / 2;
    }

}
