package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Anchor;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Margins;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Palette;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kern
 * @version 1.0
 */
public final class LayoutHandler extends BoxBracket implements Layouter {

    public LayoutHandler(Poibox poiBox) {
        super(poiBox);
    }

    @Override
    public MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress) {
        sheet.addMergedRegion(cellRangeAddress);
        return new MergedRange(sheet, cellRangeAddress);
    }

    @Override
    public MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(topRow, footRow, leftColumn, rightColumn);
        return mergedRegion(sheet, cellRangeAddress);
    }

    @Override
    public MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn) {
        return mergedRegion(sheet, row, row, leftColumn, rightColumn);
    }

    @Override
    public MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow) {
        return mergedRegion(sheet, topRow, footRow, column, column);
    }

    @Override
    public void mergedByColumnContent(Sheet sheet, int column) {
        if (sheet.getLastRowNum() <= 0){
            return;
        }
        Map<Object, CellRangeAddress> map = new HashMap<>();
        for (Row row : sheet){
            Cell cell = row.getCell(column);
            if (cell == null) {
                continue;
            }
            Object value = CellValueUtil.getCellValue(cell);
            //diverse value , to merge already recorded and reset map.
            CellRangeAddress address = map.get(value);
            if (address == null){
                map.values().forEach(e -> {
                    if (e.getFirstRow() != e.getLastRow()){
                        sheet.addMergedRegion(e);
                    }
                });
                map.clear();
                map.put(value, new CellRangeAddress(row.getRowNum(), row.getRowNum(), column, column));
            } else {
                address.setLastRow(row.getRowNum());
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                if (e.getFirstRow() != e.getLastRow()){
                    sheet.addMergedRegion(e);
                }
            });
            map.clear();
        }
    }

    @Override
    public void mergedByRowContent(Sheet sheet, int row) {
        Row mergedRow;
        if ((mergedRow = sheet.getRow(row)) == null || mergedRow.getLastCellNum() <= 0){
            return;
        }
        Map<Object, CellRangeAddress> map = new HashMap<>();
        for (Cell cell : mergedRow){
            Object value = CellValueUtil.getCellValue(cell);
            //diverse value , to merge already recorded and reset map.
            CellRangeAddress address = map.get(value);
            if (address == null){
                map.values().forEach(e -> {
                    if (e.getFirstColumn() != e.getLastColumn()){
                        sheet.addMergedRegion(e);
                    }
                });
                map.clear();
                map.put(value, new CellRangeAddress(mergedRow.getRowNum(), mergedRow.getRowNum(), cell.getColumnIndex(), cell.getColumnIndex()));
            } else {
                address.setLastColumn(cell.getColumnIndex());
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                if (e.getFirstColumn() != e.getLastColumn()){
                    sheet.addMergedRegion(e);
                }
            });
            map.clear();
        }
    }

    @Override
    public void setCellsSize(Sheet sheet, Float height, Integer width) {
        final short[] columnIndex = {0, 0};
        sheet.forEach(r -> {
            if (height != null){
                r.setHeightInPoints(height);
            }
            columnIndex[0] = r.getFirstCellNum() > columnIndex[1] ? r.getFirstCellNum() : columnIndex[1];
            columnIndex[1] = r.getLastCellNum() - 1 > columnIndex[1] ? (short) (r.getLastCellNum() - 1) : columnIndex[1];
        });
        if (width != null){
            for (int i = 0 ; i < columnIndex[0] ; i++ ){
                sheet.setColumnWidth(i , BoxGadget.adjustCellWidth(width));
            }
        }
    }

    @Override
    public SimpleTextBox addTextBox(Sheet sheet, String text, AnchorIndex anchorIndex) {
        Shape shape;
        if (sheet instanceof HSSFSheet) {
            shape = addTextBox((HSSFSheet) sheet, anchorIndex, text);
        } else if (sheet instanceof XSSFSheet){
            shape = addTextBox((XSSFSheet) sheet, anchorIndex, text);
        } else {
            throw new IllegalArgumentException("Unsupported sheet type");
        }
        return new SimpleTextBox(shape);
    }

    @Override
    public void addTextBox(Sheet sheet, Textbox textbox) {
        Anchor anchor = textbox.anchorIndex();
        AnchorIndex anchorIndex = new AnchorIndex(
                anchor.left(), anchor.top(), anchor.right(), anchor.bottom(),
                anchor.col1(), anchor.row1(), anchor.col2(), anchor.row2()
        );
        String value = textbox.value();
        SimpleTextBox simpleTextBox = addTextBox(sheet, value, anchorIndex);
        cn.kerninventor.tools.poibox.data.tabulation.annotations.Palette fillColor = textbox.fillColor();
        simpleTextBox.setFillColor(fillColor.red(), fillColor.green(), fillColor.bule());
        Palette lineColor = textbox.lineColor();
        simpleTextBox.setLineColor(lineColor.red(), lineColor.green(), lineColor.bule());
        Margins margins = textbox.margins();
        simpleTextBox.setMargins(margins.left(), margins.top(), margins.right(), margins.bottom());
        simpleTextBox.setVerticalAlignment(textbox.verticalAlignment());
    }

    @Override
    public HSSFTextbox addTextBox(HSSFSheet sheet, AnchorIndex anchorIndex, String text) {
        HSSFPatriarch drawing = sheet.createDrawingPatriarch();
        HSSFClientAnchor clientAnchor = drawing.createAnchor(
                anchorIndex.getLeft(),
                anchorIndex.getTop(),
                anchorIndex.getRight(),
                anchorIndex.getBottom(),
                anchorIndex.getCol1(),
                anchorIndex.getRow1(),
                anchorIndex.getCol2(),
                anchorIndex.getRow2()
        );
        HSSFTextbox textBox = drawing.createTextbox(clientAnchor);
        textBox.setString(new HSSFRichTextString(text));
        return textBox;
    }

    @Override
    public XSSFTextBox addTextBox(XSSFSheet sheet, AnchorIndex anchorIndex, String text) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor clientAnchor = drawing.createAnchor(
                anchorIndex.getLeft(),
                anchorIndex.getTop(),
                anchorIndex.getRight(),
                anchorIndex.getBottom(),
                anchorIndex.getCol1(),
                anchorIndex.getRow1(),
                anchorIndex.getCol2(),
                anchorIndex.getRow2()
        );
        XSSFTextBox textBox = drawing.createTextbox(clientAnchor);
        textBox.setFillColor(255,255,255);
        textBox.setLineStyle(0);
        textBox.setLineStyleColor(0,0,0);
        textBox.setText(text);
        return textBox;
    }

    @Override
    public Picture addPicture(Sheet sheet, File file, AnchorIndex anchorIndex) throws IOException {
        FileInputStream in = new FileInputStream(file);
        PictureFormat format = PictureFormat.getByFileName(file.getName());
        return addPicture(sheet, in, format, anchorIndex);
    }

    @Override
    public Picture addPicture(Sheet sheet, InputStream source, PictureFormat format, AnchorIndex anchorIndex) throws IOException {
        try {
            byte[] bytes = new byte[source.available()];
            source.read(bytes, 0, bytes.length);
            return addPicture(sheet, bytes, format, anchorIndex);
        } catch (IOException e) {
            throw e;
        } finally {
            if (source != null) {
                source.close();
            }
        }
    }

    @Override
    public Picture addPicture(Sheet sheet, byte[] bytes, PictureFormat format, AnchorIndex anchorIndex) {
        int index = getParent().workbook().addPicture(bytes, format.getIndex());
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = drawing.createAnchor(
                anchorIndex.getLeft(),
                anchorIndex.getTop(),
                anchorIndex.getRight(),
                anchorIndex.getBottom(),
                anchorIndex.getCol1(),
                anchorIndex.getRow1(),
                anchorIndex.getCol2(),
                anchorIndex.getRow2()
        );
        Picture picture = drawing.createPicture(clientAnchor, index);
        return picture;
    }

}
