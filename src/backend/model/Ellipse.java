package backend.model;

public class Ellipse implements Figure{

    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;
    protected final static double OFFSET = 10.0;


    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        this.centerPoint = centerPoint;
        setAxis(sMayorAxis, sMinorAxis);
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

    public void rotate(){
        setAxis(sMinorAxis, sMayorAxis);
    }

    public void flipV(){
        double diffY = sMinorAxis;
        centerPoint.move(0, -diffY);
    }

    public void flipH(){
        double diffX = sMayorAxis;
        centerPoint.move(diffX, 0);
    }

    private void setAxis(double sMayorAxis, double sMinorAxis){
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public Figure duplicate(){
        Point newCenterPoint = new Point(centerPoint.getX() - OFFSET, centerPoint.getY() - OFFSET);
        return new Ellipse(newCenterPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public Pair<Figure> divide(){
        Point center1, center2;
        if(sMayorAxis >= sMinorAxis){ //respetando el eje horizontal
            center1 = new Point(centerPoint.getX() - sMayorAxis/4, centerPoint.getY());
            center2 = new Point(centerPoint.getX() + sMayorAxis/4, centerPoint.getY());
        }
        else{
            center1 = new Point(centerPoint.getX(), centerPoint.getY() - sMayorAxis/4);
            center2 = new Point(centerPoint.getX(), centerPoint.getY() + sMayorAxis/4);
        }
        Ellipse sub1 = new Ellipse(center1, sMayorAxis/2, sMinorAxis/2);
        Ellipse sub2 = new Ellipse(center2, sMayorAxis/2, sMinorAxis/2);
        return new Pair<>(sub1, sub2);
    }

    public boolean belongs(Point eventPoint){
        return (Math.pow(eventPoint.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis, 2)) + (Math.pow(eventPoint.getY() - getCenterPoint().getY(), 2) / Math.pow(sMinorAxis, 2)) <= 0.30;
    }

}
