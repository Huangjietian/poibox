package cn.kerninventor.tools.poibox.exception;

/**
 * <h1>中文注释</h1>
 * <p>
 *      错误的源class异常
 * </p>
 * @author Kern
 * @version 1.0
 */
public class IllegalSourceClassException extends RuntimeException{

    public IllegalSourceClassException() {
        super();
    }

    public IllegalSourceClassException(String message) {
        super(message);
    }

    public IllegalSourceClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSourceClassException(Throwable cause) {
        super(cause);
    }

}
