package cn.kerninventor.tools.poibox.exception;

/**
 * <h1>中文注释</h1>
 * <p>
 *      不支持的数据类型异常
 * </p>
 * @author Kern
 * @version 1.0
 */
public class UnSupportedDataTypeException extends RuntimeException {

    public UnSupportedDataTypeException() {
        super();
    }

    public UnSupportedDataTypeException(String message) {
        super(message);
    }

    public UnSupportedDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportedDataTypeException(Throwable cause) {
        super(cause);
    }

    public UnSupportedDataTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
