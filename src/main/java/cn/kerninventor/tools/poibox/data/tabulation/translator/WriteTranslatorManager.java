package cn.kerninventor.tools.poibox.data.tabulation.translator;

/**
 * <h1>中文注释</h1>
 * <p>
 *     写入时翻译器管理器
 * </p>
 *
 * @author Kern
 * @version 1.0
 */
public class WriteTranslatorManager extends TranslatorManager {
    @Override
    public Object translate(ColumnDataTranslate translate, Object searchCondition) {
        if (translate.open()) {
            return getValue(translate.translator(), translate.tag(), translate.unmatchStrategy(), searchCondition);
        }
        return searchCondition;
    }
}
