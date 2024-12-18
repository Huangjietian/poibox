package io.github.huangjietian.data.tabulation.writer.basic;

import io.github.huangjietian.data.tabulation.translator.TranslatorManager;

import java.util.List;

/**
 * @author Kern
 * @version 1.0
 */
public class BodyWriterFactory {

    public static <T> BodyWriter<T> getTbodyWriter(TranslatorManager translatorManager, List<T> tList) {
        if (tList != null) {
            return new BodyDataWriter<T>(tList, translatorManager);
        } else {
            return new BodyTemplateWriter<T>();
        }
    }
}
