package cn.kerninventor.tools.poibox.data.tabulation.validation.date;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn} <br/>
 * </p>
 * <p>
 *     日期区间数据有效性
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = DateDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDataValid {

    /**
     * 在通用的日期比较中，常用到当前日期，由于注解的参数需要指定一个常量，因而我们无法将一个当前对象的表示作为参数传入。
     * 如遇到上述问题，你可以使用 ‘now()’ 来表示当前日期，程序将会把该字符解析为当前日期的正确表示。
     */
    String NOW = "now()";

    /**
     * 日期解析格式
     * @return
     */
    String parseFormat() default "yyyy-MM-dd";

    /**
     * 日期值
     * @return
     */
    String date() default "1900-01-01";

    /**
     * 可选值，个别比较类型需要两个值进行比较，否则将导致异常。可以参考 {@link CompareType#isOptionalValueValidity()}
     * @return
     */
    String optionalDate() default "";

    /**
     * 比较类型
     * @return
     */
    CompareType compareType() default CompareType.GT;

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
