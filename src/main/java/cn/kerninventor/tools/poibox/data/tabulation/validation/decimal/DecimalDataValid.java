package cn.kerninventor.tools.poibox.data.tabulation.validation.decimal;

import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn} <br/>
 * </p>
 * <p>
 *     小数区间比较数据有效性
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = DecimalDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalDataValid {

    /**
     * 双精度浮点值
     * @return
     */
    double value() default 0.00;

    /**
     * 可选值，个别比较类型需要两个值进行比较，否则将导致异常。可以参考 {@link CompareType#isOptionalValueValidity()}
     * @return
     */
    double optionalVal() default -1.00;

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
