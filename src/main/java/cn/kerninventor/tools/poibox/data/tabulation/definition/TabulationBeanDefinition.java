package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Range;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.List;
import java.util.Map;

/**
 * <h1>中文注释</h1>
 * <p>
 *     表格Bean定义实现
 * </p>
 * @author Kern
 * @version 1.0
 */
public class TabulationBeanDefinition<T> extends BoxBracket implements TabulationDefinition {

    private Class<T> tabulationClass;
    private float theadRowHeight;
    private float tbodyRowHeight;
    private int maximumColumnsWidth;
    private int minimumColumnsWidth;
    private int startRowIndex;
    private int effectiveRows;
    private Textbox[] textnodes;
    private Map<Integer, CellStyle> theadStyleMap;
    private Map<Integer, CellStyle> tbodyStyleMap;
    private List<BannerDefinition> bannerDefinitions;
    private List<ColumnDefinition> columnDefinitions;

    public TabulationBeanDefinition(Class<T> tabulationClass, Poibox poiBox) {
        super(poiBox);
        this.tabulationClass = tabulationClass;
    }

    void setTheadStyleMap(Map<Integer, CellStyle> theadStyleMap) {
        this.theadStyleMap = theadStyleMap;
    }

    void setTbodyStyleMap(Map<Integer, CellStyle> tbodyStyleMap) {
        this.tbodyStyleMap = tbodyStyleMap;
    }

    void setBannerDefinitions(List<BannerDefinition> bannerDefinitions) {
        this.bannerDefinitions = bannerDefinitions;
    }

    void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    void setTextnodes(Textbox[] textnodes) {
        this.textnodes = textnodes;
    }

    private int getRowIndexIncrementsByBanners(List<BannerDefinition> bannerDefinitions) {
        int maximum = bannerDefinitions.stream().mapToInt(BannerDefinition::getLastRowIndex).max().orElse(Range.defaultVal);
        if (maximum >= 0) {
            maximum++;
        } else {
            return 0;
        }
        return maximum;
    }

    @Override
    public Class<T> getTabulationClass() {
        return tabulationClass;
    }

    @Override
    public float getTheadRowHeight() {
        return theadRowHeight;
    }

    @Override
    public float getTbodyRowHeight() {
        return tbodyRowHeight;
    }

    @Override
    public int getStartRowIndex() {
        return startRowIndex;
    }

    @Override
    public int getEffectiveRows() {
        return effectiveRows;
    }

    @Override
    public int getMaximumColumnsWidth() {
        return BoxGadget.adjustCellWidth(maximumColumnsWidth);
    }

    @Override
    public int getMinimumColumnsWidth() {
        return BoxGadget.adjustCellWidth(minimumColumnsWidth);
    }

    @Override
    public int getTheadRowIndex() {
        return getStartRowIndex() + getRowIndexIncrementsByBanners(getBannerDefinitions());
    }

    @Override
    public int getTbodyFirstRowIndex(){
        return getTheadRowIndex() + 1;
    }

    @Override
    public Map<Integer, CellStyle> getTheadStyleMap() {
        return theadStyleMap;
    }

    @Override
    public Map<Integer, CellStyle> getTbodyStyleMap() {
        return tbodyStyleMap;
    }

    @Override
    public List<BannerDefinition> getBannerDefinitions() {
        return bannerDefinitions;
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public Textbox[] getTextnodes() {
        return textnodes;
    }

    @Override
    public TabulationDefinition setStartRowIndex(int startRowIndex) {
        BeanUtil.isTrue(startRowIndex >= 0, "StartRowIndex must be greater than or equal than 0!");
        this.startRowIndex = startRowIndex;
        return this;
    }

    @Override
    public TabulationDefinition setTheadRowHeight(float theadRowHeight) {
        BeanUtil.isTrue(theadRowHeight > 0.0f, "Row height must be greater than 0.0!");
        this.theadRowHeight = theadRowHeight;
        return this;
    }

    @Override
    public TabulationDefinition setTbodyRowHeight(float tbodyRowHeight) {
        BeanUtil.isTrue(tbodyRowHeight > 0.0f, "Row height must be greater than 0.0!");
        this.tbodyRowHeight = tbodyRowHeight;
        return this;
    }

    @Override
    public TabulationDefinition setEffectiveRows(int effectiveRows) {
        BeanUtil.isTrue(effectiveRows >= 1, "EffectiveRows must be greater than or equal than 1!");
        this.effectiveRows = effectiveRows;
        return this;
    }

    @Override
    public TabulationDefinition setMaximumColumnsWidth(int maximunColumnsWidth) {
        BeanUtil.isTrue(maximunColumnsWidth > 0 && maximunColumnsWidth > getMinimumColumnsWidth(), "MaximumColumnsWidth must be greater than 0 and greater than minimumColumnsWidth!");
        this.maximumColumnsWidth = maximunColumnsWidth;
        return this;
    }

    @Override
    public TabulationDefinition setMinimumColumnsWidth(int minmunColumnsWidth) {
        BeanUtil.isTrue(minmunColumnsWidth >= 0, "MinimumColumnsWidth must be greater than or equal than 0!");
        this.minimumColumnsWidth = minmunColumnsWidth;
        return this;
    }


}
