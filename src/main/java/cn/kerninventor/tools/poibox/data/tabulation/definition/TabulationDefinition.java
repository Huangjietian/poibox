package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.List;
import java.util.Map;

/**
 * <h1>中文注释<h1/>
 * <p>
 *     表格定义类。poibox会将用户使用一系列注解配置的bean最终解析成一个TabulationDefinition对象。
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface TabulationDefinition<T> {

    /**
     * 获取Poibox
     * @return
     */
    Poibox getParent();

    /**
     * 获取配置Bean的Class
     * @return
     */
    Class<T> getTabulationClass();

    /**
     * 获取表头行行高
     * @return
     */
    float getTheadRowHeight();

    /**
     * 获取表体行行高
     * @return
     */
    float getTbodyRowHeight();

    /**
     * 获取起始行下标
     * @return
     */
    int getStartRowIndex();

    /**
     * 获取配置有效行数
     * @return
     */
    int getEffectiveRows();

    /**
     * 获取最大列宽
     * @return
     */
    int getMaximumColumnsWidth();

    /**
     * 获取最小列宽
     * @return
     */
    int getMinimumColumnsWidth();

    /**
     * 获取表头行下标
     * @return
     */
    int getTheadRowIndex();

    /**
     * 获取表体起始行下标
     * @return
     */
    int getTbodyFirstRowIndex();

    /**
     * 获取表头风格Map
     * @return
     */
    Map<Integer, CellStyle> getTheadStyleMap();

    /**
     * 获取表体风格Map
     * @return
     */
    Map<Integer, CellStyle> getTbodyStyleMap();

    /**
     * 获取横幅定义集合
     * @return
     */
    List<BannerDefinition> getBannerDefinitions();

    /**
     * 获取列定义集合
     * @return
     */
    List<ColumnDefinition> getColumnDefinitions();

    /**
     * 获取文本框配置注解数组
     * @return
     */
    Textbox[] getTextnodes();

    /**
     * 设置起始行下标
     * @param startRowIndex
     * @return
     */
    TabulationDefinition setStartRowIndex(int startRowIndex);

    /**
     * 设置表头行行高
     * @param theadRowHeight
     * @return
     */
    TabulationDefinition setTheadRowHeight(float theadRowHeight);

    /**
     * 设置表体行行高
     * @param tbodyRowHeight
     * @return
     */
    TabulationDefinition setTbodyRowHeight(float tbodyRowHeight);

    /**
     * 设置表体有效配置行数
     * @param effctiveRows
     * @return
     */
    TabulationDefinition setEffectiveRows(int effctiveRows);

    /**
     * 设置最大列宽
     * @param maxmunColumnsWidth
     * @return
     */
    TabulationDefinition setMaximumColumnsWidth(int maxmunColumnsWidth);

    /**
     * 设置最小列宽
     * @param minmunColumnsWidth
     * @return
     */
    TabulationDefinition setMinimumColumnsWidth(int minmunColumnsWidth);



}
