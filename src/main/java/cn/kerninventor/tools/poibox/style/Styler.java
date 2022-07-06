package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * <h1>中文注释</h1>
 * <p>
 *     风格处理器
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface Styler {

    /**
     * 风格生成器，基于{@link CellStyle}的接口封装，提供了builder模式的CellStyle对象构造器。
     * @return
     */
    StyleProducer producer();

    /**
     * 默认的标题风格
     * @param fontSize
     * @return
     */
    CellStyle defaultHeadline(Integer fontSize);

    /**
     * 默认的表头风格
     * @param fontSize
     * @return
     */
    CellStyle defaultThead(Integer fontSize);

    /**
     * 默认的表体风格
     * @param fontSize
     * @return
     */
    CellStyle defaultTbody(Integer fontSize);

    /**
     * 根据注解生成{@link CellStyle}对象
     * @param style {@link cn.kerninventor.tools.poibox.data.tabulation.annotations.Style}
     * @return {@link org.apache.poi.ss.usermodel.CellStyle}
     */
    CellStyle generate(Style style);

    /**
     * 复制风格，生成一个克隆的新实例
     * @param targetStyle
     * @return
     */
    CellStyle copyStyle(CellStyle targetStyle);

    /***
     * 复制风格，生成一个克隆的新实例
     * @param targetStyle
     * @param workbook
     * @return
     */
    CellStyle copyStyle(CellStyle targetStyle, Workbook workbook);
}
