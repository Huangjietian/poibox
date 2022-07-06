package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     单元格风格配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Style {

    /**
     * 风格下标
     * @return
     */
    int index();

    /**
     * 风格边框
     * @see Border
     * @return
     */
    Border[] borders() default {@Border(borderStyle = BorderStyle.THIN)};

    /**
     * 风格字体
     * @see Font
     * @return
     */
    Font font() default @Font;

    /**
     * 填充样式
     * @return
     */
    FillPatternType fillPatternType() default FillPatternType.NO_FILL;

    /**
     * 前景颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined foregroudColor() default HSSFColor.HSSFColorPredefined.AUTOMATIC;

    /**
     * 背景颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined backgroudColor() default HSSFColor.HSSFColorPredefined.AUTOMATIC;

    /**
     * 垂直居中样式
     * @return
     */
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    /**
     * 水平居中样式
     * @return
     */
    HorizontalAlignment alignment() default HorizontalAlignment.CENTER;

    /**
     * 自动换行
     * @return
     */
    boolean wrapText() default false;

    /**
     * 风格锁
     * @return
     */
    boolean locked() default false;

    /**
     * 缩进
     * @return
     */
    int indention() default 0;

    /**
     * 隐藏
     * @return
     */
    boolean hidden() default false;


}
