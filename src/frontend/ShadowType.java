package frontend;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public enum ShadowType {
    SIMPLE(10.0, 10.0){
        @Override
        public Color getShadeColor(Color base) {
            return Color.GRAY;
        }
    }, COLOUR(10.0, 10.0){
        @Override
        public Color getShadeColor(Color base) {
            return base.darker();
        }
    }, INVERSE(-10.0, -10.0){
        @Override
        public Color getShadeColor(Color base) {
            return ;
        }
    }, COLOUR_INVERSE(-10.0, -10.0){

    };

    ShadowType(double offsetX, double offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
//        this.colour = colour;
    }


    private final double offsetX, offsetY;
//    private final boolean colour;

    public abstract Color getShadeColor(Color base);

    public double getOffsetX() {
         return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }


//    public void drawShadow(boolean isShade, GraphicsContext gc, Color shadeColour, double offsetX, double offsetY, Point centerPoint, double sMayorAxis, double sMinorAxis, double diameter){
//        if(isShade){
//            gc.setFill(shadeColour);
//            gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + offsetX, centerPoint.getY() - (sMinorAxis / 2) + offsetY, diameter, diameter);
//        }
//    }
}
