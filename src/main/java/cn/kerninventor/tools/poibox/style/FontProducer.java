package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.FontCharset;
import cn.kerninventor.tools.poibox.style.enums.FontUnderline;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * <h1>中文注释</h1>
 * <p>
 *     字体生成器
 * </p>
 * @author Kern
 * @version 1.0
 */
public final class FontProducer {

    private Font font;

    FontProducer(Font font) {
        this.font = font;
    }

    /**
     * 设置字体名称
     * @param fontName
     * @return
     */
    public FontProducer setFontName(String fontName) {
        font.setFontName(fontName);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize
     * @return
     */
    public FontProducer setFontSize(int fontSize) {
        font.setFontHeightInPoints((short) fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor
     * @return
     */
    public FontProducer setFontColor(HSSFColor.HSSFColorPredefined fontColor) {
        font.setColor(fontColor.getIndex());
        return this;
    }

    /**
     * 设置字体倾斜
     * @param italic
     * @return
     */
    public FontProducer setItalic(boolean italic) {
        font.setItalic(italic);
        return this;
    }

    /**
     * 设置删除线
     * @param strikeout
     * @return
     */
    public FontProducer setStrikeout(boolean strikeout) {
        font.setStrikeout(strikeout);
        return this;
    }

    /**
     * 设置加粗
     * @param bold
     * @return
     */
    public FontProducer setBold(boolean bold) {
        font.setBold(bold);
        return this;
    }

    /**
     * 设置下划线
     * @param fontUnderline
     * @return
     */
    public FontProducer setUnderline(FontUnderline fontUnderline) {
        font.setUnderline(fontUnderline.getIndex());
        return this;
    }

    /**
     * 设置编码格式
     * @param fontCharset
     * @return
     */
    public FontProducer setCharSet(FontCharset fontCharset) {
        font.setCharSet(fontCharset.getIndex());
        return this;
    }

    /**
     * 获取字体
     * @return
     */
    public Font get(){
        return font;
    }
}
