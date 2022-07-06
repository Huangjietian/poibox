package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import cn.kerninventor.tools.poibox.style.enums.FontUnderline;
import cn.kerninventor.tools.poibox.style.Fonter;
import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     字体配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Font {

    /**
     * 字体名称
     * @return
     */
    String fontName() default Fonter.DEF_NAME_TEXTPART;

    /**
     * 字体大小
     * @return
     */
    int fontSize() default Fonter.DEF_SIZE_TEXTPART;

    /**
     * 字体颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined color() default HSSFColor.HSSFColorPredefined.BLACK;

    /**
     * 字体加粗
     * @return
     */
    boolean bold() default false;

    /**
     * 字体倾斜
     * @return
     */
    boolean italic() default false;

    /**
     * 字体删除线
     * @return
     */
    boolean strikeout() default false;

    /**
     * 字体下划线
     * @return
     */
    FontUnderline underline() default FontUnderline.NONE;
}
