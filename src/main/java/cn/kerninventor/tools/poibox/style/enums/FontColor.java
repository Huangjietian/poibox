package cn.kerninventor.tools.poibox.style.enums;

import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @version 1.0
 */
public enum  FontColor {

    BLACK(Font.COLOR_NORMAL),
    RED(Font.COLOR_RED),
    ;
    private int index;

    FontColor(int index) {
        this.index = index;
    }

    public short getIndex() {
        return (short) index;
    }

}
