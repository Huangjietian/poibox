package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.utils.JsMathUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

/**
 * <h1>中文注释</h1>
 * <p>
 *     为解决 {@link HSSFTextbox} 和 {@link XSSFTextBox}的不兼容，通过桥接的形式设计了该对象。<br/>
 *     提供了关于 Textbox 的几个通用方法。如果已知 Textbox对象的实际类型，建议调用精确泛型的相关方法
 * </p>
 * @author Kern
 * @version 1.0
 */
public final class SimpleTextBox<Agent extends Shape> {

    private Agent agent;

    public SimpleTextBox(Agent agent) {
        if (!(agent instanceof HSSFTextbox) && !(agent instanceof XSSFTextBox)) {
            throw new IllegalArgumentException("TextBox not found!");
        }
        this.agent = agent;
    }

    /**
     * 设置文本框内容
     * @param str
     * @return
     */
    public SimpleTextBox setString(String str) {
        if (agent instanceof HSSFTextbox) {
            ((HSSFTextbox)agent).setString(new HSSFRichTextString(str));
        } else if (agent instanceof XSSFTextBox) {
            ((XSSFTextBox)agent).clearText();
            ((XSSFTextBox)agent).setText(str);
        }
        return this;
    }

    /**
     * 设置文本框的垂直居中
     * @param verticalAlignment
     * @return
     */
    public SimpleTextBox setVerticalAlignment(VerticalAlignment verticalAlignment) {
        if (agent instanceof HSSFTextbox) {
            ((HSSFTextbox)agent).setVerticalAlignment(verticalAlignment.getCode());
        } else if (agent instanceof XSSFTextBox) {
            ((XSSFTextBox)agent).setVerticalAlignment(verticalAlignment);
        }
        return this;
    }

    /**
     * 设置边缘留白区域
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    @Deprecated
    public SimpleTextBox setMargins(int left, int top, int right, int bottom) {
        boolean b = JsMathUtil.up2Standard(0, JsMathUtil.JsSimpleComparison.greaterThanOrEqualTo, left, top, right, bottom);
        if (!b) {
            throw new IllegalArgumentException("An invalid parameter was entered, margins must be greater than or equal to 0!");
        }
        //FIXME invalid when textbox is HSSFTextbox
        if (agent instanceof HSSFTextbox) {
            HSSFTextbox textbox = ((HSSFTextbox)agent);
            textbox.setMarginLeft(left);
            textbox.setMarginTop(top);
            textbox.setMarginRight(right);
            textbox.setMarginBottom(bottom);
        } else if (agent instanceof XSSFTextBox) {
            XSSFTextBox textBox = ((XSSFTextBox)agent);
            textBox.setLeftInset(left);
            textBox.setTopInset(top);
            textBox.setRightInset(right);
            textBox.setBottomInset(bottom);
        }
        return this;
    }

    /**
     * 设置填充颜色
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public SimpleTextBox setFillColor(int red, int green, int blue) {
        agent.setFillColor(red, green, blue);
        return this;
    }

    /**
     * 设置填充颜色
     * @param palette
     * @return
     */
    public SimpleTextBox setFillColor(Palette palette) {
        agent.setFillColor(palette.getRed(), palette.getGreen(), palette.getBlue());
        return this;
    }

    /**
     * 设置边框风格
     * @param lineStyle
     * @return
     */
    public SimpleTextBox setLineStyle(int lineStyle){
        if (agent instanceof HSSFShape) {
            ((HSSFShape)agent).setLineStyle(lineStyle);
        } else if (agent instanceof XSSFShape) {
            ((XSSFShape)agent).setLineStyle(lineStyle);
        }
        return this;
    }

    /**
     * 设置边框颜色
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public SimpleTextBox setLineColor(int red, int green, int blue){
        agent.setLineStyleColor(red, green, blue);
        return this;
    }

    /**
     * 设置边框颜色
     * @param palette
     * @return
     */
    public SimpleTextBox setLineColor(Palette palette){
        agent.setLineStyleColor(palette.getRed(), palette.getGreen(), palette.getBlue());
        return this;
    }

}
