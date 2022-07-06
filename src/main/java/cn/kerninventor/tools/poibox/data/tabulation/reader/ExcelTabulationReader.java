package cn.kerninventor.tools.poibox.data.tabulation.reader;

import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ReadTranslatorManager;
import cn.kerninventor.tools.poibox.data.tabulation.translator.TranslatorManager;
import cn.kerninventor.tools.poibox.exception.IllegalSourceClassException;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @version 1.0
 */
public class ExcelTabulationReader<T> implements TabulationReader<T> {

    private TabulationDefinition tabulationDefinition;

    private TranslatorManager translatorManager;

    private List<BeanValidator<T, ?>> beanValidators;

    public ExcelTabulationReader(TabulationDefinition tabulationDefinition) {
        this.tabulationDefinition = Objects.requireNonNull(tabulationDefinition);
        this.translatorManager = new ReadTranslatorManager();
    }

    @Override
    public List<T> readFrom(String sheetName) {
        Sheet sheet = tabulationDefinition.getParent().workbook().getSheet(sheetName);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        Sheet sheet = tabulationDefinition.getParent().workbook().getSheetAt(sheetAt);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        List<T> list = new ArrayList();
        List<ColumnDefinition> columnDefinitions = tabulationDefinition.getColumnDefinitions();
        for (int i = tabulationDefinition.getTbodyFirstRowIndex(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            Class<T> tClass;
            try {
                 tClass = tabulationDefinition.getTabulationClass();
                t = ReflectUtil.newInstance(tClass);
            } catch (Exception e) {
                throw new IllegalSourceClassException("The tabulation Class Missing parameterless constructor! Class: " + tabulationDefinition.getTabulationClass());
            }
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            int nullCount = 0;
            for (ColumnDefinition column : columnDefinitions){
                Cell cell = row.getCell(column.getColumnIndex());
                if (cell == null){
                    nullCount++;
                    continue;
                }
                Object value = null;
                value = CellValueUtil.getCellValueBySpecifiedType(cell, column.getField().getType());
                value = translatorManager.translate(column.getColumnDataTranslate(), value);
                if (null == value) {
                    nullCount++;
                }
                try {
                    ReflectUtil.setFieldValue(column.getField(), t, value);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Set value to Field error! Field: " + column.getFieldName());
                }
            }
            if (nullCount < tabulationDefinition.getColumnDefinitions().size()){
                if (BeanUtil.isNotEmpty(beanValidators)) {
                    for (BeanValidator validator : beanValidators) {
                        validator.validate(t);
                    }
                }
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public TabulationReader withBeanValidator(BeanValidator<T, ?>... beanValidators) {
        if (this.beanValidators == null) {
            this.beanValidators = new ArrayList();
        }
        this.beanValidators.addAll(Arrays.stream(beanValidators).collect(Collectors.toList()));
        return this;
    }

    @Override
    public TabulationReader<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator) {
        this.translatorManager.putTranslator(translatorName, columnDataTranslator);
        return this;
    }

    @Override
    public TabulationReader<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap) {
        this.translatorManager.putAllTranslator(columnDataTranslatorMap);
        return this;
    }

}
