package backend.model;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, double radius, int layer) {
        super(centerPoint, radius * 2, radius * 2, layer);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getsMayorAxis() / 2);
    }

    public double getRadius() {
        return getsMayorAxis() / 2;
    }

}
