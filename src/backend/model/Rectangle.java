package backend.model;

public class Rectangle implements Figure {

    protected Point topLeft, bottomRight;
    protected final static double OFFSET = 10.0;
    protected final int layer;

    public Rectangle(Point topLeft, Point bottomRight, int layer) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.layer = layer;
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

    @Override
    public void flipV(){
        double diffY = bottomRight.getY() - topLeft.getY();
        topLeft.move(0, diffY);
        bottomRight.move(0, diffY);
    }

    @Override
    public void flipH(){
        double diffX = bottomRight.getX() - topLeft.getX();
        topLeft.move(diffX, 0);
        bottomRight.move(diffX, 0);
    }

    @Override
    public void rotate(){
        Point centerPoint = getCenter();
        double topLeftX = centerPoint.getX() - (bottomRight.getY() - centerPoint.getY());
        double topLeftY = centerPoint.getY() - (centerPoint.getX() - topLeft.getX());
        double bottomRightX = centerPoint.getX() + (centerPoint.getY() - topLeft.getY());
        double bottomRightY = centerPoint.getY() + (bottomRight.getX() - centerPoint.getX());
        topLeft = new Point(topLeftX, topLeftY);
        bottomRight = new Point(bottomRightX, bottomRightY);
    }

    protected Pair<Point> duplicatePoint(){
        Point newTopLeft = new Point(topLeft.getX() + OFFSET, topLeft.getY() + OFFSET);
        Point newBottomRight = new Point(bottomRight.getX() + OFFSET, bottomRight.getY() + OFFSET);
        return new Pair<>(newTopLeft, newBottomRight);
    }

    @Override
    public Figure duplicate(){
        Pair<Point> point = duplicatePoint();
        return new Rectangle(point.getLeft(), point.getRight(), layer);
    }

    protected Pair<Pair<Point>> dividePoints(){
        double centerY = (topLeft.getY() + bottomRight.getY())/2;
        double centerX = (topLeft.getX() + bottomRight.getX())/2;

        Point s1topLeft = new Point(topLeft.getX(), centerY + (topLeft.getY() - bottomRight.getY())/4);
        Point s1bottomRight = new Point(centerX, centerY - (topLeft.getY() - bottomRight.getY())/4);

        Point s2topLeft = new Point(s1bottomRight.getX(), s1topLeft.getY());
        Point s2bottomRight = new Point(bottomRight.getX(), s1bottomRight.getY());

        return new Pair<>(new Pair<>(s1topLeft, s1bottomRight), new Pair<>(s2topLeft, s2bottomRight));
    }

    @Override
    public Pair<Figure> divide(){
        Figure sub1, sub2;
        Pair<Pair<Point>> p = dividePoints();
        sub1 = new Rectangle(p.getLeft().getLeft(), p.getLeft().getRight(), layer);
        sub2 = new Rectangle(p.getRight().getLeft(), p.getRight().getRight(), layer);
        return new Pair<>(sub1, sub2);
    }

    @Override
    public boolean belongs(Point eventPoint){
        return eventPoint.getX() > topLeft.getX() && eventPoint.getX() < bottomRight.getX() &&
                eventPoint.getY() > topLeft.getY() && eventPoint.getY() < bottomRight.getY();
    }

    public int getLayer() {
        return layer;
    }
}
