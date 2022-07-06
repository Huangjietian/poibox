package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Kern
 * @date 2019/10/30 17:51
 */
public final class MergedRange {

    private Sheet sheet;

    private CellRangeAddress range;

    public Sheet getSheet() {
        return sheet;
    }

    public CellRangeAddress getRange() {
        return range;
    }

    public MergedRange(Sheet sheet, CellRangeAddress range) {
        this.sheet = sheet;
        this.range = range;
    }

    public MergedRange setMergeRangeContent(double d) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(d);
        return this;
    }

    public MergedRange setMergeRangeContent(String s) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(s);
        return this;
    }

    public MergedRange setMergeRangeContent(boolean b) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(b);
        return this;
    }

    public MergedRange setMergeRangeContent(Date date) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(date);
        return this;
    }

    public MergedRange setMergeRangeContent(Calendar calendar) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(calendar);
        return this;
    }

    public MergedRange setMergeRangeContent(RichTextString richTextString) {
        BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(richTextString);
        return this;
    }

    public MergedRange setMergeRangeContent(Object value) {
        CellValueUtil.setCellObjectValue(BoxGadget.getCellForce(BoxGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()), value);
        return this;
    }

    public MergedRange setMergeRangeStyle(CellStyle style) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.cloneStyleFrom(style);
        for (int rowIdx = range.getFirstRow() ; rowIdx <= range.getLastRow() ; rowIdx++ ) {
            Row row = BoxGadget.getRowForce(sheet, rowIdx);
            for (int columnIdx = range.getFirstColumn() ; columnIdx <= range.getLastColumn() ; columnIdx++ ) {
                Cell cell = BoxGadget.getCellForce(row, columnIdx);
                cell.setCellStyle(cellStyle);
            }
        }
        return this;
    }

    public MergedRange setRowHeight(final Float rowHeight) {
        if (rowHeight != null && rowHeight > 0.0f) {
            for (int index = range.getFirstRow() ; index <= range.getLastRow(); index++) {
                Row row = BoxGadget.getRowForce(sheet, index);
                row.setHeightInPoints(rowHeight);
            }
        }
        return this;
    }


}
