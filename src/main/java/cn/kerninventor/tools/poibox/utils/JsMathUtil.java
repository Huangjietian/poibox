package cn.kerninventor.tools.poibox.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Kern
 * @version 1.0
 */
public final class JsMathUtil {

    private static ScriptEngineManager engineManager;
    private static ScriptEngine jsEngine;

    static {
        engineManager = new ScriptEngineManager();
        jsEngine = engineManager.getEngineByName("js");
    }

    public static  <T extends Number> boolean up2Standard(T standard, JsSimpleComparison comparison, T... ts) {
        if (ts.length == 0 || standard == null) {
            return false;
        }
        StringBuilder equation = new StringBuilder();
        for (T t : ts) {
            equation.append(t)
                    .append(comparison.getExpression())
                    .append(standard)
                    .append(" && ");
        }
        equation.append("true");
        try {
            return (boolean) jsEngine.eval(equation.toString());
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Js parse error" + e.getMessage() , e);
        }
    }

    public static <T extends Number> boolean compare(T t1, JsSimpleComparison comparison, T t2) {
        try {
            return (boolean) jsEngine.eval(t1 + comparison.getExpression() + t2);
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Js parse error" + e.getMessage() , e);
        }
    }

    public enum JsSimpleComparison {

        equal(" == "),
        greaterThan(" > "),
        lessThan(" < "),
        greaterThanOrEqualTo(" >= "),
        lessThanOrEqualTo(" <= ")
        ;

        JsSimpleComparison(String expression) {
            this.expression = expression;
        }

        private String expression;

        public String getExpression() {
            return expression;
        }
    }
}
