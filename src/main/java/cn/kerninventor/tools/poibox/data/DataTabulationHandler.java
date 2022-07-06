package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationClassParser;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.reader.TabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.writer.ExcelTabulationWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kern
 * @version 1.0
 */
public final class DataTabulationHandler extends BoxBracket implements DataTabulator {

    private static boolean cacheAble;
    private static Map<Class, TabulationDefinition> tabulationDefinitionCache = new ConcurrentHashMap<>(16);

    public DataTabulationHandler(Poibox poiBox) {
        super(poiBox);
    }

    private <T> TabulationDefinition<T> getTabulationDefinition(Class<T> sourceClass) {
        TabulationDefinition<T> tabulationDefinition;
        if (cacheAble) {
            tabulationDefinition = tabulationDefinitionCache.get(sourceClass);
            if (tabulationDefinition == null) {
                tabulationDefinition = new TabulationClassParser().parse(sourceClass, getParent());
                tabulationDefinitionCache.put(sourceClass, tabulationDefinition);
            }
        } else {
            tabulationDefinition = new TabulationClassParser().parse(sourceClass, getParent());
        }
        return tabulationDefinition;
    }

    @Override
    public <T> TabulationWriter<T> writer(Class<T> sourceClass) {
        TabulationDefinition<T> tabulationDefinition = getTabulationDefinition(sourceClass);
        return new ExcelTabulationWriter<T>(tabulationDefinition);
    }

    @Override
    public <T> TabulationReader<T> reader(Class<T> sourceClass) {
        TabulationDefinition<T> tabulationDefinition = getTabulationDefinition(sourceClass);
        return new ExcelTabulationReader<T>(tabulationDefinition);
    }

    @Override
    public DataTabulator cache(boolean cacheAble) {
        this.cacheAble = cacheAble;
        return this;
    }

}
