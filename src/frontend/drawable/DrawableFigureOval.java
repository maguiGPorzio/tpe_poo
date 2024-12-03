package frontend.drawable;

import backend.interfaces.Drawable;
import backend.model.Point;
import frontend.Shadow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public interface DrawableFigureOval extends Drawable {

    default void drawShade(Shadow shadow, GraphicsContext gc, Color firstFillColor, Point centerPoint, double sMayorAxis, double sMinorAxis){
        if(shadow.hasShade()){
            gc.setFill(shadow.getType().getShadeColor(firstFillColor));
            gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + shadow.getType().getOffsetX(), centerPoint.getY() - (sMinorAxis) + shadow.getType().getOffsetY(), sMayorAxis, sMinorAxis);
        }
    }

    default void drawGradient(boolean isGradient, GraphicsContext gc, Color firstFillColor, Color secondFillColor){
        if(isGradient){
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, firstFillColor),
                    new Stop(1, secondFillColor));
            gc.setFill(radialGradient);
        }
    }

    default void drawBevel(boolean isBevel, GraphicsContext gc, double arcX, double arcY, double sMayorAxis, double sMinorAxis){
        if(isBevel){
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX, arcY, sMayorAxis, sMinorAxis, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX, arcY, sMayorAxis, sMinorAxis, 225, 180, ArcType.OPEN);
        }
    }

    default void drawOval(Shadow shadow, boolean gradient, boolean bevel, GraphicsContext gc, Color lineColor, Color firstColor, Color secondColor, boolean isSelected, Point centerPoint, Double sMayorAxis, Double sMinorAxis){
        drawShade(shadow, gc, firstColor,centerPoint, sMayorAxis, sMinorAxis);
        double arcX = centerPoint.getX() - sMayorAxis / 2;
        double arcY = centerPoint.getY() - sMinorAxis / 2;
        drawBevel(bevel, gc, arcX, arcY, sMayorAxis, sMinorAxis);
        gc.setStroke(isSelected ? Color.RED : lineColor);
        gc.setFill(firstColor);
        gc.fillOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMinorAxis*2, sMayorAxis*2);
        gc.strokeOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, sMinorAxis*2, sMayorAxis*2);
        drawGradient(gradient, gc, firstColor, secondColor);
    }

    default double belongsInOval(Point eventPoint, Point centerPoint, Double mayorAxis, Double minorAxis){
        return (Math.pow(eventPoint.getX() - centerPoint.getX(), 2) / Math.pow(mayorAxis, 2)) +
                (Math.pow(eventPoint.getY() - centerPoint.getY(), 2) / Math.pow(minorAxis, 2));
    }

}
