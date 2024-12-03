package backend.model;

import frontend.drawable.DrawableEllipse;
import javafx.scene.paint.Color;

public abstract class Ellipse extends Figure {

    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
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

}
