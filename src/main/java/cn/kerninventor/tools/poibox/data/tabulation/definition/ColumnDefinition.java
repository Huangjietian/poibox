package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsWriter;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Field;

/**
 * <h1>中文注释</h1>
 * <p>
 *     列字段定义
 * </p>
 * @author Kern
 * @version 1.0
 */
public final class ColumnDefinition implements Comparable<ColumnDefinition> {

    private int columnIndex;
    private final Field field;
    private String titleName;
    private int columnSort;
    private int columnWidth;
    private String dataFormatEx;
    private String formula;
    private CellStyle theadStyle;
    private CellStyle tbodyStyle;
    private AbstractDvBuilder dvBuilder;
    private CellsWriter cellsWriter;
    private ColumnDataTranslate columnDataTranslate;

    ColumnDefinition(Field field, ExcelColumn excelColumn, CellStyle theadStyle, CellStyle tbodyStyle, AbstractDvBuilder dvBuilder, CellsWriter cellsWriter) {
        this.field = field;
        this.theadStyle = theadStyle;
        this.tbodyStyle = tbodyStyle;
        this.dvBuilder = dvBuilder;
        this.cellsWriter = cellsWriter;
        this.titleName = excelColumn.value();
        this.columnWidth = excelColumn.columnWidth();
        this.dataFormatEx = excelColumn.dataFormatEx();
        this.formula = excelColumn.formula();
        this.columnSort = excelColumn.columnSort();
        this.columnDataTranslate = excelColumn.dataTranslate();
    }

    void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public AbstractDvBuilder getDataValidationBuilder() {
        return dvBuilder;
    }

    public CellsWriter getCellsWriter() {
        return cellsWriter;
    }

    public ColumnDataTranslate getColumnDataTranslate() {
        return columnDataTranslate;
    }

    public Field getField() {
        return field;
    }

    public String getFieldName() {
        return field.getName();
    }

    public String getTitleName() {
        return titleName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public String getDataFormatEx() {
        return dataFormatEx;
    }

    public String getFormula() {
        return formula;
    }

    public CellStyle getTheadStyle() {
        return theadStyle;
    }

    public CellStyle getTbodyStyle() {
        return tbodyStyle;
    }


    public ColumnDefinition setTitleName(String titleName) {
        this.titleName = titleName;
        return this;
    }

    public ColumnDefinition setTheadStyle(CellStyle cellStyle) {
        this.theadStyle = cellStyle;
        return this;
    }

    public ColumnDefinition setTbodyStyle(CellStyle cellStyle) {
        this.tbodyStyle = cellStyle;
        return this;
    }

    public ColumnDefinition setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
        return this;
    }

    public ColumnDefinition setDataFormatExpreesion(String dataFormatExpreesion) {
        this.dataFormatEx = dataFormatExpreesion;
        return this;
    }

    public ColumnDefinition setFormula(String formula) {
        this.formula = formula;
        return this;
    }

    public int compareTo(ColumnDefinition o) {
        return Integer.compare(this.columnSort, o.columnSort);
    }

    @Override
    public String toString() {
        return titleName;
    }


}
