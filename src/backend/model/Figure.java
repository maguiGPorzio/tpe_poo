package backend.model;

import backend.Format;
import backend.interfaces.Choosable;
import backend.interfaces.Drawable;
import backend.interfaces.Movable;
import frontend.ShadowType;


public abstract class Figure implements Movable, Drawable, Choosable {

    protected Format format;

    public Figure(Format format){
        this.format = format;
    }

    protected final static double OFFSET = 0.5;

    public abstract void rotate();

    public abstract void flipH();

    public abstract void flipV();

    public ShadowType hasShade() {
        return format.getShadow();
    }
    public boolean hasGradient() {
        return format.getGradient();
    }
    public boolean hasBevel() {
        return format.getBevel();
    }

    public Format getFormat(){
        return format;
    }

    public abstract Figure duplicate();
    public abstract Pair<Figure> divide();
}
