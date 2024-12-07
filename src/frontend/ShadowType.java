package frontend;

import javafx.scene.paint.Color;

public enum ShadowType {
    NO_SHADOW(0.0, 0.0) {
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
