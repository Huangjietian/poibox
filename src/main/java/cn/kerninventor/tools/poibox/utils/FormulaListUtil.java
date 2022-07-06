package cn.kerninventor.tools.poibox.utils;

import cn.kerninventor.tools.poibox.BoxGadget;
import org.apache.poi.ss.usermodel.*;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Kern
 * @version 1.0
 */
public class FormulaListUtil {

    public final static String HIDDEN_SHEET_NAME = "Pandora";

    public static String addFormulaList(Sheet sheet, String nameName, Collection<String> datas) {
        if (datas == null || datas.isEmpty()) {
            return nameName;
        }
        Workbook workbook = sheet.getWorkbook();
        Sheet hiddenSheet = BoxGadget.getSheetForce(workbook, HIDDEN_SHEET_NAME);
        if (sheet.equals(hiddenSheet)) {
            throw new IllegalArgumentException("Sheet name is cannot be " + HIDDEN_SHEET_NAME);
        }
        int dataRowIndex = hiddenSheet.getLastRowNum() + 1;
        Row dataRow = hiddenSheet.createRow(dataRowIndex);

        //赋值到隐藏的字典sheet页
        int i = 0 ;
        for (Iterator<String> iterator = datas.iterator(); iterator.hasNext() ; i ++) {
            Cell cell = dataRow.createCell(i);
            String value = iterator.next();
            cell.setCellValue(value);
        }

        //创建名称管理器
        Name nameManager = workbook.createName();
        nameManager.setNameName(nameName);

        //创建引用
        int dataRowSerial = dataRowIndex + 1;
        String endCellEnIndex = BoxGadget.transferExcelColumnIndex(dataRow.getLastCellNum());
        StringBuilder formulaExBulder = new StringBuilder();
        formulaExBulder
                .append(HIDDEN_SHEET_NAME)
                .append("!$A$")
                .append(dataRowSerial)
                .append(":$")
                .append(endCellEnIndex)
                .append("$")
                .append(dataRowSerial);
        nameManager.setRefersToFormula(formulaExBulder.toString());

        if (!workbook.isSheetHidden(workbook.getSheetIndex(hiddenSheet))) {
            workbook.setSheetHidden(workbook.getSheetIndex(hiddenSheet), true);
        }
        return nameName;
    }


}
