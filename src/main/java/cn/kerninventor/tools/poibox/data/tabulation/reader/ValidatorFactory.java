package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.Validation;

/**
 * @author Kern
 * @version 1.0
 */
public class ValidatorFactory {

    /**
     * 程序默认提供hibernate Bean 校验器。版本请参考本项目的pom文件
     * @return
     */
    public static BeanValidator hibernateValidator(){
        javax.validation.ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return new DefaultBeanValidator(validatorFactory.getValidator());
    }

}
