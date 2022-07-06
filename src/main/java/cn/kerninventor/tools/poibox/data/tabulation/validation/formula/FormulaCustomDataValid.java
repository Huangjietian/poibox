package cn.kerninventor.tools.poibox.data.tabulation.validation.formula;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn} <br/>
 * </p>
 * <p>
 *     自定义函数数据有效性
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = FormulaCustomDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaCustomDataValid {

    /**
     * 函数表达式，详情查阅Excel单元格函数表达式
     * @return
     */
    String formula();

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
