package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     文本框边缘留白配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Margins {

    int left();

    int top();

    int right();

    int bottom();
}
