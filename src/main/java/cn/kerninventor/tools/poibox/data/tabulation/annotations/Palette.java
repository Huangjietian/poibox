package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     文本框调色板配置(三原色)
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Palette {

    int red();

    int green();

    int bule();
}
