package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.translator.TranslatorManager;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @version 1.0
 */
public final class BodyDataWriter<T> implements BodyWriter<T> {

    private final List<T> datas;
    private final TranslatorManager translatorManager;

    public BodyDataWriter(List<T> datas, TranslatorManager TranslatorManager) {
        this.datas = datas;
        this.translatorManager = TranslatorManager;
    }

    @Override
    public void doWirte(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet) {
        CellsWriter cellsWriter = columnDefinition.getCellsWriter();
        cellsWriter.pre();
        for (int datasIndex = 0, rowIndex = tabulationDefinition.getTbodyFirstRowIndex(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(tabulationDefinition.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(columnDefinition.getField(), datas.get(datasIndex));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Failed to get field value. Field name: " + columnDefinition.getFieldName());
            }
            value = this.translatorManager.translate(columnDefinition.getColumnDataTranslate(), value);
            cellsWriter.setCellValue(bodyCell, value);

        }
        cellsWriter.flush();
    }

}
