package backend.model;

import backend.interfaces.Choosable;
import backend.interfaces.Drawable;
import backend.interfaces.Movable;
import frontend.ShadowType;
import javafx.scene.paint.Color;

public abstract class Figure implements Movable, Drawable, Choosable {

    protected Color color1, color2;
    protected ShadowType shadow;
    protected boolean gradient;
    protected boolean bevel;

    public Figure(ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        setProperties(shadow, gradient, bevel, color1, color2);
    }

    public void setProperties(ShadowType shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        this.shadow = shadow;
        this.gradient = gradient;
        this.bevel = bevel;
        this.color1 = color1;
        this.color2 = color2;
    }

    protected final static double OFFSET = 0.5;

    public abstract void rotate();

    public abstract void flipH();

    public abstract void flipV();

    public ShadowType hasShade() {
        return shadow;
    }
    public boolean hasGradient() {
        return gradient;
    }
    public boolean hasBevel() {
        return bevel;
    }

    public abstract Figure duplicate();
    public abstract Pair<Figure> divide();
}
