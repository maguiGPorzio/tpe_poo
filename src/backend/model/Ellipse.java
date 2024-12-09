package backend.model;

public class Ellipse implements Figure{

    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;
    protected final static double OFFSET = 10.0;
    protected final int layer;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, int layer){
        this.centerPoint = centerPoint;
        setAxis(sMayorAxis, sMinorAxis);
        this.layer = layer;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    @Override
    public void move(double diffX, double diffY){
        centerPoint.move(diffX, diffY);
    }

    @Override
    public void rotate(){
        setAxis(sMinorAxis, sMayorAxis);
    }

    @Override
    public void flipV(){
        double diffY = sMinorAxis;
        centerPoint.move(0, -diffY);
    }

    @Override
    public void flipH(){
        double diffX = sMayorAxis;
        centerPoint.move(diffX, 0);
    }

    private void setAxis(double sMayorAxis, double sMinorAxis){
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    protected Point duplicatePoint(){
        return new Point(centerPoint.getX() + OFFSET, centerPoint.getY() + OFFSET);
    }

    @Override
    public Figure duplicate(){
        return new Ellipse(duplicatePoint(), sMayorAxis, sMinorAxis, layer);
    }

    protected Pair<Point> dividePoints(){
        Point center1, center2;
        if(sMayorAxis >= sMinorAxis){ //respetando el eje horizontal
            center1 = new Point(centerPoint.getX() - sMayorAxis/4, centerPoint.getY());
            center2 = new Point(centerPoint.getX() + sMayorAxis/4, centerPoint.getY());
        }
        else{
            center1 = new Point(centerPoint.getX(), centerPoint.getY() - sMayorAxis/4);
            center2 = new Point(centerPoint.getX(), centerPoint.getY() + sMayorAxis/4);
        }
        return new Pair<>(center1, center2);
    }

    @Override
    public Pair<Figure> divide(){
        Pair<Point> p = dividePoints();
        Ellipse sub1 = new Ellipse(p.getLeft(), sMayorAxis/2, sMinorAxis/2, layer);
        Ellipse sub2 = new Ellipse(p.getRight(), sMayorAxis/2, sMinorAxis/2, layer);
        return new Pair<>(sub1, sub2);
    }

    @Override
    public boolean belongs(Point eventPoint){
        return (Math.pow(eventPoint.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis, 2)) + (Math.pow(eventPoint.getY() - getCenterPoint().getY(), 2) / Math.pow(sMinorAxis, 2)) <= 0.30;
    }

    public int getLayer() {
        return layer;
    }
}
