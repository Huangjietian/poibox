package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsWriter;
import cn.kerninventor.tools.poibox.exception.IllegalColumnFieldException;
import cn.kerninventor.tools.poibox.exception.IllegalSourceClassException;
import cn.kerninventor.tools.poibox.exception.IllegalTabulationBeanException;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>中文注释</h1>
 * <p>
 *     配置类的解析器，最终返回{@link TabulationDefinition}
 *     分离了 表格定义的 构造和使用。
 * </p>
 *
 * @author Kern
 * @version 1.0
 */
public class TabulationClassParser {

    public <T> TabulationDefinition<T> parse(Class<T> sourceClass, Poibox poibox) {
        Objects.requireNonNull(sourceClass, "SourceClass can not be null!");
        Objects.requireNonNull(poibox, "Poibox can not be null!");
        ExcelTabulation excelTabulation = Objects.requireNonNull(sourceClass.getDeclaredAnnotation(ExcelTabulation.class),"Not found annotation @ExcelTabulation at : " + sourceClass.getName());
        TabulationBeanDefinition<T> beanDefinition = new TabulationBeanDefinition<T>(sourceClass, poibox);
        beanDefinition
                .setStartRowIndex(excelTabulation.startRowIndex())
                .setEffectiveRows(excelTabulation.effectiveRows())
                .setTheadRowHeight(excelTabulation.theadRowHeight())
                .setTbodyRowHeight(excelTabulation.tbodyRowHeight())
                .setMaximumColumnsWidth(excelTabulation.maximumColumnsWidth())
                .setMinimumColumnsWidth(excelTabulation.minimumColumnsWidth())
        ;
        beanDefinition.setTextnodes(excelTabulation.textboxes());
        Map<Integer, CellStyle> theadStyleMap = parseStyles(excelTabulation.theadStyles(), poibox.styler());
        beanDefinition.setTheadStyleMap(theadStyleMap);
        Map<Integer, CellStyle> tbodyStyleMap = parseStyles(excelTabulation.tbodyStyles(), poibox.styler());
        beanDefinition.setTbodyStyleMap(tbodyStyleMap);
        Field[] fields = sourceClass.getDeclaredFields();
        List<ColumnDefinition> columnDefinitions = parseColumns(fields, theadStyleMap, tbodyStyleMap);
        beanDefinition.setColumnDefinitions(columnDefinitions);
        List<BannerDefinition> bannerDefinitions = parseBanners(excelTabulation.banners(), poibox.styler());
        beanDefinition.setBannerDefinitions(bannerDefinitions);
        return beanDefinition;
    }

    private Map<Integer, CellStyle> parseStyles(Style[] styles, final Styler styler) {
        if (styles == null || styles.length == 0) {
            throw new IllegalTabulationBeanException("Undefined style at @ExcelTabulation!");
        }
        Map<Integer, CellStyle> styleMap = new HashMap<>();
        for (Style style : styles) {
            styleMap.put(style.index(), styler.generate(style));
        }
        return styleMap;
    }

    private List<BannerDefinition> parseBanners(ExcelBanner[] banners, final Styler styler) {
        return Arrays.stream(banners).map(e -> new BannerDefinition(styler, e)).collect(Collectors.toList());
    }

    private List<ColumnDefinition> parseColumns(Field[] columnFields, Map<Integer, CellStyle> theadCellStyleMap, Map<Integer, CellStyle> tbodyCellStyleMap) {
        List<ColumnDefinition> columnDefinitions = new ArrayList<>(columnFields.length);
        Set<String> ColumnsTitleNameSet = new HashSet<>(columnFields.length);
        ExcelColumn excelColumn;
        for (Field field : columnFields){
            if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                CellStyle theadCellStyle = theadCellStyleMap.get(excelColumn.theadStyleIndex());
                CellStyle tbodyCellStyle = tbodyCellStyleMap.get(excelColumn.tbodyStyleIndex());
                AbstractDvBuilder dvBuilder = getDvBuilderOf(field);
                CellsWriter cellsWriter = getCellsWriterOf(field, excelColumn);
                ColumnDefinition columnDefinition = new ColumnDefinition(field, excelColumn, theadCellStyle, tbodyCellStyle, dvBuilder, cellsWriter);
                columnDefinitions.add(columnDefinition);
                ColumnsTitleNameSet.add(columnDefinition.getTitleName());
            }
        }
        if (columnDefinitions.size() == 0){
            throw new IllegalSourceClassException("ExcelTabulation bean lack column definition, you should use @ExcelColumn to annotate object's field!");
        }
        if (ColumnsTitleNameSet.size() != columnDefinitions.size()) {
            throw new IllegalTabulationBeanException("@ExcelColumn value must be unique!");
        }
        setColumnsIndexBySort(columnDefinitions);
        return columnDefinitions;
    }

    private CellsWriter getCellsWriterOf(Field field, ExcelColumn excelColumn) {
        CellsWriter cellsWriter = null;
        try {
            cellsWriter = excelColumn.cellsWriter().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalColumnFieldException("CellsWriter lack of parameterless constructors! field : " + field.getName());
        }
        cellsWriter.supportedDataType(field);
        return cellsWriter;
    }

    private AbstractDvBuilder getDvBuilderOf(Field field) {
        Annotation annotation = ReflectUtil.getFirstMarkedAnnotation(field, DataValid.class);
        AbstractDvBuilder dvBuilder = null;
        if (annotation != null) {
            dvBuilder = AbstractDvBuilder.getInstance(annotation);
        }
        return dvBuilder;
    }

    public static void setColumnsIndexBySort(List<ColumnDefinition> columnDefinitions) {
        Collections.sort(columnDefinitions);
        for (int i = 0; i < columnDefinitions.size() ; i ++) {
            columnDefinitions.get(i).setColumnIndex(i);
        }
    }
}
