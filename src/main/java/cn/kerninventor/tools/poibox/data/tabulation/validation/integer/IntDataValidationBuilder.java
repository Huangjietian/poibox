package cn.kerninventor.tools.poibox.data.tabulation.validation.integer;

import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @version 1.0
 */
public class IntDataValidationBuilder extends AbstractDvBuilder<IntDataValid> {

    public IntDataValidationBuilder(IntDataValid intDataValid) {
        super(intDataValid);
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
        IntDataValid intDataValid = getAnnotation();
        String var1 = intDataValid.value() + "";
        String var2 = intDataValid.optionalVal() == -1 ? null : intDataValid.optionalVal() + "";
        DataValidationConstraint dvConstraint = dvHelper.createIntegerConstraint(
                intDataValid.compareType().getCode(),
                var1,
                var2
        );
        return dvConstraint;
    }

    @Override
    public void setDataValidation(TabulationDefinition tableContext, ColumnDefinition columnDefinition, Sheet sheet) {
        annotationValid(columnDefinition, getAnnotation());
        super.setDataValidation(tableContext, columnDefinition, sheet);
    }

    private void annotationValid(ColumnDefinition columnDefinition, IntDataValid intDataValid) {
        if (intDataValid.compareType().isOptionalValueValidity()){
            if (intDataValid.value() > intDataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnDefinition.getFieldName());
            }
        }
    }




}
