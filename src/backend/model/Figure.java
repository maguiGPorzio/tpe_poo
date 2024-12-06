package backend.model;

import backend.Format;
import backend.interfaces.Choosable;
import backend.interfaces.Drawable;
import backend.interfaces.Movable;


public abstract class Figure implements Movable, Choosable, Drawable {

    protected Format format;

    public Figure(Format format){
        this.format = format;
    }

    protected final static double OFFSET = 10.0;
    protected final static double RECTANGLE_OFFSET = 0.0;
    protected final static double OVAL_OFFSET = 0.0;

    public abstract void rotate();

    public abstract void flipH();

    public abstract void flipV();

    public Format getFormat(){
        return format;
    }

    public void setFormat(Format format){ this.format = format; }

    public abstract Figure duplicate();
    public abstract Pair<Figure> divide();
}
