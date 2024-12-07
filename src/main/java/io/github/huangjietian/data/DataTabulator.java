package io.github.huangjietian.data;

import io.github.huangjietian.data.tabulation.reader.TabulationReader;
import io.github.huangjietian.data.tabulation.writer.TabulationWriter;

/**
 * <h1>中文注释</h1>
 * <p>
 *     数据处理器,提供写入器和读取器
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface DataTabulator {

    /**
     * 根绝配置Bean的Class获得写入器
     * @param sourceClass
     * @param <T>
     * @return
     */
    <T> TabulationWriter<T> writer(Class<T> sourceClass);

    /**
     * 根据配置Bean的Class获取读取器
     * @param sourceClass
     * @param <T>
     * @return
     */
    <T> TabulationReader<T> reader(Class<T> sourceClass);

    /**
     * 是否启用缓存保留解析过的配置Bean
     * @param cacheAble
     * @return
     */
    DataTabulator cache(boolean cacheAble);
}
