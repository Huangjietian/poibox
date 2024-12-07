package io.github.huangjietian.data.tabulation.writer.basic;

import io.github.huangjietian.data.tabulation.definition.ColumnDefinition;
import io.github.huangjietian.data.tabulation.definition.TabulationDefinition;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @version 1.0
 */
public interface BodyWriter<T> {

    void doWirte(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet);

}
