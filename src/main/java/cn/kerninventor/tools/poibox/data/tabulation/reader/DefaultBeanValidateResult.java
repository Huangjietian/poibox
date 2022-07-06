package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @version 1.0
 */
public class DefaultBeanValidateResult<T> {

    private Set<ConstraintViolation<T>> constraintViolations;

    public DefaultBeanValidateResult(Set<ConstraintViolation<T>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public void throwing(String separator) throws Throwable {
        throwing(separator, new IllegalArgumentException());
    }

    public void throwing(String separator, Throwable throwable) throws Throwable {
        separator = separator == null ? "" : separator;
        StringBuilder builder = new StringBuilder();
        String finalSeparator = separator;
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(finalSeparator);
        });
        throwable.addSuppressed(new IllegalArgumentException(builder.toString()));
        throw throwable;
    }

    public void throwingLine() throws Throwable {
        throwing(System.lineSeparator());
    }

    public Set<ConstraintViolation<T>> getConstraintViolations() {
        return constraintViolations;
    }
}
