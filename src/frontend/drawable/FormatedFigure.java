package frontend.drawable;

import backend.model.Figure;
import frontend.ShadowType;
import javafx.scene.paint.Color;

abstract public class FormatedFigure implements DrawableFigure{

    private Format format;

    public void setShadow(ShadowType shadow){
        format.setShadow(shadow);
    }

    public void setBevel(boolean bevel){
        format.setBevel(bevel);
    }

    public void setColor1(Color color){
        format.setColor1(color);
    }

    public void setColor2(Color color){
        format.setColor2(color);
    }

    public void draw(boolean isSelected){}

}
