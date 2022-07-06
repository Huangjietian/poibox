package cn.kerninventor.tools.poibox.data.tabulation.validation;

import java.lang.annotation.*;

/**
 * @author Kern
 * @version 1.0
 */
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataValid {

    /**
     * @see AbstractDvBuilder
     * @return
     */
    Class<? extends AbstractDvBuilder> dvBuilder();

}
