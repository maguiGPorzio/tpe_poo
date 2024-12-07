package backend.model;

import backend.interfaces.Movable;

public class Point implements Movable {

    private double x, y;

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

    public double distance(Point other){
        if(other != null){
            return Math.pow((Math.pow(x-other.x,2))+(Math.pow(y-other.y, 2)), 0.5);
        }
        return 0;
    }

    public boolean atLeft(Point other){
        if(other != null){
            return this.x < other.x;
        }
        return false;
    }
    public boolean atRight(Point other){
        return !atLeft(other);
    }
    public boolean under(Point other){
        if(other != null){
            return this.y > other.y;
        }
        return false;
    }
    public boolean above(Point other){
        return !under(other);
    }
}
