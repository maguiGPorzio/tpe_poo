package frontend.drawable;

import backend.interfaces.Drawable;
import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;
import backend.Format;

public interface DrawableFigureOval extends Drawable {

    default void drawShade(ShadowType shadow, GraphicsContext gc, Color firstFillColor, Point centerPoint, double sMayorAxis, double sMinorAxis){
        if(!shadow.equals(ShadowType.NOSHADOW)){
            gc.setFill(shadow.getShadeColor(firstFillColor));
            gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + shadow.getOffsetX(), centerPoint.getY() - (sMinorAxis / 2) + shadow.getOffsetY(), sMayorAxis, sMinorAxis);
        }
    }

    default void drawGradient(boolean isGradient, GraphicsContext gc, Color firstFillColor, Color secondFillColor){
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, firstFillColor),
                    new Stop(1, secondFillColor));
            gc.setFill(radialGradient);
    }

    default void drawBevel(boolean isBevel, GraphicsContext gc, double arcX, double arcY, double sMayorAxis, double sMinorAxis, double offset){
        if(isBevel){
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 225, 180, ArcType.OPEN);
            gc.setLineWidth(1);
        }
    }

    default void drawOval(Format format, GraphicsContext gc, boolean isSelected, Point centerPoint, Double sMayorAxis, Double sMinorAxis, double offset){
        drawShade(format.getShadow(), gc, format.getColor1(),centerPoint, sMayorAxis, sMinorAxis);
        double arcX = centerPoint.getX() - sMayorAxis / 2;
        double arcY = centerPoint.getY() - sMinorAxis / 2;
        drawBevel(format.getBevel(), gc, arcX, arcY, sMayorAxis, sMinorAxis, offset);
        gc.setStroke(isSelected ? Color.RED : Color.BLACK);
        gc.setFill(format.getColor1());
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        drawGradient(format.getGradient(), gc, format.getColor1(), format.getColor2());
    }
}
