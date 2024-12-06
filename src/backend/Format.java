package backend;

import frontend.ShadowType;

public class Format<Color> {

    private boolean bevel;
    private ShadowType shadow;
    private Color color1, color2;

    public Format(boolean bevel, ShadowType shadow, Color color1, Color color2){
        setProperties(shadow, bevel, color1, color2);
    }

    public void setProperties(ShadowType shadow,  boolean bevel, Color color1, Color color2){
        this.shadow = shadow;
        this.bevel = bevel;
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public ShadowType getShadow() {
        return shadow;
    }

    public boolean getBevel(){
        return bevel;
    }

    public void setBevel(boolean bevel) {
        this.bevel = bevel;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setShadow(ShadowType shadow) {
        this.shadow = shadow;
    }
}
