package backend.model;

import backend.interfaces.Choosable;
import backend.interfaces.Movable;


public interface Figure extends Movable, Choosable {

    void rotate();

    void flipH();

    void flipV();

    Figure duplicate();

    Pair<Figure> divide();

}
