package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.data.tabulation.enums.SupportedDataType;
import cn.kerninventor.tools.poibox.exception.UnSupportedDataTypeException;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;

/**
 * @author Kern
 * @version 1.0
 */
public class CellsMergeColumnsWriter implements CellsWriter {

    private Sheet sheet;
    private Integer columnIdx;
    private Integer firstRdx;
    private Integer lastRdx;
    private Object temporary;

    @Override
    public void supportedDataType(Field field) {
        boolean isSupportedType = SupportedDataType.isSupportedType(field);
        if (!isSupportedType) {
            throw new UnSupportedDataTypeException("" +
                    "The Field data type is not supported when using the MergeAbleCellsWriter!" +
                    System.lineSeparator() +
                    "Please check the enumeration values in SupportedDataType class! Field name: " + field.getName());
        }
    }

    @Override
    public void setCellValue(Cell cell, Object value) {
        if (sheet == null) {
            sheet = cell.getSheet();
        }
        int rowIndex = cell.getRow().getRowNum() - 1;
        if (columnIdx == null) {
            columnIdx = cell.getColumnIndex();
            firstRdx = rowIndex;
            lastRdx = rowIndex;
            temporary = value;
        } else {
            if (temporary != null && temporary.equals(value)) {
                lastRdx = rowIndex ;
            } else {
                if (firstRdx != lastRdx) {
                    flush();
                } else {
                    CellValueUtil.setCellObjectValue(sheet.getRow(lastRdx).getCell(columnIdx), temporary);
                }
                firstRdx = rowIndex;
                lastRdx = rowIndex;
                temporary = value;
            }
        }
    }

    @Override
    public void flush() {
        Layouter layouter = new LayoutHandler(null);
        CellRangeAddress range = new CellRangeAddress(firstRdx, lastRdx, columnIdx, columnIdx);
        layouter.mergedRegion(sheet, range).setMergeRangeContent(temporary);
    }
}
