package frontend.formatted;

import frontend.ShadowType;
import javafx.scene.paint.Color;

public interface FormattedFigure extends DrawableFigure {

    void setShadow(ShadowType shadow);
    void setBevel(boolean bevel);
    void setColor1(Color color);
    void setColor2(Color color);
    Format getFormat();
    default void setFormat(Format format){
        setShadow(format.getShadow());
        setBevel(format.getBevel());
        setColor1(format.getColor1());
        setColor2(format.getColor2());
    }
}
