package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     表格配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    int MaximumColumnWidthAllowed = 255;

    /**
     * 定义横幅
     * @return
     */
    ExcelBanner[] banners() default {};

    /**
     * 定义文本框
     * @return
     */
    Textbox[] textboxes() default {};

    /**
     * 定义表头风格
     * @return
     */
    Style[] theadStyles() default {@Style(index = 0)};

    /**
     * 定义表体风格
     * @return
     */
    Style[] tbodyStyles() default {@Style(index = 0)};

    /**
     * 定义表头行行高
     * @return
     */
    float theadRowHeight() default 15.0f;

    /**
     * 定义表体行行高
     * @return
     */
    float tbodyRowHeight() default 15.0f;

    /**
     * 定义起始行
     * @return
     */
    int startRowIndex() default 0;

    /**
     * 定义表体配置有效行数
     * @return
     */
    int effectiveRows() default 20;

    /**
     * 定义最小列宽
     * @return
     */
    int minimumColumnsWidth() default 0;

    /**
     * 定义最大列宽
     * @return
     */
    int maximumColumnsWidth() default MaximumColumnWidthAllowed;


}
