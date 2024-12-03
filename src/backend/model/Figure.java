package backend.model;

import backend.interfaces.Choosable;
import backend.interfaces.Drawable;
import backend.interfaces.Movable;
import javafx.scene.paint.Color;

public abstract class Figure implements Movable, Drawable, Choosable {

    protected Color color1, color2;
    protected boolean shadow;
    protected boolean gradient;
    protected boolean bevel;

    public Figure(boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
        setProperties(shadow, gradient, bevel, color1, color2);
    }

    public void setProperties(boolean shadow, boolean gradient, boolean bevel, Color color1, Color color2){
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

    public boolean isShade() {
        return shadow;
    }
    public boolean isGradient() {
        return gradient;
    }
    public boolean isBevel() {
        return bevel;
    }

    public abstract Figure duplicate();
}
