package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn} <br/>
 * </p>
 * <p>
 *     函数列表数据有效性(下拉框)
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = FormulaListDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaListDataValid {

    String NAME_PREFIX = "NAME_";

    String CASCADE_TAG = "cascade:";

    /**
     * 指定一个函数列表的名称，该函数列表将在运行时由 {@link cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter#withFormulaList(String, Set)}方法输入，<br/>
     * 将查找不到对应的函数列表时，将导致异常。 如果一切正常，最终将为该字段列生成一个下拉集合数据有效性校验。<br/>
     * 关于级联的使用，如果你需要对两个函数列表进行级联。你需要进行如下配置： <br/>
     * {@code @FoumulaListDataValid(value="casecade:upperFieldName")} 指定你的上流字段名 {@link Field#getName()}<br/>
     * 之后在写入时，你需要把formulaList的name指定为你的上流的具体的下拉值。
     * @return
     */
    String value();

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
