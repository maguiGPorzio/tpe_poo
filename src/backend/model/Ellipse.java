package backend.model;

import frontend.ShadowType;
import frontend.drawable.DrawableEllipse;
import frontend.drawable.DrawableRectangle;
import javafx.scene.paint.Color;

public abstract class Ellipse extends Figure {

    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        super(shadow, gradient, bevel, color1, color2);
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
        double diffY = sMinorAxis * 2;
        centerPoint.move(0, -diffY);
    }

    public void flipH(){
        double diffX = sMayorAxis * 2;
        centerPoint.move(diffX, 0);
    }

    private void setAxis(double sMayorAxis, double sMinorAxis){
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public Figure duplicate(){
        Point newCenterPoint = new Point(centerPoint.getX() - OFFSET, centerPoint.getY() - OFFSET);
        return new DrawableEllipse(newCenterPoint, sMayorAxis, sMinorAxis, shadow, gradient, bevel, color1, color2);
    }

    @Override
    public Pair<Figure> divide(){
        Point center1, center2;
        if(sMayorAxis >= sMinorAxis){ //respetando el eje horizontal
            center1 = new Point(centerPoint.getX() - sMayorAxis/2, centerPoint.getY());
            center2 = new Point(centerPoint.getX() + sMayorAxis/2, centerPoint.getY());
        }
        else{
            center1 = new Point(centerPoint.getX(), centerPoint.getY() - sMayorAxis/2);
            center2 = new Point(centerPoint.getX(), centerPoint.getY() + sMayorAxis/2);
        }
        DrawableEllipse sub1 = new DrawableEllipse(center1, sMayorAxis/2, sMinorAxis/2, shadow, gradient, bevel, color1, color2);
        DrawableEllipse sub2 = new DrawableEllipse(center2, sMayorAxis/2, sMinorAxis/2, shadow, gradient, bevel, color1, color2);
        return new Pair<Figure>(sub1, sub2);
    }

}
