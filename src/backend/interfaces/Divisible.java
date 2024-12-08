package backend.interfaces;

import backend.model.Pair;

@FunctionalInterface
public interface Divisible<E> {
    Pair<E> divide();
}
