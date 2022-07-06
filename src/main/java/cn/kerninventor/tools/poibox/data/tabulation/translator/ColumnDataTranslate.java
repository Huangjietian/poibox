package cn.kerninventor.tools.poibox.data.tabulation.translator;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     字段列翻译器配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnDataTranslate {

    /**
     * @see ExcelColumn#dataTranslate()
     * @return
     */
    String translator();

    /**
     * 指定翻译器中特定的子标记，类似于 Key / subKey 的关系
     * @return
     */
    String tag() default "";

    /**
     * 当翻译器未匹配时的策略<br/>
     * @see TranslatorManager#unmatcHandle(int, String, Object, String, UnmatchStrategy, ColumnDataTranslator)
     * @return
     */
    UnmatchStrategy unmatchStrategy() default UnmatchStrategy.CONSOLE;

    /**
     * 启用标识， {@code open{} = true}时，将执行翻译，反之不执行。
     * @return
     */
    boolean open() default true;

}
