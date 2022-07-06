package cn.kerninventor.tools.poibox.exception;

/**
 * <h1>中文注释</h1>
 * <p>
 *     错误的列字段配置异常
 * </p>
 * @author Kern
 * @version 1.0
 */
public class IllegalColumnFieldException extends RuntimeException {

    public IllegalColumnFieldException() {
        super();
    }

    public IllegalColumnFieldException(String message) {
        super(message);
    }
}
