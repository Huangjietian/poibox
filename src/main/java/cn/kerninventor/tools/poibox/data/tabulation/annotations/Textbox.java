package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     文本框配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Textbox {

    /**
     * 文本框的内容
     * @return
     */
    String value();

    /**
     * 文本框的锚坐标
     * @see Anchor
     * @return
     */
    Anchor anchorIndex();

    /**
     * 文本框垂直居中样式
     * @return
     */
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    /**
     * 文本框边缘留白
     * @see Margins
     * @return
     */
    @Deprecated
    Margins margins() default @Margins(left = 0, top = 0, right = 0, bottom = 0);

    /**
     * 文本框填充颜色
     * @return
     */
    Palette fillColor() default @Palette(red = 255, green = 255, bule = 255);

    /**
     * 文本框边框颜色
     * @return
     */
    Palette lineColor() default @Palette(red = 0, green = 0, bule = 0);
}
