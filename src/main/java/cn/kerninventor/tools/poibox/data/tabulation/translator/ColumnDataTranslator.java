package cn.kerninventor.tools.poibox.data.tabulation.translator;

/**
 * <h1>中文注释</h1>
 * <p>
 *     所有自定义的字段值翻译器都需要实现该接口。
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface ColumnDataTranslator<K, V> {

    V getValue(K key, String tag);

    K getKey(V value, String tag);

    default K getDefaultKey() {
        return null;
    }

    default V getDefaultValue() {
        return null;
    }

}
