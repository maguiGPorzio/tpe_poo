package backend.interfaces;

import backend.model.Point;

@FunctionalInterface
public interface Choosable {
    boolean belongs(Point point);
}
