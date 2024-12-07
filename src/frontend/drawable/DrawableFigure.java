package frontend.drawable;

import backend.model.Figure;


public interface DrawableFigure extends Figure{
    void draw(boolean isSelected);
}
