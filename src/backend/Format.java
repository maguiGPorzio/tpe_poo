package backend;

import javafx.scene.paint.Color;
import frontend.ShadowType;

public class Format {

    private boolean gradient, bevel;
    private ShadowType shadow;
    private Color color1, color2;

    public Format(boolean gradient, boolean bevel, ShadowType shadow, Color color1, Color color2){
        setProperties(shadow, gradient, bevel, color1, color2);
    }

    public void setProperties(ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        this.shadow = shadow;
        this.gradient = gradient;
        this.bevel = bevel;
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public ShadowType getShadow() {
        return shadow;
    }

    public boolean getGradient(){
        return gradient;
    }

    public boolean getBevel(){
        return bevel;
    }
}
