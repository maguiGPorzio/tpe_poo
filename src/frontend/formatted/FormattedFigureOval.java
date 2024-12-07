package frontend.formatted;

import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public interface FormattedFigureOval extends FormattedFigure {

    default void drawShade(ShadowType shadow, GraphicsContext gc, Color firstFillColor, Point centerPoint, double sMayorAxis, double sMinorAxis){
        gc.setFill(shadow.getShadeColor(firstFillColor));
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + shadow.getOffsetX(), centerPoint.getY() - (sMinorAxis / 2) + shadow.getOffsetY(), sMayorAxis, sMinorAxis);
    }

    default void drawGradient(GraphicsContext gc, Color firstFillColor, Color secondFillColor){
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, firstFillColor),
                    new Stop(1, secondFillColor));
            gc.setFill(radialGradient);
    }

    default void drawBevel(boolean isBevel, GraphicsContext gc, double arcX, double arcY, double sMayorAxis, double sMinorAxis){
        if(isBevel){
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX , arcY, sMayorAxis , sMinorAxis , 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX , arcY , sMayorAxis, sMinorAxis, 225, 180, ArcType.OPEN);
            gc.setLineWidth(1);
        }
    }

    default void drawOval(Format format, GraphicsContext gc, boolean isSelected, Point centerPoint, Double sMayorAxis, Double sMinorAxis){
        drawShade(format.getShadow(), gc, format.getColor1(),centerPoint, sMayorAxis, sMinorAxis);
        double arcX = centerPoint.getX() - sMayorAxis / 2;
        double arcY = centerPoint.getY() - sMinorAxis / 2;
        drawBevel(format.getBevel(), gc, arcX, arcY, sMayorAxis, sMinorAxis);
        drawGradient(gc, format.getColor1(), format.getColor2());
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
    }
}
