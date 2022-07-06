package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import cn.kerninventor.tools.poibox.exception.IllegalColumnFieldException;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

import java.util.Arrays;

/**
 * @author Kern
 * @version 1.0
 */
public class EnumExplicitListDataValidationBuilder extends AbstractDvBuilder<EnumExplicitListDataValid> {

    public EnumExplicitListDataValidationBuilder(EnumExplicitListDataValid enumExplicitListDataValid) {
        super(enumExplicitListDataValid);
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
        EnumExplicitListDataValid enumExplicitListDataValid = getAnnotation();
        Class enumClazz = enumExplicitListDataValid.enumClass();
        if (!enumClazz.isEnum()) {
            throw new IllegalColumnFieldException("EnumExplicitListDataValid enumClass() must specify an enumeration class!");
        }
        EnumExplicitList[] explicitLists = (EnumExplicitList[]) enumClazz.getEnumConstants();
        String[] list = Arrays.stream(explicitLists).map(EnumExplicitList::explicitList).toArray(String[]::new);
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(list);
        return dvConstraint;
    }

}
