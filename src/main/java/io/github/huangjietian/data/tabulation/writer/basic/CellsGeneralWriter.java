package io.github.huangjietian.data.tabulation.writer.basic;

import io.github.huangjietian.data.tabulation.enums.SupportedDataType;
import io.github.huangjietian.exception.UnSupportedDataTypeException;
import io.github.huangjietian.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;

/**
 * @author Kern
 * @version 1.0
 */
public class CellsGeneralWriter implements CellsWriter {

    @Override
    public void supportedDataType(Field field) {
        boolean isSupportedType = SupportedDataType.isSupportedType(field);
        if (!isSupportedType) {
            throw new UnSupportedDataTypeException("" +
                    "The Field data type is not supported when using the GeneralColWriter!" +
                    System.lineSeparator() +
                    "Please check the enumeration values in SupportedDataType class! Field name: " + field.getName());
        }
    }

    @Override
    public void setCellValue(Cell cell, Object value) {
        CellValueUtil.setCellObjectValue(cell, value);
    }

}
