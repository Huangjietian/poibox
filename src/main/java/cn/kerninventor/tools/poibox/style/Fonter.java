package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.FontColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * <h1>中文注释</h1>
 * <p>
 *     字体处理器
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface Fonter {
    /**
     * 默认的表头字体，对应中文名黑体
     */
    String DEF_NAME_HEADER = "Arial Black";
    /**
     * 默认的表体字体，对应中文名宋体
     */
    String DEF_NAME_TEXTPART = "SimSun";
    /**
     * 默认的标题字体大小
     */
    int DEF_SIZE_HEADLINE = 14;
    /**
     * 默认的表头字体大小
     */
    int DEF_SIZE_TABLEHEADER = 12;
    /**
     * 默认的正文字体大小
     */
    int DEF_SIZE_TEXTPART = 10;
    /**
     * 默认的字体调整基准
     */
    int DEFAULT_FONT_HEIGHT_IN_POINTS = 12;

    /**
     * 字体生成器，基于{@link Font}的接口封装，提供了builder模式的Font对象构造器。
     * @return
     */
    FontProducer producer();

    /**
     * 简单的{@link Font}对象生成
     * @param fontName 字体名称
     * @param fontSize 字体大小
     * @return
     */
    Font simpleFont(String fontName, int fontSize);

    /**
     * 简单的{@link Font}对象生成
     * @param fontName 字体名称
     * @param fontSize 字体大小
     * @param fontColor 字体颜色
     * @return
     */
    Font simpleFont(String fontName, int fontSize, FontColor fontColor);

    /**
     * 简单的{@link Font}对象生成
     * @param fontName 字体名称
     * @param fontSize 字体大小
     * @param bold 是否加粗
     * @return
     */
    Font simpleFont(String fontName, int fontSize, boolean bold);

    /**
     * 根据注解生成{@link Font}对象
     * @param font {@link cn.kerninventor.tools.poibox.data.tabulation.annotations.Font}
     * @return {@link org.apache.poi.ss.usermodel.Font}
     */
    Font generate(cn.kerninventor.tools.poibox.data.tabulation.annotations.Font font);

}
