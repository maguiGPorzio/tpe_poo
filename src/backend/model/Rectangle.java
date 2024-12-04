package backend.model;

import frontend.drawable.DrawableRectangle;
import backend.Format;

public abstract class Rectangle extends Figure {

    protected Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, Format format) {
        super(format);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    protected double getVLength(){
        return topLeft.getY() - bottomRight.getY();
    }
    protected double getHLength(){
        return bottomRight.getX() - topLeft.getX();
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    protected Point getCenter(){
        return new Point((bottomRight.getX()+topLeft.getX())/2, (bottomRight.getY()+topLeft.getY())/2);
    }

    @Override
    public void move(double diffX, double diffY){
        topLeft.move(diffX, diffY);
        bottomRight.move(diffX, diffY);
    }

    public void flipV(){
        double diffY = bottomRight.getY() - topLeft.getY();
        topLeft.move(0, diffY);
        bottomRight.move(0, diffY);
    }

    public void flipH(){
        double diffX = bottomRight.getX() - topLeft.getX();
        topLeft.move(diffX, 0);
        bottomRight.move(diffX, 0);
    }

    public void rotate(){
        Point centerPoint = getCenter();
        double topLeftX = centerPoint.getX() - topLeft.getY() + centerPoint.getY();
        double topLeftY = centerPoint.getY() + topLeft.getX() - centerPoint.getX();
        double bottomRightX = centerPoint.getX() - bottomRight.getY() + centerPoint.getY();
        double bottomRightY = centerPoint.getY() + bottomRight.getX() - centerPoint.getX();
        topLeft = new Point(topLeftX, topLeftY);
        bottomRight = new Point(bottomRightX, bottomRightY);
    }

    @Override
    public Figure duplicate(){
        Point newTopLeft = new Point(topLeft.getX() - OFFSET, topLeft.getY() - OFFSET);
        Point newBottomRight = new Point(bottomRight.getX() - OFFSET, bottomRight.getY() - OFFSET);
        return new DrawableRectangle(newTopLeft, newBottomRight, format);
    }

    @Override
    public Pair<Figure> divide(){
        double verticalLength = getVLength();
        double horizontalLength = getHLength();

        Figure sub1, sub2;
        if (verticalLength >= horizontalLength) { //dividimos manteniendo eje vertical
            double centerX = (topLeft.getX() + bottomRight.getX())/2;
            sub1 = new DrawableRectangle(topLeft, new Point(centerX, bottomRight.getY()), format);
            sub2 = new DrawableRectangle(new Point(centerX, topLeft.getY()), bottomRight, format);
        }
        else{ // dividimos manteniendo el eje horizontal
            double centerY = (topLeft.getY() + bottomRight.getY())/2;
            sub1 = new DrawableRectangle(topLeft, new Point(bottomRight.getX(), centerY), format);
            sub2 = new DrawableRectangle(new Point(topLeft.getX(), centerY), bottomRight, format);
        }

        return new Pair<Figure>(sub1, sub2);
    }
}
