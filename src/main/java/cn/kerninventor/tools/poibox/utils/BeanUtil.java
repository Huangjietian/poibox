package cn.kerninventor.tools.poibox.utils;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Kern
 * @version 1.0
 */
public class BeanUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean hasElement(Object[] arr) {
        return arr != null && arr.length > 0;
    }

    public static boolean isAllEmpty(String... strs) {
        return Arrays.stream(strs).allMatch(e -> isEmpty(e));
    }

    public static boolean anyEmpty(String... strs) {
        return Arrays.stream(strs).anyMatch(e -> isEmpty(e));
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static void isTrue(boolean trueStatement, String errorMessage) {
        if (!trueStatement) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
