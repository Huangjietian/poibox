package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @version 1.0
 */
public interface BodyWriter<T> {

    void doWirte(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet);

}
