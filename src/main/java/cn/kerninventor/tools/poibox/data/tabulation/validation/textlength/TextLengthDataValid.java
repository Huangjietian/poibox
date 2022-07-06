package cn.kerninventor.tools.poibox.data.tabulation.validation.textlength;

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
 *     文本长度区间比较数据有效性
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = TextLengthDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TextLengthDataValid {

    /**
     * 文本长度整数值
     * @return
     */
    int value();

    /**
     * 可选值，个别比较类型需要两个值进行比较，否则将导致异常。可以参考 {@link CompareType#isOptionalValueValidity()}
     * @return
     */
    int optionalVal() default -1;

    /**
     * 比较类型
     * @return
     */
    CompareType compareType() default CompareType.LTE;

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
