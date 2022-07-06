package cn.kerninventor.tools.poibox.data.tabulation.validation.decimal;

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
public class DecimalDataValidationBuilder extends AbstractDvBuilder<DecimalDataValid> {

    public DecimalDataValidationBuilder(DecimalDataValid decimalDataValid) {
        super(decimalDataValid);
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
        DecimalDataValid decimalDataValid = getAnnotation();
        String var1 = decimalDataValid.value() + "";
        String var2 = decimalDataValid.optionalVal() == -1.00 ? null : decimalDataValid.optionalVal() + "";
        DataValidationConstraint dvConstraint = dvHelper.createDecimalConstraint(
                decimalDataValid.compareType().getCode(),
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

    private void annotationValid(ColumnDefinition columnDefinition, DecimalDataValid decimalDataValid) {
        if (decimalDataValid.compareType().isOptionalValueValidity()){
            if (decimalDataValid.value() > decimalDataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnDefinition.getFieldName());
            }
        }
    }

}
