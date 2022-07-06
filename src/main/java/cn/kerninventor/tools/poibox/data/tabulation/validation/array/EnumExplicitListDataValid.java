package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据有效性配置注解，使用该注解标注在标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation}的类字段上，将生成对应的数据有效性校验<br/>
 *     注意：标注的字段必须标注了{@link cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn} <br/>
 * </p>
 * <p>
 *     枚举类列表数据有效性(下拉框)
 * </p>
 * @author Kern
 * @version 1.0
 */
@DataValid(dvBuilder = EnumExplicitListDataValidationBuilder.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumExplicitListDataValid {

    /**
     * 指定实现了 {@link EnumExplicitList}的枚举类(enum),将会为该字段列生成一个下拉列表的数据有效性验证。
     * @return
     */
    Class<? extends EnumExplicitList> enumClass();

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
