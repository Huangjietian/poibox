package cn.kerninventor.tools.poibox.data.tabulation.reader;

/**
 * <h1>中文注释</h1>
 * <p>
 *     Bean的表单校验器，需要指定两个泛型，T泛型指定可供校验的Bean，R泛型指定最终返回的结果。
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface BeanValidator<T, R> {

    void validate(T t);

    R getResult();

}
