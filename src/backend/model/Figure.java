package backend.model;

import backend.interfaces.Choosable;
import backend.interfaces.Movable;


public interface Figure extends Movable, Choosable {

    public abstract void rotate();

    public abstract void flipH();

    public abstract void flipV();

    public abstract Figure duplicate();

    public abstract Pair<Figure> divide();
}
