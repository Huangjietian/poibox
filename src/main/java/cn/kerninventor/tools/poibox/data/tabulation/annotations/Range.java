package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     单元格区域配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    int defaultVal = -1;

    int fistRow() default defaultVal;
    int firstCell() default defaultVal;
    int lastRow() default defaultVal;
    int lastCell() default defaultVal;

}
