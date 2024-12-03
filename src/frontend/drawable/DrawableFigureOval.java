package frontend.drawable;

import backend.interfaces.Drawable;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public interface DrawableFigureOval extends Drawable {

    default void drawShade(boolean isShade, GraphicsContext gc, double offsetX, double offsetY, Point centerPoint, double sMayorAxis, double sMinorAxis, double diameter){
        if(isShade){
            gc.setFill(Color.GRAY);
            gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + 10.0, centerPoint.getY() - (sMinorAxis) + 10.0, diameter, diameter);
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

    default void drawBevel(boolean isBevel, GraphicsContext gc, double arcX, double arcY, double diameter){
        if(isBevel){
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX, arcY, diameter, diameter, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX, arcY, diameter, diameter, 225, 180, ArcType.OPEN);

        }
    }

    default void drawOval(Shadow shadow, boolean gradient, boolean bevel, GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected, Point centerPoint, Double mayorRadius, Double minorRadius){
        drawShade(shadow, gc, 10.0, 10.0, centerPoint, );
        drawBevel();
        gc.setStroke(isSelected ? Color.RED : lineColor);
        gc.setFill(fillColor);
        gc.fillOval(centerPoint.getX() - mayorRadius, centerPoint.getY() - minorRadius, minorRadius*2, mayorRadius*2);
        gc.strokeOval(centerPoint.getX() - mayorRadius, centerPoint.getY() - minorRadius, minorRadius*2, mayorRadius*2);
        drawGradient();
    }

    default double belongsInOval(Point eventPoint, Point centerPoint, Double mayorAxis, Double minorAxis){
        return (Math.pow(eventPoint.getX() - centerPoint.getX(), 2) / Math.pow(mayorAxis, 2)) +
                (Math.pow(eventPoint.getY() - centerPoint.getY(), 2) / Math.pow(minorAxis, 2));
    }

}
