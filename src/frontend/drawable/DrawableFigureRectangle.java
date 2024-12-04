package frontend.drawable;

import backend.Format;
import backend.model.Point;
import frontend.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public interface DrawableFigureRectangle {


    default void drawShade(ShadowType shadow, GraphicsContext gc, Color firstFillColor, Point topLeft, Point bottomRight){
        // aca me queda la duda si no es mejor estilo directamente no hacer el if
        // y que dibuje arriba (?
        // no se como quedara
        // pero es pq NOSHADOW tiene implementado el getShadeColor tb

        if(!shadow.equals(ShadowType.NOSHADOW)) {
            gc.setFill(shadow.getShadeColor(firstFillColor));
            gc.fillRect(topLeft.getX() + shadow.getOffsetX(), topLeft.getY() + shadow.getOffsetY(), Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
        }
    }

    default void drawGradient(boolean isGradient, GraphicsContext gc, Color firstFillColor, Color secondFillColor){
        if (isGradient){
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, firstFillColor),
                    new Stop(1, secondFillColor));
            gc.setFill(linearGradient);
        }
    }

    default void drawBevel(boolean isBevel, GraphicsContext gc, Point topLeft, Point bottomRight){
        if(isBevel){
            double x = topLeft.getX();
            double y = topLeft.getY();
            double width = Math.abs(x - bottomRight.getX());
            double height = Math.abs(y - bottomRight.getY());
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeLine(x, y, x + width, y);
            gc.strokeLine(x, y, x, y + height);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x + width, y, x + width, y + height);
            gc.strokeLine(x, y + height, x + width, y + height);
        }
    }

    default void drawRectangle(Format format, GraphicsContext gc, boolean isSelected, Point topLeft, Point bottomRight){
        if(topLeft.getX() < bottomRight.getX() && topLeft.getY() > bottomRight.getY()){
            drawShade(format.getShadow(), gc, format.getColor1(), topLeft, bottomRight);
            drawBevel(format.getBevel(), gc, topLeft, bottomRight);
            gc.setStroke(isSelected ? Color.RED : Color.BLACK);
            gc.setFill(format.getColor1());
            double width = Math.abs(topLeft.getX() - bottomRight.getX());
            double height = Math.abs(topLeft.getY() - bottomRight.getY());
            gc.fillRect(topLeft.getX(), topLeft.getY(), width, height);
            gc.strokeRect(topLeft.getX(), topLeft.getY(), width, height);
            drawGradient(format.getGradient(), gc, format.getColor1(), format.getColor2());
        }
    }

}
