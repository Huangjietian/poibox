package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import cn.kerninventor.tools.poibox.style.Fonter;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     横幅配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelBanner {

    /**
     * 横幅内容
     * @return
     */
    String value();

    /**
     * 风格配置
     * @see Style
     * @return
     */
    Style style() default @Style(
            index = -1,
            borders = @Border(borderStyle = BorderStyle.THIN),
            font = @Font(fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_HEADLINE
            ),
            wrapText = true
    );

    /**
     * 行高
     * @return
     */
    float rowHeight() default 15.0f;

    /**
     * 区间，当Range的四个属性取默认值时，将自动匹配到{首行，0-最后一列}的区间。 <br/>
     * {@code CellRangeAddress range = new CellRangeAddress(0,0,0,lastCell)}
     * @see Range
     * @return
     */
    Range range() default @Range;

}
