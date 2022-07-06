package cn.kerninventor.tools.poibox.style.enums;

import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @version 1.0
 */
public enum FontUnderline {

    NONE(Font.U_NONE),
    SINGLE(Font.U_SINGLE),
    DOUBLE(Font.U_DOUBLE),
    SINGLE_ACCOUNTING(Font.U_SINGLE_ACCOUNTING),
    DOUBLE_ACCOUNTING(Font.U_DOUBLE_ACCOUNTING),
    ;

    private byte index;

    FontUnderline(int index) {
        this.index = (byte) index;
    }

    public byte getIndex() {
        return index;
    }

}
