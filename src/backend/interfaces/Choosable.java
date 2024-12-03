package backend.interfaces;

import backend.model.Point;

public interface Choosable {
    boolean belongs(Point point);
}
