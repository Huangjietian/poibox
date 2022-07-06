package cn.kerninventor.tools.poibox.data.tabulation.validation.textlength;

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
public class TextLengthDataValidationBuilder extends AbstractDvBuilder<TextLengthDataValid> {

    public TextLengthDataValidationBuilder(TextLengthDataValid textLengthDataValid) {
        super(textLengthDataValid);
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
        TextLengthDataValid textLengthDataValid = getAnnotation();
        String var1 = textLengthDataValid.value() + "";
        String var2 = textLengthDataValid.optionalVal() == -1 ? null : textLengthDataValid.optionalVal() + "";
        DataValidationConstraint dvConstraint = dvHelper.createTextLengthConstraint(
                textLengthDataValid.compareType().getCode(),
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

    private void annotationValid(ColumnDefinition columnDefinition, TextLengthDataValid textLengthDataValid) {
        if (textLengthDataValid.compareType().isOptionalValueValidity()){
            if (textLengthDataValid.value() > textLengthDataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnDefinition.getFieldName());
            }
        }
    }


}
