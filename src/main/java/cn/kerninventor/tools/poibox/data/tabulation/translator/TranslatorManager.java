package cn.kerninventor.tools.poibox.data.tabulation.translator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kern
 * @version 1.0
 */
public abstract class TranslatorManager {

    private Map<String, ColumnDataTranslator> translatorMap = new HashMap<>(16);

    public void putTranslator(String name, ColumnDataTranslator columnDataTranslator) {
        translatorMap.put(name, columnDataTranslator);
    }

    public void putAllTranslator(Map<String, ColumnDataTranslator> translatorMap) {
        this.translatorMap.putAll(translatorMap);
    }

    public abstract Object translate(ColumnDataTranslate translate, Object searchCondition);

    protected Object getValue(String translatorName, String tag, UnmatchStrategy unmatchStrategy, Object key) {
        ColumnDataTranslator translator = translatorMap.get(translatorName);
        if (translator == null) {
            //如果translator没有匹配到
            return unmatcHandle(1, translatorName, key, tag, unmatchStrategy, translator);
        }
        Object value = translator.getValue(key, tag);
        if (value == null) {
            return unmatcHandle(1, translatorName, key, tag, unmatchStrategy, translator);
        }
        return value;
    }

    protected Object getKey(String translatorName, String tag, UnmatchStrategy unmatchStrategy, Object value) {
        ColumnDataTranslator translator = translatorMap.get(translatorName);
        if (translator == null) {
            return unmatcHandle(2, translatorName, value, tag, unmatchStrategy, translator);
        }
        Object key = translator.getKey(value, tag);
        if (key == null) {
            return unmatcHandle(2, translatorName, value, tag, unmatchStrategy, translator);
        }
        return key;
    }

    private Object unmatcHandle(int mode, String translatorName, Object obj, String tag, UnmatchStrategy unmatchStrategy, ColumnDataTranslator translator) {
        switch (unmatchStrategy) {
            case CONSOLE:
                StringBuilder consoleMsg = new StringBuilder();
                consoleMsg.append("Unmatch ColumnDataTranslator with: name[")
                        .append(translatorName)
                        .append("], searchCondition[")
                        .append(obj.toString())
                        .append("],tag[")
                        .append(tag)
                        .append("].");
                System.out.println(consoleMsg.toString());
            case EXCEPTION:
                StringBuilder exceMsg = new StringBuilder();
                exceMsg.append("Unmatch ColumnDataTranslator with: name[")
                        .append(translatorName)
                        .append("], searchCondition[")
                        .append(obj.toString())
                        .append("],tag[")
                        .append(tag)
                        .append("].");
                throw new IllegalArgumentException(exceMsg.toString());
            case DEFAULT:
                if (translator == null) {
                    return null;
                } else {
                    if (mode == 1) {
                        return translator.getDefaultValue();
                    } else {
                        return translator.getDefaultKey();
                    }
                }
            default:
                return null;
        }
    }


}
