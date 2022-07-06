package cn.kerninventor.tools.poibox.data.tabulation.enums;


import cn.kerninventor.tools.poibox.exception.IllegalColumnFieldException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h1>中文注释</h1>
 * <p>
 *     程序默认支持的数据类型枚举。
 * </p>
 * @author Kern
 * @version 1.0
 */
public enum SupportedDataType {

    ByteType(Byte.class),
    ShortType(Short.class),
    IntegerType(Integer.class),
    LongType(Long.class),

    FloatType(Float.class),
    DoubleType(Double.class),
    BigDecimalType(BigDecimal.class),

    StringType(String.class),
    CharacterType(Character.class),

    DateType(java.util.Date.class),
    DateSqlType(java.sql.Date.class),
    TimeType(java.sql.Time.class),

    LocalDateType(LocalDate.class),
    LocalDateTimeType(LocalDateTime.class),

    BooleanType(Boolean.class),
    ;

    private Class clazz;

    SupportedDataType(Class clazz) {
        this.clazz = clazz;
    }

    public static boolean isSupportedType(Field field) {
        if (field.getType().isPrimitive()) {
            throw new IllegalColumnFieldException("Basic data type is unsupported, please used wrapper type! Field： " + field.getName());
        }
        SupportedDataType[] types = SupportedDataType.values();
        for (SupportedDataType type : types) {
            if (type.clazz.isAssignableFrom(field.getType())) {
                return true;
            }
        }
        return false;
    }


}
