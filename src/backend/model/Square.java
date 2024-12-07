package backend.model;

public class Square extends Rectangle {

    protected double size;

    public Square(Point topLeft, double size) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size));
        this.size = size;
    }

    protected double getSize(){
        return size;
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
