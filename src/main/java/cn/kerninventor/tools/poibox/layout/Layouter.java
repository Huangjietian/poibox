package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1>中文注释</h1>
 * <p>
 *     布局处理器
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface Layouter {

    /**
     * 合并指定区间的单元格
     * @param sheet 指定的sheet
     * @param cellRangeAddress 指定的区间
     * @return 合并的单元格 {@link MergedRange}
     */
    MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    /**
     * 合并指定区间的单元格
     * @param sheet 指定的sheet
     * @param topRow 起始行
     * @param footRow 结束行
     * @param leftColumn 起始列
     * @param rightColumn 结束列
     * @return 合并的单元格 {@link MergedRange}
     */
    MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    /**
     * 合并单行
     * @param sheet 指定的sheet
     * @param row 指定行下标
     * @param leftColumn 起始列
     * @param rightColumn 结束列
     * @return
     */
    MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    /**
     * 合并单列
     * @param sheet 指定的sheet
     * @param column 指定的列下标
     * @param topRow 起始行
     * @param footRow 结束行
     * @return
     */
    MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);

    /**
     * 根据指定的列内容合并
     * @param sheet 指定sheet
     * @param column 指定的列下标
     */
    void mergedByColumnContent(Sheet sheet, int column);

    /**
     * 根据指定的行内容合并
     * @param sheet 指定sheet
     * @param row 指定的行下标
     */
    void mergedByRowContent(Sheet sheet, int row);

    /**
     * 设置单元格高宽
     * @param sheet 指定的sheet
     * @param height 单元格高度
     * @param width 单元格宽度
     */
    void setCellsSize(Sheet sheet, Float height, Integer width);

    /**
     * 添加文本框
     * @param sheet 指定的sheet
     * @param text 指定的文本框内容
     * @param anchorIndex 文本框的锚坐标 参考 {@link AnchorIndex}
     * @return SimpleTextBox 文本框对象
     */
    SimpleTextBox addTextBox(Sheet sheet, String text, AnchorIndex anchorIndex);

    /**
     * 根据注解生成文本框
     * @param sheet 指定的sheet
     * @param textbox {@link Textbox}
     */
    void addTextBox(Sheet sheet, Textbox textbox);

    /**
     * 添加文本框
     * @param sheet 指定的sheet
     * @param anchorIndex 文本框的锚坐标 参考 {@link AnchorIndex}
     * @param text 指定的文本框内容
     * @return HSSFTextbox 文本框对象
     */
    HSSFTextbox addTextBox(HSSFSheet sheet, AnchorIndex anchorIndex, String text);

    /**
     * 添加文本框
     * @param sheet 指定的sheet
     * @param anchorIndex 文本框的锚坐标 参考 {@link AnchorIndex}
     * @param text 指定的文本框内容
     * @return XSSFTextBox 文本框对象
     */
    XSSFTextBox addTextBox(XSSFSheet sheet, AnchorIndex anchorIndex, String text);

    /**
     * 添加图片
     * @param sheet 指定的sheet
     * @param file 指定的图片文件，文件需要是 {@link PictureFormat}下规定的几种图片文件之一
     * @param anchorIndex 图片的锚坐标 参考 {@link AnchorIndex}
     * @return
     * @throws IOException
     */
    Picture addPicture(Sheet sheet, File file, AnchorIndex anchorIndex) throws IOException;

    /**
     * 添加图片
     * @param sheet 指定的sheet
     * @param source 图片的输入流
     * @param format 图片的格式 ，文件需要是 {@link PictureFormat}下规定的几种图片文件之一
     * @param anchorIndex 图片的锚坐标 参考 {@link AnchorIndex}
     * @return
     * @throws IOException
     */
    Picture addPicture(Sheet sheet, InputStream source, PictureFormat format, AnchorIndex anchorIndex) throws IOException;

    /**
     * 添加图片
     * @param sheet 指定的sheet
     * @param bytes 图片的字节
     * @param format 图片的格式 ，文件需要是 {@link PictureFormat}下规定的几种图片文件之一
     * @param anchorIndex 图片的锚坐标 参考 {@link AnchorIndex}
     * @return
     */
    Picture addPicture(Sheet sheet, byte[] bytes, PictureFormat format, AnchorIndex anchorIndex);
}
