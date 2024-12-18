package io.github.huangjietian.data.tabulation.validation.array;

import io.github.huangjietian.data.tabulation.validation.DataValid;
import io.github.huangjietian.data.tabulation.annotations.ExcelColumn;
import io.github.huangjietian.data.tabulation.annotations.ExcelTabulation;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link ExcelColumn} <br/>
 * </p>
 * <p>
 *     显示列表数据有效性(下拉框)
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = ExplicitListDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplicitListDataValid {

    int ALLOWED_MAX_LIST_BYTES_LENGTH = 255;

    /**
     * 提供一个字符数组，将在对应的字段列生成一个下拉列表
     * @return
     */
    String[] list();

    /**
     * 点击时消息
     * @return
     */
    String promptMessage() default "";

    /**
     * 输入错误时消息
     * @return
     */
    String errorMessage() default "";

}
