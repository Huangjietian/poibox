package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     风格生成器
 * </p>
 * @author Kern
 * @version 1.0
 */
public class StyleProducer {

    private CellStyle cellStyle;

    public StyleProducer(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    /**
     * 获取风格
     * @return
     */
    public CellStyle get() {
        return cellStyle;
    }

    /**
     * 设置字体
     * @param font
     * @return
     */
    public StyleProducer setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    /**
     * 设置边框
     * @param direction
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setBorder(BorderDirection direction, BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        switch (direction){
            case TOP :
                setTopBorder(borderStyle, hssfColorPredefined);
                break;
            case BOTTOM:
                setBottomBorder(borderStyle, hssfColorPredefined);
                break;
            case LEFT:
                setLeftBorder(borderStyle, hssfColorPredefined);
                break;
            case RIGHT:
                setRightBorder(borderStyle, hssfColorPredefined);
                break;
            case SURROUND:
                setSurroundBorder(borderStyle,hssfColorPredefined);
                break;
            default:
        }
        return this;
    }

    /**
     * 设置上边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setTopBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderTop(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置下边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setBottomBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderBottom(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置左边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setLeftBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderLeft(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置右边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setRightBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderRight(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置四周边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setSurroundBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        if (borderStyle != null) {
            cellStyle.setBorderTop(borderStyle);
            cellStyle.setBorderBottom(borderStyle);
            cellStyle.setBorderLeft(borderStyle);
            cellStyle.setBorderRight(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置填充形式
     * @param fillPatternType
     * @return
     */
    public StyleProducer setFillPattern(FillPatternType fillPatternType) {
        cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    /**
     * 设置背景填充颜色
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillBackgroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    /**
     * 设置前景填充颜色
     * @param hssfColorPredefined
     * @return
     */
    public StyleProducer setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillForegroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    /**
     * 设置垂直居中形式
     * @param verticalAlignment
     * @return
     */
    public StyleProducer setVerticalAlignment(VerticalAlignment verticalAlignment) {
        cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    /**
     * 设置水平居中形式
     * @param horizontalAlignment
     * @return
     */
    public StyleProducer setAlignment(HorizontalAlignment horizontalAlignment) {
        cellStyle.setAlignment(horizontalAlignment);
        return this;
    }

    /**
     * 设置完全居中
     * @return
     */
    public StyleProducer setWholeCenter() {
        setVerticalAlignment(VerticalAlignment.CENTER).setAlignment(HorizontalAlignment.CENTER);
        return this;
    }

    /**
     * 设置自动换行
     * @param wrapText
     * @return
     */
    public StyleProducer setWrapText(boolean wrapText) {
        cellStyle.setWrapText(wrapText);
        return this;
    }

    /**
     * 设置风格锁
     * @param locked
     * @return
     */
    public StyleProducer setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    /**
     * 设置缩进
     * @param indention
     * @return
     */
    public StyleProducer setIndention(short indention) {
        cellStyle.setIndention(indention);
        return this;
    }

    /**
     * 设置隐藏
     * @param hidden
     * @return
     */
    public StyleProducer setHidden(Boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    /**
     * 设置单元格格式
     * @param index
     * @return
     */
    public StyleProducer setDataFormat(short index) {
        cellStyle.setDataFormat(index);
        return this;
    }
}
