package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     边框配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Border {

    /**
     * 边框风格
     * @return
     */
    BorderStyle borderStyle() default BorderStyle.NONE;

    /**
     * 边框方向
     * @return
     */
    BorderDirection direction() default BorderDirection.SURROUND;

    /**
     * 边框颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined color() default HSSFColor.HSSFColorPredefined.BLACK;

}
