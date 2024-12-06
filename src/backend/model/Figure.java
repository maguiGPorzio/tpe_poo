package backend.model;

import backend.Format;
import backend.interfaces.Choosable;
import backend.interfaces.Movable;


public abstract class Figure<Color> implements Movable, Choosable {

    protected Format<Color> format;

    public Figure(Format<Color> format){
        this.format = format;
    }

    protected final static double OFFSET = 10.0;

    public abstract void rotate();

    public abstract void flipH();

    public abstract void flipV();

    public Format<Color> getFormat(){
        return format;
    }

    public void setFormat(Format<Color> format){ this.format = format; }

    public abstract Figure<Color> duplicate();
    public abstract Pair<Figure<Color>> divide();
}
