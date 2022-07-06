package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Range;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * <h1>中文注释</h1>
 * <p>
 *     横幅定义
 * </p>
 * @author Kern
 * @version 1.0
 */
public class BannerDefinition {

    private CellStyle cellStyle;

    private CellRangeAddress rangeAddress;

    private String value;

    private Float rowHeight;

    public BannerDefinition(CellStyle cellStyle, String value, Float rowHeight, int firstRow, int lastRow, int firstCell, int lastCell) {
        this.cellStyle = cellStyle;
        this.value = value;
        this.rowHeight = rowHeight;
        this.rangeAddress = new CellRangeAddress(firstRow, lastRow, firstCell, lastCell);
    }

    public BannerDefinition(CellStyle cellStyle, String value, Float rowHeight, CellRangeAddress rangeAddress) {
        this.cellStyle = cellStyle;
        this.rangeAddress = rangeAddress;
        this.rowHeight = rowHeight;
        this.value = value;
    }

    public BannerDefinition(Styler styler, ExcelBanner banner) {
        this.value = banner.value();
        this.cellStyle = styler.generate(banner.style());
        this.rowHeight = banner.rowHeight();
        this.rangeAddress = new CellRangeAddress(banner.range().fistRow(), banner.range().lastRow(), banner.range().firstCell(), banner.range().lastCell());
    }

    public int getLastRowIndex(){
        return rangeAddress.getLastRow();
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public CellRangeAddress getRangeAddress() {
        return rangeAddress;
    }

    public String getValue() {
        return value;
    }

    public Float getRowHeight() {
        return rowHeight;
    }

    public CellRangeAddress adjustCellRangeAddress(int startRowIndex, List<ColumnDefinition> columns) {
        if (rangeAddress.getFirstRow() == Range.defaultVal) {
            rangeAddress.setFirstRow(startRowIndex);
        }
        if (rangeAddress.getLastRow() == Range.defaultVal) {
            rangeAddress.setLastRow(startRowIndex);
        }
        if (rangeAddress.getFirstColumn() == Range.defaultVal) {
            rangeAddress.setFirstColumn(columns.get(0).getColumnIndex());
        }
        if (rangeAddress.getLastColumn() == Range.defaultVal) {
            rangeAddress.setLastColumn(columns.get(columns.size() - 1).getColumnIndex());
        }
        return rangeAddress;
    }

    public String getContent() {
        return value;
    }

    public BannerDefinition setContent(String value) {
        this.value = value;
        return this;
    }

    public BannerDefinition setCellStyle(CellStyle style) {
        this.cellStyle = style;
        return this;
    }

    public BannerDefinition setRange(CellRangeAddress cellRangeAddress) {
        this.rangeAddress = cellRangeAddress;
        return this;
    }

    public BannerDefinition setRange(int row1, int row2, int col1, int col2) {
        this.rangeAddress = new CellRangeAddress(row1, row2, col1, col2);
        return null;
    }
}
