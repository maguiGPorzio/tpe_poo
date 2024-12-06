package frontend.drawable;

import backend.model.Figure;

@FunctionalInterface
public interface Drawable {
    void draw(boolean isSelected);
}
