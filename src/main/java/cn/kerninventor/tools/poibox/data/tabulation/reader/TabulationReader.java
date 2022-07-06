package cn.kerninventor.tools.poibox.data.tabulation.reader;

import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * <h1>中文注释</h1>
 * <p>
 *     读取器接口
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface TabulationReader<T> {

    /**
     * 读取指定sheetName对应的Sheet对象
     * @param sheetName 名称
     * @return 数据集合
     */
    List<T> readFrom(String sheetName);

    /**
     * 读取指定下标对应的Sheet对象
     * @param sheetAt 下标
     * @return 数据集合
     */
    List<T> readFrom(int sheetAt);

    /**
     * 对象指定的Sheet对象
     * @param sheet 实例
     * @return 数据集合
     */
    List<T> readFrom(Sheet sheet);

    /**
     * 添加Bean的表单校验器。程序默认提供了hibernate的表单校验器实现 {@link ValidatorFactory#hibernateValidator()}
     * @param beanValidators {@link BeanValidator}
     * @return
     */
    TabulationReader withBeanValidator(BeanValidator<T, ?>... beanValidators);

    /**
     * 添加列数据翻译器
     * @see cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter#withColumnDataTranslator(String, ColumnDataTranslator)
     * @param translatorName
     * @param columnDataTranslator
     * @return
     */
    TabulationReader<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator);

    /**
     * 添加列数据翻译器
     * @see cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter#withColumnDataTranslator(String, ColumnDataTranslator)
     * @param columnDataTranslatorMap
     * @return
     */
    TabulationReader<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap);

}
