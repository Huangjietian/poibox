package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Kern
 * @version 1.0
 */
public class DefaultBeanValidator<T> implements BeanValidator<T, DefaultBeanValidateResult>{

    private Validator validator;

    private DefaultBeanValidateResult defaultBeanValidateResult;

    public DefaultBeanValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        defaultBeanValidateResult = new DefaultBeanValidateResult(constraintViolations);
    }

    @Override
    public DefaultBeanValidateResult getResult() {
        return defaultBeanValidateResult;
    }
}
