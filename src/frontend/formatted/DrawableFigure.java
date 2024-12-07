package frontend.formatted;

import backend.model.Figure;


public interface DrawableFigure extends Figure{
    void draw(boolean isSelected);
}
