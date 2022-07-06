package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Border;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Kern
 * @version 1.0
 */
public final class StyleHandler extends BoxBracket implements Styler {

    public StyleHandler(Poibox poiBox) {
        super(poiBox);
    }

    @Override
    public StyleProducer producer() {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        return new StyleProducer(cellStyle);
    }

    @Override
    public CellStyle defaultHeadline(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_HEADLINE : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize, true);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN, null)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle defaultThead(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TABLEHEADER : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN, null)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle defaultTbody(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TEXTPART : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_TEXTPART, fontSize);
        return producer()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN, null)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle generate(Style style) {
        StyleProducer producer = producer();
        for (Border border : style.borders()) {
            producer.setBorder(border.direction(), border.borderStyle(), border.color());
        }
        return producer
                .setFont(getParent().fonter().generate(style.font()))
                .setFillPattern(style.fillPatternType())
                .setFillForegroundColor(style.foregroudColor())
                .setFillBackgroundColor(style.backgroudColor())
                .setVerticalAlignment(style.verticalAlignment())
                .setAlignment(style.alignment())
                .setWrapText(style.wrapText())
                .setLocked(style.locked())
                .setIndention((short) style.indention())
                .setHidden(style.hidden())
                .get();
    }

    @Override
    public CellStyle copyStyle(CellStyle targetStyle) {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        cellStyle.cloneStyleFrom(targetStyle);
        return cellStyle;
    }

    @Override
    public CellStyle copyStyle(CellStyle targetStyle, Workbook workbook) {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        cellStyle.setDataFormat(targetStyle.getDataFormat());
        cellStyle.setFont(workbook.getFontAt(targetStyle.getFontIndexAsInt()));
        cellStyle.setFillForegroundColor(targetStyle.getFillForegroundColor());
        cellStyle.setFillBackgroundColor(targetStyle.getFillBackgroundColor());
        cellStyle.setFillPattern(targetStyle.getFillPattern());
        cellStyle.setTopBorderColor(targetStyle.getTopBorderColor());
        cellStyle.setLeftBorderColor(targetStyle.getLeftBorderColor());
        cellStyle.setRightBorderColor(targetStyle.getRightBorderColor());
        cellStyle.setBottomBorderColor(targetStyle.getBottomBorderColor());
        cellStyle.setBorderTop(targetStyle.getBorderTop());
        cellStyle.setBorderBottom(targetStyle.getBorderBottom());
        cellStyle.setBorderLeft(targetStyle.getBorderLeft());
        cellStyle.setBorderRight(targetStyle.getBorderRight());
        cellStyle.setAlignment(targetStyle.getAlignment());
        cellStyle.setVerticalAlignment(targetStyle.getVerticalAlignment());
        cellStyle.setIndention(targetStyle.getIndention());
        cellStyle.setLocked(targetStyle.getLocked());
        cellStyle.setRotation(targetStyle.getRotation());
        cellStyle.setWrapText(targetStyle.getWrapText());
        cellStyle.setHidden(targetStyle.getHidden());
        cellStyle.setQuotePrefixed(targetStyle.getQuotePrefixed());
        cellStyle.setShrinkToFit(targetStyle.getShrinkToFit());
        return cellStyle;
    }
}
