package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h1>中文注释</h1>
 * <p>
 *     写入器接口
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface TabulationWriter<T> {

    /**
     * 写入模板到对应sheetName的Sheet对象中
     * @param sheetName 对应Sheet对象的name
     * @return
     */
    TabulationWriter<T> writeTo(String sheetName);

    /**
     * 写入模板到Sheet对象中
     * @param sheet 指定的Sheet对象
     * @return
     */
    TabulationWriter<T> writeTo(Sheet sheet);

    /**
     * 写入数据到对应sheetName的Sheet对象中
     * @param sheetName 对应Sheet对象的name
     * @param datas 数据集合
     * @param ignore 本次写入忽略的字段名称集合
     * @return
     */
    TabulationWriter<T> writeTo(String sheetName, List<T> datas, String... ignore);

    /**
     * 写入数据到Sheet对象中
     * @param sheet 指定的Sheet对象
     * @param datas 数据集合
     * @param ignore 本次写入忽略的字段名称集合
     * @return
     */
    TabulationWriter<T> writeTo(Sheet sheet, List<T> datas, String... ignore);

    /**
     * 添加FormulaList到当前poibox持有的Workbook对象中，默认情况下，将会把该部分数据写入到隐藏的Sheet中 <br/>
     * 需要搭配{@link cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid} <br/>
     * 详情请参考Excel的名称管理器功能
     * @param name {@link cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid#value()}
     * @param formulaList 数据集合
     * @return
     */
    TabulationWriter<T> withFormulaList(String name, Set<String> formulaList);

    /**
     * 添加FormulaList到当前poibox持有的Workbook对象中，默认情况下，将会把该部分数据写入到隐藏的Sheet中 <br/>
     * 需要搭配{@link cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid} <br/>
     * 详情请参考Excel的名称管理器功能
     * @param formulaListMap 数据Map
     * @return
     */
    TabulationWriter<T> withAllFormulaList(Map<String, Set<String>> formulaListMap);

    /**
     * 添加 {@link ColumnDataTranslator}，以提供配置Bean所需的字段值翻译功能。
     *
     * @param translatorName {@link ExcelColumn#dataTranslate()} {@link ColumnDataTranslate#translator()}
     * @param columnDataTranslator 实现了{@link ColumnDataTranslator}接口的类实例
     * @return
     */
    TabulationWriter<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator);

    /**
     * 添加 {@link ColumnDataTranslator}，以提供配置Bean所需的字段值翻译功能。
     * @param columnDataTranslatorMap 翻译器Map
     * @return
     */
    TabulationWriter<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap);

    /**
     * 获取Bean配置的定义
     * @return
     */
    TabulationDefinition getTabulationDefinition();

}
