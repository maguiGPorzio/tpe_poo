package backend.model;

public class Rectangle implements Figure {

    protected Point topLeft, bottomRight;
    protected final static double OFFSET = 10.0;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
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
        double topLeftX = centerPoint.getX() - (bottomRight.getY() - centerPoint.getY());
        double topLeftY = centerPoint.getY() - (centerPoint.getX() - topLeft.getX());
        double bottomRightX = centerPoint.getX() + (centerPoint.getY() - topLeft.getY());
        double bottomRightY = centerPoint.getY() + (bottomRight.getX() - centerPoint.getX());
        topLeft = new Point(topLeftX, topLeftY);
        bottomRight = new Point(bottomRightX, bottomRightY);
    }

    @Override
    public Figure duplicate(){
        Point newTopLeft = new Point(topLeft.getX() - OFFSET, topLeft.getY() - OFFSET);
        Point newBottomRight = new Point(bottomRight.getX() - OFFSET, bottomRight.getY() - OFFSET);
        return new Rectangle(newTopLeft, newBottomRight);
    }

    @Override
    public Pair<Figure> divide(){
        Figure sub1, sub2;
        double centerY = (topLeft.getY() + bottomRight.getY())/2;
        double centerX = (topLeft.getX() + bottomRight.getX())/2;
        Point s1topLeft = new Point(topLeft.getX(), centerY + (topLeft.getY() - bottomRight.getY())/4);
        Point s1bottomRight = new Point(centerX, centerY - (topLeft.getY() - bottomRight.getY())/4);
        sub1 = new Rectangle(s1topLeft, s1bottomRight);
        Point s2topLeft = new Point(s1bottomRight.getX(), s1topLeft.getY());
        Point s2bottomRight = new Point(bottomRight.getX(), s1bottomRight.getY());
        sub2 = new Rectangle(s2topLeft, s2bottomRight);
        return new Pair<>(sub1, sub2);
    }

    public boolean belongs(Point eventPoint){
        return eventPoint.getX() > topLeft.getX() && eventPoint.getX() < bottomRight.getX() &&
                eventPoint.getY() > topLeft.getY() && eventPoint.getY() < bottomRight.getY();
    }

}
