package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import cn.kerninventor.tools.poibox.data.tabulation.definition.BannerDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationClassParser;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.translator.TranslatorManager;
import cn.kerninventor.tools.poibox.data.tabulation.translator.WriteTranslatorManager;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.DefaultTabulationWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.BodyWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.BodyWriterFactory;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import cn.kerninventor.tools.poibox.utils.FormulaListUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Kern
 * @version 1.0
 */
public final class ExcelTabulationWriter<T> implements TabulationWriter<T> {

    private final TabulationDefinition<T> tabulationDefinition;

    private Map<String, Set<String>> formulaListMap;

    private final TranslatorManager translatorManager;

    public ExcelTabulationWriter(TabulationDefinition<T> tabulationDefinition) {
        this.tabulationDefinition = Objects.requireNonNull(tabulationDefinition);
        this.translatorManager = new WriteTranslatorManager();
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName) {
        return writeTo(sheetName, null);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet) {
        return writeTo(sheet, null);
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName, List<T> datas, String... ignore) {
        Sheet sheet = tabulationDefinition.getParent().getSheet(sheetName);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet, List<T> datas, String... ignore) {
        BodyWriter<T> bodyWriter = BodyWriterFactory.getTbodyWriter(translatorManager, datas);
        DefaultTabulationWriter<T> defaultTabulationWriter = new DefaultTabulationWriter<>(bodyWriter);
        doWrite(sheet, ignore, defaultTabulationWriter);
        return this;
    }

    private void doWrite(Sheet sheet, final String[] igonre, final DefaultTabulationWriter<T> defaultTabulationWriter) {
        TabulationDefinition<T> tabulationDefinition = getTabulationDefinition();
        List<ColumnDefinition> columnDefinitions = adaptIgonreColumns(igonre, tabulationDefinition);

        writeformulaList(sheet);
        writeTextbox(tabulationDefinition, sheet);
        writeBanners(tabulationDefinition, columnDefinitions, sheet);
        defaultTabulationWriter.doWrite(tabulationDefinition, columnDefinitions, sheet);
    }

    private List<ColumnDefinition> adaptIgonreColumns(final String[] igonre, TabulationDefinition<T> tabulationDefinition) {
        List<ColumnDefinition> columnDefinitions = tabulationDefinition.getColumnDefinitions();
        if (BeanUtil.hasElement(igonre)) {
            columnDefinitions = columnDefinitions.stream().filter(e -> Arrays.stream(igonre).noneMatch(str -> e.getTitleName().equals(str.trim()))).collect(Collectors.toList());
            TabulationClassParser.setColumnsIndexBySort(columnDefinitions);
        }
        return columnDefinitions;
    }

    private void writeformulaList(Sheet sheet) {
        Map<String, Set<String>> formulaListMap = getFormulaListMap();
        if (formulaListMap != null && !formulaListMap.isEmpty()){
            formulaListMap.keySet().forEach(e -> FormulaListUtil.addFormulaList(sheet, FormulaListDataValid.NAME_PREFIX + e, formulaListMap.get(e)));
            formulaListMap.clear();
        }
    }

    private void writeBanners(TabulationDefinition<T> tabulationDefinition, List<ColumnDefinition> columnDefinitions, Sheet sheet) {
        Layouter layouter = tabulationDefinition.getParent().layouter();
        List<BannerDefinition> bannerDefinitions = tabulationDefinition.getBannerDefinitions();

        for (BannerDefinition bannerDefinition : bannerDefinitions) {
            CellRangeAddress cellAddresses = bannerDefinition.adjustCellRangeAddress(tabulationDefinition.getStartRowIndex(), columnDefinitions);
            layouter.mergedRegion(sheet, cellAddresses)
                    .setRowHeight(bannerDefinition.getRowHeight())
                    .setMergeRangeContent(bannerDefinition.getValue())
                    .setMergeRangeStyle(bannerDefinition.getCellStyle());
        }
    }

    private void writeTextbox(TabulationDefinition<T> tabulationDefinition, Sheet sheet) {
        Textbox[] textboxes = tabulationDefinition.getTextnodes();
        for (Textbox textbox : textboxes) {
            tabulationDefinition.getParent().layouter().addTextBox(sheet, textbox);
        }
    }

    @Override
    public TabulationWriter<T> withFormulaList(String name, Set<String> formulaList) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.put(name, formulaList);
        return this;
    }

    @Override
    public TabulationWriter<T> withAllFormulaList(Map<String, Set<String>> formulaListMap) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.putAll(formulaListMap);
        return this;
    }

    @Override
    public TabulationWriter<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator) {
        this.translatorManager.putTranslator(translatorName, columnDataTranslator);
        return this;
    }

    @Override
    public TabulationWriter<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap) {
        this.translatorManager.putAllTranslator(columnDataTranslatorMap);
        return this;
    }

    private Map<String, Set<String>> getFormulaListMap() {
        return formulaListMap;
    }

    @Override
    public TabulationDefinition<T> getTabulationDefinition() {
        return tabulationDefinition;
    }

}
