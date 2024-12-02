package backend.model;

import backend.interfaces.Movable;

public class Point implements Movable {

    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public void move(double diffX, double diffY){
        this.x += diffX;
        this.y += diffY;
    }
}
