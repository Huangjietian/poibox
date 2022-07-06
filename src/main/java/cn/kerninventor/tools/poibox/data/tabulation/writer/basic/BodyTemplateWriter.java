package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @version 1.0
 */
public final class BodyTemplateWriter<T> implements BodyWriter<T> {

    @Override
    public void doWirte(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet) {
        for (int rowIndex = tabulationDefinition.getTbodyFirstRowIndex(); rowIndex < tabulationDefinition.getEffectiveRows() + tabulationDefinition.getTbodyFirstRowIndex(); rowIndex ++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(tabulationDefinition.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            if (BeanUtil.isNotEmpty(columnDefinition.getFormula())) {
                bodyCell.setCellFormula(columnDefinition.getFormula());
            }
        }
        if (columnDefinition.getDataValidationBuilder() != null) {
            columnDefinition.getDataValidationBuilder().setDataValidation(tabulationDefinition, columnDefinition, sheet);
        }
    }
}
