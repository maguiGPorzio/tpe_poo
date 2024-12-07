package backend.model;

import backend.interfaces.Choosable;
import backend.interfaces.Duplicable;
import backend.interfaces.Movable;


public interface Figure extends Movable, Choosable, Duplicable<Figure> {

    void rotate();

    void flipH();

    void flipV();

    Figure duplicate();

    Pair<Figure> divide();

    boolean belongs(Point point);

}
