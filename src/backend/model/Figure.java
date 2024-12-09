package backend.model;

import backend.interfaces.*;


public interface Figure extends Movable, Choosable, Duplicable<Figure>, Divisible<Figure>, Flippable, Rotable {

    void rotate();

    void flipH();

    void flipV();

    Figure duplicate();

    Pair<Figure> divide();

    boolean belongs(Point point);

    int getLayer();
}
