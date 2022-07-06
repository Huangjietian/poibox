package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     锚坐标配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anchor {

    int left() default 0;

    int top() default 0;

    int right() default 0;

    int bottom() default 0;

    /**
     * 起始列 left
     * @return
     */
    int col1();

    /**
     * 起始行 top
     * @return
     */
    int row1();

    /**
     * 结束列 right
     * @return
     */
    int col2();

    /**
     * 结束行 bottom
     * @return
     */
    int row2();
}
