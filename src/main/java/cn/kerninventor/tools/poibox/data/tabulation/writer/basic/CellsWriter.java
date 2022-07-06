package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;

/**
 * <h1>中文注释</h1>
 * <p>
 *     单元格值写入器，默认提供了以下几种实现： <br/>
 *     {@link CellsGeneralWriter} 通用的写入器，也是缺省情况下的选择。  <br/>
 *     {@link CellsMergeColumnsWriter} 合并单元格写入器，将会在写入时合并相同内容的单元格。  <br/>
 *     {@link CellsCollection2CharsWriter} 集合转换为字符串写入器，将会把集合的内容转换为文本进行单元格的写入。  <br/>
 *     使用时需要注意字段数据类型的支持问题，请检查CellsWriter的 {@link CellsWriter#supportedDataType(Field)}方法。<br/>
 *     如果需要自定义的单元格值写入器，实现该接口，并在{@link ExcelColumn#cellsWriter()}中指定自定义写入器的{@link Class},注意，你需要为自定义的写入器提供一个无参的构造器。
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface CellsWriter {

    default void pre() {
        //To do prepare work before forEach.
    }

    void supportedDataType(Field field);

    void setCellValue(Cell cell, Object value);

    default void flush() {
        //To do finish work after forEach.
    }


}
