package io.github.huangjietian.data.tabulation.writer.basic;

import io.github.huangjietian.BoxGadget;
import io.github.huangjietian.data.tabulation.annotations.ExcelColumn;
import io.github.huangjietian.data.tabulation.definition.ColumnDefinition;
import io.github.huangjietian.data.tabulation.definition.TabulationDefinition;
import io.github.huangjietian.style.Styler;
import io.github.huangjietian.utils.BeanUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @author Kern
 * @version 1.0
 */
public final class DefaultTabulationWriter<T> {

    private final BodyWriter<T> bodyWriter;

    public DefaultTabulationWriter(BodyWriter<T> bodyWriter) {
        this.bodyWriter = bodyWriter;
    }

    public void doWrite(TabulationDefinition<T> tabualationDefinition, List<ColumnDefinition> columnDefinitions, Sheet sheet) {
        Row headRow = sheet.createRow(tabualationDefinition.getTheadRowIndex());
        headRow.setHeightInPoints(tabualationDefinition.getTheadRowHeight());
        Workbook workbook = tabualationDefinition.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tabualationDefinition.getParent().styler();
        for (ColumnDefinition columnDefinition : columnDefinitions) {
            Cell headRowCell = headRow.createCell(columnDefinition.getColumnIndex());
            headRowCell.setCellValue(columnDefinition.getTitleName());
            headRowCell.setCellStyle(columnDefinition.getTheadStyle());
            short theadFontHeightInPoints = BoxGadget.getFontFrom(columnDefinition.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(tabualationDefinition, columnDefinition, sheet, theadFontHeightInPoints);
            CellStyle tbodyStyle = columnDefinition.getTbodyStyle();
            if (BeanUtil.isNotEmpty(columnDefinition.getDataFormatEx())) {
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(columnDefinition.getDataFormatEx()));
                columnDefinition.setTbodyStyle(tbodyStyle);
            }
            this.bodyWriter.doWirte(tabualationDefinition, columnDefinition, sheet);
        }
    }

    private void setColumnWidth(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet, int theadFontHeightInPoints) {
        int width;
        if (columnDefinition.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(columnDefinition.getTitleName(), theadFontHeightInPoints);
            width = Math.max(width, tabulationDefinition.getMinimumColumnsWidth());
            width = Math.min(width, tabulationDefinition.getMaximumColumnsWidth());
        } else {
            width = BoxGadget.adjustCellWidth(columnDefinition.getColumnWidth());
        }
        sheet.setColumnWidth(columnDefinition.getColumnIndex(), width);
    }

}
