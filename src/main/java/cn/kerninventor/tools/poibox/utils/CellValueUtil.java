package cn.kerninventor.tools.poibox.utils;

import cn.kerninventor.tools.poibox.BoxGadget;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @uthor Kern
 * @version 1.0
 */
public final class CellValueUtil extends BoxGadget {

    public static <T extends Object> void setCellObjectValue(Cell cell, T value) {
        if (value == null) {
            return;
        }
        Class type = value.getClass();
        //Number
        if (DataTypeGroupUtil.isMemberOfIntType(type)){
            cell.setCellValue(((Integer)value));
        } else if (DataTypeGroupUtil.isMemberOfDecimal(type)){
            cell.setCellValue(((Number)value).doubleValue());
        }
        //Date
        else if (DataTypeGroupUtil.isMemberOfDateType(type)) {
            cell.setCellValue((Date)value);
        }
        //LocalDate
        else if (value instanceof LocalDate) {
            cell.setCellValue((Date.from(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        //LocalDateTime
        else if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime)value);
        }
        //Boolean
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean)value);
        }
        //Other cases
        else {
            cell.setCellValue(value.toString());
        }
    }

    public static Object getCellValueBySpecifiedType(Cell cell, Class targetClass) {
        Object ret = null;
        //String
        if (cell.getCellType() == CellType.STRING && String.class == targetClass) {
            String str = cell.getStringCellValue().trim();
            ret = "".equals(str) ? null : str;
        }
        //Date
        else if (isCellDateFormat(cell)) {
            if (LocalDate.class == targetClass){
                ret = cell.getLocalDateTimeCellValue().toLocalDate();
            } else if (LocalDateTime.class == targetClass) {
                ret = cell.getLocalDateTimeCellValue();
            } else if (Date.class.isAssignableFrom(targetClass)){
                ret = cell.getDateCellValue();
            }
        }
        //Double
        else if (cell.getCellType() == CellType.NUMERIC){
            Double doub = cell.getNumericCellValue();
            //decimal
            if (BigDecimal.class.isAssignableFrom(targetClass)){
                ret = BigDecimal.valueOf(doub);
            } else if (Float.class == targetClass) {
                ret = doub.floatValue();
            } else if (Double.class == targetClass) {
                ret = doub;
             //int
            } else if (Long.class == targetClass) {
                ret = doub.longValue();
            } else if (Integer.class == targetClass){
                //自动装拆包
                ret = doub.intValue();
            //String
            } else if (String.class == targetClass){
                ret = doub.intValue() + "";
            }
        }
        //Boolean
        else if (cell.getCellType() == CellType.BOOLEAN && Boolean.class == targetClass) {
            ret = cell.getBooleanCellValue();
        }
        //In other cases , ret == null
        return ret;

    }

    public static Object getCellValue(Cell cell) {
        //String
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }
        //Date
        else if (isCellDateFormat(cell)) {
            return cell.getDateCellValue();
        }
        //Double
        else if (cell.getCellType() == CellType.NUMERIC){
            return cell.getNumericCellValue();
        }
        //Boolean
        else if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        return "";
    }

    private static boolean isCellDateFormat(Cell cell) {
        if (cell == null) {
            return false;
        } else {
            boolean bDate = false;
            if (cell.getCellType() == CellType.NUMERIC) {
                double d = cell.getNumericCellValue();
                if (DateUtil.isValidExcelDate(d)) {
                    ExcelNumberFormat nf = ExcelNumberFormat.from(cell, null);
                    if (nf == null) {
                        return false;
                    }

                    bDate = DateUtil.isADateFormat(nf);
                }
            }
            return bDate;
        }
    }
}
