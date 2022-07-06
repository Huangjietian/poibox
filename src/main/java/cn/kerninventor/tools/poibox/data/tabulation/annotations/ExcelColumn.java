package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsGeneralWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsWriter;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     列配置
 * </p>
 * @author Kern
 * @version 1.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    int DefaultColumnWidth = -1;

    /**
     * 定义列表头内容
     * @return
     */
    String value();

    /**
     * 定义列排序
     * @return
     */
    int columnSort() default Integer.MAX_VALUE;

    /**
     * 定义列宽
     * @return
     */
    int columnWidth() default DefaultColumnWidth;

    /**
     * 定义列的单元格格式，详情参考Excel的单元格格式自定义部分
     * @return
     */
    String dataFormatEx() default "";

    /**
     * 定义列的单元格函数
     * @return
     */
    String formula() default "";

    /**
     * 定义列的表头风格下标 {@link ExcelTabulation#theadStyles()}
     * @return
     */
    int theadStyleIndex() default 0;

    /**
     * 定义列的表体风格下标 {@link ExcelTabulation#tbodyStyles()}
     * @return
     */
    int tbodyStyleIndex() default 0;

    /**
     * 定义列的单元格写入器 默认提供的几种单元格写入器请参考 {@link CellsWriter}
     * @return
     */
    Class<? extends CellsWriter> cellsWriter() default CellsGeneralWriter.class;

    /**
     * 定义列的字段值翻译器。 如果你指定了某个 {@link ColumnDataTranslate#translator()}, 并置 {@link ColumnDataTranslate#open()} = true <br/>
     * 那么你需要在对应的读写器中添加对应的 {@link cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator},<br/>
     * 例如
     * {@link cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter#withColumnDataTranslator(String, ColumnDataTranslator)} 或者
     * {@link cn.kerninventor.tools.poibox.data.tabulation.reader.TabulationReader#withColumnDataTranslator(String, ColumnDataTranslator)}
     * @return
     */
    ColumnDataTranslate dataTranslate() default @ColumnDataTranslate(translator = "", open = false);
}
