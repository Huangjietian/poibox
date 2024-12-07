package io.github.huangjietian.data.tabulation.validation.array;

import io.github.huangjietian.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

/**
 * @author Kern
 * @version 1.0
 */
public class ExplicitListDataValidationBuilder extends AbstractDvBuilder<ExplicitListDataValid> {

    public ExplicitListDataValidationBuilder(ExplicitListDataValid explicitListDataValid) {
        super(explicitListDataValid);
    }

    @Override
    protected String getPromptBoxMessage() {
        return getAnnotation().promptMessage();
    }

    @Override
    protected String getErrorBoxMessage() {
        return getAnnotation().errorMessage();
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        ExplicitListDataValid explicitListDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(explicitListDataValid.list());
        return dvConstraint;
    }

}
