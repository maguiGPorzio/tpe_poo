package backend.model;

import backend.Format;

public class Square<Color> extends Rectangle<Color> {

    public Square(Point topLeft, double size, Format<Color> format) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size), format);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
