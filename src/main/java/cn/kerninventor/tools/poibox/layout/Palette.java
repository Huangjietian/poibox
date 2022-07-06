package cn.kerninventor.tools.poibox.layout;

/**
 * <h1>中文注释</h1>
 * <p>
 *     调色盘枚举，提供几种常用颜色的枚举
 * </p>
 * @author Kern
 */
public enum Palette {

    BLACK(0,0,0),
    WHITE(255,255,255),
    RED(255,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255),
    YELLOW(255,255,0),
    PURPLE(255,0,255),
    CYAN(0,255,255),
    GRAY(127,127,127),
    ;

    Palette(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private int red;

    private int green;

    private int blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

}
