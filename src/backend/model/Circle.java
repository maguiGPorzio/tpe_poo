package backend.model;


import backend.Format;

public class Circle<Color> extends Ellipse<Color> {

    public Circle(Point centerPoint, double radius, Format<Color> format) {
        super(centerPoint, radius * 2, radius * 2, format);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getsMayorAxis() / 2);
    }

    public double getRadius() {
        return getsMayorAxis() / 2;
    }

}
