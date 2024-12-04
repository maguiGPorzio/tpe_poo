package frontend;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Esto esta bastante feo porque el codigo de simple y el de colour es lo mismo y desp. lo mismo con lo otro

public enum ShadowType {
    NOSHADOW(0.0, 0.0) {
      @Override
      public Color getShadeColor(Color base) {return base;}
    }, SIMPLE(10.0, 10.0){
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
            return Color.GRAY;
        }
    }, COLOUR_INVERSE(-10.0, -10.0){
        @Override
        public Color getShadeColor(Color base) {
            return base.darker();
        }
    };

    ShadowType(double offsetX, double offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    private final double offsetX, offsetY;

    public abstract Color getShadeColor(Color base);

    public double getOffsetX() {
         return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

}
