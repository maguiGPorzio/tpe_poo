package frontend;

public class Shadow {
    private final boolean hasShade;
    private final ShadowType type;

    public Shadow(boolean hasShade, ShadowType type) {
        this.hasShade = hasShade;
        this.type = type;
    }

    public ShadowType getType() {
        return this.type;
    }

    public boolean hasShade() {
        return this.hasShade;
    }
}
