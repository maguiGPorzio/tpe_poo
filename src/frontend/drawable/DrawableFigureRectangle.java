package frontend.drawable;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public interfaceDrawableFigureRectangle {
    default void drawShade(boolean isShade, GraphicsContext gc, Color shadeColour, double offsetX, double offsetY, Point centerPoint, double sMayorAxis, double sMinorAxis, double diameter){

    }

    default void drawGradient(boolean isGradient, GraphicsContext gc, BackColor fillColor){
        if (isGradient){
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, firstFillColor),
                    new Stop(1, secondFillColor));
            gc.setFill(linearGradient);
        }
    }

    default void drawRectangle(GraphicsContext gc, Color lineColor, Color fillColor, boolean isSelected, Point topLeft, Point bottomRight){
        gc.setStroke(isSelected ? Color.RED : lineColor);
        gc.setFill(fillColor);
        double width = Math.abs(topLeft.getX() - bottomRight.getX());
        double height = Math.abs(topLeft.getY() - bottomRight.getY());
        gc.fillRect(topLeft.getX(), topLeft.getY(), width, height);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), width, height);
    }

    default boolean belongsInRectangle(Point eventPoint, Point topLeft, Point bottomRight){
        return eventPoint.getX() > topLeft.getX() && eventPoint.getX() < bottomRight.getX() &&
                eventPoint.getY() > topLeft.getY() && eventPoint.getY() < bottomRight.getY();
    }
}
