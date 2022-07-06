package cn.kerninventor.tools.poibox.style.enums;

import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @version 1.0
 */
public enum FontCharset {

    ANSI_CHARSET(Font.ANSI_CHARSET,"ansi"),
    DEFAULT_CHARSET(Font.DEFAULT_CHARSET,"default"),
    SYMBOL_CHARSET(Font.SYMBOL_CHARSET,"symbol"),
    ;

    private byte index;

    private String description;

    FontCharset(int index, String description) {
        this.index = (byte) index;
        this.description = description;
    }

    public byte getIndex() {
        return index;
    }
    public String getDescription() {
        return description;
    }
}
