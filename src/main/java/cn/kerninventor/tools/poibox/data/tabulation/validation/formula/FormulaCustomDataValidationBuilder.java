package cn.kerninventor.tools.poibox.data.tabulation.validation.formula;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

/**
 * @author Kern
 * @version 1.0
 */
public class FormulaCustomDataValidationBuilder extends AbstractDvBuilder<FormulaCustomDataValid> {


    public FormulaCustomDataValidationBuilder(FormulaCustomDataValid formulaCustomDataValid) {
        super(formulaCustomDataValid);
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
        FormulaCustomDataValid formulaCustomDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createCustomConstraint(formulaCustomDataValid.formula());
        return dvConstraint;
    }

}
